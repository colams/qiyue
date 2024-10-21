package com.foxconn.sw.business.oa;

import com.foxconn.sw.common.utils.DateTimeUtils;
import com.foxconn.sw.data.constants.enums.oa.RejectStatusEnum;
import com.foxconn.sw.data.constants.enums.oa.TaskStatusEnums;
import com.foxconn.sw.data.dto.PageParams;
import com.foxconn.sw.data.dto.entity.oa.BriefTaskVo;
import com.foxconn.sw.data.dto.entity.oa.TaskParams;
import com.foxconn.sw.data.entity.SwTask;
import com.foxconn.sw.data.mapper.extension.oa.SwTaskExtensionMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;

@Component
public class SwTaskBusiness {

    @Autowired
    SwTaskExtensionMapper taskExtensionMapper;

    public boolean createTask(SwTask task) {
        return taskExtensionMapper.insertSelective(task) > 0;
    }


    public boolean updateTask(SwTask task) {
        return taskExtensionMapper.updateByPrimaryKeySelective(task) > 0;
    }

    public List<SwTask> listBriefVos(PageParams<TaskParams> data, List<String> employeeNos, String proposer) {
        LocalDateTime localDateTime = LocalDateTime.now();
        String now = DateTimeUtils.formatYMD(localDateTime);
        int start = (data.getCurrentPage() - 1) * data.getPageSize();
        return taskExtensionMapper.listBriefVos(start, data.getPageSize(), data.getParams(), employeeNos, now, proposer);
    }

    public int getTotalCountByParams(TaskParams params, List<String> employeeNos, String proposer) {
        LocalDateTime localDateTime = LocalDateTime.now();
        String now = DateTimeUtils.formatYMD(localDateTime);
        return taskExtensionMapper.getTotalCountByParams(params, employeeNos, now, proposer);
    }

    public int getTotalCountByParams(int searchType, List<String> employees, String now, String proposer) {
        TaskParams params = new TaskParams();
        params.setKeyWord("");
        params.setSearchType(searchType);
        return taskExtensionMapper.getTotalCountByParams(params, employees, now,proposer);
    }

    public BriefTaskVo getTaskById(Integer taskId) {
        return taskExtensionMapper.selectByTaskId(taskId);
    }

    public boolean updateProgress(Integer taskId, Integer progress, String content) {
        SwTask task = new SwTask();
        task.setId(taskId);
        task.setProgressPercent(progress);
        task.setStatus(TaskStatusEnums.PROCESSING.getCode());
        if (progress == 100) {
            task.setStatus(TaskStatusEnums.ACCEPTING.getCode());
            task.setRejectStatus(RejectStatusEnum.UN_REJECT.getCode());
            task.setReflection(content);
        }
        return taskExtensionMapper.updateByPrimaryKeySelective(task) > 0;
    }

    public boolean achieveTask(Integer taskId, Integer progress, String content) {
        SwTask task = new SwTask();
        task.setId(taskId);
        task.setProgressPercent(progress);
        task.setStatus(TaskStatusEnums.ACCEPTING.getCode());
        task.setRejectStatus(RejectStatusEnum.UN_REJECT.getCode());
        task.setReflection(content);
        return taskExtensionMapper.updateByPrimaryKeySelective(task) > 0;
    }

    public boolean assignTask(Integer taskId, String assignEid) {
        SwTask task = new SwTask();
        task.setId(taskId);
        task.setHandleEid(assignEid);
        task.setRejectStatus(RejectStatusEnum.UN_REJECT.getCode());
        return taskExtensionMapper.updateByPrimaryKeySelective(task) > 0;
    }

    public boolean updateTaskStatus(Integer id, TaskStatusEnums taskStatus) {
        SwTask task = new SwTask();
        task.setId(id);
        task.setStatus(taskStatus.getCode());
        return taskExtensionMapper.updateByPrimaryKeySelective(task) > 0;
    }

    public boolean updateTaskStatus(Integer id, TaskStatusEnums taskStatus, Integer rejectStatus) {
        SwTask task = new SwTask();
        task.setId(id);
        task.setStatus(taskStatus.getCode());
        task.setRejectStatus(rejectStatus);
        return taskExtensionMapper.updateByPrimaryKeySelective(task) > 0;
    }
}
