package com.foxconn.sw.service.processor.system;

import com.foxconn.sw.business.SwConfigDicBusiness;
import com.foxconn.sw.data.dto.PageEntity;
import com.foxconn.sw.data.dto.PageParams;
import com.foxconn.sw.data.dto.request.config.ConfigDicParams;
import com.foxconn.sw.data.dto.request.config.ListParams;
import com.foxconn.sw.data.entity.SwConfigDic;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ConfigDicProcessor {

    @Autowired
    SwConfigDicBusiness configDicBusiness;

    public PageEntity<List<SwConfigDic>> getConfigDicList(PageParams<ListParams> data) {
        List<SwConfigDic> documents = configDicBusiness.queryConfigDicList(data);
        Long dicCount = 0L;
        return new PageEntity(dicCount, documents);
    }

    public Boolean save(ConfigDicParams data) {
        SwConfigDic configDic = new SwConfigDic();
        configDic.setId(data.getId());
        configDic.setItem(data.getItem());
        configDic.setItemValue(data.getItemValue());
        configDic.setRemark(data.getRemark());
        return configDicBusiness.updateOrInsert(configDic) > 0;
    }
}
