package com.foxconn.sw.data.dto.entity.meeting;

import com.foxconn.sw.data.dto.communal.MeetingDateTimeVo;
import com.foxconn.sw.data.dto.entity.ResourceVo;
import com.foxconn.sw.data.dto.entity.acount.EmployeeVo;
import com.foxconn.sw.data.dto.entity.oa.InfoColorVo;
import com.foxconn.sw.data.dto.entity.universal.OptionsVo;

import java.util.List;

public class MeetingV2Vo {

    private Integer meetingID;
    private OptionsVo roomVo;

    private String title;
    private MeetingDateTimeVo meetingDateVo;

    private EmployeeVo chairman;

    private List<ResourceVo> resource;
    private InfoColorVo statusVo;


    private String updateTime;
    private Boolean isUpdate;

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

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public MeetingDateTimeVo getMeetingDateVo() {
        return meetingDateVo;
    }

    public void setMeetingDateVo(MeetingDateTimeVo meetingDateVo) {
        this.meetingDateVo = meetingDateVo;
    }

    public EmployeeVo getChairman() {
        return chairman;
    }

    public void setChairman(EmployeeVo chairman) {
        this.chairman = chairman;
    }

    public List<ResourceVo> getResource() {
        return resource;
    }

    public void setResource(List<ResourceVo> resource) {
        this.resource = resource;
    }

    public InfoColorVo getStatusVo() {
        return statusVo;
    }

    public void setStatusVo(InfoColorVo statusVo) {
        this.statusVo = statusVo;
    }

    public String getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime;
    }

    public Boolean getUpdate() {
        return isUpdate;
    }

    public void setUpdate(Boolean update) {
        isUpdate = update;
    }
}
