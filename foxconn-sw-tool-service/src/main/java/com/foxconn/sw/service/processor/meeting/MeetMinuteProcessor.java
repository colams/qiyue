package com.foxconn.sw.service.processor.meeting;

import com.foxconn.sw.business.meeting.SwMeetingMinutesBusiness;
import com.foxconn.sw.business.meeting.SwMeetingMinutesDetailBusiness;
import com.foxconn.sw.business.meeting.SwMeetingMinutesMembersBusiness;
import com.foxconn.sw.common.constanst.NumberConstants;
import com.foxconn.sw.data.constants.enums.MeetingRoleFlagEnums;
import com.foxconn.sw.data.dto.entity.meeting.MeetingMinuteItemVo;
import com.foxconn.sw.data.dto.entity.meeting.MeetingMinuteVo;
import com.foxconn.sw.data.dto.enums.MeetingItemTypeEnums;
import com.foxconn.sw.data.dto.request.meeting.MeetingMinuteParams;
import com.foxconn.sw.data.entity.SwMeetingMinute;
import com.foxconn.sw.data.entity.SwMeetingMinuteDetail;
import com.foxconn.sw.data.entity.SwMeetingMinuteMember;
import com.foxconn.sw.service.processor.utils.EmployeeUtils;
import com.google.common.collect.Lists;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.util.*;
import java.util.stream.Collectors;

@Component
public class MeetMinuteProcessor {
    @Autowired
    SwMeetingMinutesBusiness meetingMinutesBusiness;
    @Autowired
    SwMeetingMinutesDetailBusiness meetingMinutesDetailBusiness;
    @Autowired
    SwMeetingMinutesMembersBusiness meetingMinutesMembersBusiness;
    @Autowired
    EmployeeUtils employeeUtils;

    public Boolean meetMinute(MeetingMinuteParams minuteParams) {

        SwMeetingMinute meetingMinutes = toMeetingMinutes(minuteParams.getMinuteVo());
        List<SwMeetingMinuteDetail> detailList = new ArrayList<>();
        toMinutesDetail(minuteParams.getDecisionVo(),
                MeetingItemTypeEnums.Decision,
                detailList);
        toMinutesDetail(
                minuteParams.getOtherVo(),
                MeetingItemTypeEnums.Other,
                detailList);

        Long meetingMinuteId = meetingMinutesBusiness.insertOrUpdate(meetingMinutes);
        if (meetingMinuteId > 0) {
            meetingMinutesDetailBusiness.batchInsert(detailList, meetingMinuteId);
            processMember(minuteParams.getMinuteVo(), meetingMinuteId);
        }
        return meetingMinuteId > 0;
    }

    private void processMember(MeetingMinuteVo minuteVo, Long meetingMinuteId) {

        Map<String, Integer> hashMap = new HashMap<>();
        if (StringUtils.isNotEmpty(minuteVo.getChairman())) {
            hashMap.put(minuteVo.getChairman(), MeetingRoleFlagEnums.Chairman_Flag.initFlag());
        }

        if (StringUtils.isNotEmpty(minuteVo.getRecorder())) {
            hashMap.put(minuteVo.getChairman(), MeetingRoleFlagEnums.Recorder.initFlag());
        }

        if (!CollectionUtils.isEmpty(minuteVo.getMembers())) {
            minuteVo.getMembers().forEach(e -> {
                int role = hashMap.getOrDefault(e, 0);
                hashMap.put(e, MeetingRoleFlagEnums.Member_Flag.setFlag(role));
            });
        }

        List<SwMeetingMinuteMember> meetingMemberList = meetingMinutesMembersBusiness.queryMeetingMinuteMember(meetingMinuteId);

        List<SwMeetingMinuteMember> newData = Lists.newArrayList();

        Optional.ofNullable(meetingMemberList)
                .orElse(Lists.newArrayList())
                .forEach(e -> {
                    SwMeetingMinuteMember member = new SwMeetingMinuteMember();
                    member.setId(e.getId());
                    int role = hashMap.getOrDefault(e.getEmployeeNo(), NumberConstants.ZERO);
                    member.setRole(role);
                    if (role == NumberConstants.ZERO) {
                        member.setIsDelete(NumberConstants.ONE);
                    }
                    newData.add(member);
                });

        for (Map.Entry<String, Integer> entry : hashMap.entrySet()) {
            boolean oldData = Optional.ofNullable(meetingMemberList)
                    .orElse(Lists.newArrayList())
                    .stream()
                    .anyMatch(e -> e.getEmployeeNo().equalsIgnoreCase(e.getEmployeeNo()));
            if (!oldData) {
                SwMeetingMinuteMember member = new SwMeetingMinuteMember();
                member.setRole(entry.getValue());
                member.setEmployeeNo(entry.getKey());
                member.setMmId(meetingMinuteId);
                member.setName(employeeUtils.mapEmployee(entry.getKey()).getName());
                newData.add(member);
            }
        }
        meetingMinutesMembersBusiness.batchUpdate(newData);
    }


    private void toMinutesDetail(List<MeetingMinuteItemVo> itemVos,
                                 MeetingItemTypeEnums itemType,
                                 List<SwMeetingMinuteDetail> otherVo) {
        if (CollectionUtils.isEmpty(itemVos)) {
            return;
        }

        List<SwMeetingMinuteDetail> result = itemVos.stream().map(e -> {
            SwMeetingMinuteDetail detail = new SwMeetingMinuteDetail();
            detail.setId(e.getId());
            detail.setItemType(itemType.getCode());
            detail.setIndexNo(e.getIndex());
            detail.setItem(e.getItemTitle());
            detail.setDirectEno(e.getDirector());
            detail.setDueDate(e.getDueDate());
            detail.setStatus(e.getStatus());
            detail.setRemark(e.getRemark());
            return detail;
        }).collect(Collectors.toList());
        otherVo.addAll(result);
    }

    private SwMeetingMinute toMeetingMinutes(MeetingMinuteVo vo) {
        SwMeetingMinute meetingMinutes = new SwMeetingMinute();
        meetingMinutes.setId(vo.getId());
        meetingMinutes.setMeetingId(vo.getMeetingID());
        meetingMinutes.setRoom(vo.getMeetingRoomKey());
        meetingMinutes.setTitle(vo.getMeetingTitle());
        meetingMinutes.setMeetingDate(vo.getDateTimeVo().getMeetingDate());
        meetingMinutes.setStartTime(vo.getDateTimeVo().getStartTime());
        meetingMinutes.setEndTime(vo.getDateTimeVo().getEndTime());
        return meetingMinutes;

    }
}
