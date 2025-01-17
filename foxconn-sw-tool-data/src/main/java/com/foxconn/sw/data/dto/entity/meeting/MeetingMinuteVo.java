package com.foxconn.sw.data.dto.entity.meeting;

import com.foxconn.sw.data.dto.communal.MeetingDateTimeVo;
import com.foxconn.sw.data.dto.entity.ResourceVo;
import com.foxconn.sw.data.dto.entity.universal.OptionsVo;

import java.util.List;

public class MeetingMinuteVo {

    private Long id;
    private Integer meetingID;
    private OptionsVo roomVo;
    private MeetingDateTimeVo dateTimeVo;
    private String chairman;
    private String recorder;
    private List<String> members;
    private String meetingTitle;
    private String meetingRoomKey;
    private List<Integer> resourceIds;

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

    public OptionsVo getRoomVo() {
        return roomVo;
    }

    public void setRoomVo(OptionsVo roomVo) {
        this.roomVo = roomVo;
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

    public List<Integer> getResourceIds() {
        return resourceIds;
    }

    public void setResourceIds(List<Integer> resourceIds) {
        this.resourceIds = resourceIds;
    }

    public String getMeetingRoomKey() {
        return meetingRoomKey;
    }

    public void setMeetingRoomKey(String meetingRoomKey) {
        this.meetingRoomKey = meetingRoomKey;
    }
}
