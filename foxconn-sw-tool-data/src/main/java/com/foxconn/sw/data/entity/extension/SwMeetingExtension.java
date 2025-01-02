package com.foxconn.sw.data.entity.extension;

import com.foxconn.sw.data.entity.SwMeeting;

public class SwMeetingExtension extends SwMeeting {

    private Integer smcdID;
    private String meetingDate2;
    private String startTime2;
    private String endTime2;

    public Integer getSmcdID() {
        return smcdID;
    }

    public void setSmcdID(Integer smcdID) {
        this.smcdID = smcdID;
    }

    public String getMeetingDate2() {
        return meetingDate2;
    }

    public void setMeetingDate2(String meetingDate2) {
        this.meetingDate2 = meetingDate2;
    }

    public String getStartTime2() {
        return startTime2;
    }

    public void setStartTime2(String startTime2) {
        this.startTime2 = startTime2;
    }

    public String getEndTime2() {
        return endTime2;
    }

    public void setEndTime2(String endTime2) {
        this.endTime2 = endTime2;
    }
}
