package com.foxconn.sw.service.processor.task;

import com.foxconn.sw.common.context.RequestContext;
import com.foxconn.sw.business.oa.SwTaskBusiness;
import com.foxconn.sw.business.oa.SwTaskEmployeeRelationBusiness;
import com.foxconn.sw.business.oa.SwTaskLogBusiness;
import com.foxconn.sw.business.oa.SwTaskProgressBusiness;
import com.foxconn.sw.data.constants.enums.oa.TaskStatusEnums;
import com.foxconn.sw.data.dto.Header;
import com.foxconn.sw.data.dto.entity.task.SwTaskEvaluationBusiness;
import com.foxconn.sw.data.dto.entity.task.TaskEvaluateParams;
import com.foxconn.sw.data.entity.SwTask;
import com.foxconn.sw.data.entity.SwTaskEmployeeRelation;
import com.foxconn.sw.data.entity.SwTaskProgress;
import com.foxconn.sw.service.processor.user.CommonUserUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static com.foxconn.sw.data.constants.enums.TaskRoleFlagEnums.Proposer_Flag;

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
    @Autowired
    SwTaskEmployeeRelationBusiness employeeRelationBusiness;

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
        SwTaskEmployeeRelation current = employeeRelationBusiness.queryRelationByTaskAndEno(data.getTaskId(), RequestContext.getEmployeeNo());

        if (Proposer_Flag.test(current.getRoleFlag())) {
            completeTask(data.getTaskId());
        }
        boolean result = saveEvaluate(data, employeeNo);
        if (result) {
            saveProgress(data, employeeNo, employeeName);
            saveEvaluateLog(data, employeeName);
        }

        processEmployeeRelation(data.getTaskId());
        return result;
    }

    private void processEmployeeRelation(Integer taskID) {
        List<SwTaskEmployeeRelation> relationList = new ArrayList<>();

        SwTaskEmployeeRelation current = employeeRelationBusiness.queryRelationByTaskAndEno(taskID, RequestContext.getEmployeeNo());
        boolean hasPrev = Objects.nonNull(current) && Objects.nonNull(current.getPrevId()) && current.getPrevId() > 0;
        if (Objects.nonNull(current)) {
            SwTaskEmployeeRelation relation = new SwTaskEmployeeRelation();
            relation.setId(current.getId());
            relation.setIsInspector(0);
            relation.setIsActive(hasPrev ? 0 : 1);
            relationList.add(relation);
        }

        if (hasPrev) {
            SwTaskEmployeeRelation previous = employeeRelationBusiness.selectByPrimaryKey(current.getPrevId());
            SwTaskEmployeeRelation relation = new SwTaskEmployeeRelation();
            relation.setId(previous.getId());
            relation.setIsInspector(1);
            relation.setIsActive(1);
            relationList.add(relation);
        }
        employeeRelationBusiness.insertOrUpdate(relationList);
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
        return taskLogBusiness.addTaskLog(data.getTaskId(), employeeName, String.format("%s 评价的该任務", employeeName));
    }

    private boolean saveProgress(TaskEvaluateParams data, String employeeNo, String employeeName) {
        SwTaskProgress progress = new SwTaskProgress();
        progress.setTaskId(data.getTaskId());
        progress.setOperateEid(employeeNo);
        progress.setContent(String.format("%s 验收了该任務", employeeName));
        return taskProgressBusiness.addProcessInfo(progress);
    }
}
