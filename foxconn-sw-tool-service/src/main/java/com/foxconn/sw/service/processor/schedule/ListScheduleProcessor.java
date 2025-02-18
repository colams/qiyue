package com.foxconn.sw.service.processor.schedule;

import com.foxconn.sw.business.SwScheduleInfoBusiness;
import com.foxconn.sw.data.dto.request.schedule.ScheduleListParams;
import com.foxconn.sw.data.dto.response.schedule.ScheduleListVo;
import com.google.common.collect.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ListScheduleProcessor {

    @Autowired
    SwScheduleInfoBusiness scheduleInfoBusiness;


    public List<ScheduleListVo> list(ScheduleListParams data) {
        return Lists.newArrayList();
    }
}
