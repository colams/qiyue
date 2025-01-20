package com.foxconn.sw.business.meeting;

import com.foxconn.sw.data.entity.SwMeetingMinuteMember;
import com.foxconn.sw.data.entity.SwMeetingMinuteMemberExample;
import com.foxconn.sw.data.mapper.extension.meeting.SwMeetingMinuteMemberExtMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Objects;

@Component
public class SwMeetingMinutesMembersBusiness {
    @Autowired
    SwMeetingMinuteMemberExtMapper meetingMinuteExtensionMapper;


    public List<SwMeetingMinuteMember> queryMeetingMinuteMember(Long id) {
        SwMeetingMinuteMemberExample example = new SwMeetingMinuteMemberExample();
        SwMeetingMinuteMemberExample.Criteria criteria = example.createCriteria();
        criteria.andMmIdEqualTo(id);
        criteria.andIsDeleteEqualTo(0);
        return meetingMinuteExtensionMapper.selectByExample(example);
    }

    public Integer insertOrUpdate(SwMeetingMinuteMember minuteMember) {
        int effectCount;
        if (Objects.nonNull(minuteMember.getId()) && minuteMember.getId() > 0) {
            effectCount = meetingMinuteExtensionMapper.updateByPrimaryKeySelective(minuteMember);
            return effectCount;
        } else {
            meetingMinuteExtensionMapper.insertSelective(minuteMember);
            return minuteMember.getId();
        }
    }

    public boolean batchUpdate(List<SwMeetingMinuteMember> newData) {
        for (SwMeetingMinuteMember member : newData) {
            insertOrUpdate(member);
        }
        return true;
    }
}
