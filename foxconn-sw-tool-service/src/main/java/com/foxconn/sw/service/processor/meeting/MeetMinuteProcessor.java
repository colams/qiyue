package com.foxconn.sw.service.processor.meeting;

import com.foxconn.sw.business.meeting.SwMeetingMinutesBusiness;
import com.foxconn.sw.data.dto.request.meeting.MeetingMinuteParams;
import com.foxconn.sw.data.entity.SwMeetingMinutes;
import com.foxconn.sw.data.entity.SwMeetingMinutesDetail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class MeetMinuteProcessor {
    @Autowired
    SwMeetingMinutesBusiness meetingMinutesBusiness;

    public Boolean meetMinute(MeetingMinuteParams minuteParams) {

        SwMeetingMinutes meetingMinutes;
        List<SwMeetingMinutesDetail> decisionVo;
        List<SwMeetingMinutesDetail> otherVo;

        toMeetingMinutes(minuteParams);
        toMinutesDetail(minuteParams);
        toMinutesDetail(minuteParams);


        return true;
    }

    private void toMinutesDetail(MeetingMinuteParams minuteParams) {
    }

    private void toMeetingMinutes(MeetingMinuteParams minuteParams) {
    }
}
