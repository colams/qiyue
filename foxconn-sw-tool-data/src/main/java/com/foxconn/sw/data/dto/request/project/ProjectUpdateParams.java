package com.foxconn.sw.data.dto.request.project;

public class ProjectUpdateParams {

    private Integer projectId;

    private String moduleType;

    private String updateBy;

    private String detailType;

    private String projectItem;

    private String projectValue;

    public Integer getProjectId() {
        return projectId;
    }

    public void setProjectId(Integer projectId) {
        this.projectId = projectId;
    }

    public String getModuleType() {
        return moduleType;
    }

    public void setModuleType(String moduleType) {
        this.moduleType = moduleType;
    }

    public String getUpdateBy() {
        return updateBy;
    }

    public void setUpdateBy(String updateBy) {
        this.updateBy = updateBy;
    }

    public String getDetailType() {
        return detailType;
    }

    public void setDetailType(String detailType) {
        this.detailType = detailType;
    }

    public String getProjectItem() {
        return projectItem;
    }

    public void setProjectItem(String projectItem) {
        this.projectItem = projectItem;
    }

    public String getProjectValue() {
        return projectValue;
    }

    public void setProjectValue(String projectValue) {
        this.projectValue = projectValue;
    }
}
