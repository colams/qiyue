package com.foxconn.sw.data.dto.request.meeting;

import com.foxconn.sw.data.dto.communal.CycleMeetingVo;
import com.foxconn.sw.data.dto.communal.MeetingDateTimeVo;
import com.foxconn.sw.data.dto.communal.MeetingMemberEnoVo;
import jakarta.validation.constraints.NotNull;

import java.util.List;

public class UpdateMeetingParams {
    private Integer meetingID;
    @NotNull
    private String room;
    @NotNull
    private MeetingDateTimeVo timeVo;
    private Integer abcMeeting;
    private CycleMeetingVo cycleVo;
    @NotNull
    private String title;
    @NotNull
    private String description;
    @NotNull
    private MeetingMemberEnoVo memberVo;
    private List<Integer> resourceIds;

    /**
     * 1-循环会议修改本次，
     * 2-循环会议修改周期
     */
    private Integer operateType;

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

    public MeetingDateTimeVo getTimeVo() {
        return timeVo;
    }

    public void setTimeVo(MeetingDateTimeVo timeVo) {
        this.timeVo = timeVo;
    }

    public Integer getAbcMeeting() {
        return abcMeeting;
    }

    public void setAbcMeeting(Integer abcMeeting) {
        this.abcMeeting = abcMeeting;
    }

    public CycleMeetingVo getCycleVo() {
        return cycleVo;
    }

    public void setCycleVo(CycleMeetingVo cycleVo) {
        this.cycleVo = cycleVo;
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

    public MeetingMemberEnoVo getMemberVo() {
        return memberVo;
    }

    public void setMemberVo(MeetingMemberEnoVo memberVo) {
        this.memberVo = memberVo;
    }

    public List<Integer> getResourceIds() {
        return resourceIds;
    }

    public void setResourceIds(List<Integer> resourceIds) {
        this.resourceIds = resourceIds;
    }

    public Integer getOperateType() {
        return operateType;
    }

    public void setOperateType(Integer operateType) {
        this.operateType = operateType;
    }
}
