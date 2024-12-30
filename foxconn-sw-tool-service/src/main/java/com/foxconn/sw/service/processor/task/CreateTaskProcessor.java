package com.foxconn.sw.service.processor.task;

import com.foxconn.sw.business.SwCapexSetBusiness;
import com.foxconn.sw.business.TaskNoSeedSingleton;
import com.foxconn.sw.business.collaboration.CollaborationDetailBusiness;
import com.foxconn.sw.business.collaboration.CollaborationUserBusiness;
import com.foxconn.sw.business.mapper.TaskMapper;
import com.foxconn.sw.business.oa.*;
import com.foxconn.sw.business.system.EmployeeBusiness;
import com.foxconn.sw.common.context.RequestContext;
import com.foxconn.sw.common.utils.ConvertUtils;
import com.foxconn.sw.data.constants.enums.TaskOperateType;
import com.foxconn.sw.data.constants.enums.TaskRoleFlagEnums;
import com.foxconn.sw.data.constants.enums.oa.RejectStatusEnum;
import com.foxconn.sw.data.dto.entity.task.TaskBriefDetailVo;
import com.foxconn.sw.data.dto.request.task.SubTaskParams;
import com.foxconn.sw.data.entity.*;
import com.foxconn.sw.service.processor.user.CommonUserUtils;
import com.google.common.collect.Lists;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.io.FileNotFoundException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

import static com.foxconn.sw.data.constants.enums.oa.TaskStatusEnums.DRAFT;
import static com.foxconn.sw.data.constants.enums.oa.TaskStatusEnums.PENDING;

@Component
public class CreateTaskProcessor {

    private static final Logger logger = LoggerFactory.getLogger(CreateTaskProcessor.class);

    @Autowired
    CommonUserUtils userUtils;
    @Autowired
    SwTaskBusiness swTaskBusiness;
    @Autowired
    EmployeeBusiness employeeBusiness;
    @Autowired
    SwTaskLogBusiness taskLogBusiness;
    @Autowired
    SwTaskProgressBusiness progressBusiness;
    @Autowired
    TaskNoSeedSingleton taskNoSeedSingleton;
    @Autowired
    SwTaskEmployeeRelationBusiness taskEmployeeRelation;
    @Autowired
    SwCapexSetBusiness capexSetBusiness;
    @Autowired
    CollaborationUserBusiness collaborationUser;
    @Autowired
    SwTaskEmployeeRelationBusiness taskEmployeeRelationBusiness;
    @Autowired
    CollaborationDetailBusiness collaborationDetailBusiness;
    @Autowired
    TaskEmployeeRelationProcessor employeeRelationProcessor;
    @Autowired
    SwTaskContentHistoryBusiness taskContentHistoryBusiness;


    public Integer createTask(TaskBriefDetailVo data) {

        SwTask task = TaskMapper.INSTANCE.brief2SwTask(data);
        task.setProposerEid(RequestContext.getEmployeeNo());
        task.setRejectStatus(RejectStatusEnum.DEFAULT.getCode());

        boolean isUpdate = true;
        if (Objects.isNull(task.getId()) || task.getId() == 0) {
            isUpdate = false;
            task.setTaskNo(taskNoSeedSingleton.getTaskNo());
        }

        int taskID = swTaskBusiness.insertOrUpdate(task);
        addTaskLog(task, isUpdate);
        Integer progressId = addProcessInfo(task, data.getManagers(), data.getResourceIds(), isUpdate);
        taskEmployeeRelation.addRelationAtCreate(taskID, data.getManagers(), data.getWatchers());

        if ("6-2".equalsIgnoreCase(data.getCategory()) || "capex".equalsIgnoreCase(data.getCategory())) {

            boolean hasSet = !CollectionUtils.isEmpty(data.getCapexParamsVos())
                    && data.getCapexParamsVos().stream().anyMatch(e -> Objects.nonNull(e.getCapexSet()));
            if (hasSet) {
                capexSetBusiness.insertSet(taskID, data.getCapexParamsVos());
            }

            if (!data.getStatus().equals(DRAFT.getCode())) {
                processHandle(taskID, hasSet, data.getResourceIds().get(0));
            }
        }

        if (taskID > 0) {
            taskContentHistoryBusiness.insertHistory(progressId, taskID, "", task.getDescription());
        }

        return taskID;
    }


