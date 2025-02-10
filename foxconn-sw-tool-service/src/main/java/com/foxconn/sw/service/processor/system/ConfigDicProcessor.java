package com.foxconn.sw.service.processor.system;

import com.foxconn.sw.business.SwConfigDicBusiness;
import com.foxconn.sw.data.dto.request.config.ConfigDicParams;
import com.foxconn.sw.data.dto.request.config.ListParams;
import com.foxconn.sw.data.dto.request.config.UpdateParams;
import com.foxconn.sw.data.entity.SwConfigDic;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ConfigDicProcessor {

    @Autowired
    SwConfigDicBusiness configDicBusiness;

    public List<SwConfigDic> getConfigDicList(ListParams listParams) {
        return configDicBusiness.queryConfigDicList(listParams);
    }

    public boolean update(UpdateParams data) {
        SwConfigDic configDic = new SwConfigDic();
        configDic.setId(data.getId());
        configDic.setItemValue(data.getValue());
        return configDicBusiness.updateOrInsert(configDic) > 0;
    }

    public Boolean create(ConfigDicParams data) {
        SwConfigDic configDic = new SwConfigDic();
        configDic.setItem(data.getItem());
        configDic.setItemValue(data.getItemValue());
        return configDicBusiness.updateOrInsert(configDic) > 0;
    }
}
