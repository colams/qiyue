package com.foxconn.sw.business.oa;

import com.foxconn.sw.data.entity.SwTaskFollow;
import com.foxconn.sw.data.entity.SwTaskFollowExample;
import com.foxconn.sw.data.mapper.extension.oa.SwTaskFollowExtensionMapper;
import com.google.common.collect.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.Optional;

@Component
public class SwTaskFollowBusiness {

    @Autowired
    SwTaskFollowExtensionMapper followExtensionMapper;

    public boolean addTaskFollow(String content, Integer taskID, String employeeName) {
        SwTaskFollow entity = new SwTaskFollow();
        entity.setTaskId(taskID);
        entity.setContent(content);
        entity.setStatus(0);
        entity.setOperator(employeeName);
        return followExtensionMapper.insertSelective(entity) > 0;
    }

    public List<SwTaskFollow> queryFollow(List<Integer> taskIDs) {
        if (CollectionUtils.isEmpty(taskIDs)) {
            return Lists.newArrayList();
        }
        SwTaskFollowExample example = new SwTaskFollowExample();
        SwTaskFollowExample.Criteria criteria = example.createCriteria();
        criteria.andTaskIdIn(taskIDs);
        criteria.andStatusEqualTo(0);
        List<SwTaskFollow> follows = followExtensionMapper.selectByExample(example);
        return Optional.ofNullable(follows).orElse(Lists.newArrayList());
    }
}
