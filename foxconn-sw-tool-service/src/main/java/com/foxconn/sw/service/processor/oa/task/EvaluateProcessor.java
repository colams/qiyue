package com.foxconn.sw.service.processor.oa.task;

import com.foxconn.sw.business.oa.SwTaskBusiness;
import com.foxconn.sw.business.oa.SwTaskLogBusiness;
import com.foxconn.sw.business.oa.SwTaskProgressBusiness;
import com.foxconn.sw.data.constants.enums.oa.TaskStatusEnums;
import com.foxconn.sw.data.dto.Header;
import com.foxconn.sw.data.dto.entity.oa.SwTaskEvaluationBusiness;
import com.foxconn.sw.data.dto.entity.oa.TaskEvaluateParams;
import com.foxconn.sw.data.entity.SwTask;
import com.foxconn.sw.data.entity.SwTaskProgress;
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
    @Autowired
    SwTaskBusiness swTaskBusiness;
    @Autowired
    SwTaskProgressBusiness taskProgressBusiness;

    /**
     * 为任务打分
     *
     * @param data
     * @param head
     * @return
     */
    public boolean evaluate(TaskEvaluateParams data, Header head) {
        String employeeNo = commonUserUtils.getEmployeeNo(head.getToken());
        String employeeName = commonUserUtils.getEmployeeName(head.getToken());
        completeTask(data.getTaskId());
        boolean result = saveEvaluate(data, employeeNo);
        if (result) {
            saveProgress(data, employeeNo, employeeName);
            saveEvaluateLog(data, employeeName);
        }
        return result;
    }

    private boolean saveEvaluate(TaskEvaluateParams data, String employeeID) {
        return taskEvaluationBusiness.saveEvaluate(data, employeeID);
    }

    private boolean completeTask(Integer taskId) {
        SwTask task = new SwTask();
        task.setId(taskId);
        task.setStatus(TaskStatusEnums.COMPLETED.getCode());
        return swTaskBusiness.updateTask(task);
    }

    private boolean saveEvaluateLog(TaskEvaluateParams data, String employeeName) {
        return taskLogBusiness.addTaskLog(data.getTaskId(), employeeName, String.format("%s 评价的该任务", employeeName));
    }

    private boolean saveProgress(TaskEvaluateParams data, String employeeNo, String employeeName) {
        SwTaskProgress progress = new SwTaskProgress();
        progress.setTaskId(data.getTaskId());
        progress.setOperateEid(employeeNo);
        progress.setContent(String.format("%s 验收的该任务", employeeName));
        return taskProgressBusiness.addProcessInfo(progress);
    }
}
