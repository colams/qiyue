package com.foxconn.sw.service.processor.oa;

import com.foxconn.sw.business.oa.SwTaskBusiness;
import com.foxconn.sw.business.oa.SwTaskEmployeeRelationBusiness;
import com.foxconn.sw.data.constants.enums.TaskRoleFlagEnums;
import com.foxconn.sw.data.dto.entity.oa.BriefTaskVo;
import com.foxconn.sw.data.entity.SwTaskEmployeeRelation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class BriefDetailProcessor {


    @Autowired
    SwTaskBusiness swTaskBusiness;
    @Autowired
    SwTaskEmployeeRelationBusiness employeeRelation;

    public BriefTaskVo getTaskById(Integer params) {
        BriefTaskVo task = swTaskBusiness.getTaskById(params);
        List<SwTaskEmployeeRelation> relations = employeeRelation.getRelationsByTaskId(params);
        task.setManagerEid(relations.stream()
                .filter(e -> TaskRoleFlagEnums.checkFlag(e.getRoleFlag(), TaskRoleFlagEnums.Manager_Flag))
                .map(SwTaskEmployeeRelation::getEmployeeNo)
                .findFirst().orElse(""));

        task.setWatchers(relations.stream()
                .filter(e -> TaskRoleFlagEnums.checkFlag(e.getRoleFlag(), TaskRoleFlagEnums.Watcher_Flag))
                .map(SwTaskEmployeeRelation::getEmployeeNo)
                .collect(Collectors.toList()));
        return task;
    }
}
