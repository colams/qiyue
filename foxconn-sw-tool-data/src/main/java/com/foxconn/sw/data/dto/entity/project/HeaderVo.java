package com.foxconn.sw.data.dto.entity.project;

public class HeaderVo {

    private String title;
    private Integer rowSpan;
    private Integer colSpan;

    public HeaderVo() {
    }

    public HeaderVo(String title, Integer rowSpan, Integer colSpan) {
        this.title = title;
        this.rowSpan = rowSpan;
        this.colSpan = colSpan;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getRowSpan() {
        return rowSpan;
    }

    public void setRowSpan(Integer rowSpan) {
        this.rowSpan = rowSpan;
    }

    public Integer getColSpan() {
        return colSpan;
    }

    public void setColSpan(Integer colSpan) {
        this.colSpan = colSpan;
    }

}
