package com.foxconn.sw.data.entity;

import java.time.LocalDateTime;

public class SwToolsHistory {
    private Integer id;

    private Integer toolId;

    private String toolName;

    private String toolIcon;

    private Integer propertyId;

    private String versionNo;

    private String filePath;

    private Double toolSize;

    private String introduction;

    private String updateContent;

    private String useGuide;

    private String operator;

    private LocalDateTime createTime;

    private LocalDateTime datetimeLastchange;

    public Integer getId() {
        return id;
    }

    public SwToolsHistory withId(Integer id) {
        this.setId(id);
        return this;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getToolId() {
        return toolId;
    }

    public SwToolsHistory withToolId(Integer toolId) {
        this.setToolId(toolId);
        return this;
    }

    public void setToolId(Integer toolId) {
        this.toolId = toolId;
    }

    public String getToolName() {
        return toolName;
    }

    public SwToolsHistory withToolName(String toolName) {
        this.setToolName(toolName);
        return this;
    }

    public void setToolName(String toolName) {
        this.toolName = toolName == null ? null : toolName.trim();
    }

    public String getToolIcon() {
        return toolIcon;
    }

    public SwToolsHistory withToolIcon(String toolIcon) {
        this.setToolIcon(toolIcon);
        return this;
    }

    public void setToolIcon(String toolIcon) {
        this.toolIcon = toolIcon == null ? null : toolIcon.trim();
    }

    public Integer getPropertyId() {
        return propertyId;
    }

    public SwToolsHistory withPropertyId(Integer propertyId) {
        this.setPropertyId(propertyId);
        return this;
    }

    public void setPropertyId(Integer propertyId) {
        this.propertyId = propertyId;
    }

    public String getVersionNo() {
        return versionNo;
    }

    public SwToolsHistory withVersionNo(String versionNo) {
        this.setVersionNo(versionNo);
        return this;
    }

    public void setVersionNo(String versionNo) {
        this.versionNo = versionNo == null ? null : versionNo.trim();
    }

    public String getFilePath() {
        return filePath;
    }

    public SwToolsHistory withFilePath(String filePath) {
        this.setFilePath(filePath);
        return this;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath == null ? null : filePath.trim();
    }

    public Double getToolSize() {
        return toolSize;
    }

    public SwToolsHistory withToolSize(Double toolSize) {
        this.setToolSize(toolSize);
        return this;
    }

    public void setToolSize(Double toolSize) {
        this.toolSize = toolSize;
    }

    public String getIntroduction() {
        return introduction;
    }

    public SwToolsHistory withIntroduction(String introduction) {
        this.setIntroduction(introduction);
        return this;
    }

    public void setIntroduction(String introduction) {
        this.introduction = introduction == null ? null : introduction.trim();
    }

    public String getUpdateContent() {
        return updateContent;
    }

    public SwToolsHistory withUpdateContent(String updateContent) {
        this.setUpdateContent(updateContent);
        return this;
    }

    public void setUpdateContent(String updateContent) {
        this.updateContent = updateContent == null ? null : updateContent.trim();
    }

    public String getUseGuide() {
        return useGuide;
    }

    public SwToolsHistory withUseGuide(String useGuide) {
        this.setUseGuide(useGuide);
        return this;
    }

    public void setUseGuide(String useGuide) {
        this.useGuide = useGuide == null ? null : useGuide.trim();
    }

    public String getOperator() {
        return operator;
    }

    public SwToolsHistory withOperator(String operator) {
        this.setOperator(operator);
        return this;
    }

    public void setOperator(String operator) {
        this.operator = operator == null ? null : operator.trim();
    }

    public LocalDateTime getCreateTime() {
        return createTime;
    }

    public SwToolsHistory withCreateTime(LocalDateTime createTime) {
        this.setCreateTime(createTime);
        return this;
    }

    public void setCreateTime(LocalDateTime createTime) {
        this.createTime = createTime;
    }

    public LocalDateTime getDatetimeLastchange() {
        return datetimeLastchange;
    }

    public SwToolsHistory withDatetimeLastchange(LocalDateTime datetimeLastchange) {
        this.setDatetimeLastchange(datetimeLastchange);
        return this;
    }

    public void setDatetimeLastchange(LocalDateTime datetimeLastchange) {
        this.datetimeLastchange = datetimeLastchange;
    }
}