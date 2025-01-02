package com.foxconn.sw.data.dto.request.collaboration;

import jakarta.validation.constraints.NotNull;

public class CollaborationSaveUpdateParams {
    @NotNull
    private Integer taskID;

    public Integer getTaskID() {
        return taskID;
    }

    public void setTaskID(Integer taskID) {
        this.taskID = taskID;
    }

}
