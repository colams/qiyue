package com.foxconn.sw.data.entity.extension;

public class MeetingEntity {

    private Integer meetingId;
    private String title;
    private String cycle;
    private String cycleStart;
    private String cycleExpire;
    private String startTime;
    private String endTime;
    private String room;
    private String meetingDate;
    private String resources;

    public Integer getMeetingId() {
        return meetingId;
    }

    public void setMeetingId(Integer meetingId) {
        this.meetingId = meetingId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCycle() {
        return cycle;
    }

    public void setCycle(String cycle) {
        this.cycle = cycle;
    }

    public String getCycleStart() {
        return cycleStart;
    }

    public void setCycleStart(String cycleStart) {
        this.cycleStart = cycleStart;
    }

    public String getCycleExpire() {
        return cycleExpire;
    }

    public void setCycleExpire(String cycleExpire) {
        this.cycleExpire = cycleExpire;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public String getRoom() {
        return room;
    }

    public void setRoom(String room) {
        this.room = room;
    }

    public String getMeetingDate() {
        return meetingDate;
    }

    public void setMeetingDate(String meetingDate) {
        this.meetingDate = meetingDate;
    }

    public String getResources() {
        return resources;
    }

    public void setResources(String resources) {
        this.resources = resources;
    }
}
