package com.foxconn.sw.business;

import com.foxconn.sw.data.entity.SwScheduleInfo;
import com.foxconn.sw.data.mapper.extension.SwScheduleInfoExtMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class SwScheduleInfoBusiness {

    @Autowired
    SwScheduleInfoExtMapper scheduleInfoExtMapper;

    public Long createSchedule(SwScheduleInfo scheduleInfo) {
        scheduleInfoExtMapper.insertSelective(scheduleInfo);
        return scheduleInfo.getId();
    }
}
