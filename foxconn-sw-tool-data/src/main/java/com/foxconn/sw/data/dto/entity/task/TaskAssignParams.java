package com.foxconn.sw.data.dto.entity.task;

import jakarta.validation.constraints.NotNull;

public class TaskAssignParams {
    @NotNull
    private Integer taskId;

    @NotNull
    private String assignEid;

    private String content;

    public Integer getTaskId() {
        return taskId;
    }

    public void setTaskId(Integer taskId) {
        this.taskId = taskId;
    }

    public String getAssignEid() {
        return assignEid;
    }

    public void setAssignEid(String assignEid) {
        this.assignEid = assignEid;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
