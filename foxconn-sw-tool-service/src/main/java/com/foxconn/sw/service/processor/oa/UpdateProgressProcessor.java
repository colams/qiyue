package com.foxconn.sw.service.processor.oa;

import com.foxconn.sw.business.mapper.TaskProgressMapper;
import com.foxconn.sw.business.oa.SwTaskBusiness;
import com.foxconn.sw.business.oa.SwTaskLogBusiness;
import com.foxconn.sw.business.oa.SwTaskProgressBusiness;
import com.foxconn.sw.data.dto.Header;
import com.foxconn.sw.data.dto.entity.oa.TaskProgressBriefParams;
import com.foxconn.sw.data.entity.SwTaskProgress;
import com.foxconn.sw.service.processor.user.CommonUserUtils;
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
    private CommonUserUtils commonUserUtils;

    public boolean updateProgress(TaskProgressBriefParams data, Header head) {

        String employeeID = commonUserUtils.getEmployeeNo(head.getToken());

        if (Objects.nonNull(data.getProgress())) {
            updateTaskProcess(data.getTaskId(), data.getProgress(), employeeID);
        }
        return addProcessInfo(data, employeeID);
    }

    /**
     * 保存进度操作信息
     *
     * @param data
     * @param employeeID
     * @return
     */
    private boolean addProcessInfo(TaskProgressBriefParams data, String employeeID) {
        SwTaskProgress progress = TaskProgressMapper.INSTANCE.toTaskProcess(data);
        progress.setOperateEid(employeeID);
        return taskProgressBusiness.addProcessInfo(progress);
    }

    /**
     * 更新任务进度，记录日志信息
     *
     * @param taskId
     * @param progress
     * @param employeeID
     * @return
     */
    private boolean updateTaskProcess(Integer taskId, Integer progress, String employeeID) {
        boolean result = taskBusiness.updateProgress(taskId, progress);
        if (result) {
            taskLogBusiness.addTaskLog(taskId, employeeID, String.format("%s 更新任务进度为：%s%%", employeeID, progress));
        }
        return result;
    }
}
