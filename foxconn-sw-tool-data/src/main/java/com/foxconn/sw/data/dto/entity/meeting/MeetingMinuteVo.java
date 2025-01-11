package com.foxconn.sw.data.dto.entity.meeting;

import com.foxconn.sw.data.dto.communal.MeetingDateTimeVo;
import com.foxconn.sw.data.dto.entity.ResourceVo;

import java.util.List;

public class MeetingMinuteVo {

    private Long id;
    private Integer meetingID;
    private String meetingRoomKey;
    private MeetingDateTimeVo dateTimeVo;
    private String chairman;
    private String recorder;
    private List<String> members;
    private String meetingTitle;
    private List<ResourceVo> resourceIds;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getMeetingID() {
        return meetingID;
    }

    public void setMeetingID(Integer meetingID) {
        this.meetingID = meetingID;
    }

    public String getMeetingRoomKey() {
        return meetingRoomKey;
    }

    public void setMeetingRoomKey(String meetingRoomKey) {
        this.meetingRoomKey = meetingRoomKey;
    }

    public MeetingDateTimeVo getDateTimeVo() {
        return dateTimeVo;
    }

    public void setDateTimeVo(MeetingDateTimeVo dateTimeVo) {
        this.dateTimeVo = dateTimeVo;
    }

    public String getChairman() {
        return chairman;
    }

    public void setChairman(String chairman) {
        this.chairman = chairman;
    }

    public String getRecorder() {
        return recorder;
    }

    public void setRecorder(String recorder) {
        this.recorder = recorder;
    }

    public List<String> getMembers() {
        return members;
    }

    public void setMembers(List<String> members) {
        this.members = members;
    }

    public String getMeetingTitle() {
        return meetingTitle;
    }

    public void setMeetingTitle(String meetingTitle) {
        this.meetingTitle = meetingTitle;
    }

    public List<ResourceVo> getResourceIds() {
        return resourceIds;
    }

    public void setResourceIds(List<ResourceVo> resourceIds) {
        this.resourceIds = resourceIds;
    }
}
