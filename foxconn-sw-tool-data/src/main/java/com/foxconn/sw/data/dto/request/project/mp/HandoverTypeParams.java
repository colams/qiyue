package com.foxconn.sw.data.dto.request.project.mp;

import lombok.Data;

@Data
public class HandoverTypeParams {
    private Integer projectId;

    public Integer getProjectId() {
        return projectId;
    }

    public void setProjectId(Integer projectId) {
        this.projectId = projectId;
    }

    public Integer getSettingType() {
        return settingType;
    }

    public void setSettingType(Integer settingType) {
        this.settingType = settingType;
    }

    private Integer settingType;
}
