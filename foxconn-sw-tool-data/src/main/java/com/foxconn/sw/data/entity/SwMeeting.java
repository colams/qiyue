package com.foxconn.sw.data.entity;

import java.time.LocalDateTime;

public class SwMeeting {
    private Integer id;

    private String room;

    private Integer abcMeeting;

    private String title;

    private String description;

    private String meetingDate;

    private String startTime;

    private String endTime;

    private String resourceIds;

    private Integer repeat;

    private String cycle;

    private Integer status;

    private String creator;

    private LocalDateTime createTime;

    private LocalDateTime datetimeLastchange;

    public Integer getId() {
        return id;
    }

    public SwMeeting withId(Integer id) {
        this.setId(id);
        return this;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getRoom() {
        return room;
    }

    public SwMeeting withRoom(String room) {
        this.setRoom(room);
        return this;
    }

    public void setRoom(String room) {
        this.room = room == null ? null : room.trim();
    }

    public Integer getAbcMeeting() {
        return abcMeeting;
    }

    public SwMeeting withAbcMeeting(Integer abcMeeting) {
        this.setAbcMeeting(abcMeeting);
        return this;
    }

    public void setAbcMeeting(Integer abcMeeting) {
        this.abcMeeting = abcMeeting;
    }

    public String getTitle() {
        return title;
    }

    public SwMeeting withTitle(String title) {
        this.setTitle(title);
        return this;
    }

    public void setTitle(String title) {
        this.title = title == null ? null : title.trim();
    }

    public String getDescription() {
        return description;
    }

    public SwMeeting withDescription(String description) {
        this.setDescription(description);
        return this;
    }

    public void setDescription(String description) {
        this.description = description == null ? null : description.trim();
    }

    public String getMeetingDate() {
        return meetingDate;
    }

    public SwMeeting withMeetingDate(String meetingDate) {
        this.setMeetingDate(meetingDate);
        return this;
    }

    public void setMeetingDate(String meetingDate) {
        this.meetingDate = meetingDate == null ? null : meetingDate.trim();
    }

    public String getStartTime() {
        return startTime;
    }

    public SwMeeting withStartTime(String startTime) {
        this.setStartTime(startTime);
        return this;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime == null ? null : startTime.trim();
    }

    public String getEndTime() {
        return endTime;
    }

    public SwMeeting withEndTime(String endTime) {
        this.setEndTime(endTime);
        return this;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime == null ? null : endTime.trim();
    }

    public String getResourceIds() {
        return resourceIds;
    }

    public SwMeeting withResourceIds(String resourceIds) {
        this.setResourceIds(resourceIds);
        return this;
    }

    public void setResourceIds(String resourceIds) {
        this.resourceIds = resourceIds == null ? null : resourceIds.trim();
    }

    public Integer getRepeat() {
        return repeat;
    }

    public SwMeeting withRepeat(Integer repeat) {
        this.setRepeat(repeat);
        return this;
    }

    public void setRepeat(Integer repeat) {
        this.repeat = repeat;
    }

    public String getCycle() {
        return cycle;
    }

    public SwMeeting withCycle(String cycle) {
        this.setCycle(cycle);
        return this;
    }

    public void setCycle(String cycle) {
        this.cycle = cycle == null ? null : cycle.trim();
    }

    public Integer getStatus() {
        return status;
    }

    public SwMeeting withStatus(Integer status) {
        this.setStatus(status);
        return this;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getCreator() {
        return creator;
    }

    public SwMeeting withCreator(String creator) {
        this.setCreator(creator);
        return this;
    }

    public void setCreator(String creator) {
        this.creator = creator == null ? null : creator.trim();
    }

    public LocalDateTime getCreateTime() {
        return createTime;
    }

    public SwMeeting withCreateTime(LocalDateTime createTime) {
        this.setCreateTime(createTime);
        return this;
    }

    public void setCreateTime(LocalDateTime createTime) {
        this.createTime = createTime;
    }

    public LocalDateTime getDatetimeLastchange() {
        return datetimeLastchange;
    }

    public SwMeeting withDatetimeLastchange(LocalDateTime datetimeLastchange) {
        this.setDatetimeLastchange(datetimeLastchange);
        return this;
    }

    public void setDatetimeLastchange(LocalDateTime datetimeLastchange) {
        this.datetimeLastchange = datetimeLastchange;
    }
}