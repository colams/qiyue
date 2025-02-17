package com.foxconn.sw.data.dto.entity.home;

public class GeneralVo {

    private Integer scheduleTask;
    private Integer unreadTask;
    private Integer collaboration;
    private Integer meeting;

    public Integer getScheduleTask() {
        return scheduleTask;
    }

    public void setScheduleTask(Integer scheduleTask) {
        this.scheduleTask = scheduleTask;
    }

    public Integer getUnreadTask() {
        return unreadTask;
    }

    public void setUnreadTask(Integer unreadTask) {
        this.unreadTask = unreadTask;
    }

    public Integer getCollaboration() {
        return collaboration;
    }

    public void setCollaboration(Integer collaboration) {
        this.collaboration = collaboration;
    }

    public Integer getMeeting() {
        return meeting;
    }

    public void setMeeting(Integer meeting) {
        this.meeting = meeting;
    }
}
