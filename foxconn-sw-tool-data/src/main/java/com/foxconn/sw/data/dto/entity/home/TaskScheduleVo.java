package com.foxconn.sw.data.dto.entity.home;

import com.foxconn.sw.data.dto.entity.oa.InfoColorVo;

public class TaskScheduleVo {

    private Integer taskId;
    private String taskTitle;
    private InfoColorVo infoColorVo;

    public Integer getTaskId() {
        return taskId;
    }

    public void setTaskId(Integer taskId) {
        this.taskId = taskId;
    }

    public String getTaskTitle() {
        return taskTitle;
    }

    public void setTaskTitle(String taskTitle) {
        this.taskTitle = taskTitle;
    }

    public InfoColorVo getInfoColorVo() {
        return infoColorVo;
    }

    public void setInfoColorVo(InfoColorVo infoColorVo) {
        this.infoColorVo = infoColorVo;
    }
}
