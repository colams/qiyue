package com.foxconn.sw.data.dto;

import java.util.List;

public class PageEntity<T> {

    public PageEntity(int totalCount, List<T> list) {
        this.totalCount = totalCount;
        this.list = list;
    }

    private int totalCount;

    private List<T> list;

    public int getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(int totalCount) {
        this.totalCount = totalCount;
    }

    public List<T> getList() {
        return list;
    }

    public void setList(List<T> list) {
        this.list = list;
    }
}
