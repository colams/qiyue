package com.foxconn.sw.service.processor.oa.task;

import com.foxconn.sw.business.oa.SwTaskBusiness;
import com.foxconn.sw.business.oa.SwTaskLogBusiness;
import com.foxconn.sw.business.oa.SwTaskProgressBusiness;
import com.foxconn.sw.data.constants.enums.oa.TaskStatusEnums;
import com.foxconn.sw.data.dto.Header;
import com.foxconn.sw.data.dto.entity.universal.IntegerParams;
import com.foxconn.sw.data.entity.SwTaskProgress;
import com.foxconn.sw.service.processor.user.CommonUserUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class RevokeProcessor {

    @Autowired
    CommonUserUtils commonUserUtils;
    @Autowired
    SwTaskBusiness taskBusiness;
    @Autowired
    SwTaskLogBusiness taskLogBusiness;
    @Autowired
    SwTaskProgressBusiness taskProgressBusiness;

    /**
     * 完成任务
     *
     * @param idParams
     * @param head
     * @return
     */
    public boolean revoke(IntegerParams idParams, Header head) {
        String employeeID = commonUserUtils.getEmployeeNo(head.getToken());
        String employeeName = commonUserUtils.getEmployeeName(head.getToken());
        boolean result = taskBusiness.updateTaskStatus(idParams.getParams(), TaskStatusEnums.REVOKE);
        if (result) {
            addProcessInfo(idParams, employeeID, employeeName);
            taskLogBusiness.addTaskLog(idParams.getParams(), employeeID, "任务关闭");
        }
        return result;
    }

    private void addProcessInfo(IntegerParams idParams, String employeeID, String employeeName) {
        SwTaskProgress progress = new SwTaskProgress();
        progress.setTaskId(idParams.getParams());
        progress.setOperateEid(employeeID);
        progress.setContent(String.format("%s 撤销了任务", employeeName));
        taskProgressBusiness.addProcessInfo(progress);
    }
}
