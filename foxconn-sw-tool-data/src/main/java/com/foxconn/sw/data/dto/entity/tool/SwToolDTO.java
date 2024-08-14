package com.foxconn.sw.data.dto.entity.tool;

import java.time.LocalDateTime;

public class SwToolDTO {

    private Integer toolId;

    private Integer historyId;

    private String toolName;

    private String toolIcon;

    private Integer propertyId;

    private String categoryName;

    private String versionNo;

    private String filePath;

    private Integer resourceId;

    private Double toolSize;

    private String introduction;

    private String updateContent;

    private String useGuide;

    private String operator;

    private LocalDateTime datetimeLastchange;

    public Integer getToolId() {
        return toolId;
    }

    public void setToolId(Integer toolId) {
        this.toolId = toolId;
    }

    public Integer getHistoryId() {
        return historyId;
    }

    public void setHistoryId(Integer historyId) {
        this.historyId = historyId;
    }

    public String getToolName() {
        return toolName;
    }

    public void setToolName(String toolName) {
        this.toolName = toolName;
    }

    public String getToolIcon() {
        return toolIcon;
    }

    public void setToolIcon(String toolIcon) {
        this.toolIcon = toolIcon;
    }

    public Integer getPropertyId() {
        return propertyId;
    }

    public void setPropertyId(Integer propertyId) {
        this.propertyId = propertyId;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public String getVersionNo() {
        return versionNo;
    }

    public void setVersionNo(String versionNo) {
        this.versionNo = versionNo;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public Integer getResourceId() {
        return resourceId;
    }

    public void setResourceId(Integer resourceId) {
        this.resourceId = resourceId;
    }

    public Double getToolSize() {
        return toolSize;
    }

    public void setToolSize(Double toolSize) {
        this.toolSize = toolSize;
    }

    public String getIntroduction() {
        return introduction;
    }

    public void setIntroduction(String introduction) {
        this.introduction = introduction;
    }

    public String getUpdateContent() {
        return updateContent;
    }

    public void setUpdateContent(String updateContent) {
        this.updateContent = updateContent;
    }

    public String getUseGuide() {
        return useGuide;
    }

    public void setUseGuide(String useGuide) {
        this.useGuide = useGuide;
    }

    public String getOperator() {
        return operator;
    }

    public void setOperator(String operator) {
        this.operator = operator;
    }

    public LocalDateTime getDatetimeLastchange() {
        return datetimeLastchange;
    }

    public void setDatetimeLastchange(LocalDateTime datetimeLastchange) {
        this.datetimeLastchange = datetimeLastchange;
    }
}
