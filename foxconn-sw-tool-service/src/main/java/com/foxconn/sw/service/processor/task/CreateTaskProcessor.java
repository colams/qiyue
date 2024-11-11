package com.foxconn.sw.service.processor.task;

import com.foxconn.sw.business.SwCapexSetBusiness;
import com.foxconn.sw.business.TaskNoSeedSingleton;
import com.foxconn.sw.business.context.RequestContext;
import com.foxconn.sw.business.mapper.TaskMapper;
import com.foxconn.sw.business.oa.SwTaskBusiness;
import com.foxconn.sw.business.oa.SwTaskEmployeeRelationBusiness;
import com.foxconn.sw.business.oa.SwTaskLogBusiness;
import com.foxconn.sw.business.oa.SwTaskProgressBusiness;
import com.foxconn.sw.business.system.EmployeeBusiness;
import com.foxconn.sw.common.utils.ConvertUtils;
import com.foxconn.sw.common.utils.JsonUtils;
import com.foxconn.sw.data.constants.enums.oa.RejectStatusEnum;
import com.foxconn.sw.data.dto.entity.oa.TaskBriefDetailVo;
import com.foxconn.sw.data.entity.SwEmployee;
import com.foxconn.sw.data.entity.SwTask;
import com.foxconn.sw.data.entity.SwTaskProgress;
import com.foxconn.sw.service.processor.user.CommonUserUtils;
import com.google.common.collect.Lists;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.Objects;

import static com.foxconn.sw.data.constants.enums.oa.TaskStatusEnums.DRAFT;
import static com.foxconn.sw.data.constants.enums.oa.TaskStatusEnums.PENDING;

@Component
public class CreateTaskProcessor {

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
        addProcessInfo(task, data.getResourceIds(), isUpdate);
        taskEmployeeRelation.addRelationAtCreate(taskID, data.getManagers(), data.getWatchers());

        if (!CollectionUtils.isEmpty(data.getCapexParamsVos())) {
            // todo process capex params
            capexSetBusiness.insertSet(taskID, data.getCapexParamsVos());
        }

        return taskID;
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

    private void addProcessInfo(SwTask task, List<Integer> resourceIds, boolean isUpdate) {
        String content = "";

        List<String> managerNos = Lists.newArrayList(task.getManagerEid());
        if (StringUtils.isNotEmpty(task.getManagerEid()) && task.getManagerEid().startsWith("[")) {
            managerNos = JsonUtils.deserialize(task.getManagerEid(), List.class, String.class);
        }

        if (task.getStatus() == PENDING.getCode()) {
            List<SwEmployee> ee = employeeBusiness.selectEmployeeByENos(managerNos);
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
        progressBusiness.addProcessInfo(progress);
    }
}
