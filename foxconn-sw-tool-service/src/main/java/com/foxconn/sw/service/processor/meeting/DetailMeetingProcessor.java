package com.foxconn.sw.service.processor.meeting;

import com.foxconn.sw.data.dto.entity.meeting.MeetingVo;
import com.foxconn.sw.data.dto.request.meeting.ListMeetingParams;
import com.google.common.collect.Lists;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class DetailMeetingProcessor {


    public List<MeetingVo> detail(ListMeetingParams data) {
        return Lists.newArrayList();
    }
}
