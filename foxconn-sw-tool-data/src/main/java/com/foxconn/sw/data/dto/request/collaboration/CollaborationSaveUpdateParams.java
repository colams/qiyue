package com.foxconn.sw.data.dto.request.collaboration;

import jakarta.validation.constraints.NotNull;

public class CollaborationSaveUpdateParams {
    @NotNull
    private Integer taskID;
    private Long detailID;
    private Long scuID;

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
}
