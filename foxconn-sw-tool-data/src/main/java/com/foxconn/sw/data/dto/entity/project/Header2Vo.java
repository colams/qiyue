package com.foxconn.sw.data.dto.entity.project;

import java.util.List;

public class Header2Vo {

    private String title;
    private Integer rowIndexStart;
    private Integer colIndexStart;
    private Integer rowIndexEnd;
    private Integer colIndexEnd;
    private List<Header2Vo> header2Vos;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getRowIndexStart() {
        return rowIndexStart;
    }

    public void setRowIndexStart(Integer rowIndexStart) {
        this.rowIndexStart = rowIndexStart;
    }

    public Integer getColIndexStart() {
        return colIndexStart;
    }

    public void setColIndexStart(Integer colIndexStart) {
        this.colIndexStart = colIndexStart;
    }

    public Integer getRowIndexEnd() {
        return rowIndexEnd;
    }

    public void setRowIndexEnd(Integer rowIndexEnd) {
        this.rowIndexEnd = rowIndexEnd;
    }

    public Integer getColIndexEnd() {
        return colIndexEnd;
    }

    public void setColIndexEnd(Integer colIndexEnd) {
        this.colIndexEnd = colIndexEnd;
    }

    public List<Header2Vo> getHeader2Vos() {
        return header2Vos;
    }

    public void setHeader2Vos(List<Header2Vo> header2Vos) {
        this.header2Vos = header2Vos;
    }
}
