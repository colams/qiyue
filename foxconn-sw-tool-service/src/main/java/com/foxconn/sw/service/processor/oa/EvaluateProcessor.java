package com.foxconn.sw.service.processor.oa;

import com.foxconn.sw.business.oa.SwTaskLogBusiness;
import com.foxconn.sw.data.dto.Header;
import com.foxconn.sw.data.dto.entity.oa.SwTaskEvaluationBusiness;
import com.foxconn.sw.data.dto.entity.oa.TaskEvaluateParams;
import com.foxconn.sw.service.processor.user.CommonUserUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class EvaluateProcessor {

    @Autowired
    CommonUserUtils commonUserUtils;
    @Autowired
    SwTaskLogBusiness taskLogBusiness;
    @Autowired
    SwTaskEvaluationBusiness taskEvaluationBusiness;

    /**
     * 为任务打分
     *
     * @param data
     * @param head
     * @return
     */
    public boolean evaluate(TaskEvaluateParams data, Header head) {
        String employeeNo = commonUserUtils.getEmployeeNo(head.getToken());
        boolean result = saveEvaluate(data, employeeNo);
        if (result) {
            saveEvaluateLog(data, employeeNo);
        }
        return result;
    }

    private boolean saveEvaluate(TaskEvaluateParams data, String employeeID) {
        return taskEvaluationBusiness.saveEvaluate(data, employeeID);
    }

    private boolean saveEvaluateLog(TaskEvaluateParams data, String employeeID) {
        return taskLogBusiness.addTaskLog(data.getTaskId(), employeeID, String.format("%s 评价的该任务", employeeID));
    }
}
