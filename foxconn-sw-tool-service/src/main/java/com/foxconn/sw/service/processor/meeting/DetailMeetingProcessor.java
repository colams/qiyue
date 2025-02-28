package com.foxconn.sw.service.processor.meeting;

import com.foxconn.sw.business.SwAppendResourceBusiness;
import com.foxconn.sw.data.context.RequestContext;
import com.foxconn.sw.business.meeting.MeetingBusiness;
import com.foxconn.sw.business.meeting.MeetingCycleDetailBusiness;
import com.foxconn.sw.business.meeting.MeetingMemberBusiness;
import com.foxconn.sw.business.system.EmployeeBusiness;
import com.foxconn.sw.common.utils.ConvertUtils;
import com.foxconn.sw.common.utils.JsonUtils;
import com.foxconn.sw.data.constants.enums.MeetingRoleFlagEnums;
import com.foxconn.sw.data.constants.enums.retcode.RetCode;
import com.foxconn.sw.data.dto.communal.CycleMeetingVo;
import com.foxconn.sw.data.dto.entity.ResourceVo;
import com.foxconn.sw.data.dto.entity.acount.EmployeeVo;
import com.foxconn.sw.data.dto.entity.meeting.MeetingVo;
import com.foxconn.sw.data.dto.request.meeting.DetailMeetingParams;
import com.foxconn.sw.data.entity.SwAppendResource;
import com.foxconn.sw.data.entity.SwMeeting;
import com.foxconn.sw.data.entity.SwMeetingCycleDetail;
import com.foxconn.sw.data.entity.SwMeetingMember;
import com.foxconn.sw.data.exception.BizException;
import com.foxconn.sw.service.processor.MeetingRoomConfig;
import com.google.common.collect.Lists;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
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
    @Autowired
    SwAppendResourceBusiness appendResourceBusiness;

    public MeetingVo detail(DetailMeetingParams data) {
        SwMeeting meeting = meetingBusiness.getMeetingByID(data.getMeetingID());
        if (Objects.isNull(meeting)) {
            throw new BizException(RetCode.VALIDATE_FAILED);
        }

        List<SwMeetingMember> meetingMembers = meetingMemberBusiness.queryMeetingMemberByMeetingID(data.getMeetingID());
        List<SwMeetingCycleDetail> meetingCycleDetails = meetingCycleDetailBusiness.queryCycleDetail(data.getMeetingID());

        return processMeetingVo(meeting, data.getSearchDate(), meetingMembers, meetingCycleDetails);
    }

    private MeetingVo processMeetingVo(SwMeeting meeting,
                                       String meetingDate,
                                       List<SwMeetingMember> allMembers,
                                       List<SwMeetingCycleDetail> cycleDetails) {

        SwMeetingCycleDetail detail = cycleDetails.stream()
                .filter(e -> e.getMeetingDate().equalsIgnoreCase(meetingDate))
                .findFirst()
                .orElse(new SwMeetingCycleDetail());

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
        vo.setRoom(Optional.ofNullable(detail.getRoom()).orElse(meeting.getRoom()));
        vo.setRoomName(MeetingRoomConfig.getText(vo.getRoom()));
        vo.setMeetingType(getMeetingType(meeting, allMembers));
        vo.setTitle(Optional.ofNullable(detail.getTitle()).orElse(meeting.getTitle()));
        vo.setDescription(Optional.ofNullable(detail.getDescription()).orElse(meeting.getDescription()));
        vo.setMeetingDate(meetingDate);
        vo.setStartTime(Optional.ofNullable(detail.getStartTime()).orElse(meeting.getStartTime()));
        vo.setEndTime(Optional.ofNullable(detail.getEndTime()).orElse(meeting.getEndTime()));
        vo.setDuration(getMeetingDuration(vo.getStartTime(), vo.getEndTime()));
        vo.setWebexUrl(meeting.getWebexUrl());

        if (StringUtils.isNotEmpty(meeting.getCycle())) {
            CycleMeetingVo cycleMeetingVo = new CycleMeetingVo();
            cycleMeetingVo.setCycle(JsonUtils.deserialize(meeting.getCycle(), List.class, Integer.class));
            cycleMeetingVo.setCycleStart(meeting.getCycleStart());
            cycleMeetingVo.setCycleExpire(meeting.getCycleExpire());
            vo.setCycleVo(cycleMeetingVo);
        }

        vo.setChairman(chairman);
        vo.setMaintainers(maintainers);
        vo.setMembers(members);
        if (StringUtils.isNotEmpty(meeting.getResourceIds()) || StringUtils.isNotEmpty(detail.getResourceIds())) {

            List<Integer> resourceIDs = Lists.newArrayList();
            if (StringUtils.isNotEmpty(meeting.getResourceIds())) {
                resourceIDs.addAll(JsonUtils.deserialize(meeting.getResourceIds(), List.class, Integer.class));
            }
            if (StringUtils.isNotEmpty(detail.getResourceIds())) {
                resourceIDs.addAll(JsonUtils.deserialize(detail.getResourceIds(), List.class, Integer.class));
            }

            if (!CollectionUtils.isEmpty(resourceIDs)) {
                List<SwAppendResource> resources = appendResourceBusiness.getAppendResources(resourceIDs);
                List<ResourceVo> resourceVos = new ArrayList<>();
                Optional.ofNullable(resources).orElse(Lists.newArrayList()).forEach(e -> {
                    ResourceVo resourceVo = new ResourceVo();
                    resourceVo.setId(e.getId());
                    resourceVo.setName(e.getOriginName());
                    resourceVo.setUrl(ConvertUtils.urlPreFix(e.getId(), e.getFilePath()));
                    resourceVos.add(resourceVo);
                });
                vo.setResource(resourceVos);
            }
        }
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
}
