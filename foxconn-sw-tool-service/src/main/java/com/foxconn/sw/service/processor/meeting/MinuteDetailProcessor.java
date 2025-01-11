package com.foxconn.sw.service.processor.meeting;

import com.foxconn.sw.business.SwAppendResourceBusiness;
import com.foxconn.sw.business.meeting.MeetingBusiness;
import com.foxconn.sw.business.meeting.MeetingCycleDetailBusiness;
import com.foxconn.sw.business.meeting.SwMeetingMinutesBusiness;
import com.foxconn.sw.business.meeting.SwMeetingMinutesMembersBusiness;
import com.foxconn.sw.data.constants.enums.MeetingRoleFlagEnums;
import com.foxconn.sw.data.dto.communal.MeetingDateTimeVo;
import com.foxconn.sw.data.dto.entity.meeting.MeetingMinuteItemVo;
import com.foxconn.sw.data.dto.entity.meeting.MeetingMinuteVo;
import com.foxconn.sw.data.dto.request.meeting.MinuteDetailParams;
import com.foxconn.sw.data.dto.response.meeting.MeetingMinuteDetailVo;
import com.foxconn.sw.data.entity.SwMeeting;
import com.foxconn.sw.data.entity.SwMeetingCycleDetail;
import com.foxconn.sw.data.entity.SwMeetingMinutes;
import com.foxconn.sw.data.entity.SwMeetingMinutesMembers;
import com.foxconn.sw.service.processor.utils.EmployeeUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

import static com.foxconn.sw.data.constants.enums.MeetingRoleFlagEnums.*;

@Component
public class MinuteDetailProcessor {

    @Autowired
    SwMeetingMinutesBusiness meetingMinutesBusiness;
    @Autowired
    MeetingBusiness meetingBusiness;
    @Autowired
    MeetingCycleDetailBusiness meetingCycleDetailBusiness;
    @Autowired
    SwAppendResourceBusiness appendResourceBusiness;
    @Autowired
    SwMeetingMinutesMembersBusiness minutesMembersBusiness;
    @Autowired
    EmployeeUtils employeeUtils;


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
        SwMeeting meeting = meetingBusiness.getMeetingByID(meetingID);
        Optional<SwMeetingCycleDetail> optional = meetingCycleDetailBusiness.queryCycleDetailEntityWithDate(meetingID, meetingDate);

        MeetingMinuteVo minuteVo = new MeetingMinuteVo();
        minuteVo.setMeetingID(meetingID);
        minuteVo.setMeetingRoomKey(optional.map(e -> e.getRoom()).orElse(meeting.getRoom()));
        minuteVo.setDateTimeVo(null);
        minuteVo.setChairman(null);
        minuteVo.setRecorder(null);
        minuteVo.setMembers(null);
        minuteVo.setMeetingTitle(optional.map(e -> e.getTitle()).orElse(meeting.getTitle()));
        minuteVo.setResourceIds(null);


        MeetingMinuteDetailVo vo = new MeetingMinuteDetailVo();
        vo.setMinuteVo(minuteVo);
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
        List<SwMeetingMinutesMembers> members = minutesMembersBusiness.queryMeetingMinuteMember(meetingMinutes.getId());

        MeetingMinuteVo vo = new MeetingMinuteVo();
        vo.setMeetingID(meetingMinutes.getMeetingId());
        vo.setMeetingRoomKey(meetingMinutes.getRoom());
        vo.setDateTimeVo(new MeetingDateTimeVo(meetingMinutes.getMeetingDate(),
                meetingMinutes.getStartTime(),
                meetingMinutes.getEndTime()));
        vo.setMeetingTitle(meetingMinutes.getTitle());

        vo.setChairman(getMembersByRole(members, Chairman_Flag));
        vo.setRecorder(getMembersByRole(members, Recorder));
        vo.setMembers(getMembersByRoles(members, Member_Flag));
        vo.setResourceIds(appendResourceBusiness.getAppendResourcesVo(meetingMinutes.getResourceIds()));

        return vo;
    }

    private List<String> getMembersByRoles(List<SwMeetingMinutesMembers> members, MeetingRoleFlagEnums recorder) {
        List<String> employeeNo = new ArrayList<>();
        return members.stream().filter(e -> recorder.test(e.getRole()))
                .map(e -> e.getEmployeeNo())
                .collect(Collectors.toList());
    }


    private String getMembersByRole(List<SwMeetingMinutesMembers> members, MeetingRoleFlagEnums recorder) {
        return members.stream().filter(e -> recorder.test(e.getRole()))
                .map(e -> e.getEmployeeNo())
                .findFirst()
                .orElse("");
    }


}
