package com.foxconn.sw.data.dto.entity.project;

public class HeaderVo {

    private String title;
    private Integer rowSpan;
    private Integer colSpan;
    private Integer rowIndex;
    private Integer colIndex;

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

    public Integer getRowIndex() {
        return rowIndex;
    }

    public void setRowIndex(Integer rowIndex) {
        this.rowIndex = rowIndex;
    }

    public Integer getColIndex() {
        return colIndex;
    }

    public void setColIndex(Integer colIndex) {
        this.colIndex = colIndex;
    }
}
