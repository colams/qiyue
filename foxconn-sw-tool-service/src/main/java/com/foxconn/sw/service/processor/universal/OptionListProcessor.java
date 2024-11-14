package com.foxconn.sw.service.processor.universal;

import com.foxconn.sw.data.dto.entity.oa.OAOptionVo;
import com.foxconn.sw.data.dto.entity.universal.StringParams;
import com.foxconn.sw.service.processor.OAOptionConfig;
import org.springframework.stereotype.Component;

@Component
public class OptionListProcessor {

    public OAOptionVo getOptions(StringParams data) {
        OAOptionVo optionVo = new OAOptionVo();
        optionVo.setProjectOptions(OAOptionConfig.initProject2());
        optionVo.setTaskTypeOptions(OAOptionConfig.initTaskType(data.getParams()));
        optionVo.setUrgencyOptions(OAOptionConfig.initUrgency());
        return optionVo;
    }
}
