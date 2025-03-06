package com.foxconn.sw.data.dto.request.meeting;

public class ListMeetingV2Params {
    private String searchStartDate;
    private String searchEndDate;

    private String chairman;
    private boolean hasMinute;
    private String room;
    private String meetingTitle;
    private String attachmentName;

    public String getSearchStartDate() {
        return searchStartDate;
    }

    public void setSearchStartDate(String searchStartDate) {
        this.searchStartDate = searchStartDate;
    }

    public String getSearchEndDate() {
        return searchEndDate;
    }

    public void setSearchEndDate(String searchEndDate) {
        this.searchEndDate = searchEndDate;
    }

    public String getChairman() {
        return chairman;
    }

    public void setChairman(String chairman) {
        this.chairman = chairman;
    }

    public boolean isHasMinute() {
        return hasMinute;
    }

    public void setHasMinute(boolean hasMinute) {
        this.hasMinute = hasMinute;
    }

    public String getRoom() {
        return room;
    }

    public void setRoom(String room) {
        this.room = room;
    }

    public String getMeetingTitle() {
        return meetingTitle;
    }

    public void setMeetingTitle(String meetingTitle) {
        this.meetingTitle = meetingTitle;
    }

    public String getAttachmentName() {
        return attachmentName;
    }

    public void setAttachmentName(String attachmentName) {
        this.attachmentName = attachmentName;
    }
}
