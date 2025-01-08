package com.foxconn.sw.service.processor.meeting;

import com.foxconn.sw.business.meeting.MeetingBusiness;
import com.foxconn.sw.business.meeting.SwMeetingMinutesBusiness;
import com.foxconn.sw.data.dto.communal.MeetingDateTimeVo;
import com.foxconn.sw.data.dto.entity.meeting.MeetingMinuteItemVo;
import com.foxconn.sw.data.dto.entity.meeting.MeetingMinuteVo;
import com.foxconn.sw.data.dto.response.meeting.MeetingLineVo;
import com.foxconn.sw.data.dto.response.meeting.MeetingMinuteDetailVo;
import com.foxconn.sw.data.entity.SwMeeting;
import com.foxconn.sw.data.entity.SwMeetingMinutes;
import com.google.common.collect.Lists;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

@Component
public class MeetingLineProcessor {

    @Autowired
    SwMeetingMinutesBusiness meetingMinutesBusiness;
    @Autowired
    MeetingBusiness meetingBusiness;

    public List<MeetingLineVo> meetingLines(Integer meetingId) {
        SwMeeting meeting = meetingBusiness.getMeetingByID(meetingId);
        if (StringUtils.isEmpty(meeting.getCycle())) {
            MeetingLineVo meetingLineVo = new MeetingLineVo();
            meetingLineVo.setMeetingDate(meeting.getMeetingDate());
            meetingLineVo.setResourceVos(Lists.newArrayList());
            return Lists.newArrayList(meetingLineVo);
        }

        List<MeetingLineVo> result = Lists.newArrayList();
        List<SwMeetingMinutes> meetingMinutes = meetingMinutesBusiness.queryMeetingMinuteByMeetingId(meetingId);
        if (!CollectionUtils.isEmpty(meetingMinutes)) {

        }
        MeetingLineVo vo = new MeetingLineVo();
        vo.setMeetingId(meetingId);
        vo.setMeetingDate("2022-12-12");
        vo.setResourceVos(null);
        result.add(vo);
        return result;
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
