package com.foxconn.sw.service.processor.meeting;

import com.foxconn.sw.business.meeting.MeetingBusiness;
import com.foxconn.sw.business.meeting.MeetingCycleDetailBusiness;
import com.foxconn.sw.common.utils.JsonUtils;
import com.foxconn.sw.data.dto.request.meeting.UpdateMeetingAttachParams;
import com.foxconn.sw.data.entity.SwMeeting;
import com.foxconn.sw.data.entity.SwMeetingCycleDetail;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.util.Objects;

@Component
public class UpdateMeetingAttachProcessor {

    @Autowired
    MeetingBusiness meetingBusiness;
    @Autowired
    MeetingCycleDetailBusiness meetingCycleDetailBusiness;

    public boolean updateMeetingAttach(UpdateMeetingAttachParams data) {
        SwMeeting swMeeting = meetingBusiness.getMeetingByID(data.getMeetingID());
        if (Objects.isNull(swMeeting)) {
            return false;
        }
        if (StringUtils.isEmpty(swMeeting.getCycle())) {
            SwMeeting updateMeeting = new SwMeeting();
            updateMeeting.setId(swMeeting.getId());
            if (CollectionUtils.isEmpty(data.getResourceIds())) {
                updateMeeting.setResourceIds("");
            } else {
                updateMeeting.setResourceIds(JsonUtils.serialize(data.getResourceIds()));
            }
            return meetingBusiness.updateMeetingDetail(updateMeeting);
        } else {
            SwMeetingCycleDetail meetingCycleDetail = meetingCycleDetailBusiness.queryCycleDetailWithDateNew(data.getMeetingID(), data.getMeetingDate());
            SwMeetingCycleDetail updateDetail = new SwMeetingCycleDetail();
            updateDetail.setResourceIds(JsonUtils.serialize(data.getResourceIds()));
            if (Objects.isNull(meetingCycleDetail)) {
                updateDetail.setMeetingId(data.getMeetingID());
            } else {
                updateDetail.setId(meetingCycleDetail.getId());
            }
            return meetingCycleDetailBusiness.updateCycle(updateDetail);
        }
    }
}
