package com.foxconn.sw.business;

import com.foxconn.sw.common.utils.LocalDateExtUtils;
import com.foxconn.sw.data.entity.SwScheduleInfo;
import com.foxconn.sw.data.mapper.extension.SwScheduleInfoExtMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.YearMonth;
import java.util.List;

@Component
public class SwScheduleInfoBusiness {

    @Autowired
    SwScheduleInfoExtMapper scheduleInfoExtMapper;

    public Long createSchedule(SwScheduleInfo scheduleInfo) {
        scheduleInfoExtMapper.insertSelective(scheduleInfo);
        return scheduleInfo.getId();
    }

    public List<SwScheduleInfo> getScheduleInfos(String employeeNo, Integer year, Integer month) {
        YearMonth yearMonth = YearMonth.of(year, month);
        String endOfMonth = LocalDateExtUtils.toString(yearMonth.atEndOfMonth());
        String startOfMonth = LocalDateExtUtils.toString(yearMonth.atDay(1));
        return scheduleInfoExtMapper.getScheduleInfos(employeeNo, startOfMonth, endOfMonth);
    }
}
