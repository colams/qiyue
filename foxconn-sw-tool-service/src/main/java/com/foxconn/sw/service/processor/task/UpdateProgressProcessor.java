package com.foxconn.sw.service.processor.task;

import com.foxconn.sw.business.context.RequestContext;
import com.foxconn.sw.business.mapper.TaskProgressMapper;
import com.foxconn.sw.business.oa.SwTaskBusiness;
import com.foxconn.sw.business.oa.SwTaskEmployeeRelationBusiness;
import com.foxconn.sw.business.oa.SwTaskLogBusiness;
import com.foxconn.sw.business.oa.SwTaskProgressBusiness;
import com.foxconn.sw.data.dto.entity.oa.TaskProgressBriefParams;
import com.foxconn.sw.data.entity.SwTaskProgress;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

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

        if (Objects.nonNull(data.getProgress())) {
            updateTaskProcess(data, data.getProgress());
        }
        boolean result = addProcessInfo(data);
        if (result) {
            employeeRelationBusiness.acceptTaskEmployee(data.getTaskId());
        }
        return result;
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
        return taskProgressBusiness.addProcessInfo(progress);
    }

    /**
     * 更新任务进度，记录日志信息
     *
     * @param data
     * @param progress
     * @return
     */
    private boolean updateTaskProcess(TaskProgressBriefParams data, Integer progress) {
        String employeeID = RequestContext.getEmployeeNo();
        String nameEmployeeNo = RequestContext.getNameEmployeeNo();
        boolean result = taskBusiness.updateProgress(data.getTaskId(), progress, data.getContent());
        if (result) {
            taskLogBusiness.addTaskLog(data.getTaskId(), employeeID, String.format("%s 更新任务进度", nameEmployeeNo, progress));
        }
        return result;
    }
}
