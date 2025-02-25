package com.foxconn.sw.service.processor.schedule;

import com.foxconn.sw.business.SwConfigDicBusiness;
import com.foxconn.sw.common.utils.JsonUtils;
import com.foxconn.sw.data.dto.entity.universal.StringParams;
import com.foxconn.sw.data.entity.SwConfigDic;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class AddCommonDestinationProcessor {

    private static final String configKey = "schedule.destination";
    @Autowired
    SwConfigDicBusiness configDicBusiness;

    public Boolean addCommonDestination(StringParams data) {

        SwConfigDic configDic = configDicBusiness.queryConfigDic(configKey);

        String strValue = Optional.ofNullable(configDic).map(e -> e.getItemValue()).orElse("");
        if (StringUtils.isEmpty(strValue)) {
            return false;
        }

        List<String> value = JsonUtils.deserialize(strValue, List.class, String.class);
        if (value.contains(data.getParams())) {
            return false;
        }

        value.add(data.getParams());
        SwConfigDic updateDic = new SwConfigDic();
        updateDic.setId(configDic.getId());
        updateDic.setItemValue(JsonUtils.serializeList(value));
        return configDicBusiness.updateOrInsert(updateDic) > 0;
    }
}
