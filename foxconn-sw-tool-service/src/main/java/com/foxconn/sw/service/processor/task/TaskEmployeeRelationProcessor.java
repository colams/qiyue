package com.foxconn.sw.service.processor.task;

import com.foxconn.sw.business.oa.SwTaskEmployeeRelationBusiness;
import com.foxconn.sw.data.constants.enums.TaskRoleFlagEnums;
import com.foxconn.sw.data.dto.request.task.TaskEmployRelationParams;
import com.foxconn.sw.data.entity.SwTaskEmployeeRelation;
import com.google.common.collect.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class TaskEmployeeRelationProcessor {

    @Autowired
    SwTaskEmployeeRelationBusiness employeeRelationBusiness;

    public boolean updateEmployeeRelation(TaskEmployRelationParams data) {
        List<SwTaskEmployeeRelation> relationList = employeeRelationBusiness.getRelationsByTaskId(data.getTaskID().intValue());

        SwTaskEmployeeRelation relationEntity = relationList.stream()
                .filter(e -> e.getEmployeeNo().equalsIgnoreCase(data.getEmployeeNo()))
                .findFirst()
                .orElse(new SwTaskEmployeeRelation());

        SwTaskEmployeeRelation proposeRelation = relationList.stream()
                .filter(e -> TaskRoleFlagEnums.Proposer_Flag.test(e.getRoleFlag()))
                .findFirst()
                .orElse(new SwTaskEmployeeRelation());

        relationEntity.setTaskId(data.getTaskID().intValue());
        relationEntity.setEmployeeNo(data.getEmployeeNo());
        relationEntity.setPrevId(proposeRelation.getId());
        relationEntity.setRoleFlag(data.getOperateType() == 1 ? TaskRoleFlagEnums.Watcher_Flag.setFlag(relationEntity.getRoleFlag()) : TaskRoleFlagEnums.Watcher_Flag.removeFlag(relationEntity.getRoleFlag()));
        relationEntity.setIsActive(0);
        relationEntity.setIsInspector(0);
        relationEntity.setIsDelete(relationEntity.getRoleFlag() == 0 ? 1 : 0);
        return employeeRelationBusiness.insertOrUpdate(Lists.newArrayList(relationEntity));
    }
}
