package com.foxconn.sw.data.dto.entity.tool;

public class ToolBriefParams {

    private Integer toolId;

    private String toolName;

    private String toolIcon;

    private Integer propertyId;

    private String versionNo;

    private Double toolSize;

    private String introduction;

    private String updateContent;

    private String useGuide;

    private String operator;

    public Integer getToolId() {
        return toolId;
    }

    public void setToolId(Integer toolId) {
        this.toolId = toolId;
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

    public String getVersionNo() {
        return versionNo;
    }

    public void setVersionNo(String versionNo) {
        this.versionNo = versionNo;
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
}
