package com.foxconn.sw.business.oa;

import com.foxconn.sw.data.dto.entity.acount.UserInfo;
import com.foxconn.sw.data.entity.SwTaskEmployeeRelation;
import com.foxconn.sw.data.entity.SwTaskEmployeeRelationExample;
import com.foxconn.sw.data.mapper.extension.oa.SwTaskEmployeeRelationExtensionMapper;
import com.google.common.collect.Lists;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class SwTaskEmployeeRelationBusiness {

    @Autowired
    SwTaskEmployeeRelationExtensionMapper employeeRelationExtensionMapper;

    public Integer addRelation(SwTaskEmployeeRelation relation) {
        SwTaskEmployeeRelation data = employeeRelationExtensionMapper.getRelation(relation);
        if (Objects.nonNull(data)) {
            return data.getId();
        }
        employeeRelationExtensionMapper.insertSelective(relation);
        return relation.getId();
    }

    public Integer addRelation(int taskID, String employeeNo, int prevID) {
        SwTaskEmployeeRelation data = employeeRelationExtensionMapper.queryRelation(taskID, employeeNo);
        if (Objects.nonNull(data)) {
            return data.getId();
        }
        SwTaskEmployeeRelation relation = new SwTaskEmployeeRelation();
        relation.setEmployeeNo(employeeNo);
        relation.setTaskId(taskID);
        relation.setPrevId(prevID);
        int effectCount = employeeRelationExtensionMapper.insertSelective(relation);
        return relation.getId();
    }

    public void addTaskEmployee(UserInfo user, int taskID, String managerEid) {
        SwTaskEmployeeRelation relation = new SwTaskEmployeeRelation();
        relation.setTaskId(taskID);
        relation.setEmployeeNo(user.getEmployeeNo());
        int parentID = addRelation(relation);

        if (StringUtils.isNoneBlank(managerEid)) {
            addRelation(taskID, managerEid, parentID);
        }
    }


    public List<String> getRelationByTaskId(Integer taskID) {
        SwTaskEmployeeRelationExample example = new SwTaskEmployeeRelationExample();
        SwTaskEmployeeRelationExample.Criteria criteria = example.createCriteria();
        criteria.andTaskIdEqualTo(taskID);
        List<SwTaskEmployeeRelation> relations = employeeRelationExtensionMapper.selectByExample(example);
        return Optional.ofNullable(relations).orElse(Lists.newArrayList())
                .stream().map(e -> e.getEmployeeNo())
                .collect(Collectors.toList());
    }
}
