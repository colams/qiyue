package com.foxconn.sw.service.processor.task;

import com.foxconn.sw.business.SwAppendResourceBusiness;
import com.foxconn.sw.business.SwCapexSetBusiness;
import com.foxconn.sw.business.oa.SwTaskBusiness;
import com.foxconn.sw.business.oa.SwTaskEmployeeRelationBusiness;
import com.foxconn.sw.business.oa.SwTaskProgressBusiness;
import com.foxconn.sw.common.utils.ConvertUtils;
import com.foxconn.sw.common.utils.JsonUtils;
import com.foxconn.sw.data.constants.enums.TaskRoleFlagEnums;
import com.foxconn.sw.data.dto.entity.ResourceVo;
import com.foxconn.sw.data.dto.entity.oa.CapexParamsVo;
import com.foxconn.sw.data.dto.entity.task.BriefTaskVo;
import com.foxconn.sw.data.entity.SwTaskEmployeeRelation;
import com.foxconn.sw.data.entity.SwTaskProgress;
import com.foxconn.sw.service.processor.resource.GetAppendResourcesProcessor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class BriefDetailProcessor {


    @Autowired
    SwTaskBusiness swTaskBusiness;
    @Autowired
    SwTaskEmployeeRelationBusiness employeeRelation;
    @Autowired
    SwTaskProgressBusiness progressBusiness;
    @Autowired
    SwCapexSetBusiness capexSetBusiness;
    @Autowired
    SwAppendResourceBusiness appendResourceBusiness;
    @Autowired
    GetAppendResourcesProcessor getAppendResourcesProcessor;

    public BriefTaskVo getTaskById(Integer params) {
        BriefTaskVo task = swTaskBusiness.getTaskById(params);
        List<SwTaskEmployeeRelation> relations = employeeRelation.getRelationsByTaskId(params);

        List<String> enos = relations.stream()
                .filter(e -> TaskRoleFlagEnums.checkFlag(e.getRoleFlag(), TaskRoleFlagEnums.Manager_Flag))
                .map(SwTaskEmployeeRelation::getEmployeeNo)
                .collect(Collectors.toList());
        if (CollectionUtils.isEmpty(enos)) {
            task.setManagerEid("");
        } else {
            task.setManagerEid(JsonUtils.serialize(enos));
        }

        task.setWatchers(relations.stream()
                .filter(e -> TaskRoleFlagEnums.checkFlag(e.getRoleFlag(), TaskRoleFlagEnums.Watcher_Flag))
                .map(SwTaskEmployeeRelation::getEmployeeNo)
                .collect(Collectors.toList()));

        task.setCollaboration(task.getCategory().equalsIgnoreCase("6-2"));
        task.setResourceVos(getTaskResourceVo(task.getId()));
        if (task.getCollaboration()) {
            List<CapexParamsVo> capexParamsVos = capexSetBusiness.queryCapexParamsOrigin(params);
            task.setCapexParamsVos(capexParamsVos);
        }
        return task;
    }

    public List<ResourceVo> getTaskResourceVo(Integer taskId) {
        List<SwTaskProgress> progresses = progressBusiness.selectReleaseProgress(taskId);
        String resources = progresses.stream()
                .filter(e -> StringUtils.isNotEmpty(e.getResourceIds()))
                .map(e -> e.getResourceIds())
                .findAny()
                .orElse("");
        List<Integer> resourceIds = ConvertUtils.stringToListInt(resources);
        return getAppendResourcesProcessor.getAppendResourcesVo(resourceIds);
    }
}
