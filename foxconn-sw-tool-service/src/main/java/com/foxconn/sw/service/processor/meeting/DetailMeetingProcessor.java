package com.foxconn.sw.service.processor.meeting;

import com.foxconn.sw.business.context.RequestContext;
import com.foxconn.sw.business.meeting.MeetingBusiness;
import com.foxconn.sw.business.meeting.MeetingCycleDetailBusiness;
import com.foxconn.sw.business.meeting.MeetingMemberBusiness;
import com.foxconn.sw.business.system.EmployeeBusiness;
import com.foxconn.sw.common.utils.JsonUtils;
import com.foxconn.sw.data.constants.enums.MeetingRoleFlagEnums;
import com.foxconn.sw.data.constants.enums.retcode.RetCode;
import com.foxconn.sw.data.dto.communal.CycleMeetingVo;
import com.foxconn.sw.data.dto.entity.acount.EmployeeVo;
import com.foxconn.sw.data.dto.entity.meeting.MeetingVo;
import com.foxconn.sw.data.dto.request.meeting.DetailMeetingParams;
import com.foxconn.sw.data.entity.SwMeeting;
import com.foxconn.sw.data.entity.SwMeetingCycleDetail;
import com.foxconn.sw.data.entity.SwMeetingMember;
import com.foxconn.sw.data.exception.BizException;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Component
public class DetailMeetingProcessor {
    @Autowired
    MeetingBusiness meetingBusiness;
    @Autowired
    MeetingMemberBusiness meetingMemberBusiness;
    @Autowired
    MeetingCycleDetailBusiness meetingCycleDetailBusiness;
    @Autowired
    EmployeeBusiness employeeBusiness;

    public MeetingVo detail(DetailMeetingParams data) {
        SwMeeting meeting = meetingBusiness.getMeetingByID(data.getMeetingID());
        if (Objects.isNull(meeting)) {
            throw new BizException(RetCode.VALIDATE_FAILED);
        }

        List<SwMeetingMember> meetingMembers = meetingMemberBusiness.queryMeetingMember(data.getMeetingID());
        List<SwMeetingCycleDetail> meetingCycleDetails = meetingCycleDetailBusiness.queryCycleDetail(data.getMeetingID());

        return processMeetingVo(meeting, meetingMembers, meetingCycleDetails);
    }

    private MeetingVo processMeetingVo(SwMeeting meeting,
                                       List<SwMeetingMember> allMembers,
                                       List<SwMeetingCycleDetail> meetingCycleDetails) {
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
}
