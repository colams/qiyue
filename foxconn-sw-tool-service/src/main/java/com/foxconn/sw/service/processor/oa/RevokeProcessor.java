package com.foxconn.sw.service.processor.oa;

import com.foxconn.sw.business.oa.SwTaskBusiness;
import com.foxconn.sw.business.oa.SwTaskLogBusiness;
import com.foxconn.sw.data.constants.enums.oa.TaskStatusEnums;
import com.foxconn.sw.data.dto.Header;
import com.foxconn.sw.data.dto.entity.universal.IntegerParams;
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

    /**
     * 完成任务
     *
     * @param idParams
     * @param head
     * @return
     */
    public boolean revoke(IntegerParams idParams, Header head) {
        String employeeID = commonUserUtils.getEmployeeNo(head.getToken());
        boolean result = taskBusiness.updateTaskStatus(idParams.getParams(), TaskStatusEnums.CLOSED);
        if (result) {
            taskLogBusiness.addTaskLog(idParams.getParams(), employeeID, "任务关闭");
        }
        return result;
    }
}
