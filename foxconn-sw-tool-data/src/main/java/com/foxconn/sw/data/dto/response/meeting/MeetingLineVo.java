package com.foxconn.sw.data.dto.response.meeting;

import com.foxconn.sw.data.dto.entity.ResourceVo;

import java.util.List;

public class MeetingLineVo {

    private Integer meetingId;
    private String meetingDate;
    private List<ResourceVo> resourceVos;

    public Integer getMeetingId() {
        return meetingId;
    }

    public void setMeetingId(Integer meetingId) {
        this.meetingId = meetingId;
    }

    public String getMeetingDate() {
        return meetingDate;
    }

    public void setMeetingDate(String meetingDate) {
        this.meetingDate = meetingDate;
    }

    public List<ResourceVo> getResourceVos() {
        return resourceVos;
    }

    public void setResourceVos(List<ResourceVo> resourceVos) {
        this.resourceVos = resourceVos;
    }
}
