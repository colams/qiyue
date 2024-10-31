package com.foxconn.sw.service.processor.meeting;

import com.foxconn.sw.business.context.RequestContext;
import com.foxconn.sw.business.meeting.MeetingBusiness;
import com.foxconn.sw.business.meeting.MeetingCycleDetailBusiness;
import com.foxconn.sw.business.meeting.MeetingMemberBusiness;
import com.foxconn.sw.business.system.EmployeeBusiness;
import com.foxconn.sw.common.utils.JsonUtils;
import com.foxconn.sw.common.utils.LocalDateExtUtils;
import com.foxconn.sw.common.utils.StringExtUtils;
import com.foxconn.sw.data.constants.enums.MeetingRoleFlagEnums;
import com.foxconn.sw.data.dto.communal.CycleMeetingVo;
import com.foxconn.sw.data.dto.entity.acount.EmployeeVo;
import com.foxconn.sw.data.dto.entity.meeting.MeetingVo;
import com.foxconn.sw.data.dto.request.meeting.ListMeetingParams;
import com.foxconn.sw.data.entity.SwMeeting;
import com.foxconn.sw.data.entity.SwMeetingCycleDetail;
import com.foxconn.sw.data.entity.SwMeetingMember;
import com.foxconn.sw.service.processor.MeetingRoomConfig;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

@Component
public class ListMeetingProcessor {

    @Autowired
    MeetingBusiness meetingBusiness;
    @Autowired
    MeetingMemberBusiness meetingMemberBusiness;
    @Autowired
    MeetingCycleDetailBusiness meetingCycleDetailBusiness;
    @Autowired
    EmployeeBusiness employeeBusiness;

    public List<MeetingVo>[] list(ListMeetingParams params) {
        LocalDate startDate = LocalDateExtUtils.toLocalDate(params.getSearchDate());
        LocalDate endDate = LocalDateExtUtils.toLocalDate(params.getSearchDate()).plusDays(7);

        List<SwMeeting> meetings = meetingBusiness.queryMeeting(startDate, endDate);
        if (CollectionUtils.isEmpty(meetings)) {
            return new List[0];
        }
        List<Integer> meetingIDs = meetings.stream().map(e -> e.getId()).collect(Collectors.toList());

        Map<Integer, List<SwMeetingMember>> meetingMembers = queryMeetingMember(meetingIDs);
        Map<Integer, List<SwMeetingCycleDetail>> meetingCycleDetails = queryCycleDetail(meetingIDs);

        List<MeetingVo>[] result = new List[7];
        int index = 0;
        while (startDate.compareTo(endDate) < 0) {
            List<MeetingVo> array = processMeetingVo2(meetings, meetingMembers, meetingCycleDetails, startDate);
            startDate = startDate.plusDays(1);
            result[index++] = array;
        }
        return result;
    }

    private List<MeetingVo> processMeetingVo2(List<SwMeeting> meetings,
                                              Map<Integer, List<SwMeetingMember>> meetingMemberMap,
                                              Map<Integer, List<SwMeetingCycleDetail>> meetingCycleDetailMap,
                                              LocalDate currentDate) {
        List<MeetingVo> vos = new ArrayList<>();
        meetings.forEach(e -> {
            if (currentMeeting(e, currentDate)) {
                List<SwMeetingMember> members = meetingMemberMap.getOrDefault(e.getId(), Lists.newArrayList());
                List<SwMeetingCycleDetail> cycleDetails = meetingCycleDetailMap.getOrDefault(e.getId(), Lists.newArrayList());
                MeetingVo vo = convert2(e, currentDate, members, cycleDetails);
                if (Objects.nonNull(vo)) {
                    vos.add(vo);
                }
            }
        });
        return vos;
    }

