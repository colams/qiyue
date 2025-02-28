package com.foxconn.sw.data.dto.request.meeting;

public class MeetingAttachmentParams {

    private Integer meetingID;
    private String meetingDate;

    public Integer getMeetingID() {
        return meetingID;
    }

    public void setMeetingID(Integer meetingID) {
        this.meetingID = meetingID;
    }

    public String getMeetingDate() {
        return meetingDate;
    }

    public void setMeetingDate(String meetingDate) {
        this.meetingDate = meetingDate;
    }
}
