package com.foxconn.sw.data.dto.request.meeting;

import com.foxconn.sw.data.dto.communal.MeetingDateTimeVo;
import com.foxconn.sw.data.dto.communal.MeetingMemberEnoVo;
import jakarta.validation.constraints.NotNull;

import java.util.List;

public class EstablishMeetingParams {
    @NotNull
    private String room;
    @NotNull
    private MeetingDateTimeVo timeVo;
    private Integer abcMeeting;
    @NotNull
    private Integer repeat;
    private List<Integer> cycleInts;
    @NotNull
    private String title;
    @NotNull
    private String description;
    @NotNull
    private MeetingMemberEnoVo memberVo;
    private List<Integer> resourceIds;

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

    public Integer getRepeat() {
        return repeat;
    }

    public void setRepeat(Integer repeat) {
        this.repeat = repeat;
    }

    public List<Integer> getCycleInts() {
        return cycleInts;
    }

    public void setCycleInts(List<Integer> cycleInts) {
        this.cycleInts = cycleInts;
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
}