    private MeetingVo convert2(SwMeeting meeting,
                               LocalDate currentDate,
                               List<SwMeetingMember> allMembers,
                               List<SwMeetingCycleDetail> cycleDetails) {
        String date = StringExtUtils.toString(currentDate);

        SwMeetingCycleDetail detail = cycleDetails.stream()
                .filter(e -> e.getMeetingDate().equalsIgnoreCase(date))
                .findFirst()
                .orElse(null);

        if (Objects.nonNull(detail) && detail.getCancel() == 1) {
            return null;
        }

        EmployeeVo chairman = allMembers.stream()
                .filter(e -> MeetingRoleFlagEnums.Chairman_Flag.test(e.getRole()))
                .map(e -> toEmployee(e.getEmployeeNo()))
                .findFirst()
                .orElse(new EmployeeVo());

        List<EmployeeVo> maintainers = allMembers.stream()
                .filter(e -> MeetingRoleFlagEnums.Maintainer_Flag.test(e.getRole()))
                .map(e -> toEmployee(e.getEmployeeNo()))
                .collect(Collectors.toList());

        List<EmployeeVo> members = allMembers.stream()
                .filter(e -> MeetingRoleFlagEnums.Member_Flag.test(e.getRole()))
                .map(e -> toEmployee(e.getEmployeeNo()))
                .collect(Collectors.toList());

        MeetingVo vo = new MeetingVo();
        vo.setMeetingID(meeting.getId());
        vo.setRoom(meeting.getRoom());
        vo.setRoomName(MeetingRoomConfig.getText(meeting.getRoom()));
        vo.setMeetingType(getMeetingType(meeting, allMembers));
        vo.setTitle(meeting.getTitle());
        vo.setDescription(meeting.getDescription());


        if (Objects.nonNull(detail)) {
            vo.setStartTime(StringUtils.isNotEmpty(detail.getStartTime()) ? detail.getStartTime() : meeting.getStartTime());
            vo.setEndTime(StringUtils.isNotEmpty(detail.getEndTime()) ? detail.getEndTime() : meeting.getEndTime());
        } else {
            vo.setStartTime(meeting.getStartTime());
            vo.setEndTime(meeting.getEndTime());
        }

        vo.setDuration(getMeetingDuration(meeting.getStartTime(), meeting.getEndTime()));


        vo.setMeetingDate(meeting.getMeetingDate());
        if (StringUtils.isNotEmpty(meeting.getCycle())) {
            vo.setMeetingDate(date);
            CycleMeetingVo cycleMeetingVo = new CycleMeetingVo();
            cycleMeetingVo.setCycle(JsonUtils.deserialize(meeting.getCycle(), List.class, Integer.class));
            if (StringUtils.isEmpty(meeting.getCycleStart())) {
                cycleMeetingVo.setCycleStart(StringExtUtils.toString(meeting.getCreateTime()));
            }
            cycleMeetingVo.setCycleExpire(meeting.getCycleExpire());
            vo.setCycleVo(cycleMeetingVo);
        }
        vo.setChairman(chairman);
        vo.setMaintainers(maintainers);
        vo.setMembers(members);
        vo.setWebexUrl(meeting.getWebexUrl());
        vo.setWeek(LocalDate.parse(vo.getMeetingDate()).getDayOfWeek().getValue());
        return vo;
    }

    private boolean currentMeeting(SwMeeting meeting, LocalDate currentDate) {
        String date = StringExtUtils.toString(currentDate);
        int weekOfDay = currentDate.getDayOfWeek().getValue();
        if (StringUtils.isNotEmpty(meeting.getMeetingDate())
                && meeting.getMeetingDate().equalsIgnoreCase(date)) {
            return true;
        }

        if (StringUtils.isNotEmpty(meeting.getCycle())) {
            List<Integer> cycleInts = JsonUtils.deserialize(meeting.getCycle(),
                    List.class, Integer.class);

            if (!cycleInts.contains(weekOfDay)) {
                return false;
            }

            if (meeting.getCycleStart().compareTo(date) > 0) {
                return false;
            }

            if (StringUtils.isEmpty(meeting.getCycleExpire())
                    || meeting.getCycleExpire().compareTo(date) > 0) {
                return true;
            }

        }
        return false;
    }

    private Integer getMeetingDuration(String startTime, String endTime) {
        LocalTime localTime_S = LocalTime.parse(startTime, DateTimeFormatter.ofPattern("HH:mm"));
        LocalTime localTime_E = LocalTime.parse(endTime, DateTimeFormatter.ofPattern("HH:mm"));
        long secondsDiff = java.time.Duration.between(localTime_S, localTime_E).getSeconds();
        return (int) (secondsDiff / 60);
    }

    private EmployeeVo toEmployee(String employeeNo) {
        EmployeeVo vo = new EmployeeVo();
        vo.setEmployeeNo(employeeNo);
        vo.setName(employeeBusiness.selectEmployeeByENo(employeeNo).getName());
        return vo;
    }

    private Integer getMeetingType(SwMeeting meeting, List<SwMeetingMember> allMembers) {
        SwMeetingMember member = allMembers.stream()
                .filter(e -> e.getEmployeeNo().equalsIgnoreCase(RequestContext.getEmployeeNo()))
                .findFirst()
                .orElse(null);
        if (MeetingRoleFlagEnums.Chairman_Flag.test(member.getRole())
                || MeetingRoleFlagEnums.Maintainer_Flag.test(member.getRole())
                || MeetingRoleFlagEnums.Creator_Flag.test(member.getRole())) {
            return 1;
        }

        if (meeting.getAbcMeeting() == 1) {
            return 2;
        }
        return 3;
    }


    private Map<Integer, List<SwMeetingMember>> queryMeetingMember(List<Integer> meetingIDs) {
        List<SwMeetingMember> members = meetingMemberBusiness.queryMeetingMember(meetingIDs);
        if (CollectionUtils.isEmpty(members)) {
            return Maps.newHashMap();
        }

        Map<Integer, List<SwMeetingMember>> map = members.stream()
                .collect(Collectors.groupingBy(SwMeetingMember::getMeetingId));
        return map;
    }

    private Map<Integer, List<SwMeetingCycleDetail>> queryCycleDetail(List<Integer> meetingIDs) {
        List<SwMeetingCycleDetail> meetingCycleDetails = meetingCycleDetailBusiness.queryCycleDetail(meetingIDs);
        if (CollectionUtils.isEmpty(meetingCycleDetails)) {
            return Maps.newHashMap();
        }

        Map<Integer, List<SwMeetingCycleDetail>> map = meetingCycleDetails.stream()
                .collect(Collectors.groupingBy(SwMeetingCycleDetail::getMeetingId));
        return map;
    }
}
