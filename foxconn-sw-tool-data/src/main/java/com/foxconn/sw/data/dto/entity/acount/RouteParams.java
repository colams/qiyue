package com.foxconn.sw.data.dto.entity.acount;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;

public class RouteParams {

    @NotNull
    @PositiveOrZero(message = "模块号不能为负数")
    private Integer moduleNo;


    public Integer getModuleNo() {
        return moduleNo;
    }

    public void setModuleNo(Integer moduleNo) {
        this.moduleNo = moduleNo;
    }
}
