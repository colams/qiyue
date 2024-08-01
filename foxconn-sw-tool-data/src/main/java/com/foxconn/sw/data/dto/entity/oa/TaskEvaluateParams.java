package com.foxconn.sw.data.dto.entity.oa;

import jakarta.validation.constraints.NotNull;
import org.springframework.stereotype.Component;

@Component
public class TaskEvaluateParams {

    /**
     * 任务ID
     */
    @NotNull(message = "任务ID不能为空")
    private Integer taskId;

    /**
     * 完成度
     */
    @NotNull(message = "完成度评价不能为空")
    private Integer completion;

    /**
     * 效率
     */
    @NotNull(message = "效率评价不能为空")
    private Integer efficiency;

    /**
     * 质量
     */
    @NotNull(message = "质量评价不能为空")
    private Integer quality;

    /**
     * 评价内容
     */
    private String content;

    public Integer getTaskId() {
        return taskId;
    }

    public void setTaskId(Integer taskId) {
        this.taskId = taskId;
    }

    public Integer getCompletion() {
        return completion;
    }

    public void setCompletion(Integer completion) {
        this.completion = completion;
    }

    public Integer getEfficiency() {
        return efficiency;
    }

    public void setEfficiency(Integer efficiency) {
        this.efficiency = efficiency;
    }

    public Integer getQuality() {
        return quality;
    }

    public void setQuality(Integer quality) {
        this.quality = quality;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
