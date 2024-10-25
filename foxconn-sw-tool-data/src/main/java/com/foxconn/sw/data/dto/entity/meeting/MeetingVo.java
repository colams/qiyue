package com.foxconn.sw.data.dto.entity.meeting;

import com.foxconn.sw.data.dto.communal.CycleMeetingVo;
import com.foxconn.sw.data.dto.entity.ResourceVo;
import com.foxconn.sw.data.dto.entity.acount.EmployeeVo;

import java.util.List;

public class MeetingVo {

    private Integer meetingID;
    private String room;
    private String roomName;
    private Integer meetingType;

    private String title;
    private String description;

    private Integer week;
    private String meetingDate;
    private String startTime;
    private String endTime;
    private Integer duration;

    private CycleMeetingVo cycleVo;

    private EmployeeVo chairman;
    private List<EmployeeVo> maintainers;
    private List<EmployeeVo> members;

    private List<ResourceVo> resource;

    private String webexUrl;

    public Integer getMeetingID() {
        return meetingID;
    }

    public void setMeetingID(Integer meetingID) {
        this.meetingID = meetingID;
    }

    public String getRoom() {
        return room;
    }

    public void setRoom(String room) {
        this.room = room;
    }

    public String getRoomName() {
        return roomName;
    }

    public void setRoomName(String roomName) {
        this.roomName = roomName;
    }

    public Integer getMeetingType() {
        return meetingType;
    }

    public void setMeetingType(Integer meetingType) {
        this.meetingType = meetingType;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getWeek() {
        return week;
    }

    public void setWeek(Integer week) {
        this.week = week;
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

    public Integer getDuration() {
        return duration;
    }

    public void setDuration(Integer duration) {
        this.duration = duration;
    }

    public CycleMeetingVo getCycleVo() {
        return cycleVo;
    }

    public void setCycleVo(CycleMeetingVo cycleVo) {
        this.cycleVo = cycleVo;
    }

    public EmployeeVo getChairman() {
        return chairman;
    }

    public void setChairman(EmployeeVo chairman) {
        this.chairman = chairman;
    }

    public List<EmployeeVo> getMaintainers() {
        return maintainers;
    }

    public void setMaintainers(List<EmployeeVo> maintainers) {
        this.maintainers = maintainers;
    }

    public List<EmployeeVo> getMembers() {
        return members;
    }

    public void setMembers(List<EmployeeVo> members) {
        this.members = members;
    }

    public List<ResourceVo> getResource() {
        return resource;
    }

    public void setResource(List<ResourceVo> resource) {
        this.resource = resource;
    }

    public String getWebexUrl() {
        return webexUrl;
    }

    public void setWebexUrl(String webexUrl) {
        this.webexUrl = webexUrl;
    }
}
