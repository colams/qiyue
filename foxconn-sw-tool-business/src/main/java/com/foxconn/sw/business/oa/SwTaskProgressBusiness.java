package com.foxconn.sw.business.oa;

import com.foxconn.sw.data.dto.entity.task.TaskProgressVo;
import com.foxconn.sw.data.entity.SwTaskProgress;
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
            taskProgressVos.add(taskProgressVo);
        });
        return taskProgressVos;
    }

    public boolean addProcessInfo(SwTaskProgress progress) {
        return progressExtensionMapper.insertSelective(progress) > 0;
    }
}
