package com.foxconn.sw.data.dto.request.collaboration;

import com.foxconn.sw.data.dto.request.collaboration.cell.CellVo;

import java.util.List;

public class CollaborationUpdateRowParams {
    private Integer taskID;
    private Integer rowIndex;
    private List<CellVo> cellVoList;

    public Integer getTaskID() {
        return taskID;
    }

    public void setTaskID(Integer taskID) {
        this.taskID = taskID;
    }

    public Integer getRowIndex() {
        return rowIndex;
    }

    public void setRowIndex(Integer rowIndex) {
        this.rowIndex = rowIndex;
    }

    public List<CellVo> getCellVoList() {
        return cellVoList;
    }

    public void setCellVoList(List<CellVo> cellVoList) {
        this.cellVoList = cellVoList;
    }
}
