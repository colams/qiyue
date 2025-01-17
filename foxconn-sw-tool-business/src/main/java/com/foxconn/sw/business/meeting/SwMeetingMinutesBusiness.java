package com.foxconn.sw.business.meeting;

import com.foxconn.sw.data.entity.SwMeetingMinute;
import com.foxconn.sw.data.entity.SwMeetingMinuteExample;
import com.foxconn.sw.data.mapper.extension.meeting.SwMeetingMinuteExtensionMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.Objects;

@Component
public class SwMeetingMinutesBusiness {

    @Autowired
    SwMeetingMinuteExtensionMapper meetingMinutesExtensionMapper;

    public List<SwMeetingMinute> queryMeetingMinuteByMeetingId(Integer meetingID) {
        SwMeetingMinuteExample example = new SwMeetingMinuteExample();
        SwMeetingMinuteExample.Criteria criteria = example.createCriteria();
        criteria.andMeetingIdEqualTo(meetingID.intValue());
        List<SwMeetingMinute> minutesList = meetingMinutesExtensionMapper.selectByExample(example);
        return minutesList;
    }

    public SwMeetingMinute queryMeetingMinute(Integer meetingID, String meetingDate) {
        SwMeetingMinuteExample example = new SwMeetingMinuteExample();
        SwMeetingMinuteExample.Criteria criteria = example.createCriteria();
        criteria.andMeetingDateEqualTo(meetingDate);
        criteria.andMeetingIdEqualTo(meetingID);
        List<SwMeetingMinute> minutesList = meetingMinutesExtensionMapper.selectByExample(example);
        if (CollectionUtils.isEmpty(minutesList)) {
            return null;
        }
        return minutesList.get(0);
    }


    public Long insertOrUpdate(SwMeetingMinute meetingMinutes) {

        if (Objects.nonNull(meetingMinutes.getId()) && meetingMinutes.getId() > 0) {
            int effectCount = meetingMinutesExtensionMapper.updateByPrimaryKeySelective(meetingMinutes);
            return effectCount > 0 ? meetingMinutes.getId() : 0;
        } else {
            meetingMinutesExtensionMapper.insertSelective(meetingMinutes);
            return meetingMinutes.getId();
        }
    }
}
