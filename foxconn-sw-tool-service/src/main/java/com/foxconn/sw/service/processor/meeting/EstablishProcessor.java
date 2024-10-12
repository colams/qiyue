package com.foxconn.sw.service.processor.meeting;

import com.foxconn.sw.business.meeting.MeetingBusiness;
import com.foxconn.sw.business.meeting.MeetingMemberBusiness;
import com.foxconn.sw.data.dto.request.meeting.EstablishMeetingParams;
import com.foxconn.sw.service.processor.meeting.utils.MeetingMemberUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class EstablishProcessor {

    @Autowired
    MeetingBusiness meetingBusiness;
    @Autowired
    MeetingMemberBusiness meetingMemberBusiness;


    public boolean create(EstablishMeetingParams data) {
        int meetingID = meetingBusiness.createMeeting(data);
        Map<String, Integer> map = MeetingMemberUtils.processMemberRole(data.getMemberVo());
        meetingMemberBusiness.updateMeetingMember(meetingID, map);
        return true;
    }


}
