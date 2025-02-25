package com.foxconn.sw.service.processor.schedule;

import com.foxconn.sw.business.SwScheduleInfoBusiness;
import com.foxconn.sw.common.utils.DateTimeUtils;
import com.foxconn.sw.common.utils.LocalDateExtUtils;
import com.foxconn.sw.data.context.RequestContext;
import com.foxconn.sw.data.dto.request.schedule.CreateScheduleParams;
import com.foxconn.sw.data.entity.SwScheduleInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Component
public class CreateScheduleProcessor {

    @Autowired
    SwScheduleInfoBusiness scheduleInfoBusiness;


    public boolean createSchedule(CreateScheduleParams data) {
        List<SwScheduleInfo> scheduleInfoList = new ArrayList<>();
        data.getDetailList().forEach(e -> {

            LocalDate startDay = LocalDateExtUtils.toLocalDate(e.getStartDate());
            long daysBetween = DateTimeUtils.getBetweenDay(e.getStartDate(), e.getEndDate());
            int i = 0;
            while (i++ <= daysBetween) {
                SwScheduleInfo scheduleInfo = new SwScheduleInfo();
                scheduleInfo.setPlace(data.getPlace());
                scheduleInfo.setEmployeeNo(RequestContext.getEmployeeNo());

                scheduleInfo.setType(e.getScheduleType().name());
                scheduleInfo.setDate(LocalDateExtUtils.toString(startDay.plusDays(i)));
                scheduleInfoList.add(scheduleInfo);
            }
        });

        return scheduleInfoBusiness.batchInsert(scheduleInfoList);
    }
}
