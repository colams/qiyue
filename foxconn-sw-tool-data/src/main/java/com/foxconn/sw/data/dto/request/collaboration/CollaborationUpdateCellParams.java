package com.foxconn.sw.data.dto.request.collaboration;

import jakarta.validation.constraints.NotNull;

public class CollaborationUpdateCellParams {
    @NotNull
    private Integer taskID;
    private Long detailID;
    private Long scuID;
    private Integer colIndex;
    private Integer rowIndex;
    private String item;
    private String value;

    public Integer getTaskID() {
        return taskID;
    }

    public void setTaskID(Integer taskID) {
        this.taskID = taskID;
    }

    public Long getDetailID() {
        return detailID;
    }

    public void setDetailID(Long detailID) {
        this.detailID = detailID;
    }

    public Long getScuID() {
        return scuID;
    }

    public void setScuID(Long scuID) {
        this.scuID = scuID;
    }

    public Integer getColIndex() {
        return colIndex;
    }

    public void setColIndex(Integer colIndex) {
        this.colIndex = colIndex;
    }

    public Integer getRowIndex() {
        return rowIndex;
    }

    public void setRowIndex(Integer rowIndex) {
        this.rowIndex = rowIndex;
    }

    public String getItem() {
        return item;
    }

    public void setItem(String item) {
        this.item = item;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
