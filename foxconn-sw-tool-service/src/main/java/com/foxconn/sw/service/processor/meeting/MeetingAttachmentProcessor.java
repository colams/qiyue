package com.foxconn.sw.service.processor.meeting;

import com.foxconn.sw.business.SwAppendResourceBusiness;
import com.foxconn.sw.business.meeting.MeetingBusiness;
import com.foxconn.sw.business.meeting.MeetingCycleDetailBusiness;
import com.foxconn.sw.business.meeting.MeetingMemberBusiness;
import com.foxconn.sw.data.constants.enums.MeetingRoleFlagEnums;
import com.foxconn.sw.data.context.RequestContext;
import com.foxconn.sw.data.dto.entity.ResourceVo;
import com.foxconn.sw.data.dto.request.meeting.MeetingAttachmentParams;
import com.foxconn.sw.data.dto.response.meeting.MeetingAttachmentVo;
import com.foxconn.sw.data.entity.SwMeeting;
import com.foxconn.sw.data.entity.SwMeetingCycleDetail;
import com.foxconn.sw.data.entity.SwMeetingMember;
import com.foxconn.sw.service.processor.resource.GetAppendResourcesProcessor;
import com.google.common.collect.Lists;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Component
public class MeetingAttachmentProcessor {

    @Autowired
    MeetingBusiness meetingBusiness;
    @Autowired
    MeetingCycleDetailBusiness meetingCycleDetailBusiness;
    @Autowired
    SwAppendResourceBusiness appendResourceBusiness;
    @Autowired
    MeetingMemberBusiness meetingMemberBusiness;
    @Autowired
    GetAppendResourcesProcessor getAppendResourcesProcessor;

    public List<MeetingAttachmentVo> attachments(MeetingAttachmentParams data) {

        SwMeeting swMeeting = meetingBusiness.getMeetingByID(data.getMeetingID());
        SwMeetingCycleDetail meetingCycleDetails =
                meetingCycleDetailBusiness.queryCycleDetailWithDateNew(data.getMeetingID(), data.getMeetingDate());

        List<SwMeetingMember> originMember = meetingMemberBusiness.queryMeetingMemberByMeetingID(meetingCycleDetails.getMeetingId());

        List<MeetingAttachmentVo> attachmentVos;
        if (Objects.isNull(meetingCycleDetails) || StringUtils.isEmpty(meetingCycleDetails.getResourceIds())) {
            attachmentVos = singleMeeting(swMeeting);
        } else {
            List<SwMeetingMember> newMembers = meetingMemberBusiness.queryMeetingMemberByDetailId(meetingCycleDetails.getMeetingId());
            List<SwMeetingMember> temMembers = CollectionUtils.isEmpty(newMembers) ? originMember : newMembers;
            attachmentVos = cycleMeeting(meetingCycleDetails, temMembers);
        }
        return attachmentVos;
    }

    private List<MeetingAttachmentVo> cycleMeeting(SwMeetingCycleDetail meetingCycleDetails, List<SwMeetingMember> temMembers) {
        List<ResourceVo> resourceVos = getAppendResourcesProcessor.getAppendResourcesVo(meetingCycleDetails.getResourceIds());
        List<MeetingAttachmentVo> attachmentVos = new ArrayList<>();
        String employeeNo = RequestContext.getEmployeeNo();
        boolean isAdmin = temMembers.stream()
                .filter(e -> e.getEmployeeNo().equalsIgnoreCase(employeeNo))
                .anyMatch(e -> MeetingRoleFlagEnums.Chairman_Flag.test(e.getRole()) || MeetingRoleFlagEnums.Maintainer_Flag.test(e.getRole()));

        Optional.ofNullable(resourceVos).orElse(Lists.newArrayList())
                .forEach(e -> {
                    MeetingAttachmentVo attachmentVo = new MeetingAttachmentVo();
                    attachmentVo.setMeetingId(meetingCycleDetails.getMeetingId());
                    attachmentVo.setMeetingDetailId(meetingCycleDetails.getId());
                    attachmentVo.setCreateTime(e.getCreateTime());
                    attachmentVo.setCanDelete(isAdmin || employeeNo.equalsIgnoreCase(e.getOperator().getEmployeeNo()));
                    attachmentVo.setId(e.getId());
                    attachmentVo.setName(e.getName());
                    attachmentVo.setUrl(e.getUrl());
                    attachmentVo.setViewUrl(e.getViewUrl());
                    attachmentVo.setOperator(e.getOperator());
                    attachmentVos.add(attachmentVo);
                });
        return attachmentVos;
    }

    private List<MeetingAttachmentVo> singleMeeting(SwMeeting swMeeting) {
        List<MeetingAttachmentVo> attachmentVos = new ArrayList<>();
        return attachmentVos;
    }
}
