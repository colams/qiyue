package com.foxconn.sw.data.entity;

import java.time.LocalDateTime;

public class SwSubTask {
    private Integer id;

    private Integer taskId;

    private Long taskNo;

    private String title;

    private String level;

    private Integer status;

    private String handleNo;

    private String deadLine;

    private LocalDateTime createTime;

    private LocalDateTime datetimeLastchange;

    public Integer getId() {
        return id;
    }

    public SwSubTask withId(Integer id) {
        this.setId(id);
        return this;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getTaskId() {
        return taskId;
    }

    public SwSubTask withTaskId(Integer taskId) {
        this.setTaskId(taskId);
        return this;
    }

    public void setTaskId(Integer taskId) {
        this.taskId = taskId;
    }

    public Long getTaskNo() {
        return taskNo;
    }

    public SwSubTask withTaskNo(Long taskNo) {
        this.setTaskNo(taskNo);
        return this;
    }

    public void setTaskNo(Long taskNo) {
        this.taskNo = taskNo;
    }

    public String getTitle() {
        return title;
    }

    public SwSubTask withTitle(String title) {
        this.setTitle(title);
        return this;
    }

    public void setTitle(String title) {
        this.title = title == null ? null : title.trim();
    }

    public String getLevel() {
        return level;
    }

    public SwSubTask withLevel(String level) {
        this.setLevel(level);
        return this;
    }

    public void setLevel(String level) {
        this.level = level == null ? null : level.trim();
    }

    public Integer getStatus() {
        return status;
    }

    public SwSubTask withStatus(Integer status) {
        this.setStatus(status);
        return this;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getHandleNo() {
        return handleNo;
    }

    public SwSubTask withHandleNo(String handleNo) {
        this.setHandleNo(handleNo);
        return this;
    }

    public void setHandleNo(String handleNo) {
        this.handleNo = handleNo == null ? null : handleNo.trim();
    }

    public String getDeadLine() {
        return deadLine;
    }

    public SwSubTask withDeadLine(String deadLine) {
        this.setDeadLine(deadLine);
        return this;
    }

    public void setDeadLine(String deadLine) {
        this.deadLine = deadLine == null ? null : deadLine.trim();
    }

    public LocalDateTime getCreateTime() {
        return createTime;
    }

    public SwSubTask withCreateTime(LocalDateTime createTime) {
        this.setCreateTime(createTime);
        return this;
    }

    public void setCreateTime(LocalDateTime createTime) {
        this.createTime = createTime;
    }

    public LocalDateTime getDatetimeLastchange() {
        return datetimeLastchange;
    }

    public SwSubTask withDatetimeLastchange(LocalDateTime datetimeLastchange) {
        this.setDatetimeLastchange(datetimeLastchange);
        return this;
    }

    public void setDatetimeLastchange(LocalDateTime datetimeLastchange) {
        this.datetimeLastchange = datetimeLastchange;
    }
}