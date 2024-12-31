package com.foxconn.sw.service.processor.meeting;

import com.foxconn.sw.data.dto.response.meeting.MeetingMinuteDetailVo;
import com.foxconn.sw.data.dto.request.meeting.MinuteDetailParams;
import org.springframework.stereotype.Component;

@Component
public class MinuteDetailProcessor {

    public MeetingMinuteDetailVo minuteDetail(MinuteDetailParams data) {
        MeetingMinuteDetailVo vo = new MeetingMinuteDetailVo();
        return vo;
    }
}
