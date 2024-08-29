package com.foxconn.sw.service.processor.oa;

import com.foxconn.sw.business.TaskNoSeedSingleton;
import com.foxconn.sw.business.mapper.TaskMapper;
import com.foxconn.sw.business.oa.SwTaskBusiness;
import com.foxconn.sw.business.oa.SwTaskLogBusiness;
import com.foxconn.sw.business.oa.SwTaskProgressBusiness;
import com.foxconn.sw.business.system.EmployeeBusiness;
import com.foxconn.sw.data.constants.enums.oa.RejectStatusEnum;
import com.foxconn.sw.data.dto.Header;
import com.foxconn.sw.data.dto.entity.acount.UserInfo;
import com.foxconn.sw.data.dto.entity.oa.TaskBriefDetailVo;
import com.foxconn.sw.data.entity.SwEmployee;
import com.foxconn.sw.data.entity.SwTask;
import com.foxconn.sw.data.entity.SwTaskProgress;
import com.foxconn.sw.service.processor.user.CommonUserUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

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

    public Integer createTask(TaskBriefDetailVo data, Header head) {

        SwTask task = TaskMapper.INSTANCE.brief2SwTask(data);
        UserInfo user = userUtils.queryUserInfo(head.getToken());
        task.setProposerEid(user.getEmployeeNo());
        task.setTaskNo(taskNoSeedSingleton.getTaskNo());

        boolean result;
        int taskID = 0;
        boolean isUpdate = false;
        if (Objects.nonNull(task.getId()) && task.getId() > 0) {
            isUpdate = true;
            taskID = task.getId();
            task.setRejectStatus(RejectStatusEnum.UN_REJECT.getCode());
            result = swTaskBusiness.updateTask(task);
        } else {
            result = swTaskBusiness.createTask(task);
            taskID = task.getId();
        }

        if (result) {
            addTaskLog(user, task, isUpdate);
            addProcessInfo(user, task, isUpdate);
        }
        return taskID;
    }

    private void addTaskLog(UserInfo user, SwTask task, boolean isUpdate) {
        String operator = String.format("%s(%s)", user.getEmployeeName(), user.getEmployeeNo());
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

    private void addProcessInfo(UserInfo user, SwTask task, boolean isUpdate) {
        String content = "";
        if (task.getStatus() == PENDING.getCode()) {
            SwEmployee ee = employeeBusiness.selectEmployeeByENo(task.getManagerEid());
            content = String.format("發起並派送給：%s(%s)", ee.getName(), ee.getEmployeeNo());
        } else if (task.getStatus() == DRAFT.getCode()) {
            if (isUpdate) {
                content = String.format("修改了草稿，任務編號：%s", task.getTaskNo());
            } else {
                content = String.format("創建了草稿，任務編號：%s", task.getTaskNo());
            }
        }

        SwTaskProgress progress = new SwTaskProgress();
        progress.setTaskId(task.getId());
        progress.setOperateEid(user.getEmployeeNo());
        progress.setResourceIds(task.getResourceIds());
        progress.setProgress(0);
        progress.setContent(content);
        progressBusiness.addProcessInfo(progress);
    }
}
