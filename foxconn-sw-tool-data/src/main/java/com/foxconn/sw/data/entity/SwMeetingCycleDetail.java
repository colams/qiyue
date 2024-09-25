package com.foxconn.sw.data.entity;

import java.time.LocalDateTime;

public class SwMeetingCycleDetail {
    private Integer id;

    private Integer meetingId;

    private String room;

    private String title;

    private String description;

    private String resourceIds;

    private String operator;

    private String meetingDate;

    private String startTime;

    private String endTime;

    private Integer cancel;

    private LocalDateTime createTime;

    private LocalDateTime datetimeLastchange;

    public Integer getId() {
        return id;
    }

    public SwMeetingCycleDetail withId(Integer id) {
        this.setId(id);
        return this;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getMeetingId() {
        return meetingId;
    }

    public SwMeetingCycleDetail withMeetingId(Integer meetingId) {
        this.setMeetingId(meetingId);
        return this;
    }

    public void setMeetingId(Integer meetingId) {
        this.meetingId = meetingId;
    }

    public String getRoom() {
        return room;
    }

    public SwMeetingCycleDetail withRoom(String room) {
        this.setRoom(room);
        return this;
    }

    public void setRoom(String room) {
        this.room = room == null ? null : room.trim();
    }

    public String getTitle() {
        return title;
    }

    public SwMeetingCycleDetail withTitle(String title) {
        this.setTitle(title);
        return this;
    }

    public void setTitle(String title) {
        this.title = title == null ? null : title.trim();
    }

    public String getDescription() {
        return description;
    }

    public SwMeetingCycleDetail withDescription(String description) {
        this.setDescription(description);
        return this;
    }

    public void setDescription(String description) {
        this.description = description == null ? null : description.trim();
    }

    public String getResourceIds() {
        return resourceIds;
    }

    public SwMeetingCycleDetail withResourceIds(String resourceIds) {
        this.setResourceIds(resourceIds);
        return this;
    }

    public void setResourceIds(String resourceIds) {
        this.resourceIds = resourceIds == null ? null : resourceIds.trim();
    }

    public String getOperator() {
        return operator;
    }

    public SwMeetingCycleDetail withOperator(String operator) {
        this.setOperator(operator);
        return this;
    }

    public void setOperator(String operator) {
        this.operator = operator == null ? null : operator.trim();
    }

    public String getMeetingDate() {
        return meetingDate;
    }

    public SwMeetingCycleDetail withMeetingDate(String meetingDate) {
        this.setMeetingDate(meetingDate);
        return this;
    }

    public void setMeetingDate(String meetingDate) {
        this.meetingDate = meetingDate == null ? null : meetingDate.trim();
    }

    public String getStartTime() {
        return startTime;
    }

    public SwMeetingCycleDetail withStartTime(String startTime) {
        this.setStartTime(startTime);
        return this;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime == null ? null : startTime.trim();
    }

    public String getEndTime() {
        return endTime;
    }

    public SwMeetingCycleDetail withEndTime(String endTime) {
        this.setEndTime(endTime);
        return this;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime == null ? null : endTime.trim();
    }

    public Integer getCancel() {
        return cancel;
    }

    public SwMeetingCycleDetail withCancel(Integer cancel) {
        this.setCancel(cancel);
        return this;
    }

    public void setCancel(Integer cancel) {
        this.cancel = cancel;
    }

    public LocalDateTime getCreateTime() {
        return createTime;
    }

    public SwMeetingCycleDetail withCreateTime(LocalDateTime createTime) {
        this.setCreateTime(createTime);
        return this;
    }

    public void setCreateTime(LocalDateTime createTime) {
        this.createTime = createTime;
    }

    public LocalDateTime getDatetimeLastchange() {
        return datetimeLastchange;
    }

    public SwMeetingCycleDetail withDatetimeLastchange(LocalDateTime datetimeLastchange) {
        this.setDatetimeLastchange(datetimeLastchange);
        return this;
    }

    public void setDatetimeLastchange(LocalDateTime datetimeLastchange) {
        this.datetimeLastchange = datetimeLastchange;
    }
}