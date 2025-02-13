package com.foxconn.sw.service.processor;

import com.foxconn.sw.data.dto.entity.home.GeneralVo;
import org.springframework.stereotype.Component;

@Component
public class GeneralProcessor {

    public GeneralVo general() {
        GeneralVo generalVo = new GeneralVo();
        generalVo.setScheduleTask(0);
        generalVo.setUnreadTask(0);
        generalVo.setCollaboration(0);
        generalVo.setMeeting(0);
        return generalVo;
    }
}
