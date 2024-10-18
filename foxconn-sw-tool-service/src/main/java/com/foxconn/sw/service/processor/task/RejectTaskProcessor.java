package com.foxconn.sw.service.processor.task;

import com.foxconn.sw.business.oa.SwTaskBusiness;
import com.foxconn.sw.business.oa.SwTaskLogBusiness;
import com.foxconn.sw.business.oa.SwTaskProgressBusiness;
import com.foxconn.sw.data.constants.enums.oa.RejectStatusEnum;
import com.foxconn.sw.data.constants.enums.oa.TaskStatusEnums;
import com.foxconn.sw.data.dto.Header;
import com.foxconn.sw.data.dto.entity.acount.UserInfo;
import com.foxconn.sw.data.dto.entity.oa.TaskRejectParams;
import com.foxconn.sw.data.entity.SwTask;
import com.foxconn.sw.data.entity.SwTaskProgress;
import com.foxconn.sw.data.exception.BizException;
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

        if (swTask.getStatus() != TaskStatusEnums.ACCEPTING.getCode()
                && swTask.getStatus() != TaskStatusEnums.PENDING.getCode()) {
            throw new BizException(1, String.format("當前狀態不允許回退(%s)", swTask.getStatus()));
        }

        boolean result;
        if (swTask.getStatus() == TaskStatusEnums.ACCEPTING.getCode()) {
            result = rejectAchieve(data, user, 2);
        } else {
            result = rejectClose(data, user, 1);
        }

        return result;
    }

    private boolean rejectAchieve(TaskRejectParams data, UserInfo user, int rejectStatus) {
        boolean result = taskBusiness.updateTaskStatus(data.getTaskId(), TaskStatusEnums.PROCESSING, rejectStatus);
        if (result) {
            taskLogBusiness.addTaskLog(data.getTaskId(), user.getEmployeeName(), "任務 验收驳回:" + data.getContent());
            addProgress(data, user, rejectStatus);
        }
        return result;
    }

    private boolean rejectClose(TaskRejectParams data, UserInfo user, int rejectStatus) {
        boolean result = taskBusiness.updateTaskStatus(data.getTaskId(), TaskStatusEnums.PENDING, rejectStatus);
        if (result) {
            taskLogBusiness.addTaskLog(data.getTaskId(), user.getEmployeeName(), "任務退回: " + data.getContent());
            addProgress(data, user, rejectStatus);
        }
        return result;
    }

    private void addProgress(TaskRejectParams data, UserInfo user, int rejectStatus) {

        String content = String.format("%s 回退了任務:%s",
                String.format("%s(%s)", user.getEmployeeName(), user.getEmployeeNo()),
                data.getContent());
        if (rejectStatus == RejectStatusEnum.ACCEPTING_REJECT.getCode()) {
            content = String.format("%s 验收驳回:%s",
                    String.format("%s(%s)", user.getEmployeeName(), user.getEmployeeNo()),
                    data.getContent());
        }

        SwTaskProgress progress = new SwTaskProgress();
        progress.setTaskId(data.getTaskId());
        progress.setOperateEid(user.getEmployeeNo());
        progress.setContent(content);
        progressBusiness.addProcessInfo(progress);
    }
}
