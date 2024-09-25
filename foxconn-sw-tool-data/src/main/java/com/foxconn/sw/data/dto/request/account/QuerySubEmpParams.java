package com.foxconn.sw.data.dto.request.account;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

public class QuerySubEmpParams {


    /**
     * departId
     */
    @NotNull(message = "参数不能为空")
    @Min(value = 0, message = "参数不能小于0")
    private Integer departId;

    /**
     * levelType
     */
    @NotNull(message = "参数不能为空")
    @Min(value = 0, message = "参数不能小于0")
    private Integer levelType;

    public Integer getDepartId() {
        return departId;
    }

    public void setDepartId(Integer departId) {
        this.departId = departId;
    }

    public Integer getLevelType() {
        return levelType;
    }

    public void setLevelType(Integer levelType) {
        this.levelType = levelType;
    }
}
