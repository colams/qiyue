package com.foxconn.sw.data.dto.entity.oa;

import com.foxconn.sw.data.dto.entity.universal.OptionsVo;

import java.util.List;

public class OAOptionVo {

    private List<OptionsVo> projectOptions;
    private List<OptionsVo> taskTypeOptions;
    private List<OptionsVo> urgencyOptions;

    public List<OptionsVo> getProjectOptions() {
        return projectOptions;
    }

    public void setProjectOptions(List<OptionsVo> projectOptions) {
        this.projectOptions = projectOptions;
    }

    public List<OptionsVo> getTaskTypeOptions() {
        return taskTypeOptions;
    }

    public void setTaskTypeOptions(List<OptionsVo> taskTypeOptions) {
        this.taskTypeOptions = taskTypeOptions;
    }

    public List<OptionsVo> getUrgencyOptions() {
        return urgencyOptions;
    }

    public void setUrgencyOptions(List<OptionsVo> urgencyOptions) {
        this.urgencyOptions = urgencyOptions;
    }
}
