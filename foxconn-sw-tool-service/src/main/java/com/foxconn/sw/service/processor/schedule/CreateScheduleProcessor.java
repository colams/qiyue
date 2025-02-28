package com.foxconn.sw.service.processor.schedule;

import com.foxconn.sw.business.SwScheduleInfoBusiness;
import com.foxconn.sw.common.constanst.NumberConstants;
import com.foxconn.sw.common.utils.DateTimeUtils;
import com.foxconn.sw.common.utils.LocalDateExtUtils;
import com.foxconn.sw.data.context.RequestContext;
import com.foxconn.sw.data.dto.enums.ScheduleTypeEnums;
import com.foxconn.sw.data.dto.request.schedule.CreateScheduleParams;
import com.foxconn.sw.data.entity.SwScheduleInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class CreateScheduleProcessor {

    @Autowired
    SwScheduleInfoBusiness scheduleInfoBusiness;


    public boolean createSchedule(CreateScheduleParams data) {
        List<SwScheduleInfo> scheduleInfoList = new ArrayList<>();


        data.getDetailList().forEach(e -> {

            List<SwScheduleInfo> oldData = scheduleInfoBusiness.getMyScheduleInfos(RequestContext.getEmployeeNo(),
                    e.getStartDate(),
                    e.getEndDate());

            LocalDate startDay = LocalDateExtUtils.toLocalDate(e.getStartDate());
            long daysBetween = DateTimeUtils.getBetweenDay(e.getStartDate(), e.getEndDate());
            int i = 0;
            while (i <= daysBetween) {
                String current = LocalDateExtUtils.toString(startDay.plusDays(i));

                Optional<SwScheduleInfo> optional = oldData.stream()
                        .filter(f -> f.getDate().equalsIgnoreCase(current))
                        .findAny();

                SwScheduleInfo scheduleInfo = new SwScheduleInfo();
                scheduleInfo.setId(optional.map(SwScheduleInfo::getId).orElse(NumberConstants.ZERO.longValue()));
                scheduleInfo.setPlace(data.getPlace());
                scheduleInfo.setEmployeeNo(RequestContext.getEmployeeNo());

                if (data.getAutoMovingDay() && (i == 0 || i == daysBetween)) {
                    scheduleInfo.setType(ScheduleTypeEnums.MovingDay.name());
                } else {
                    scheduleInfo.setType(e.getScheduleType().name());
                }

                scheduleInfo.setDate(current);
                scheduleInfoList.add(scheduleInfo);
                i++;
            }
        });

        return scheduleInfoBusiness.batchInsert(scheduleInfoList);
    }
}
