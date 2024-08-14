package com.foxconn.sw.data.dto.entity.universal;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import org.hibernate.validator.constraints.Length;

public class StringParams {

    /**
     * employeeNo
     */
    @NotNull(message = "参数不能为空")
    @Pattern(regexp = "^\\S*$", message = "字符串不能全部为空格")
    private String params;

    public String getParams() {
        return params;
    }

    public void setParams(String params) {
        this.params = params;
    }
}
