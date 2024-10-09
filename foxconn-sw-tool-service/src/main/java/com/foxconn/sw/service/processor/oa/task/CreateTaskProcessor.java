package com.foxconn.sw.service.processor.oa.task;

import com.foxconn.sw.business.TaskNoSeedSingleton;
import com.foxconn.sw.business.context.RequestContext;
import com.foxconn.sw.business.mapper.TaskMapper;
import com.foxconn.sw.business.oa.SwTaskBusiness;
import com.foxconn.sw.business.oa.SwTaskEmployeeRelationBusiness;
import com.foxconn.sw.business.oa.SwTaskLogBusiness;
import com.foxconn.sw.business.oa.SwTaskProgressBusiness;
import com.foxconn.sw.business.system.EmployeeBusiness;
import com.foxconn.sw.common.utils.ConvertUtils;
import com.foxconn.sw.data.constants.enums.oa.RejectStatusEnum;
import com.foxconn.sw.data.dto.Header;
import com.foxconn.sw.data.dto.entity.oa.TaskBriefDetailVo;
import com.foxconn.sw.data.entity.SwEmployee;
import com.foxconn.sw.data.entity.SwTask;
import com.foxconn.sw.data.entity.SwTaskProgress;
import com.foxconn.sw.service.processor.user.CommonUserUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

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

    public Integer createTask(TaskBriefDetailVo data) {

        SwTask task = TaskMapper.INSTANCE.brief2SwTask(data);
        task.setProposerEid(RequestContext.getEmployeeNo());

        boolean result;
        int taskID = 0;
        boolean isUpdate = false;
        if (Objects.nonNull(task.getId()) && task.getId() > 0) {
            isUpdate = true;
            taskID = task.getId();
            task.setRejectStatus(RejectStatusEnum.UN_REJECT.getCode());
            result = swTaskBusiness.updateTask(task);
        } else {
            task.setTaskNo(taskNoSeedSingleton.getTaskNo());
            result = swTaskBusiness.createTask(task);
            taskID = task.getId();
        }

        if (result) {
            addTaskLog(task, isUpdate);
            addProcessInfo(task, data.getResourceIds(), isUpdate);
            taskEmployeeRelation.addRelationAtCreate(taskID, task.getManagerEid(), data.getWatchers());
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
        if (task.getStatus() == PENDING.getCode()) {
            SwEmployee ee = employeeBusiness.selectEmployeeByENo(task.getManagerEid());
            content = String.format("發起並派送給：%s(%s)", ee.getName(), ee.getEmployeeNo());
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
