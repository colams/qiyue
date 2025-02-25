package com.foxconn.sw.service.processor;

import com.foxconn.sw.business.meeting.MeetingBusiness;
import com.foxconn.sw.business.oa.SwTaskBusiness;
import com.foxconn.sw.data.dto.entity.home.GeneralVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class GeneralProcessor {
    @Autowired
    SwTaskBusiness taskBusiness;
    @Autowired
    MeetingBusiness meetingBusiness;

    public GeneralVo general() {
        GeneralVo generalVo = new GeneralVo();
        generalVo.setScheduleTask(taskBusiness.getTaskCount());
        generalVo.setUnreadTask(taskBusiness.getUnReadTaskCount());
        generalVo.setCollaboration(taskBusiness.getCollaborationCount());
        generalVo.setMeeting(meetingBusiness.getMeetingCount().intValue());
        return generalVo;
    }

}
