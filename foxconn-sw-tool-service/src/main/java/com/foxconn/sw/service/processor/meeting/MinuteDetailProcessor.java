package com.foxconn.sw.service.processor.meeting;

import com.foxconn.sw.business.meeting.SwMeetingMinutesBusiness;
import com.foxconn.sw.data.dto.communal.MeetingDateTimeVo;
import com.foxconn.sw.data.dto.entity.meeting.MeetingMinuteItemVo;
import com.foxconn.sw.data.dto.entity.meeting.MeetingMinuteVo;
import com.foxconn.sw.data.dto.request.meeting.MinuteDetailParams;
import com.foxconn.sw.data.dto.response.meeting.MeetingMinuteDetailVo;
import com.foxconn.sw.data.entity.SwMeetingMinutes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Component
public class MinuteDetailProcessor {

    @Autowired
    SwMeetingMinutesBusiness meetingMinutesBusiness;

    public MeetingMinuteDetailVo minuteDetail(MinuteDetailParams data) {
        SwMeetingMinutes meetingMinutes = meetingMinutesBusiness.queryMeetingMinute(data.getMeetingID(), data.getMeetingDate());
        MeetingMinuteDetailVo vo;
        if (Objects.nonNull(meetingMinutes)) {
            vo = map2Detail(meetingMinutes);
        } else {
            vo = initDefault(data.getMeetingID(), data.getMeetingDate());
        }
        return vo;
    }

    private MeetingMinuteDetailVo initDefault(Integer meetingID, String meetingDate) {

        MeetingMinuteDetailVo vo = new MeetingMinuteDetailVo();
        vo.setMinuteVo(null);
        return vo;
    }

    private MeetingMinuteDetailVo map2Detail(SwMeetingMinutes meetingMinutes) {
        MeetingMinuteVo minuteVo = map2MinuteVo(meetingMinutes);
        List<MeetingMinuteItemVo> decisionVo = new ArrayList<>();
        List<MeetingMinuteItemVo> otherVo = new ArrayList<>();

        MeetingMinuteDetailVo vo = new MeetingMinuteDetailVo();
        vo.setMinuteVo(minuteVo);
        vo.setDecisionVo(decisionVo);
        vo.setOtherVo(otherVo);
        return vo;
    }

    private MeetingMinuteVo map2MinuteVo(SwMeetingMinutes meetingMinutes) {
        MeetingMinuteVo vo = new MeetingMinuteVo();
        vo.setMeetingID(meetingMinutes.getMeetingId());
        vo.setMeetingRoomKey(meetingMinutes.getRoom());
        vo.setDateTimeVo(new MeetingDateTimeVo(meetingMinutes.getMeetingDate(),
                meetingMinutes.getStartTime(),
                meetingMinutes.getEndTime()));
        vo.setMeetingTitle(meetingMinutes.getTitle());

        vo.setChairman(null);
        vo.setRecorder(null);
        vo.setMembers(null);
        vo.setResourceIds(null);

        return vo;
    }


}
