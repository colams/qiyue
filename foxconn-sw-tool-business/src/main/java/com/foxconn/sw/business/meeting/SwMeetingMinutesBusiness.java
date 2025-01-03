package com.foxconn.sw.business.meeting;

import com.foxconn.sw.data.entity.SwMeetingMinutes;
import com.foxconn.sw.data.entity.SwMeetingMinutesExample;
import com.foxconn.sw.data.mapper.extension.meeting.SwMeetingMinutesExtensionMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.util.List;

@Component
public class SwMeetingMinutesBusiness {

    @Autowired
    SwMeetingMinutesExtensionMapper meetingMinutesExtensionMapper;

    public SwMeetingMinutes queryMeetingMinute(Integer meetingID, String meetingDate) {
        SwMeetingMinutesExample example = new SwMeetingMinutesExample();
        SwMeetingMinutesExample.Criteria criteria = example.createCriteria();
        criteria.andMeetingDateEqualTo(meetingDate);
        criteria.andMeetingIdEqualTo(meetingID);
        List<SwMeetingMinutes> minutesList = meetingMinutesExtensionMapper.selectByExample(example);
        if (CollectionUtils.isEmpty(minutesList)) {
            return null;
        }
        return minutesList.get(0);
    }


}
