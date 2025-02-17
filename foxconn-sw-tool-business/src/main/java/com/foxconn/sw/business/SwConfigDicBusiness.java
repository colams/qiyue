package com.foxconn.sw.business;

import com.foxconn.sw.data.dto.PageParams;
import com.foxconn.sw.data.dto.request.config.ListParams;
import com.foxconn.sw.data.entity.SwConfigDic;
import com.foxconn.sw.data.mapper.extension.SwConfigDicExtMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Objects;

@Component
public class SwConfigDicBusiness {

    @Autowired
    SwConfigDicExtMapper configDicExtMapper;

    public List<SwConfigDic> queryConfigDicList(PageParams<ListParams> data) {
        int start = (data.getCurrentPage() - 1) * data.getPageSize();
        return configDicExtMapper.selectListParams(data.getParams(), start, data.getPageSize());
    }

    public SwConfigDic queryConfigDic(String item) {
        return configDicExtMapper.selectSwConfigDic(item);
    }

    public Integer updateOrInsert(SwConfigDic configDic) {
        if (Objects.isNull(configDic.getId()) || configDic.getId() <= 0) {
            configDicExtMapper.insertSelective(configDic);
            return configDic.getId();
        } else {
            return configDicExtMapper.updateByPrimaryKeySelective(configDic);
        }
    }
}
