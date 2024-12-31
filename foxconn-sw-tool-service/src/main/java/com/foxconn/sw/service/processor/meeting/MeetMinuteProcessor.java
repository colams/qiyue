package com.foxconn.sw.service.processor.meeting;

import com.foxconn.sw.data.dto.request.meeting.MeetingMinuteParams;
import org.springframework.stereotype.Component;

@Component
public class MeetMinuteProcessor {


    public Boolean meetMinute(MeetingMinuteParams minuteParams) {
        return true;
    }
}
