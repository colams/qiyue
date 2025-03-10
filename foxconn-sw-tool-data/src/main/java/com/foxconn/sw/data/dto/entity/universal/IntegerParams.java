package com.foxconn.sw.data.dto.entity.universal;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

public class IntegerParams {

    /**
     * params
     */
    @NotNull(message = "参数不能为空")
    @Min(value = 0, message = "参数不能小于0")
    private Integer params;

    public Integer getParams() {
        return params;
    }

    public void setParams(Integer params) {
        this.params = params;
    }
}
