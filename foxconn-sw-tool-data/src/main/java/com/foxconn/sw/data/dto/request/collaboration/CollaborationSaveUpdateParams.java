package com.foxconn.sw.data.dto.request.collaboration;

import jakarta.validation.constraints.NotNull;

public class CollaborationSaveUpdateParams {
    @NotNull
    private Long taskID;

    public Long getTaskID() {
        return taskID;
    }

    public void setTaskID(Long taskID) {
        this.taskID = taskID;
    }

}
