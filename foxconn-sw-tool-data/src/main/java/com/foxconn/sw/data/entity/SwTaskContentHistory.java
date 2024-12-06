package com.foxconn.sw.data.entity;

import java.time.LocalDateTime;

public class SwTaskContentHistory {
    private Integer id;

    private Integer taskId;

    private Integer progressId;

    private String operator;

    private LocalDateTime datetimeLastchange;

    private String content;

    public Integer getId() {
        return id;
    }

    public SwTaskContentHistory withId(Integer id) {
        this.setId(id);
        return this;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getTaskId() {
        return taskId;
    }

    public SwTaskContentHistory withTaskId(Integer taskId) {
        this.setTaskId(taskId);
        return this;
    }

    public void setTaskId(Integer taskId) {
        this.taskId = taskId;
    }

    public Integer getProgressId() {
        return progressId;
    }

    public SwTaskContentHistory withProgressId(Integer progressId) {
        this.setProgressId(progressId);
        return this;
    }

    public void setProgressId(Integer progressId) {
        this.progressId = progressId;
    }

    public String getOperator() {
        return operator;
    }

    public SwTaskContentHistory withOperator(String operator) {
        this.setOperator(operator);
        return this;
    }

    public void setOperator(String operator) {
        this.operator = operator == null ? null : operator.trim();
    }

    public LocalDateTime getDatetimeLastchange() {
        return datetimeLastchange;
    }

    public SwTaskContentHistory withDatetimeLastchange(LocalDateTime datetimeLastchange) {
        this.setDatetimeLastchange(datetimeLastchange);
        return this;
    }

    public void setDatetimeLastchange(LocalDateTime datetimeLastchange) {
        this.datetimeLastchange = datetimeLastchange;
    }

    public String getContent() {
        return content;
    }

    public SwTaskContentHistory withContent(String content) {
        this.setContent(content);
        return this;
    }

    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }
}