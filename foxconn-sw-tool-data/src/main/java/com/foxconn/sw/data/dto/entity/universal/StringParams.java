package com.foxconn.sw.data.dto.entity.universal;

import jakarta.validation.constraints.NotNull;
import org.hibernate.validator.constraints.Length;

public class StringParams {

    /**
     * employeeNo
     */
    @NotNull(message = "参数不能为空")
    @Length(min = 1, message = "参数不能为空")
    private String params;

    public String getParams() {
        return params;
    }

    public void setParams(String params) {
        this.params = params;
    }
}
