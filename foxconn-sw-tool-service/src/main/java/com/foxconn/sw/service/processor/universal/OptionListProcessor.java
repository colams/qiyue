package com.foxconn.sw.service.processor.universal;

import com.foxconn.sw.data.dto.entity.oa.OAOptionVo;
import com.foxconn.sw.service.processor.OAOptionConfig;
import org.springframework.stereotype.Component;

@Component
public class OptionListProcessor {

    public OAOptionVo getOptions() {
        OAOptionVo optionVo = new OAOptionVo();
        optionVo.setProjectOptions(OAOptionConfig.initProject2());
        optionVo.setTaskTypeOptions(OAOptionConfig.initTaskType());
        optionVo.setUrgencyOptions(OAOptionConfig.initUrgency());
        return optionVo;
    }
}
