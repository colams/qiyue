package com.foxconn.sw.business.meeting;

import com.foxconn.sw.business.context.RequestContext;
import com.foxconn.sw.common.utils.JsonUtils;
import com.foxconn.sw.data.dto.request.meeting.EstablishMeetingParams;
import com.foxconn.sw.data.entity.SwMeeting;
import com.foxconn.sw.data.mapper.extension.meeting.SwMeetingExtensionMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

@Component
public class MeetingBusiness {

    @Autowired
    SwMeetingExtensionMapper meetingMapper;

    public Integer createMeeting(EstablishMeetingParams data) {
        SwMeeting meeting = new SwMeeting();
        meeting.setRoom(data.getRoom());
        meeting.setStartTime(data.getTimeVo().getStartTime());
        meeting.setEndTime(data.getTimeVo().getEndTime());
        meeting.setMeetingDate(data.getTimeVo().getMeetingDate());
        meeting.setTitle(data.getTitle());
        meeting.setDescription(data.getDescription());
        if (!CollectionUtils.isEmpty(data.getResourceIds())) {
            meeting.setResourceIds(JsonUtils.serialize(data.getResourceIds()));
        }

        if (Objects.nonNull(data.getCycleVo()) && Objects.nonNull(data.getCycleVo().getCycle())) {
            meeting.setCycle(JsonUtils.serialize(data.getCycleVo().getCycle()));
            meeting.setCycleExpire(data.getCycleVo().getCycleExpire());
            meeting.setCycleStart(data.getCycleVo().getCycleStart());
            meeting.setIsRepeat(1);
        }
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
