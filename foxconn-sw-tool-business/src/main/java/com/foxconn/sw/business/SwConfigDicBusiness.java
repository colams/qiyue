package com.foxconn.sw.business;

import com.foxconn.sw.data.entity.SwConfigDic;
import com.foxconn.sw.data.entity.SwConfigDicExample;
import com.foxconn.sw.data.mapper.extension.SwConfigDicExtMapper;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.Objects;

@Component
public class SwConfigDicBusiness {

    @Autowired
    SwConfigDicExtMapper configDicExtMapper;

    public SwConfigDic getConfigDic(String item) {
        SwConfigDicExample example = new SwConfigDicExample();
        SwConfigDicExample.Criteria criteria = example.createCriteria();
        criteria.andItemEqualTo(item);
        criteria.andIsDeleteEqualTo(0);
        List<SwConfigDic> dictionaries = configDicExtMapper.selectByExample(example);
        if (!CollectionUtils.isEmpty(dictionaries)) {
            return null;
        }
        return dictionaries.get(0);
    }

    public List<SwConfigDic> queryConfigDicList(String item, Integer isDisable) {
        SwConfigDicExample example = new SwConfigDicExample();
        SwConfigDicExample.Criteria criteria = example.createCriteria();

        if (StringUtils.isNotEmpty(item)) {
            criteria.andItemEqualTo(item);
        }

        if (Objects.nonNull(isDisable)) {
            criteria.andIsDisableEqualTo(isDisable);
        }

        criteria.andIsDeleteEqualTo(0);
        List<SwConfigDic> dictionaries = configDicExtMapper.selectByExample(example);
        return dictionaries;
    }


}
