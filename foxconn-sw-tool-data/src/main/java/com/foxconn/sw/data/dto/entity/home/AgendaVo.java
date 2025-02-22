package com.foxconn.sw.data.dto.entity.home;

public class AgendaVo {
    private String currentDate;

    private Integer taskTotalCount;
    private Integer meetingCount;

    private Integer taskOverdue;
    private Integer taskFinish;
    private Integer taskFinishOverdue;

    public String getCurrentDate() {
        return currentDate;
    }

    public void setCurrentDate(String currentDate) {
        this.currentDate = currentDate;
    }

    public Integer getTaskTotalCount() {
        return taskTotalCount;
    }

    public void setTaskTotalCount(Integer taskTotalCount) {
        this.taskTotalCount = taskTotalCount;
    }

    public Integer getTaskOverdue() {
        return taskOverdue;
    }

    public void setTaskOverdue(Integer taskOverdue) {
        this.taskOverdue = taskOverdue;
    }

    public Integer getTaskFinish() {
        return taskFinish;
    }

    public void setTaskFinish(Integer taskFinish) {
        this.taskFinish = taskFinish;
    }

    public Integer getTaskFinishOverdue() {
        return taskFinishOverdue;
    }

    public void setTaskFinishOverdue(Integer taskFinishOverdue) {
        this.taskFinishOverdue = taskFinishOverdue;
    }

    public Integer getMeetingCount() {
        return meetingCount;
    }

    public void setMeetingCount(Integer meetingCount) {
        this.meetingCount = meetingCount;
    }
}
