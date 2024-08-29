package com.foxconn.sw.data.entity;

import java.time.LocalDateTime;

public class SwTaskProgress {
    private Integer id;

    private Integer taskId;

    private String operateEid;

    private String resourceIds;

    private Integer progress;

    private String content;

    private LocalDateTime datetimeLastchange;

    public Integer getId() {
        return id;
    }

    public SwTaskProgress withId(Integer id) {
        this.setId(id);
        return this;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getTaskId() {
        return taskId;
    }

    public SwTaskProgress withTaskId(Integer taskId) {
        this.setTaskId(taskId);
        return this;
    }

    public void setTaskId(Integer taskId) {
        this.taskId = taskId;
    }

    public String getOperateEid() {
        return operateEid;
    }

    public SwTaskProgress withOperateEid(String operateEid) {
        this.setOperateEid(operateEid);
        return this;
    }

    public void setOperateEid(String operateEid) {
        this.operateEid = operateEid == null ? null : operateEid.trim();
    }

    public String getResourceIds() {
        return resourceIds;
    }

    public SwTaskProgress withResourceIds(String resourceIds) {
        this.setResourceIds(resourceIds);
        return this;
    }

    public void setResourceIds(String resourceIds) {
        this.resourceIds = resourceIds == null ? null : resourceIds.trim();
    }

    public Integer getProgress() {
        return progress;
    }

    public SwTaskProgress withProgress(Integer progress) {
        this.setProgress(progress);
        return this;
    }

    public void setProgress(Integer progress) {
        this.progress = progress;
    }

    public String getContent() {
        return content;
    }

    public SwTaskProgress withContent(String content) {
        this.setContent(content);
        return this;
    }

    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }

    public LocalDateTime getDatetimeLastchange() {
        return datetimeLastchange;
    }

    public SwTaskProgress withDatetimeLastchange(LocalDateTime datetimeLastchange) {
        this.setDatetimeLastchange(datetimeLastchange);
        return this;
    }

    public void setDatetimeLastchange(LocalDateTime datetimeLastchange) {
        this.datetimeLastchange = datetimeLastchange;
    }
}