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

    private Integer isRepeat;

    private String cycle;

    private String cycleStart;

    private String cycleExpire;

    private Integer status;

    private String webexUrl;

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

    public Integer getIsRepeat() {
        return isRepeat;
    }

    public SwMeeting withIsRepeat(Integer isRepeat) {
        this.setIsRepeat(isRepeat);
        return this;
    }

    public void setIsRepeat(Integer isRepeat) {
        this.isRepeat = isRepeat;
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

    public String getCycleStart() {
        return cycleStart;
    }

    public SwMeeting withCycleStart(String cycleStart) {
        this.setCycleStart(cycleStart);
        return this;
    }

    public void setCycleStart(String cycleStart) {
        this.cycleStart = cycleStart == null ? null : cycleStart.trim();
    }

    public String getCycleExpire() {
        return cycleExpire;
    }

    public SwMeeting withCycleExpire(String cycleExpire) {
        this.setCycleExpire(cycleExpire);
        return this;
    }

    public void setCycleExpire(String cycleExpire) {
        this.cycleExpire = cycleExpire == null ? null : cycleExpire.trim();
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

    public String getWebexUrl() {
        return webexUrl;
    }

    public SwMeeting withWebexUrl(String webexUrl) {
        this.setWebexUrl(webexUrl);
        return this;
    }

    public void setWebexUrl(String webexUrl) {
        this.webexUrl = webexUrl == null ? null : webexUrl.trim();
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