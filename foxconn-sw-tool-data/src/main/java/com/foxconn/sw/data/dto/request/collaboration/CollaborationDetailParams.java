package com.foxconn.sw.data.dto.request.collaboration;

import jakarta.validation.constraints.NotNull;

public class CollaborationDetailParams {

    @NotNull
    private Integer taskID;

    private Long id;

    public Integer getTaskID() {
        return taskID;
    }

    public void setTaskID(Integer taskID) {
        this.taskID = taskID;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
