package com.foxconn.sw.business.meeting;

import com.foxconn.sw.data.entity.SwMeeting;
import com.foxconn.sw.data.entity.SwMeetingCycleDetail;
import com.foxconn.sw.data.entity.SwMeetingCycleDetailExample;
import com.foxconn.sw.data.mapper.extension.meeting.SwMeetingCycleDetailExtensionMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class MeetingCycleDetailBusiness {

    @Autowired
    SwMeetingCycleDetailExtensionMapper meetingCycleDetailMapper;

    public Integer createMeeting() {
        SwMeetingCycleDetail meetingCycleDetail = new SwMeetingCycleDetail();
//        meetingCycleDetail.setId();
//        meetingCycleDetail.setMeetingId();
//        meetingCycleDetail.setRoom();
//        meetingCycleDetail.setMeetingDate();
//        meetingCycleDetail.setStartTime();
//        meetingCycleDetail.setEndTime();
//        meetingCycleDetail.setCancel();
//        meetingCycleDetail.setCreateTime();
//        meetingCycleDetail.setDatetimeLastchange();


        meetingCycleDetailMapper.insertSelective(meetingCycleDetail);
        return meetingCycleDetail.getId();
    }

    public List<SwMeetingCycleDetail> queryCycleDetail(List<Integer> meetingIDs) {
        SwMeetingCycleDetailExample example = new SwMeetingCycleDetailExample();
        SwMeetingCycleDetailExample.Criteria criteria = example.createCriteria();
        criteria.andMeetingIdIn(meetingIDs);
        return meetingCycleDetailMapper.selectByExample(example);
    }

    public List<SwMeetingCycleDetail> queryCycleDetail(Integer meetingID) {
        SwMeetingCycleDetailExample example = new SwMeetingCycleDetailExample();
        SwMeetingCycleDetailExample.Criteria criteria = example.createCriteria();
        criteria.andMeetingIdEqualTo(meetingID);
        criteria.andCancelEqualTo(0);
        return meetingCycleDetailMapper.selectByExample(example);
    }

    public boolean addCycleCancelDate(String deleteDate, SwMeeting meeting) {
        SwMeetingCycleDetail detail = new SwMeetingCycleDetail();
        detail.setMeetingId(meeting.getId());
        detail.setMeetingDate(deleteDate);
        detail.setCancel(1);
        return meetingCycleDetailMapper.insertSelective(detail) > 1;
    }
}
