package com.foxconn.sw.service.processor.schedule;

import com.foxconn.sw.business.SwScheduleInfoBusiness;
import com.foxconn.sw.common.utils.LocalDateExtUtils;
import com.foxconn.sw.data.context.RequestContext;
import com.foxconn.sw.data.dto.request.schedule.MyScheduleParams;
import com.foxconn.sw.data.dto.response.schedule.ScheduleListVo;
import com.foxconn.sw.data.entity.SwScheduleInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class MyScheduleProcessor {

    @Autowired
    SwScheduleInfoBusiness scheduleInfoBusiness;


    public List<ScheduleListVo> mySchedule(MyScheduleParams data) {
        String employeeNo = RequestContext.getEmployeeNo();
        List<SwScheduleInfo> scheduleInfos = scheduleInfoBusiness.getScheduleInfos(employeeNo, data.getYear(), data.getMonth());
        List<ScheduleListVo> scheduleListVos = scheduleInfos.stream().map(e -> {
            ScheduleListVo vo = new ScheduleListVo();
            vo.setDate(e.getDate());
            vo.setDestination(e.getPlace());
            vo.setType(e.getType());
            vo.setCurrent(e.getDate().equalsIgnoreCase(LocalDateExtUtils.toString(LocalDate.now())));
            return vo;
        }).collect(Collectors.toList());
        return scheduleListVos;
    }
}
