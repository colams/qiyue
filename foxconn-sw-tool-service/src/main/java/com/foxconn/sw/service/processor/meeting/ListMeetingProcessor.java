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

    public List[] list(ListMeetingParams params) {
        LocalDate startDate = LocalDateExtUtils.toLocalDate(params.getSearchDate());
        LocalDate endDate = LocalDateExtUtils.toLocalDate(params.getSearchDate()).plusDays(7);

        List<SwMeeting> meetings = queryMeeting(startDate, endDate);
        if (CollectionUtils.isEmpty(meetings)) {
            return new List[0];
        }
        List<Integer> meetingIDs = meetings.stream().map(e -> e.getId()).collect(Collectors.toList());

        Map<Integer, List<SwMeetingMember>> meetingMembers = queryMeetingMember(meetingIDs);
        Map<Integer, List<SwMeetingCycleDetail>> meetingCycleDetails = queryCycleDetail(meetingIDs);

        List<MeetingVo> lists = processMeetingVo(meetings, meetingMembers, meetingCycleDetails);

        List<MeetingVo>[] result = new List[7];
        int index = 0;
        while (startDate.compareTo(endDate) < 0) {
            String date = StringExtUtils.toString(startDate);
            List<MeetingVo> array = lists.stream()
                    .filter(e -> e.getMeetingDate().equalsIgnoreCase(date))
                    .collect(Collectors.toList());
            startDate = startDate.plusDays(1);
            result[index++] = array;
        }
        return result;
    }

    private List<MeetingVo> processMeetingVo(List<SwMeeting> meetings,
                                             Map<Integer, List<SwMeetingMember>> meetingMemberMap,
                                             Map<Integer, List<SwMeetingCycleDetail>> meetingCycleDetailMap) {
        List<MeetingVo> vos = new ArrayList<>();
        meetings.forEach(e -> {
            List<SwMeetingMember> members = meetingMemberMap.getOrDefault(e.getId(), Lists.newArrayList());
            List<SwMeetingCycleDetail> cycleDetails = meetingCycleDetailMap.getOrDefault(e.getId(), Lists.newArrayList());
            MeetingVo vo = convert(e, members, cycleDetails);
            vos.add(vo);
        });
        return vos;
    }

    private MeetingVo convert(SwMeeting meeting,
                              List<SwMeetingMember> allMembers,
                              List<SwMeetingCycleDetail> cycleDetails) {

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
        vo.setMeetingType(getMeetingType(meeting, allMembers));
        vo.setTitle(meeting.getTitle());
        vo.setDescription(meeting.getDescription());
        vo.setMeetingDate(meeting.getMeetingDate());
        vo.setStartTime(meeting.getStartTime());
        vo.setEndTime(meeting.getEndTime());
        vo.setDuration(getMeetingDuration(meeting.getStartTime(), meeting.getEndTime()));
        if (StringUtils.isNotEmpty(meeting.getCycle())) {
            CycleMeetingVo cycleMeetingVo = new CycleMeetingVo();
            cycleMeetingVo.setCycle(JsonUtils.deserialize(meeting.getCycle(), List.class, Integer.class));
            cycleMeetingVo.setCycleStart(meeting.getCycleStart());
            cycleMeetingVo.setCycleExpire(meeting.getCycleExpire());
        }
        vo.setChairman(chairman);
        vo.setMaintainers(maintainers);
        vo.setMembers(members);
        return vo;
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
        if (MeetingRoleFlagEnums.Chairman_Flag.test(member.getRole())) {
            return 1;
        }

        if (meeting.getAbcMeeting() == 1) {
            return 2;
        }
        return 3;
    }


    private List<SwMeeting> queryMeeting(LocalDate startDate, LocalDate endDate) {
        String employeeNo = RequestContext.getEmployeeNo();
        List<SwMeeting> meetings = meetingBusiness.queryMeeting(startDate, endDate, employeeNo);
        return meetings;
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
