package com.foxconn.sw.service.processor.schedule;

import com.foxconn.sw.business.SwScheduleInfoBusiness;
import com.foxconn.sw.data.context.RequestContext;
import com.foxconn.sw.data.dto.request.schedule.MyScheduleParams;
import com.foxconn.sw.data.dto.response.schedule.ScheduleListVo;
import com.foxconn.sw.data.entity.SwScheduleInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

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
            vo.setStartDate(e.getStartDate());
            vo.setEndDate(e.getEndDate());
            vo.setDestination(e.getPlace());
            return vo;
        }).collect(Collectors.toList());
        return scheduleListVos;
    }
}
