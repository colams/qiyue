package com.foxconn.sw.data.dto.response.meeting;

import com.foxconn.sw.data.dto.entity.meeting.MeetingV2Vo;
import com.foxconn.sw.data.dto.entity.universal.OptionsVo;

import java.util.List;

public class MeetListVo {

    private List<MeetingV2Vo> meetingList;

    private List<OptionsVo> chairmanFilter;


    public List<MeetingV2Vo> getMeetingList() {
        return meetingList;
    }

    public void setMeetingList(List<MeetingV2Vo> meetingList) {
        this.meetingList = meetingList;
    }

    public List<OptionsVo> getChairmanFilter() {
        return chairmanFilter;
    }

    public void setChairmanFilter(List<OptionsVo> chairmanFilter) {
        this.chairmanFilter = chairmanFilter;
    }
}
