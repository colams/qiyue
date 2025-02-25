package com.foxconn.sw.business;

import com.foxconn.sw.common.utils.JsonUtils;
import com.foxconn.sw.data.dto.PageParams;
import com.foxconn.sw.data.dto.request.config.ListParams;
import com.foxconn.sw.data.entity.SwConfigDic;
import com.foxconn.sw.data.mapper.extension.SwConfigDicExtMapper;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

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

    /**
     * 获取配置信息
     *
     * @param configItem     配置项目 item name
     * @param mainClazz      主类型
     * @param parameterClazz 副类型
     * @param <T>            返回类型
     * @return
     */
    public <T> T getConfigDicValue(String configItem, Class mainClazz, Class... parameterClazz) {
        SwConfigDic configDic = queryConfigDic(configItem);
        if (Objects.isNull(configDic) || StringUtils.isEmpty(configDic.getItemValue())) {
            return null;
        }
        return JsonUtils.deserialize(configDic.getItemValue(), mainClazz, parameterClazz);
    }

    /**
     * 获取配置信息
     *
     * @param configItem 配置项目 item name
     * @return
     */
    public String getConfigDicValue(String configItem) {
        SwConfigDic configDic = queryConfigDic(configItem);
        return Optional.ofNullable(configDic).map(SwConfigDic::getItemValue).orElse("");
    }

}
