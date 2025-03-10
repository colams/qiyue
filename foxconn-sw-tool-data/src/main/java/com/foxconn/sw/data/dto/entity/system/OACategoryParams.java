package com.foxconn.sw.data.dto.entity.system;


import com.foxconn.sw.data.constants.enums.BasicPropertyEnums;
import com.foxconn.sw.data.validate.EnumValue;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Pattern;

public class OACategoryParams {
    @Schema(description = "基础分类信息：", allowableValues = {"0-所有", "1-工具库工具分类", "1-系统功能模块"})
    @EnumValue(enumClass = BasicPropertyEnums.class, message = "超出基础分类信息")
    @Pattern(regexp = "0|1", message = "有效性只能赋值：0,1")
    private Integer category;

    public Integer getCategory() {
        return category;
    }

    public void setCategory(Integer category) {
        this.category = category;
    }

}
