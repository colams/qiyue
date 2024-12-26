package com.foxconn.sw.business.oa;

import com.foxconn.sw.business.SwAppendResourceBusiness;
import com.foxconn.sw.common.context.RequestContext;
import com.foxconn.sw.common.utils.ConvertUtils;
import com.foxconn.sw.data.constants.enums.TaskOperateType;
import com.foxconn.sw.data.dto.entity.ResourceVo;
import com.foxconn.sw.data.dto.entity.task.TaskProgressVo;
import com.foxconn.sw.data.entity.SwTaskProgress;
import com.foxconn.sw.data.entity.SwTaskProgressExample;
import com.foxconn.sw.data.entity.extension.SwTaskProgressExtension;
import com.foxconn.sw.data.mapper.extension.oa.SwTaskProgressExtensionMapper;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class SwTaskProgressBusiness {

    @Autowired
    SwTaskProgressExtensionMapper progressExtensionMapper;
    @Autowired
    SwAppendResourceBusiness appendResourceBusiness;

    public List<TaskProgressVo> selectTaskProcess(Integer taskId) {
        List<SwTaskProgressExtension> taskProgresses = progressExtensionMapper.selectTaskProgressVo(taskId);
        List<TaskProgressVo> taskProgressVos = new ArrayList<>();
        if (CollectionUtils.isEmpty(taskProgresses)) {
            return taskProgressVos;
        }
        taskProgresses.forEach(e -> {
            TaskProgressVo taskProgressVo = new TaskProgressVo();
            taskProgressVo.setId(e.getId());
            taskProgressVo.setTaskId(e.getTaskId());
            taskProgressVo.setOperateEid(String.format("%s(%s)", e.getEmployeeName(), e.getOperateEid()));
            if (StringUtils.isNoneBlank(e.getResourceIds())) {
                taskProgressVo.setResourceIds(Arrays.stream(e.getResourceIds().split(","))
                        .map(Integer::parseInt)
                        .collect(Collectors.toList()));
            }
            taskProgressVo.setContent(e.getContent());
            taskProgressVo.setCreateTime(e.getDatetimeLastchange());
            taskProgressVo.setProgress(e.getProgress());
            taskProgressVo.setContentHistoryId(e.getContentHistoryId());
            taskProgressVos.add(taskProgressVo);
        });
        return taskProgressVos;
    }

    public SwTaskProgress addProcessInfo2(SwTaskProgress progress) {
        progressExtensionMapper.insertSelective(progress);
        return progress.getId() > 0 ? progress : null;
    }

    public Integer addProcessInfo(SwTaskProgress progress) {
        progressExtensionMapper.insertSelective(progress);
        return progress.getId();
    }

    public boolean addProcessInfo(Integer taskId, String content) {
        SwTaskProgress progress = new SwTaskProgress();
        progress.setTaskId(taskId);
        progress.setOperateEid(RequestContext.getEmployeeNo());
        progress.setProgress(100);
        progress.setContent(content);
        return progressExtensionMapper.insertSelective(progress) > 0;
    }

    public List<ResourceVo> getTaskResourceVo(Integer taskId) {
        SwTaskProgressExample example = new SwTaskProgressExample();
        SwTaskProgressExample.Criteria criteria = example.createCriteria();
        criteria.andTaskIdEqualTo(taskId);
        criteria.andOperateTypeEqualTo(TaskOperateType.RELEASE.getOperateType());
        List<SwTaskProgress> progresses = progressExtensionMapper.selectByExample(example);
        String resources = progresses.stream()
                .filter(e -> StringUtils.isNotEmpty(e.getResourceIds()))
                .map(e -> e.getResourceIds())
                .findAny()
                .orElse("");
        List<Integer> resourceIds = ConvertUtils.stringToListInt(resources);
        return appendResourceBusiness.getAppendResourcesVo(resourceIds);
    }
}
