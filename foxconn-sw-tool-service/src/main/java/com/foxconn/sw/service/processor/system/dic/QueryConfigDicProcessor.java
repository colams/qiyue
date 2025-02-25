package com.foxconn.sw.service.processor.system.dic;

import com.foxconn.sw.business.SwConfigDicBusiness;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class QueryConfigDicProcessor {

    @Autowired
    SwConfigDicBusiness configDicBusiness;

    public <T> T getConfigDicValue(String configKey, Class mainClazz, Class... parameterClazz) {
        return configDicBusiness.getConfigDicValue(configKey, mainClazz, parameterClazz);
    }
}
