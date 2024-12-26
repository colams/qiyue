package com.foxconn.sw.service.processor.task;

import com.foxconn.sw.common.context.RequestContext;
import com.foxconn.sw.business.mapper.TaskProgressMapper;
import com.foxconn.sw.business.oa.SwTaskBusiness;
import com.foxconn.sw.business.oa.SwTaskEmployeeRelationBusiness;
import com.foxconn.sw.business.oa.SwTaskLogBusiness;
import com.foxconn.sw.business.oa.SwTaskProgressBusiness;
import com.foxconn.sw.data.constants.enums.oa.TaskStatusEnums;
import com.foxconn.sw.data.dto.entity.task.TaskProgressBriefParams;
import com.foxconn.sw.data.entity.SwTaskEmployeeRelation;
import com.foxconn.sw.data.entity.SwTaskProgress;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Component
public class UpdateProgressProcessor {


    @Autowired
    private SwTaskBusiness taskBusiness;
    @Autowired
    private SwTaskLogBusiness taskLogBusiness;
    @Autowired
    private SwTaskProgressBusiness taskProgressBusiness;
    @Autowired
    SwTaskEmployeeRelationBusiness employeeRelationBusiness;


    public boolean updateProgress(TaskProgressBriefParams data) {

        boolean result = addProcessInfo(data);
        if (result) {
            employeeRelationBusiness.acceptTaskEmployee(data.getTaskId());
        }

        if (Objects.nonNull(data.getProgress())) {
            TaskStatusEnums taskStatusEnums = TaskStatusEnums.PROCESSING;
            if (Objects.nonNull(data.getProgress()) && data.getProgress() == 100) {
                taskStatusEnums = TaskStatusEnums.ACCEPTING;
            }
            updateTaskProcess(data, taskStatusEnums);

            if (taskStatusEnums.equals(TaskStatusEnums.ACCEPTING)) {
                processEmployeeState(data);
            }
        }
        return result;
    }

    private void processEmployeeState(TaskProgressBriefParams data) {
        SwTaskEmployeeRelation current = employeeRelationBusiness.
                queryRelationByTaskAndEno(data.getTaskId(), RequestContext.getEmployeeNo());
        SwTaskEmployeeRelation previous = employeeRelationBusiness.selectByPrimaryKey(current.getPrevId());

        List<SwTaskEmployeeRelation> relations = new ArrayList<>();
        if (Objects.nonNull(current)) {
            SwTaskEmployeeRelation relation = new SwTaskEmployeeRelation();
            relation.setId(current.getId());
            relation.setIsActive(0);
            relations.add(relation);
        }
        if (Objects.nonNull(previous)) {
            SwTaskEmployeeRelation relation = new SwTaskEmployeeRelation();
            relation.setId(previous.getId());
            relation.setIsActive(1);
            relation.setIsInspector(1);
            relations.add(relation);
        }
        employeeRelationBusiness.insertOrUpdate(relations);
    }

    /**
     * 保存进度操作信息
     *
     * @param data
     * @return
     */
    private boolean addProcessInfo(TaskProgressBriefParams data) {
        String employeeID = RequestContext.getEmployeeNo();
        SwTaskProgress progress = TaskProgressMapper.INSTANCE.toTaskProcess(data);
        progress.setOperateEid(employeeID);
        return taskProgressBusiness.addProcessInfo(progress)>0;
    }

    /**
     * 更新任务进度，记录日志信息
     *
     * @param data
     * @param taskStatusEnums
     * @return
     */
    private boolean updateTaskProcess(TaskProgressBriefParams data, TaskStatusEnums taskStatusEnums) {
        String employeeID = RequestContext.getEmployeeNo();
        String nameEmployeeNo = RequestContext.getNameEmployeeNo();
        boolean result = taskBusiness.updateProgress(data, taskStatusEnums);
        if (result) {
            String log = String.format("%s 更新任务进度", nameEmployeeNo);
            taskLogBusiness.addTaskLog(data.getTaskId(), employeeID, log);
        }
        return result;
    }
}
