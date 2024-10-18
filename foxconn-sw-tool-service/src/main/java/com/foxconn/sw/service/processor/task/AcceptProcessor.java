package com.foxconn.sw.service.processor.task;

import com.foxconn.sw.business.collaboration.CollaborationUserBusiness;
import com.foxconn.sw.business.oa.SwTaskBusiness;
import com.foxconn.sw.business.oa.SwTaskEmployeeRelationBusiness;
import com.foxconn.sw.business.oa.SwTaskProgressBusiness;
import com.foxconn.sw.data.constants.enums.oa.TaskStatusEnums;
import com.foxconn.sw.data.dto.Header;
import com.foxconn.sw.data.dto.entity.acount.UserInfo;
import com.foxconn.sw.data.entity.SwTask;
import com.foxconn.sw.data.entity.SwTaskProgress;
import com.foxconn.sw.data.exception.BizException;
import com.foxconn.sw.service.processor.user.CommonUserUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class AcceptProcessor {

    @Autowired
    SwTaskBusiness taskBusiness;
    @Autowired
    SwTaskProgressBusiness taskProgressBusiness;
    @Autowired
    CommonUserUtils commonUserUtils;
    @Autowired
    SwTaskEmployeeRelationBusiness employeeRelationBusiness;
    @Autowired
    CollaborationUserBusiness collaborationUser;

    public boolean accept(Integer taskID, Header head) {
        UserInfo userInfo = commonUserUtils.queryUserInfo(head.getToken());
        SwTask task = taskBusiness.getTaskById(taskID);

        if (task.getStatus() != TaskStatusEnums.PENDING.getCode()) {
            throw new BizException(1, String.format("當前任務狀態不允許接受(%s)", task.getStatus()));
        }

        boolean result = taskBusiness.updateTaskStatus(taskID, TaskStatusEnums.PROCESSING);
        if (result) {
            SwTaskProgress progress = new SwTaskProgress();
            progress.setTaskId(taskID);
            progress.setOperateEid(userInfo.getEmployeeNo());
            progress.setProgress(0);
            progress.setContent(String.format("%s(%s) 接受了任務", userInfo.getEmployeeName(), userInfo.getEmployeeNo()));
            taskProgressBusiness.addProcessInfo(progress);
            employeeRelationBusiness.acceptTaskEmployee(taskID);

            if ("6-2".equalsIgnoreCase(task.getCategory())) {
                collaborationUser.acceptTask(task);
            }
        }
        return result;
    }


}
