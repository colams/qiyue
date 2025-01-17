package com.foxconn.sw.service.processor.meeting;

import com.foxconn.sw.business.SwAppendResourceBusiness;
import com.foxconn.sw.business.meeting.*;
import com.foxconn.sw.common.constanst.NumberConstants;
import com.foxconn.sw.common.utils.JsonUtils;
import com.foxconn.sw.data.constants.enums.MeetingRoleFlagEnums;
import com.foxconn.sw.data.context.RequestContext;
import com.foxconn.sw.data.dto.communal.MeetingDateTimeVo;
import com.foxconn.sw.data.dto.entity.meeting.MeetingMinuteItemVo;
import com.foxconn.sw.data.dto.entity.meeting.MeetingMinuteVo;
import com.foxconn.sw.data.dto.entity.universal.OptionsVo;
import com.foxconn.sw.data.dto.enums.MeetingItemTypeEnums;
import com.foxconn.sw.data.dto.request.meeting.MinuteDetailParams;
import com.foxconn.sw.data.dto.response.meeting.MeetingMinuteDetailVo;
import com.foxconn.sw.data.entity.*;
import com.foxconn.sw.service.processor.MeetingRoomConfig;
import com.foxconn.sw.service.processor.utils.EmployeeUtils;
import com.google.common.collect.Lists;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
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
    @Autowired
    SwMeetingMinutesDetailBusiness minutesDetailBusiness;
    @Autowired
    MeetingMemberBusiness meetingMemberBusiness;


    public MeetingMinuteDetailVo minuteDetail(MinuteDetailParams data) {
        SwMeetingMinute meetingMinutes = meetingMinutesBusiness.queryMeetingMinute(data.getMeetingID(), data.getMeetingDate());
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
        List<SwMeetingMember> meetingMembers = meetingMemberBusiness.queryMeetingMember(meetingID);
        Map<Integer, List<SwMeetingMember>> hashMap = meetingMembers.stream()
                .collect(Collectors.groupingBy(SwMeetingMember::getMeetingDetailId));
        List<SwMeetingMember> members = Lists.newArrayList();
        if (optional.isPresent()) {
            members = hashMap.getOrDefault(optional.get().getId(), hashMap.get(NumberConstants.ZERO));
        }

        MeetingMinuteVo minuteVo = new MeetingMinuteVo();
        minuteVo.setMeetingID(meetingID);
        minuteVo.setRoomVo(getRoomVo(optional.map(e -> e.getRoom()).orElse(meeting.getRoom())));
        minuteVo.setDateTimeVo(new MeetingDateTimeVo(meetingDate,
                optional.map(e -> e.getStartTime()).orElse(meeting.getStartTime()),
                optional.map(e -> e.getEndTime()).orElse(meeting.getEndTime())));
        minuteVo.setChairman(members.stream().filter(e -> Chairman_Flag.test(e.getRole())).map(SwMeetingMember::getEmployeeNo).collect(Collectors.toList()).get(0));
        minuteVo.setRecorder(RequestContext.getEmployeeNo());
        minuteVo.setMembers(members.stream().filter(e -> Member_Flag.test(e.getRole())).map(SwMeetingMember::getEmployeeNo).collect(Collectors.toList()));
        minuteVo.setMeetingTitle(optional.map(e -> e.getTitle()).orElse(meeting.getTitle()));
        if (StringUtils.isNotEmpty(meeting.getResourceIds())) {
            minuteVo.setResourceIds(JsonUtils.deserialize(meeting.getResourceIds(), List.class, Integer.class));
        }

        MeetingMinuteDetailVo vo = new MeetingMinuteDetailVo();
        vo.setMinuteVo(minuteVo);
        vo.setResourceVos(appendResourceBusiness.getAppendResourcesVo(meeting.getResourceIds()));
        return vo;
    }

    private MeetingMinuteDetailVo map2Detail(SwMeetingMinute meetingMinutes) {
        List<SwMeetingMinuteDetail> minutesDetails = minutesDetailBusiness.queryMeetingMinuteDetail(meetingMinutes.getId());

        MeetingMinuteVo minuteVo = map2MinuteVo(meetingMinutes);
        List<MeetingMinuteItemVo> decisionVo = getMinuteItems(minutesDetails, MeetingItemTypeEnums.Decision);
        List<MeetingMinuteItemVo> otherVo = getMinuteItems(minutesDetails, MeetingItemTypeEnums.Other);

        MeetingMinuteDetailVo vo = new MeetingMinuteDetailVo();
        vo.setMinuteVo(minuteVo);
        vo.setDecisionVo(decisionVo);
        vo.setOtherVo(otherVo);
        vo.setResourceVos(appendResourceBusiness.getAppendResourcesVo(meetingMinutes.getResourceIds()));
        return vo;
    }

    private List<MeetingMinuteItemVo> getMinuteItems(List<SwMeetingMinuteDetail> minutesDetails, MeetingItemTypeEnums other) {
        return Optional.ofNullable(minutesDetails).orElse(Lists.newArrayList())
                .stream()
                .filter(e -> other.getCode().equalsIgnoreCase(e.getItemType()))
                .map(e -> map2MinuteDetail(e))
                .collect(Collectors.toList());
    }

    private MeetingMinuteItemVo map2MinuteDetail(SwMeetingMinuteDetail e) {
        MeetingMinuteItemVo vo = new MeetingMinuteItemVo();
        vo.setId(e.getId());
        vo.setIndex(e.getIndexNo());
        vo.setItemTitle(e.getItem());
        vo.setDirector(e.getDirectEno());
        vo.setDueDate(e.getDueDate());
        vo.setStatus(e.getStatus());
        vo.setRemark(e.getRemark());
        return vo;
    }

    private MeetingMinuteVo map2MinuteVo(SwMeetingMinute meetingMinutes) {
        List<SwMeetingMinuteMember> members = minutesMembersBusiness.queryMeetingMinuteMember(meetingMinutes.getId());

        MeetingMinuteVo vo = new MeetingMinuteVo();
        vo.setId(meetingMinutes.getId());
        vo.setMeetingID(meetingMinutes.getMeetingId());
        vo.setRoomVo(getRoomVo(meetingMinutes.getRoom()));
        vo.setDateTimeVo(new MeetingDateTimeVo(meetingMinutes.getMeetingDate(),
                meetingMinutes.getStartTime(),
                meetingMinutes.getEndTime()));
        vo.setMeetingTitle(meetingMinutes.getTitle());

        vo.setChairman(getMembersByRole(members, Chairman_Flag));
        vo.setRecorder(getMembersByRole(members, Recorder));
        vo.setMembers(getMembersByRoles(members, Member_Flag));
        if (StringUtils.isNotEmpty(meetingMinutes.getResourceIds())) {
            vo.setResourceIds(JsonUtils.deserialize(meetingMinutes.getResourceIds(), List.class, Integer.class));
        }

        return vo;
    }


    private OptionsVo getRoomVo(String roomKey) {
        return new OptionsVo(roomKey, MeetingRoomConfig.getText(roomKey));
    }

    private List<String> getMembersByRoles(List<SwMeetingMinuteMember> members, MeetingRoleFlagEnums recorder) {
        return members.stream().filter(e -> recorder.test(e.getRole()))
                .map(e -> e.getEmployeeNo())
                .collect(Collectors.toList());
    }


    private String getMembersByRole(List<SwMeetingMinuteMember> members, MeetingRoleFlagEnums recorder) {
        return members.stream().filter(e -> recorder.test(e.getRole()))
                .map(e -> e.getEmployeeNo())
                .findFirst()
                .orElse("");
    }


}
