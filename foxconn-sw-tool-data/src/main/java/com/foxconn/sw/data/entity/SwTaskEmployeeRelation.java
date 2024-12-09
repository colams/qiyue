package com.foxconn.sw.data.entity;

import java.time.LocalDateTime;

public class SwTaskEmployeeRelation {
    private Integer id;

    private Integer taskId;

    private String employeeNo;

    private Integer prevId;

    private Integer roleFlag;

    private Integer isActive;

    private Integer isInspector;

    private Integer isRead;

    private Integer processStatus;

    private Integer isDelete;

    private LocalDateTime createTime;

    private LocalDateTime datetimeLastchange;

    public Integer getId() {
        return id;
    }

    public SwTaskEmployeeRelation withId(Integer id) {
        this.setId(id);
        return this;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getTaskId() {
        return taskId;
    }

    public SwTaskEmployeeRelation withTaskId(Integer taskId) {
        this.setTaskId(taskId);
        return this;
    }

    public void setTaskId(Integer taskId) {
        this.taskId = taskId;
    }

    public String getEmployeeNo() {
        return employeeNo;
    }

    public SwTaskEmployeeRelation withEmployeeNo(String employeeNo) {
        this.setEmployeeNo(employeeNo);
        return this;
    }

    public void setEmployeeNo(String employeeNo) {
        this.employeeNo = employeeNo == null ? null : employeeNo.trim();
    }

    public Integer getPrevId() {
        return prevId;
    }

    public SwTaskEmployeeRelation withPrevId(Integer prevId) {
        this.setPrevId(prevId);
        return this;
    }

    public void setPrevId(Integer prevId) {
        this.prevId = prevId;
    }

    public Integer getRoleFlag() {
        return roleFlag;
    }

    public SwTaskEmployeeRelation withRoleFlag(Integer roleFlag) {
        this.setRoleFlag(roleFlag);
        return this;
    }

    public void setRoleFlag(Integer roleFlag) {
        this.roleFlag = roleFlag;
    }

    public Integer getIsActive() {
        return isActive;
    }

    public SwTaskEmployeeRelation withIsActive(Integer isActive) {
        this.setIsActive(isActive);
        return this;
    }

    public void setIsActive(Integer isActive) {
        this.isActive = isActive;
    }

    public Integer getIsInspector() {
        return isInspector;
    }

    public SwTaskEmployeeRelation withIsInspector(Integer isInspector) {
        this.setIsInspector(isInspector);
        return this;
    }

    public void setIsInspector(Integer isInspector) {
        this.isInspector = isInspector;
    }

    public Integer getIsRead() {
        return isRead;
    }

    public SwTaskEmployeeRelation withIsRead(Integer isRead) {
        this.setIsRead(isRead);
        return this;
    }

    public void setIsRead(Integer isRead) {
        this.isRead = isRead;
    }

    public Integer getProcessStatus() {
        return processStatus;
    }

    public SwTaskEmployeeRelation withProcessStatus(Integer processStatus) {
        this.setProcessStatus(processStatus);
        return this;
    }

    public void setProcessStatus(Integer processStatus) {
        this.processStatus = processStatus;
    }

    public Integer getIsDelete() {
        return isDelete;
    }

    public SwTaskEmployeeRelation withIsDelete(Integer isDelete) {
        this.setIsDelete(isDelete);
        return this;
    }

    public void setIsDelete(Integer isDelete) {
        this.isDelete = isDelete;
    }

    public LocalDateTime getCreateTime() {
        return createTime;
    }

    public SwTaskEmployeeRelation withCreateTime(LocalDateTime createTime) {
        this.setCreateTime(createTime);
        return this;
    }

    public void setCreateTime(LocalDateTime createTime) {
        this.createTime = createTime;
    }

    public LocalDateTime getDatetimeLastchange() {
        return datetimeLastchange;
    }

    public SwTaskEmployeeRelation withDatetimeLastchange(LocalDateTime datetimeLastchange) {
        this.setDatetimeLastchange(datetimeLastchange);
        return this;
    }

    public void setDatetimeLastchange(LocalDateTime datetimeLastchange) {
        this.datetimeLastchange = datetimeLastchange;
    }
}