    private void processHandle(Integer taskID, boolean hasSet, Integer resourceId) {
        if (!hasSet) {
            List<SwCollaborationUser> collaborationUsers = collaborationUser.queryCollaborationUser(taskID);
            List<SwTaskEmployeeRelation> relations = taskEmployeeRelationBusiness.getRelationsByTaskIdAndRole(taskID,
                    TaskRoleFlagEnums.Manager_Flag);

            for (SwTaskEmployeeRelation relation : relations) {
                boolean has = collaborationUsers.stream()
                        .anyMatch(e -> e.getEmployeeNo().equalsIgnoreCase(relation.getEmployeeNo()));
                if (!has) {
                    collaborationUser.acceptTask(taskID, relation.getEmployeeNo());
                }
            }
        } else {
            Long cuId = collaborationUser.acceptTask(taskID, RequestContext.getEmployeeNo());
            try {
                collaborationDetailBusiness.readExcelContent(cuId, resourceId);
            } catch (FileNotFoundException e) {
                logger.error("processHandle", e);
            }
        }
    }

    private void addTaskLog(SwTask task, boolean isUpdate) {
        String operator = RequestContext.getNameEmployeeNo();
        String content = String.format("由 %s 创建了任务", operator);

        if (task.getStatus() == DRAFT.getCode()) {
            if (isUpdate) {
                content = String.format(" %s 修改了草稿任务", operator);
            } else {
                content = String.format("由 %s 创建了草稿任务", operator);
            }

        }
        taskLogBusiness.addTaskLog(task.getId(), operator, content);
    }

    private Integer addProcessInfo(SwTask task, List<String> managers, List<Integer> resourceIds, boolean isUpdate) {
        String content = "";

        if (task.getStatus() == PENDING.getCode()) {
            List<SwEmployee> ee = employeeBusiness.selectEmployeeByENos(managers);
            String name = "";
            for (SwEmployee employee : ee) {
                name += String.format("：%s(%s)", employee.getName(), employee.getEmployeeNo());
            }
            content = String.format("發起並派送給%s", name);
        } else if (task.getStatus() == DRAFT.getCode()) {
            if (isUpdate) {
                content = "修改了草稿";
            } else {
                content = String.format("創建了草稿，任務編號：%s", task.getTaskNo());
            }
        }

        SwTaskProgress progress = new SwTaskProgress();
        progress.setTaskId(task.getId());
        progress.setOperateEid(RequestContext.getEmployeeNo());
        progress.setResourceIds(ConvertUtils.listIntegerToString(resourceIds));
        progress.setProgress(0);
        progress.setContent(content);
        progress.setOperateType(TaskOperateType.RELEASE.getOperateType());
        return progressBusiness.addProcessInfo(progress);
    }

    public Integer createSubTask(SubTaskParams data) {
        SwTask task = new SwTask();
        task.setTaskNo(taskNoSeedSingleton.getTaskNo());
        task.setTitle(data.getTitle());
        task.setId(data.getTaskID());
        task.setProposerEid(RequestContext.getEmployeeNo());
        task.setDeadLine(data.getDeadline());
        task.setParentId(data.getParentTaskID());
        task.setCreateTime(LocalDateTime.now());
        task.setStatus(data.getStatus());
        task.setProgressPercent(data.getProgressPercent());
        task.setDescription(data.getDescription());
        task.setLevel(data.getLevel());
        int subTaskID = swTaskBusiness.insertOrUpdate(task);
        if (subTaskID > 0) {
            taskEmployeeRelation.addRelationAtCreate(subTaskID, Lists.newArrayList(data.getHandler()), null);
        }
        return subTaskID;
    }
}
