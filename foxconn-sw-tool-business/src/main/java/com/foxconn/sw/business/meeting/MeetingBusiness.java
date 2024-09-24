package com.foxconn.sw.business.meeting;

import com.foxconn.sw.business.context.RequestContext;
import com.foxconn.sw.common.utils.ConvertUtils;
import com.foxconn.sw.data.dto.request.meeting.EstablishMeetingParams;
import com.foxconn.sw.data.entity.SwMeeting;
import com.foxconn.sw.data.mapper.extension.meeting.SwMeetingExtensionMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.List;

@Component
public class MeetingBusiness {

    @Autowired
    SwMeetingExtensionMapper meetingMapper;

    public Integer createMeeting(EstablishMeetingParams data) {
        SwMeeting meeting = new SwMeeting();
        meeting.setRoom(data.getRoom());
        meeting.setCreator(RequestContext.getEmployeeNo());
        meeting.setStartTime(data.getTimeVo().getStartTime());
        meeting.setEndTime(data.getTimeVo().getEndTime());
        meeting.setMeetingDate(data.getTimeVo().getMeetingDate());
        meeting.setTitle(data.getTitle());
        meeting.setDescription(data.getDescription());
        meeting.setResourceIds(ConvertUtils.listIntegerToString(data.getResourceIds()));
        meeting.setRepeat(data.getRepeat());
        meeting.setCycle(ConvertUtils.listIntegerToString(data.getCycleInts()));
        meetingMapper.insertSelective(meeting);
        return meeting.getId();
    }

    public List<SwMeeting> queryMeeting(LocalDate startDate, LocalDate endDate, String employeeNo) {
        List<SwMeeting> meetings = meetingMapper.selectMeetings(startDate, endDate, employeeNo);
        return meetings;
    }

    public SwMeeting getMeetingByID(Integer meetingID) {
        return meetingMapper.selectByPrimaryKey(meetingID);
    }

    public boolean updateMeetingStatus(SwMeeting meeting) {
        SwMeeting updateMeeting = new SwMeeting();
        updateMeeting.setId(meeting.getId());
        updateMeeting.setStatus(2);
        return meetingMapper.updateByPrimaryKeySelective(updateMeeting) > 1;
    }

    public Boolean updateMeetingDetail(SwMeeting meeting) {
        return meetingMapper.updateByPrimaryKeySelective(meeting) > 1;
    }
}
