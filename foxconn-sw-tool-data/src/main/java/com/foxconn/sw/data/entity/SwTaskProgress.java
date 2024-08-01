package com.foxconn.sw.data.entity;

import java.time.LocalDateTime;

public class SwTaskProgress {
    private Integer id;

    private Integer taskId;

    private String operateEid;

    private String resourceIds;

    private String content;

    private String remark;

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

    public String getRemark() {
        return remark;
    }

    public SwTaskProgress withRemark(String remark) {
        this.setRemark(remark);
        return this;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
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