package com.foxconn.sw.service.processor.schedule;

import com.foxconn.sw.business.SwScheduleInfoBusiness;
import com.foxconn.sw.business.system.EmployeeBusiness;
import com.foxconn.sw.common.utils.DateTimeUtils;
import com.foxconn.sw.common.utils.LocalDateExtUtils;
import com.foxconn.sw.data.context.RequestContext;
import com.foxconn.sw.data.dto.enums.ScheduleTypeEnums;
import com.foxconn.sw.data.dto.request.schedule.MyScheduleParams;
import com.foxconn.sw.data.dto.response.schedule.ScheduleListVo;
import com.foxconn.sw.data.entity.SwEmployee;
import com.foxconn.sw.data.entity.SwScheduleInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.YearMonth;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class MyScheduleProcessor {

    @Autowired
    SwScheduleInfoBusiness scheduleInfoBusiness;
    @Autowired
    EmployeeBusiness employeeBusiness;


    public List<ScheduleListVo> mySchedule(MyScheduleParams data) {
        String employeeNo = RequestContext.getEmployeeNo();
        SwEmployee employee = employeeBusiness.selectEmployeeByENo(employeeNo);

        YearMonth yearMonth = YearMonth.of(data.getYear(), data.getMonth());
        String endOfMonth = LocalDateExtUtils.toString(yearMonth.atEndOfMonth());
        String startOfMonth = LocalDateExtUtils.toString(yearMonth.atDay(1));
        List<SwScheduleInfo> scheduleInfos = scheduleInfoBusiness.getMyScheduleInfos(employeeNo, startOfMonth, endOfMonth);

        List<ScheduleListVo> scheduleListVos = scheduleInfos.stream().map(e -> {
            ScheduleListVo vo = new ScheduleListVo();
            vo.setDate(e.getDate());
            vo.setDestination(e.getPlace());
            vo.setType(e.getType());
            vo.setCurrent(e.getDate().equalsIgnoreCase(LocalDateExtUtils.toString(LocalDate.now())));
            return vo;
        }).collect(Collectors.toList());

        LocalDate startDay = yearMonth.atDay(1);
        long daysBetween = DateTimeUtils.getBetweenDay(startOfMonth, endOfMonth);

        int i = 0;
        while (i <= daysBetween) {
            String currentDate = LocalDateExtUtils.toString(startDay.plusDays(i++));
            if (!scheduleListVos
                    .stream()
                    .anyMatch(e -> e.getDate().equalsIgnoreCase(currentDate))) {
                ScheduleListVo vo = new ScheduleListVo();
                vo.setDate(currentDate);
                vo.setDestination(employee.getStationedPlace());
                vo.setType(ScheduleTypeEnums.Working.name());
                vo.setCurrent(currentDate.equalsIgnoreCase(LocalDateExtUtils.toString(LocalDate.now())));
                scheduleListVos.add(vo);
            }
        }
        return scheduleListVos.stream().sorted(Comparator.comparing(ScheduleListVo::getDate)).collect(Collectors.toList());
    }
}
