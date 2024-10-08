package com.foxconn.sw.service.processor.meeting;

import com.foxconn.sw.business.meeting.MeetingBusiness;
import com.foxconn.sw.business.meeting.MeetingMemberBusiness;
import com.foxconn.sw.data.dto.request.meeting.EstablishMeetingParams;
import com.foxconn.sw.service.processor.meeting.utils.MeetingMemberUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.util.Map;

@Component
public class EstablishProcessor {

    @Autowired
    MeetingBusiness meetingBusiness;
    @Autowired
    MeetingMemberBusiness meetingMemberBusiness;


    public boolean create(EstablishMeetingParams data) {
        if (StringUtils.isNotEmpty(data.getTimeVo().getMeetingDate())
                && !CollectionUtils.isEmpty(data.getCycleVo().getCycle())) {
            int cycleMeetingID = meetingBusiness.createMeeting(data, 1);
            Map<String, Integer> map = MeetingMemberUtils.processMemberRole(data.getMemberVo());
            meetingMemberBusiness.updateMeetingMember(cycleMeetingID, map);

            int meetingID = meetingBusiness.createMeeting(data );
            Map<String, Integer> map1 = MeetingMemberUtils.processMemberRole(data.getMemberVo());
            meetingMemberBusiness.updateMeetingMember(meetingID, map1);
        } else {
            int meetingID = meetingBusiness.createMeeting(data);
            Map<String, Integer> map = MeetingMemberUtils.processMemberRole(data.getMemberVo());
            meetingMemberBusiness.updateMeetingMember(meetingID, map);
        }
        return true;
    }


}
