package com.foxconn.sw.data.entity;

import java.time.LocalDateTime;

public class SwMeetingMinute {
    private Long id;

    private Integer meetingId;

    private String room;

    private String title;

    private String meetingDate;

    private String startTime;

    private String endTime;

    private String webexUrl;

    private String resourceIds;

    private String status;

    private LocalDateTime createTime;

    private LocalDateTime datetimeLastchange;

    public Long getId() {
        return id;
    }

    public SwMeetingMinute withId(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getMeetingId() {
        return meetingId;
    }

    public SwMeetingMinute withMeetingId(Integer meetingId) {
        this.setMeetingId(meetingId);
        return this;
    }

    public void setMeetingId(Integer meetingId) {
        this.meetingId = meetingId;
    }

    public String getRoom() {
        return room;
    }

    public SwMeetingMinute withRoom(String room) {
        this.setRoom(room);
        return this;
    }

    public void setRoom(String room) {
        this.room = room == null ? null : room.trim();
    }

    public String getTitle() {
        return title;
    }

    public SwMeetingMinute withTitle(String title) {
        this.setTitle(title);
        return this;
    }

    public void setTitle(String title) {
        this.title = title == null ? null : title.trim();
    }

    public String getMeetingDate() {
        return meetingDate;
    }

    public SwMeetingMinute withMeetingDate(String meetingDate) {
        this.setMeetingDate(meetingDate);
        return this;
    }

    public void setMeetingDate(String meetingDate) {
        this.meetingDate = meetingDate == null ? null : meetingDate.trim();
    }

    public String getStartTime() {
        return startTime;
    }

    public SwMeetingMinute withStartTime(String startTime) {
        this.setStartTime(startTime);
        return this;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime == null ? null : startTime.trim();
    }

    public String getEndTime() {
        return endTime;
    }

    public SwMeetingMinute withEndTime(String endTime) {
        this.setEndTime(endTime);
        return this;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime == null ? null : endTime.trim();
    }

    public String getWebexUrl() {
        return webexUrl;
    }

    public SwMeetingMinute withWebexUrl(String webexUrl) {
        this.setWebexUrl(webexUrl);
        return this;
    }

    public void setWebexUrl(String webexUrl) {
        this.webexUrl = webexUrl == null ? null : webexUrl.trim();
    }

    public String getResourceIds() {
        return resourceIds;
    }

    public SwMeetingMinute withResourceIds(String resourceIds) {
        this.setResourceIds(resourceIds);
        return this;
    }

    public void setResourceIds(String resourceIds) {
        this.resourceIds = resourceIds == null ? null : resourceIds.trim();
    }

    public String getStatus() {
        return status;
    }

    public SwMeetingMinute withStatus(String status) {
        this.setStatus(status);
        return this;
    }

    public void setStatus(String status) {
        this.status = status == null ? null : status.trim();
    }

    public LocalDateTime getCreateTime() {
        return createTime;
    }

    public SwMeetingMinute withCreateTime(LocalDateTime createTime) {
        this.setCreateTime(createTime);
        return this;
    }

    public void setCreateTime(LocalDateTime createTime) {
        this.createTime = createTime;
    }

    public LocalDateTime getDatetimeLastchange() {
        return datetimeLastchange;
    }

    public SwMeetingMinute withDatetimeLastchange(LocalDateTime datetimeLastchange) {
        this.setDatetimeLastchange(datetimeLastchange);
        return this;
    }

    public void setDatetimeLastchange(LocalDateTime datetimeLastchange) {
        this.datetimeLastchange = datetimeLastchange;
    }
}