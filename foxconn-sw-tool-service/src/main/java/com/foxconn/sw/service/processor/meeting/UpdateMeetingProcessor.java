package com.foxconn.sw.service.processor.meeting;

import com.foxconn.sw.business.context.RequestContext;
import com.foxconn.sw.business.meeting.MeetingBusiness;
import com.foxconn.sw.business.meeting.MeetingCycleDetailBusiness;
import com.foxconn.sw.business.meeting.MeetingMemberBusiness;
import com.foxconn.sw.business.meeting.utils.CycleUtils;
import com.foxconn.sw.common.utils.JsonUtils;
import com.foxconn.sw.data.dto.request.meeting.UpdateMeetingParams;
import com.foxconn.sw.data.entity.SwMeeting;
import com.foxconn.sw.data.entity.SwMeetingCycleDetail;
import com.foxconn.sw.service.processor.meeting.utils.MeetingMemberUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.Map;
import java.util.Objects;

@Component
public class UpdateMeetingProcessor {

    @Autowired
    MeetingBusiness meetingBusiness;
    @Autowired
    MeetingCycleDetailBusiness meetingCycleDetailBusiness;
    @Autowired
    MeetingMemberBusiness meetingMemberBusiness;

    public Boolean update(UpdateMeetingParams data) {
        SwMeeting meeting = meetingBusiness.getMeetingByID(data.getMeetingID());
        if (((Objects.nonNull(meeting.getIsRepeat())
                && meeting.getIsRepeat() == 1)
                || StringUtils.isNotEmpty(meeting.getCycle()))
                && Objects.nonNull(data.getOperateType())
                && data.getOperateType() == 1) {
            processCycle(meeting, data);
        } else {
            processMeeting(meeting, data);
            processMembers(data);
        }
        return true;
    }

    private boolean processMeeting(SwMeeting meeting, UpdateMeetingParams data) {
        SwMeeting updateMeeting = new SwMeeting();
        updateMeeting.setId(meeting.getId());
        updateMeeting.setRoom(data.getRoom());
        updateMeeting.setAbcMeeting(data.getAbcMeeting());
        updateMeeting.setTitle(data.getTitle());
        updateMeeting.setDescription(data.getDescription());
        updateMeeting.setMeetingDate(data.getTimeVo().getMeetingDate());
        updateMeeting.setStartTime(data.getTimeVo().getStartTime());
        updateMeeting.setEndTime(data.getTimeVo().getEndTime());
        updateMeeting.setResourceIds(JsonUtils.serialize(data.getResourceIds()));
        CycleUtils.processCycle(updateMeeting, data.getCycleVo());
        return meetingBusiness.updateMeetingDetail(updateMeeting);
    }

    private boolean processCycle(SwMeeting meeting, UpdateMeetingParams data) {
        List<SwMeetingCycleDetail> cycles = meetingCycleDetailBusiness.queryCycleDetailWithDate(meeting.getId(),
                data.getTimeVo().getMeetingDate());
        SwMeetingCycleDetail detail = cycles.stream().findFirst().orElse(new SwMeetingCycleDetail());
        detail.setMeetingId(meeting.getId());
        detail.setRoom(data.getRoom());
        detail.setTitle(data.getTitle());
        detail.setDescription(data.getDescription());
        if (CollectionUtils.isEmpty(data.getResourceIds())) {
            detail.setResourceIds(JsonUtils.serialize(data.getResourceIds()));
        }
        detail.setOperator(RequestContext.getEmployeeNo());
        detail.setMeetingDate(data.getTimeVo().getMeetingDate());
        detail.setStartTime(data.getTimeVo().getStartTime());
        detail.setEndTime(data.getTimeVo().getEndTime());
        detail.setCancel(0);
        meetingCycleDetailBusiness.updateCycle(detail);
        return false;
    }

    private boolean processMembers(UpdateMeetingParams data) {
        Map<String, Integer> map = MeetingMemberUtils.processMemberRole2(data.getMemberVo());
        meetingMemberBusiness.updateMeetingMember(data.getMeetingID(), map);
        return true;
    }
}
