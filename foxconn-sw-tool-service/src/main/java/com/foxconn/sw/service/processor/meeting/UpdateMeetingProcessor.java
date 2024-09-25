package com.foxconn.sw.service.processor.meeting;

import com.foxconn.sw.business.context.RequestContext;
import com.foxconn.sw.business.meeting.MeetingBusiness;
import com.foxconn.sw.business.meeting.MeetingCycleDetailBusiness;
import com.foxconn.sw.business.meeting.MeetingMemberBusiness;
import com.foxconn.sw.common.utils.ConvertUtils;
import com.foxconn.sw.data.dto.request.meeting.UpdateMeetingParams;
import com.foxconn.sw.data.entity.SwMeeting;
import com.foxconn.sw.data.entity.SwMeetingCycleDetail;
import com.foxconn.sw.service.processor.meeting.utils.MeetingMemberUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

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
        if (meeting.getRepeat() == 1) {
            processCycle(meeting, data);
        } else {
            processMeeting(meeting, data);
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
        updateMeeting.setResourceIds(ConvertUtils.listIntegerToString(data.getResourceIds()));
        updateMeeting.setRepeat(data.getRepeat());
        updateMeeting.setCycle(ConvertUtils.listIntegerToString(data.getResourceIds()));
        updateMeeting.setCreator(RequestContext.getEmployeeNo());
        return meetingBusiness.updateMeetingDetail(meeting);
    }

    private boolean processCycle(SwMeeting meeting, UpdateMeetingParams data) {

        List<SwMeetingCycleDetail> cycles = meetingCycleDetailBusiness.queryCycleDetail(meeting.getId());

//
//        SwMeetingCycleDetail cycleDetail = new SwMeetingCycleDetail();
//        cycleDetail.setMeetingId();
//        cycleDetail.setRoom();
//        cycleDetail.setMeetingDate();
//        cycleDetail.setStartTime();
//        cycleDetail.setEndTime();
//        cycleDetail.setCancel();
//        cycleDetail.setCreateTime();
//        cycleDetail.setDatetimeLastchange();

        return false;
    }

    private boolean processMembers(UpdateMeetingParams data) {
        Map<String, Integer> map = MeetingMemberUtils.processMemberRole(data.getMemberVo());
        meetingMemberBusiness.updateMeetingMember(data.getMeetingID(), map);
        return true;
    }
}
