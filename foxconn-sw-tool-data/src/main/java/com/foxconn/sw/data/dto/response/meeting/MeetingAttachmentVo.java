package com.foxconn.sw.data.dto.response.meeting;

import com.foxconn.sw.data.dto.entity.ResourceVo;

public class MeetingAttachmentVo extends ResourceVo {

    private Integer meetingId;
    private Integer meetingDetailId;
    private boolean canDelete;

    public Integer getMeetingId() {
        return meetingId;
    }

    public void setMeetingId(Integer meetingId) {
        this.meetingId = meetingId;
    }

    public Integer getMeetingDetailId() {
        return meetingDetailId;
    }

    public void setMeetingDetailId(Integer meetingDetailId) {
        this.meetingDetailId = meetingDetailId;
    }

    public boolean isCanDelete() {
        return canDelete;
    }

    public void setCanDelete(boolean canDelete) {
        this.canDelete = canDelete;
    }
}
