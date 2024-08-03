package com.foxconn.sw.data.dto;

import io.swagger.v3.oas.annotations.media.Schema;

public class PageParams<T> {

    @Schema(description = "请求页码")
    private int currentPage = 0;

    @Schema(description = "请求页容量，每页数量")
    private int pageSize = 10;

    private T params;

    public int getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public T getParams() {
        return params;
    }

    public void setParams(T params) {
        this.params = params;
    }
}
