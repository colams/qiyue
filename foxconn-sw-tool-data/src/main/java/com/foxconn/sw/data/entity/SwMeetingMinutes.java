package com.foxconn.sw.data.entity;

import java.time.LocalDateTime;

public class SwMeetingMinutes {
    private Long id;

    private String room;

    private String title;

    private String meetingDate;

    private String startTime;

    private String endTime;

    private String webexUrl;

    private LocalDateTime createTime;

    private LocalDateTime datetimeLastchange;

    public Long getId() {
        return id;
    }

    public SwMeetingMinutes withId(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getRoom() {
        return room;
    }

    public SwMeetingMinutes withRoom(String room) {
        this.setRoom(room);
        return this;
    }

    public void setRoom(String room) {
        this.room = room == null ? null : room.trim();
    }

    public String getTitle() {
        return title;
    }

    public SwMeetingMinutes withTitle(String title) {
        this.setTitle(title);
        return this;
    }

    public void setTitle(String title) {
        this.title = title == null ? null : title.trim();
    }

    public String getMeetingDate() {
        return meetingDate;
    }

    public SwMeetingMinutes withMeetingDate(String meetingDate) {
        this.setMeetingDate(meetingDate);
        return this;
    }

    public void setMeetingDate(String meetingDate) {
        this.meetingDate = meetingDate == null ? null : meetingDate.trim();
    }

    public String getStartTime() {
        return startTime;
    }

    public SwMeetingMinutes withStartTime(String startTime) {
        this.setStartTime(startTime);
        return this;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime == null ? null : startTime.trim();
    }

    public String getEndTime() {
        return endTime;
    }

    public SwMeetingMinutes withEndTime(String endTime) {
        this.setEndTime(endTime);
        return this;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime == null ? null : endTime.trim();
    }

    public String getWebexUrl() {
        return webexUrl;
    }

    public SwMeetingMinutes withWebexUrl(String webexUrl) {
        this.setWebexUrl(webexUrl);
        return this;
    }

    public void setWebexUrl(String webexUrl) {
        this.webexUrl = webexUrl == null ? null : webexUrl.trim();
    }

    public LocalDateTime getCreateTime() {
        return createTime;
    }

    public SwMeetingMinutes withCreateTime(LocalDateTime createTime) {
        this.setCreateTime(createTime);
        return this;
    }

    public void setCreateTime(LocalDateTime createTime) {
        this.createTime = createTime;
    }

    public LocalDateTime getDatetimeLastchange() {
        return datetimeLastchange;
    }

    public SwMeetingMinutes withDatetimeLastchange(LocalDateTime datetimeLastchange) {
        this.setDatetimeLastchange(datetimeLastchange);
        return this;
    }

    public void setDatetimeLastchange(LocalDateTime datetimeLastchange) {
        this.datetimeLastchange = datetimeLastchange;
    }
}