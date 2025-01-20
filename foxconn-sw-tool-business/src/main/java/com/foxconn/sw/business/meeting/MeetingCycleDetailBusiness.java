package com.foxconn.sw.business.meeting;

import com.foxconn.sw.data.entity.SwMeeting;
import com.foxconn.sw.data.entity.SwMeetingCycleDetail;
import com.foxconn.sw.data.entity.SwMeetingCycleDetailExample;
import com.foxconn.sw.data.mapper.extension.meeting.SwMeetingCycleDetailExtensionMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Component
public class MeetingCycleDetailBusiness {

    @Autowired
    SwMeetingCycleDetailExtensionMapper meetingCycleDetailMapper;

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

    public List<SwMeetingCycleDetail> queryCycleDetailWithDate(Integer meetingID, String meetingDate) {
        SwMeetingCycleDetailExample example = new SwMeetingCycleDetailExample();
        SwMeetingCycleDetailExample.Criteria criteria = example.createCriteria();
        criteria.andMeetingIdEqualTo(meetingID);
        criteria.andCancelEqualTo(0);
        criteria.andMeetingDateEqualTo(meetingDate);
        return meetingCycleDetailMapper.selectByExample(example);
    }

    public Optional<SwMeetingCycleDetail> queryCycleDetailEntityWithDate(Integer meetingID, String meetingDate) {
        List<SwMeetingCycleDetail> detailList = queryCycleDetailWithDate(meetingID, meetingDate);
        return detailList.stream().findFirst();
    }


    public boolean addCycleCancelDate(String deleteDate, SwMeeting meeting) {
        SwMeetingCycleDetail detail = new SwMeetingCycleDetail();
        detail.setMeetingId(meeting.getId());
        detail.setMeetingDate(deleteDate);
        detail.setCancel(1);
        return meetingCycleDetailMapper.insertSelective(detail) > 1;
    }

    public boolean updateCycle(SwMeetingCycleDetail detail) {
        if (Objects.isNull(detail.getId())) {
            return meetingCycleDetailMapper.insertSelective(detail) > 0;
        }
        return meetingCycleDetailMapper.updateByPrimaryKeySelective(detail) > 0;
    }
}
