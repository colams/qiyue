package com.foxconn.sw.service.processor.schedule;

import com.foxconn.sw.business.SwScheduleInfoBusiness;
import com.foxconn.sw.data.context.RequestContext;
import com.foxconn.sw.data.dto.request.schedule.CreateScheduleParams;
import com.foxconn.sw.data.entity.SwScheduleInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CreateScheduleProcessor {

    @Autowired
    SwScheduleInfoBusiness scheduleInfoBusiness;


    public Long createSchedule(CreateScheduleParams data) {
        SwScheduleInfo scheduleInfo = new SwScheduleInfo();
        scheduleInfo.setType(data.getScheduleType());
        scheduleInfo.setEmployeeNo(RequestContext.getEmployeeNo());
        scheduleInfo.setPlace(data.getPlace());
        scheduleInfo.setStartDate(data.getStartDate());
        scheduleInfo.setEndDate(data.getEndDate());
        return scheduleInfoBusiness.createSchedule(scheduleInfo);
    }
}
