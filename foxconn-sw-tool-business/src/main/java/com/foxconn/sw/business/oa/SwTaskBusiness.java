package com.foxconn.sw.business.oa;

import com.foxconn.sw.common.utils.DateTimeUtils;
import com.foxconn.sw.data.constants.enums.oa.RejectStatusEnum;
import com.foxconn.sw.data.constants.enums.oa.TaskStatusEnums;
import com.foxconn.sw.data.dto.PageParams;
import com.foxconn.sw.data.dto.entity.oa.TaskBriefListVo;
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

    public List<TaskBriefListVo> listBriefVos(PageParams<TaskParams> data, String employeeId) {
        LocalDateTime localDateTime = LocalDateTime.now();
        String now = DateTimeUtils.formatYMD(localDateTime);
        int start = (data.getCurrentPage() - 1) * data.getPageSize();
        return taskExtensionMapper.listBriefVos(start, data.getPageSize(), data.getParams(), employeeId, now);
    }

    public int getTotalCountByParams(TaskParams params, String employeeId) {
        LocalDateTime localDateTime = LocalDateTime.now();
        String now = DateTimeUtils.formatYMD(localDateTime);
        return taskExtensionMapper.getTotalCountByParams(params, employeeId, now);
    }

    public int getTotalCountByParams(int searchType, String employeeId, String now) {
        TaskParams params = new TaskParams();
        params.setKeyWord("");
        params.setSearchType(searchType);
        return taskExtensionMapper.getTotalCountByParams(params, employeeId, now);
    }

    public SwTask getTaskById(Integer taskId) {
        return taskExtensionMapper.selectByPrimaryKey(taskId);
    }

    public boolean updateProgress(Integer taskId, Integer progress) {
        SwTask task = new SwTask();
        task.setId(taskId);
        task.setProgressPercent(progress);
        task.setStatus(TaskStatusEnums.PROCESSING.getCode());
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
