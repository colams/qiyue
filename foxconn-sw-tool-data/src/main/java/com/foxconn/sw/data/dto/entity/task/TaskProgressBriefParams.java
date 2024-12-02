package com.foxconn.sw.data.dto.entity.task;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.List;

public class TaskProgressBriefParams {

    @NotNull(message = "taskId 不能为空")
    private Integer taskId;

    private List<Integer> resourceIds;

    @NotBlank(message = "内容不能为空字符串")
    @NotNull(message = "内容不能为空")
    private String content;

    private Integer progress;

    public Integer getTaskId() {
        return taskId;
    }

    public void setTaskId(Integer taskId) {
        this.taskId = taskId;
    }

    public List<Integer> getResourceIds() {
        return resourceIds;
    }

    public void setResourceIds(List<Integer> resourceIds) {
        this.resourceIds = resourceIds;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Integer getProgress() {
        return progress;
    }

    public void setProgress(Integer progress) {
        this.progress = progress;
    }
}
