package com.foxconn.sw.data.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

public class PageParams<T> {

    @Schema(description = "请求页码")
    @NotNull(message = "参数不能为空")
    @Min(value = 1, message = "页号必须大于0")
    private Integer currentPage = 0;

    @Schema(description = "请求页容量，每页数量")
    @NotNull(message = "参数不能为空")
    @Min(value = 1, message = "页容量必须大于0")
    private Integer pageSize = 10;

    @Schema(description = "查询参数信息")
    @Valid
    @NotNull(message = "params对象不能为null！")
    private T params;

    public Integer getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(Integer currentPage) {
        this.currentPage = currentPage;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public T getParams() {
        return params;
    }

    public void setParams(T params) {
        this.params = params;
    }
}
