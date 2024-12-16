package com.foxconn.sw.data.dto;

import com.google.common.collect.Lists;
import io.swagger.v3.oas.annotations.media.Schema;

import java.util.List;

public class PageEntity<T> {

    public PageEntity() {
        this.totalCount = 0L;
        this.list = Lists.newArrayList();
    }

    public PageEntity(Long totalCount, List<T> list) {
        this.totalCount = totalCount;
        this.list = list;
    }

    @Schema(description = "总页数")
    private Long totalCount;

    @Schema(description = "结果对象List")
    private List<T> list;


    public Long getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(Long totalCount) {
        this.totalCount = totalCount;
    }

    public List<T> getList() {
        return list;
    }

    public void setList(List<T> list) {
        this.list = list;
    }

}
