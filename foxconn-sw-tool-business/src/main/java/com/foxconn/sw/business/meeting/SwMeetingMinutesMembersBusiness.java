package com.foxconn.sw.business.meeting;

import com.foxconn.sw.data.entity.SwMeetingMinutesMembers;
import com.foxconn.sw.data.entity.SwMeetingMinutesMembersExample;
import com.foxconn.sw.data.mapper.extension.meeting.SwMeetingMinutesMembersExtMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class SwMeetingMinutesMembersBusiness {
    @Autowired
    SwMeetingMinutesMembersExtMapper minutesMembersExtMapper;


    public List<SwMeetingMinutesMembers> queryMeetingMinuteMember(Long id) {
        SwMeetingMinutesMembersExample example = new SwMeetingMinutesMembersExample();
        SwMeetingMinutesMembersExample.Criteria criteria = example.createCriteria();
        criteria.andMmIdEqualTo(id);
        criteria.andIsDeleteEqualTo(0);
        return minutesMembersExtMapper.selectByExample(example);
    }
}
