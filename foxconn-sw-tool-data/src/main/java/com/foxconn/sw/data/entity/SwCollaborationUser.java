package com.foxconn.sw.data.entity;

import java.time.LocalDateTime;

public class SwCollaborationUser {
    private Integer id;

    private Integer taskId;

    private String employeeNo;

    private Integer isDelete;

    private Integer status;

    private LocalDateTime createTime;

    private LocalDateTime datetimeLastchange;

    public Integer getId() {
        return id;
    }

    public SwCollaborationUser withId(Integer id) {
        this.setId(id);
        return this;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getTaskId() {
        return taskId;
    }

    public SwCollaborationUser withTaskId(Integer taskId) {
        this.setTaskId(taskId);
        return this;
    }

    public void setTaskId(Integer taskId) {
        this.taskId = taskId;
    }

    public String getEmployeeNo() {
        return employeeNo;
    }

    public SwCollaborationUser withEmployeeNo(String employeeNo) {
        this.setEmployeeNo(employeeNo);
        return this;
    }

    public void setEmployeeNo(String employeeNo) {
        this.employeeNo = employeeNo == null ? null : employeeNo.trim();
    }

    public Integer getIsDelete() {
        return isDelete;
    }

    public SwCollaborationUser withIsDelete(Integer isDelete) {
        this.setIsDelete(isDelete);
        return this;
    }

    public void setIsDelete(Integer isDelete) {
        this.isDelete = isDelete;
    }

    public Integer getStatus() {
        return status;
    }

    public SwCollaborationUser withStatus(Integer status) {
        this.setStatus(status);
        return this;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public LocalDateTime getCreateTime() {
        return createTime;
    }

    public SwCollaborationUser withCreateTime(LocalDateTime createTime) {
        this.setCreateTime(createTime);
        return this;
    }

    public void setCreateTime(LocalDateTime createTime) {
        this.createTime = createTime;
    }

    public LocalDateTime getDatetimeLastchange() {
        return datetimeLastchange;
    }

    public SwCollaborationUser withDatetimeLastchange(LocalDateTime datetimeLastchange) {
        this.setDatetimeLastchange(datetimeLastchange);
        return this;
    }

    public void setDatetimeLastchange(LocalDateTime datetimeLastchange) {
        this.datetimeLastchange = datetimeLastchange;
    }
}