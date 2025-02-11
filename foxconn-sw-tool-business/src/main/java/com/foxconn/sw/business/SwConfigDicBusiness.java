package com.foxconn.sw.business;

import com.foxconn.sw.data.dto.request.config.ListParams;
import com.foxconn.sw.data.entity.SwConfigDic;
import com.foxconn.sw.data.entity.SwConfigDicExample;
import com.foxconn.sw.data.mapper.extension.SwConfigDicExtMapper;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Objects;

@Component
public class SwConfigDicBusiness {

    @Autowired
    SwConfigDicExtMapper configDicExtMapper;

    public List<SwConfigDic> queryConfigDicList(ListParams listParams) {
        SwConfigDicExample example = new SwConfigDicExample();
        SwConfigDicExample.Criteria criteria = example.createCriteria();

        if (Objects.nonNull(listParams)) {
            if (StringUtils.isNotEmpty(listParams.getItemName())) {
                criteria.andItemLike("%" + listParams.getItemName() + "%");
            }

            if (Objects.nonNull(listParams.getDisable())) {
                criteria.andIsDisableEqualTo(listParams.getDisable());
            }
        }
        criteria.andIsDeleteEqualTo(0);
        List<SwConfigDic> dictionaries = configDicExtMapper.selectByExample(example);
        return dictionaries;
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
