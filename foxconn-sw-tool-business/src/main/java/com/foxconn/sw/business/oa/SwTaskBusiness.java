package com.foxconn.sw.business.oa;

import com.foxconn.sw.data.constants.enums.oa.TaskStatusEnums;
import com.foxconn.sw.data.dto.PageParams;
import com.foxconn.sw.data.dto.entity.oa.TaskBriefListVo;
import com.foxconn.sw.data.dto.entity.oa.TaskParams;
import com.foxconn.sw.data.entity.SwTask;
import com.foxconn.sw.data.mapper.extension.oa.SwTaskExtensionMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class SwTaskBusiness {

    @Autowired
    SwTaskExtensionMapper taskExtensionMapper;

    public boolean createTask(SwTask task) {
        return taskExtensionMapper.insertSelective(task) > 0;
    }

    public List<TaskBriefListVo> listBriefVos(PageParams<TaskParams> data, String employeeId) {
        int start = (data.getCurrentPage() - 1) * data.getPageSize();
        return taskExtensionMapper.listBriefVos(start, data.getPageSize(), data.getParams(), employeeId);
    }

    public int getTotalCountByParams(TaskParams params, String employeeId) {
        return taskExtensionMapper.getTotalCountByParams(params, employeeId);
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

    public boolean assignTask(Integer taskId, String assignEid) {
        SwTask task = new SwTask();
        task.setId(taskId);
        task.setHandleEid(assignEid);
        return taskExtensionMapper.updateByPrimaryKeySelective(task) > 0;
    }

    public boolean closeTask(Integer id) {
        SwTask task = new SwTask();
        task.setId(id);
        task.setStatus(TaskStatusEnums.CLOSED.getCode());
        return taskExtensionMapper.updateByPrimaryKeySelective(task) > 0;
    }
}
