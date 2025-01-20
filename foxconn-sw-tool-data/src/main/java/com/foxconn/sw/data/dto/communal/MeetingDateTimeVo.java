package com.foxconn.sw.data.dto.communal;

public class MeetingDateTimeVo {

    private String meetingDate;
    private String startTime;
    private String endTime;
    private String weekInfo;

    public MeetingDateTimeVo() {
    }

    public MeetingDateTimeVo(String meetingDate, String startTime, String endTime, String weekInfo) {
        this.meetingDate = meetingDate;
        this.startTime = startTime;
        this.endTime = endTime;
        this.weekInfo = weekInfo;
    }

    public MeetingDateTimeVo(String meetingDate, String startTime, String endTime) {
        this.meetingDate = meetingDate;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    public String getMeetingDate() {
        return meetingDate;
    }

    public void setMeetingDate(String meetingDate) {
        this.meetingDate = meetingDate;
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

    public String getWeekInfo() {
        return weekInfo;
    }

    public void setWeekInfo(String weekInfo) {
        this.weekInfo = weekInfo;
    }
}
