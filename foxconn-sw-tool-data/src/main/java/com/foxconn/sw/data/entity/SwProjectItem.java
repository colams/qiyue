package com.foxconn.sw.data.entity;

import java.time.LocalDateTime;

public class SwProjectItem {
    private Integer id;

    private Integer projectId;

    private String moduleType;

    private String updateBy;

    private String detailType;

    private String projectItem;

    private String projectValue;

    private String operator;

    private Integer isDelete;

    private LocalDateTime createTime;

    private LocalDateTime datetimeLastchange;

    public Integer getId() {
        return id;
    }

    public SwProjectItem withId(Integer id) {
        this.setId(id);
        return this;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getProjectId() {
        return projectId;
    }

    public SwProjectItem withProjectId(Integer projectId) {
        this.setProjectId(projectId);
        return this;
    }

    public void setProjectId(Integer projectId) {
        this.projectId = projectId;
    }

    public String getModuleType() {
        return moduleType;
    }

    public SwProjectItem withModuleType(String moduleType) {
        this.setModuleType(moduleType);
        return this;
    }

    public void setModuleType(String moduleType) {
        this.moduleType = moduleType == null ? null : moduleType.trim();
    }

    public String getUpdateBy() {
        return updateBy;
    }

    public SwProjectItem withUpdateBy(String updateBy) {
        this.setUpdateBy(updateBy);
        return this;
    }

    public void setUpdateBy(String updateBy) {
        this.updateBy = updateBy == null ? null : updateBy.trim();
    }

    public String getDetailType() {
        return detailType;
    }

    public SwProjectItem withDetailType(String detailType) {
        this.setDetailType(detailType);
        return this;
    }

    public void setDetailType(String detailType) {
        this.detailType = detailType == null ? null : detailType.trim();
    }

    public String getProjectItem() {
        return projectItem;
    }

    public SwProjectItem withProjectItem(String projectItem) {
        this.setProjectItem(projectItem);
        return this;
    }

    public void setProjectItem(String projectItem) {
        this.projectItem = projectItem == null ? null : projectItem.trim();
    }

    public String getProjectValue() {
        return projectValue;
    }

    public SwProjectItem withProjectValue(String projectValue) {
        this.setProjectValue(projectValue);
        return this;
    }

    public void setProjectValue(String projectValue) {
        this.projectValue = projectValue == null ? null : projectValue.trim();
    }

    public String getOperator() {
        return operator;
    }

    public SwProjectItem withOperator(String operator) {
        this.setOperator(operator);
        return this;
    }

    public void setOperator(String operator) {
        this.operator = operator == null ? null : operator.trim();
    }

    public Integer getIsDelete() {
        return isDelete;
    }

    public SwProjectItem withIsDelete(Integer isDelete) {
        this.setIsDelete(isDelete);
        return this;
    }

    public void setIsDelete(Integer isDelete) {
        this.isDelete = isDelete;
    }

    public LocalDateTime getCreateTime() {
        return createTime;
    }

    public SwProjectItem withCreateTime(LocalDateTime createTime) {
        this.setCreateTime(createTime);
        return this;
    }

    public void setCreateTime(LocalDateTime createTime) {
        this.createTime = createTime;
    }

    public LocalDateTime getDatetimeLastchange() {
        return datetimeLastchange;
    }

    public SwProjectItem withDatetimeLastchange(LocalDateTime datetimeLastchange) {
        this.setDatetimeLastchange(datetimeLastchange);
        return this;
    }

    public void setDatetimeLastchange(LocalDateTime datetimeLastchange) {
        this.datetimeLastchange = datetimeLastchange;
    }
}