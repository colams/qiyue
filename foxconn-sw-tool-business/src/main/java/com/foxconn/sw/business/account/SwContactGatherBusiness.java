package com.foxconn.sw.business.account;

import com.foxconn.sw.data.entity.SwContactGather;
import com.foxconn.sw.data.entity.SwContactGatherExample;
import com.foxconn.sw.data.mapper.extension.acount.SwContactGatherExtensionMapper;
import com.google.common.collect.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class SwContactGatherBusiness {

    @Autowired
    SwContactGatherExtensionMapper gatherExtensionMapper;


    public SwContactGather queryGatherInfo(String employeeNo, String gatherEmployeeNo) {
        SwContactGatherExample example = new SwContactGatherExample();
        SwContactGatherExample.Criteria criteria = example.createCriteria();
        criteria.andEmployeeNoEqualTo(employeeNo);
        criteria.andGatherEmployeeNoEqualTo(gatherEmployeeNo);
        List<SwContactGather> gathers = gatherExtensionMapper.selectByExample(example);
        if (CollectionUtils.isEmpty(gathers)) {
            return null;
        }
        return gathers.get(0);
    }

    public Map<String, SwContactGather> queryGatherInfo(String employeeNo) {
        SwContactGatherExample example = new SwContactGatherExample();
        SwContactGatherExample.Criteria criteria = example.createCriteria();
        criteria.andEmployeeNoEqualTo(employeeNo);
        criteria.andStatusEqualTo(1);
        List<SwContactGather> gathers = gatherExtensionMapper.selectByExample(example);
        return Optional.ofNullable(gathers).orElse(Lists.newArrayList()).stream().collect(Collectors.toMap(SwContactGather::getGatherEmployeeNo, e -> e));
    }

    public boolean addGather(SwContactGather gather) {
        return gatherExtensionMapper.insertSelective(gather) > 0;
    }

    public boolean updateGatherStatus(SwContactGather gather) {
        SwContactGather contactGather = new SwContactGather();
        contactGather.setId(gather.getId());
        contactGather.setStatus(gather.getStatus());
        return gatherExtensionMapper.updateByPrimaryKeySelective(contactGather) > 0;
    }
}
