package com.foxconn.sw.data.entity;

import java.time.LocalDateTime;

public class SwCollaborationDetailSpare {
    private Long id;

    private Integer taskId;

    private Long detailId;

    private String operator;

    private String value;

    private Integer isDelete;

    private LocalDateTime createTime;

    private LocalDateTime datetimeLastchange;

    public Long getId() {
        return id;
    }

    public SwCollaborationDetailSpare withId(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getTaskId() {
        return taskId;
    }

    public SwCollaborationDetailSpare withTaskId(Integer taskId) {
        this.setTaskId(taskId);
        return this;
    }

    public void setTaskId(Integer taskId) {
        this.taskId = taskId;
    }

    public Long getDetailId() {
        return detailId;
    }

    public SwCollaborationDetailSpare withDetailId(Long detailId) {
        this.setDetailId(detailId);
        return this;
    }

    public void setDetailId(Long detailId) {
        this.detailId = detailId;
    }

    public String getOperator() {
        return operator;
    }

    public SwCollaborationDetailSpare withOperator(String operator) {
        this.setOperator(operator);
        return this;
    }

    public void setOperator(String operator) {
        this.operator = operator == null ? null : operator.trim();
    }

    public String getValue() {
        return value;
    }

    public SwCollaborationDetailSpare withValue(String value) {
        this.setValue(value);
        return this;
    }

    public void setValue(String value) {
        this.value = value == null ? null : value.trim();
    }

    public Integer getIsDelete() {
        return isDelete;
    }

    public SwCollaborationDetailSpare withIsDelete(Integer isDelete) {
        this.setIsDelete(isDelete);
        return this;
    }

    public void setIsDelete(Integer isDelete) {
        this.isDelete = isDelete;
    }

    public LocalDateTime getCreateTime() {
        return createTime;
    }

    public SwCollaborationDetailSpare withCreateTime(LocalDateTime createTime) {
        this.setCreateTime(createTime);
        return this;
    }

    public void setCreateTime(LocalDateTime createTime) {
        this.createTime = createTime;
    }

    public LocalDateTime getDatetimeLastchange() {
        return datetimeLastchange;
    }

    public SwCollaborationDetailSpare withDatetimeLastchange(LocalDateTime datetimeLastchange) {
        this.setDatetimeLastchange(datetimeLastchange);
        return this;
    }

    public void setDatetimeLastchange(LocalDateTime datetimeLastchange) {
        this.datetimeLastchange = datetimeLastchange;
    }
}