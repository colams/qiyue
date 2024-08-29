package com.foxconn.sw.data.dto.entity.oa;

import jakarta.validation.constraints.NotNull;

public class FollowParams {

    @NotNull(message = "taskId 不能为空")
    private Integer taskID;

    private String content;

    public Integer getTaskID() {
        return taskID;
    }

    public void setTaskID(Integer taskID) {
        this.taskID = taskID;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
