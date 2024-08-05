package com.foxconn.sw.service.processor.oa;

import com.foxconn.sw.business.oa.SwTaskBusiness;
import com.foxconn.sw.business.oa.SwTaskLogBusiness;
import com.foxconn.sw.data.constants.enums.oa.TaskStatusEnums;
import com.foxconn.sw.data.dto.Header;
import com.foxconn.sw.data.dto.entity.acount.UserInfo;
import com.foxconn.sw.data.dto.entity.oa.TaskRejectParams;
import com.foxconn.sw.data.entity.SwTask;
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

    /**
     * @param data
     * @param head
     * @return
     */
    public boolean reject(TaskRejectParams data, Header head) {
        UserInfo user = commonUserUtils.queryUserInfo(head.getToken());
        SwTask swTask = taskBusiness.getTaskById(data.getTaskId());
        boolean result = rejectClose(data.getTaskId(), user.getEmployeeNo());
        if (result) {
            copyTask(swTask, user);
        }
        return result;
    }

    private boolean copyTask(SwTask swTask, UserInfo user) {
        swTask.setStatus(TaskStatusEnums.DRAFT.getCode());
        boolean result = taskBusiness.createTask(swTask);
        if (result) {
            String operator = String.format("%s(%s)", user.getEmployeeName(), user.getEmployeeNo());
            String content = String.format("%s 创建了任务", operator);
            taskLogBusiness.addTaskLog(swTask.getId(), operator, content);
        }
        return result;
    }

    private boolean rejectClose(int taskID, String employeeNo) {
        boolean result = taskBusiness.closeTask(taskID);
        if (result) {
            taskLogBusiness.addTaskLog(taskID, employeeNo, "任务 因驳回 关闭");
        }
        return result;
    }
}
