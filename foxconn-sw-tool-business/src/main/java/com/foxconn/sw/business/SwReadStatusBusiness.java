package com.foxconn.sw.business;

import com.foxconn.sw.data.constants.enums.ModuleEnums;
import com.foxconn.sw.data.context.RequestContext;
import com.foxconn.sw.data.entity.SwReadStatus;
import com.foxconn.sw.data.entity.SwReadStatusExample;
import com.foxconn.sw.data.mapper.extension.SwReadStatusExtMapper;
import com.google.common.collect.Lists;
import org.python.google.common.collect.Maps;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class SwReadStatusBusiness {

    @Autowired
    SwReadStatusExtMapper readStatusExtMapper;

    public Map<Integer, Integer> getReadStatusList(ModuleEnums moduleEnums, List<Integer> idList) {
        if (CollectionUtils.isEmpty(idList)) {
            return Maps.newHashMap();
        }

        SwReadStatusExample example = new SwReadStatusExample();
        SwReadStatusExample.Criteria criteria = example.createCriteria();
        criteria.andForeignIdIn(idList);
        criteria.andModuleTypeEqualTo(moduleEnums.name());
        criteria.andEmployeeNoEqualTo(RequestContext.getEmployeeNo());
        List<SwReadStatus> readStatuses = readStatusExtMapper.selectByExample(example);
        return Optional.ofNullable(readStatuses).orElse(Lists.newArrayList())
                .stream()
                .collect(Collectors.toMap(SwReadStatus::getForeignId, SwReadStatus::getIsRead));
    }

    public boolean insertReadStatus(ModuleEnums moduleEnums, List<Integer> foreignIds) {
        if (CollectionUtils.isEmpty(foreignIds)) {
            return false;
        }

        foreignIds.forEach(e -> {
            SwReadStatus readStatus = new SwReadStatus();
            readStatus.setModuleType(moduleEnums.name());
            readStatus.setForeignId(e);
            readStatus.setEmployeeNo(RequestContext.getEmployeeNo());
            readStatus.setIsRead(1);
            readStatusExtMapper.insertSelective(readStatus);
        });
        return true;
    }

    public int getForumUnReadCount(Integer bbsId) {
        return readStatusExtMapper.getForumUnReadCount(ModuleEnums.Forum.name(), bbsId, RequestContext.getEmployeeNo());
    }
}
