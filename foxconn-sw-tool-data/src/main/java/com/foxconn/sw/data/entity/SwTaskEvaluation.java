package com.foxconn.sw.data.entity;

import java.time.LocalDateTime;

public class SwTaskEvaluation {
    private Integer id;

    private Integer taskId;

    private Integer completion;

    private Integer efficiency;

    private Integer quality;

    private String content;

    private LocalDateTime createTime;

    public Integer getId() {
        return id;
    }

    public SwTaskEvaluation withId(Integer id) {
        this.setId(id);
        return this;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getTaskId() {
        return taskId;
    }

    public SwTaskEvaluation withTaskId(Integer taskId) {
        this.setTaskId(taskId);
        return this;
    }

    public void setTaskId(Integer taskId) {
        this.taskId = taskId;
    }

    public Integer getCompletion() {
        return completion;
    }

    public SwTaskEvaluation withCompletion(Integer completion) {
        this.setCompletion(completion);
        return this;
    }

    public void setCompletion(Integer completion) {
        this.completion = completion;
    }

    public Integer getEfficiency() {
        return efficiency;
    }

    public SwTaskEvaluation withEfficiency(Integer efficiency) {
        this.setEfficiency(efficiency);
        return this;
    }

    public void setEfficiency(Integer efficiency) {
        this.efficiency = efficiency;
    }

    public Integer getQuality() {
        return quality;
    }

    public SwTaskEvaluation withQuality(Integer quality) {
        this.setQuality(quality);
        return this;
    }

    public void setQuality(Integer quality) {
        this.quality = quality;
    }

    public String getContent() {
        return content;
    }

    public SwTaskEvaluation withContent(String content) {
        this.setContent(content);
        return this;
    }

    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }

    public LocalDateTime getCreateTime() {
        return createTime;
    }

    public SwTaskEvaluation withCreateTime(LocalDateTime createTime) {
        this.setCreateTime(createTime);
        return this;
    }

    public void setCreateTime(LocalDateTime createTime) {
        this.createTime = createTime;
    }
}