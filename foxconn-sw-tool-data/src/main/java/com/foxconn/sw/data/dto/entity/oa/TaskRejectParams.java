package com.foxconn.sw.data.dto.entity.oa;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.springframework.stereotype.Component;

@Component
public class TaskRejectParams {

    /**
     * 任务ID
     */
    @NotNull(message = "任务ID不能为空")
    private Integer taskId;

    /**
     * 驳回内容
     */
    @NotBlank(message = "内容不能为空字符串")
    @NotNull(message = "内容不能为空")
    private String content;

    public Integer getTaskId() {
        return taskId;
    }

    public void setTaskId(Integer taskId) {
        this.taskId = taskId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
