package com.foxconn.sw.data.entity.extension;

public class MeetingTjEntity {

    private Integer id;
    private String meetingDate;
    private String cycle;
    private String detailMeetingDate;
    private String cancel;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getMeetingDate() {
        return meetingDate;
    }

    public void setMeetingDate(String meetingDate) {
        this.meetingDate = meetingDate;
    }

    public String getCycle() {
        return cycle;
    }

    public void setCycle(String cycle) {
        this.cycle = cycle;
    }

    public String getDetailMeetingDate() {
        return detailMeetingDate;
    }

    public void setDetailMeetingDate(String detailMeetingDate) {
        this.detailMeetingDate = detailMeetingDate;
    }

    public String getCancel() {
        return cancel;
    }

    public void setCancel(String cancel) {
        this.cancel = cancel;
    }
}
