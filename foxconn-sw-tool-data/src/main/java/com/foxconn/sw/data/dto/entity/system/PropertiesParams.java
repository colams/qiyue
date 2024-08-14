package com.foxconn.sw.data.dto.entity.system;

import com.foxconn.sw.data.constants.enums.BasicPropertyEnums;
import com.foxconn.sw.data.validate.EnumValue;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Pattern;

public class PropertiesParams {

    @Schema(description = "基础分类信息：", allowableValues = {"0-所有", "1-工具库工具分类", "1-系统功能模块"})
    @EnumValue(enumClass = BasicPropertyEnums.class, message = "超出基础分类信息")
    private Integer category;

    @Schema(description = "根据属性名获取属性信息")
    @Pattern(regexp = "^\\S*$", message = "字符串不能全部为空格")
    private String propertyName;

    public Integer getCategory() {
        return category;
    }

    public void setCategory(Integer category) {
        this.category = category;
    }

    public String getPropertyName() {
        return propertyName;
    }

    public void setPropertyName(String propertyName) {
        this.propertyName = propertyName;
    }

}
