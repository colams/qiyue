package com.foxconn.sw.data.dto.entity.system;

import io.swagger.v3.oas.annotations.media.Schema;

public class PropertiesParams {

    @Schema(description = "基础分类信息：", allowableValues = {"0-所有", "1-工具库工具分类", "1-系统功能模块"})
    private int category;

    @Schema(description = "根据属性名获取属性信息")
    private String propertyName;

    public int getCategory() {
        return category;
    }

    public void setCategory(int category) {
        this.category = category;
    }

    public String getPropertyName() {
        return propertyName;
    }

    public void setPropertyName(String propertyName) {
        this.propertyName = propertyName;
    }

}
