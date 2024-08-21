package com.foxconn.sw.service.processor.oa;

import com.foxconn.sw.business.oa.SwTaskBusiness;
import com.foxconn.sw.business.oa.SwTaskLogBusiness;
import com.foxconn.sw.business.oa.SwTaskProgressBusiness;
import com.foxconn.sw.data.constants.enums.oa.TaskStatusEnums;
import com.foxconn.sw.data.dto.Header;
import com.foxconn.sw.data.dto.entity.acount.UserInfo;
import com.foxconn.sw.data.dto.entity.oa.TaskRejectParams;
import com.foxconn.sw.data.entity.SwTask;
import com.foxconn.sw.data.entity.SwTaskProgress;
import com.foxconn.sw.service.processor.user.CommonUserUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class RejectTaskProcessor {

    @Autowired
    CommonUserUtils commonUserUtils;
    @Autowired
    SwTaskBusiness taskBusiness;
    @Autowired
    SwTaskLogBusiness taskLogBusiness;
    @Autowired
    SwTaskProgressBusiness progressBusiness;

    /**
     * @param data
     * @param head
     * @return
     */
    public boolean reject(TaskRejectParams data, Header head) {
        UserInfo user = commonUserUtils.queryUserInfo(head.getToken());
        SwTask swTask = taskBusiness.getTaskById(data.getTaskId());

        boolean result;
        if (swTask.getProposerEid().equalsIgnoreCase(user.getEmployeeNo())) {
            result = rejectAchieve(data.getTaskId(), user);
        } else {
            result = rejectClose(data.getTaskId(), user);
        }

        return result;
    }

    private boolean rejectAchieve(Integer taskId, UserInfo user) {
        boolean result = taskBusiness.updateTaskStatus(taskId, TaskStatusEnums.PROCESSING);
        if (result) {
            taskLogBusiness.addTaskLog(taskId, user.getEmployeeName(), "任务 验收驳回");
            addProgress(taskId, user);
        }
        return result;
    }

    private boolean rejectClose(int taskID, UserInfo user) {
        boolean result = taskBusiness.updateTaskStatus(taskID, TaskStatusEnums.CLOSED);
        if (result) {
            taskLogBusiness.addTaskLog(taskID, user.getEmployeeName(), "任务 因驳回 关闭");
            addProgress(taskID, user);
        }
        return result;
    }

    private void addProgress(int taskID, UserInfo user) {
        String content = String.format("%s 駁回了任務", String.format("%s(%s)", user.getEmployeeName(), user.getEmployeeNo()));
        SwTaskProgress progress = new SwTaskProgress();
        progress.setTaskId(taskID);
        progress.setOperateEid(user.getEmployeeNo());
        progress.setContent(content);
        progressBusiness.addProcessInfo(progress);
    }
}
