package com.foxconn.sw.business.oa;

import com.foxconn.sw.business.context.RequestContext;
import com.foxconn.sw.data.constants.enums.TaskRoleFlagEnums;
import com.foxconn.sw.data.entity.SwTaskEmployeeRelation;
import com.foxconn.sw.data.entity.SwTaskEmployeeRelationExample;
import com.foxconn.sw.data.mapper.extension.oa.SwTaskEmployeeRelationExtensionMapper;
import com.google.common.collect.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Component
public class SwTaskEmployeeRelationBusiness {

    @Autowired
    SwTaskEmployeeRelationExtensionMapper employeeRelationExtensionMapper;


    /**
     * 增加工作任务和员工关联信息
     *
     * @param employeeNo
     * @param taskID
     * @param roleFlags
     * @return
     */
    public Integer addTaskEmployee(String employeeNo, int taskID, TaskRoleFlagEnums... roleFlags) {
        int roleFlag = 0;
        for (TaskRoleFlagEnums taskRoleFlags : roleFlags) {
            roleFlag = TaskRoleFlagEnums.setFlag(roleFlag, taskRoleFlags);
        }

        SwTaskEmployeeRelation relation = new SwTaskEmployeeRelation();
        relation.setTaskId(taskID);
        relation.setEmployeeNo(employeeNo);
        relation.setRoleFlag(roleFlag);
        addRelation(relation);
        return relation.getId();
    }

    /**
     * 增加工作任务和员工关联信息
     *
     * @param employeeNo
     * @param taskID
     * @param relationID
     * @param roleFlag
     * @return
     */
    public Integer addTaskEmployee(String employeeNo, Integer taskID, Integer relationID, TaskRoleFlagEnums roleFlag) {
        SwTaskEmployeeRelation relation = new SwTaskEmployeeRelation();
        relation.setTaskId(taskID);
        relation.setEmployeeNo(employeeNo);
        relation.setRoleFlag(TaskRoleFlagEnums.initFlag(roleFlag));
        relation.setPrevId(relationID);
        addRelation(relation);
        return relation.getId();
    }

    /**
     * 增加工作任务和员工关联信息
     *
     * @param relation 关联信息
     * @return
     */
    private Integer addRelation(SwTaskEmployeeRelation relation) {
        SwTaskEmployeeRelation data = employeeRelationExtensionMapper.getRelation(relation);
        if (Objects.nonNull(data)) {
            SwTaskEmployeeRelation employeeRelation = new SwTaskEmployeeRelation();
            employeeRelation.setId(data.getId());
            employeeRelation.setRoleFlag(relation.getRoleFlag());
            employeeRelation.setPrevId(relation.getPrevId());
            employeeRelationExtensionMapper.updateByPrimaryKeySelective(employeeRelation);
            relation.setId(data.getId());
            return data.getId();
        }
        employeeRelationExtensionMapper.insertSelective(relation);
        return relation.getId();
    }


    public Integer assignTaskEmployee(String employeeNo, int taskID, TaskRoleFlagEnums roleFlagEnum) {

        SwTaskEmployeeRelation data = employeeRelationExtensionMapper.queryRelation(taskID, employeeNo);
        if (Objects.nonNull(data)) {
            SwTaskEmployeeRelation employeeRelation = new SwTaskEmployeeRelation();
            employeeRelation.setId(data.getId());
            employeeRelation.setRoleFlag(TaskRoleFlagEnums.setFlag(data.getRoleFlag(), roleFlagEnum));
            employeeRelationExtensionMapper.updateByPrimaryKeySelective(employeeRelation);
            return data.getId();
        }

        SwTaskEmployeeRelation current = employeeRelationExtensionMapper.queryRelation(taskID, RequestContext.getEmployeeNo());
        SwTaskEmployeeRelation employeeRelation = new SwTaskEmployeeRelation();
        employeeRelation.setRoleFlag(TaskRoleFlagEnums.initFlag(roleFlagEnum));
        employeeRelation.setEmployeeNo(employeeNo);
        employeeRelation.setPrevId(current.getId());
        employeeRelation.setTaskId(taskID);
        employeeRelationExtensionMapper.insertSelective(employeeRelation);
        return employeeRelation.getId();
    }

    public Integer acceptTaskEmployee(int taskID) {
        String employeeNo = RequestContext.getEmployeeNo();
        return assignTaskEmployee(employeeNo, taskID, TaskRoleFlagEnums.Handler_Flag);
    }

    public List<SwTaskEmployeeRelation> getRelationsByTaskId(Integer taskID) {
        SwTaskEmployeeRelationExample example = new SwTaskEmployeeRelationExample();
        SwTaskEmployeeRelationExample.Criteria criteria = example.createCriteria();
        criteria.andTaskIdEqualTo(taskID);
        List<SwTaskEmployeeRelation> relations = employeeRelationExtensionMapper.selectByExample(example);
        return Optional.ofNullable(relations).orElse(Lists.newArrayList());
    }

    public SwTaskEmployeeRelation getRelationEntityByTaskId(Integer taskID, String employeeNo) {
        SwTaskEmployeeRelationExample example = new SwTaskEmployeeRelationExample();
        SwTaskEmployeeRelationExample.Criteria criteria = example.createCriteria();
        criteria.andTaskIdEqualTo(taskID);
        criteria.andEmployeeNoEqualTo(employeeNo);
        criteria.andIsDeleteEqualTo(0);
        List<SwTaskEmployeeRelation> relations = employeeRelationExtensionMapper.selectByExample(example);
        return Optional.ofNullable(relations).orElse(Lists.newArrayList()).stream().findFirst().orElse(null);
    }
}
