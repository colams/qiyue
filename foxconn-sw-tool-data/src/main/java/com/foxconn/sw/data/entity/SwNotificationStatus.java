package com.foxconn.sw.data.entity;

import java.time.LocalDateTime;

public class SwNotificationStatus {
    private Long id;

    private Long notificationId;

    private String employeeNo;

    private String status;

    private LocalDateTime createTime;

    private LocalDateTime datetimeLastchange;

    public Long getId() {
        return id;
    }

    public SwNotificationStatus withId(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getNotificationId() {
        return notificationId;
    }

    public SwNotificationStatus withNotificationId(Long notificationId) {
        this.setNotificationId(notificationId);
        return this;
    }

    public void setNotificationId(Long notificationId) {
        this.notificationId = notificationId;
    }

    public String getEmployeeNo() {
        return employeeNo;
    }

    public SwNotificationStatus withEmployeeNo(String employeeNo) {
        this.setEmployeeNo(employeeNo);
        return this;
    }

    public void setEmployeeNo(String employeeNo) {
        this.employeeNo = employeeNo == null ? null : employeeNo.trim();
    }

    public String getStatus() {
        return status;
    }

    public SwNotificationStatus withStatus(String status) {
        this.setStatus(status);
        return this;
    }

    public void setStatus(String status) {
        this.status = status == null ? null : status.trim();
    }

    public LocalDateTime getCreateTime() {
        return createTime;
    }

    public SwNotificationStatus withCreateTime(LocalDateTime createTime) {
        this.setCreateTime(createTime);
        return this;
    }

    public void setCreateTime(LocalDateTime createTime) {
        this.createTime = createTime;
    }

    public LocalDateTime getDatetimeLastchange() {
        return datetimeLastchange;
    }

    public SwNotificationStatus withDatetimeLastchange(LocalDateTime datetimeLastchange) {
        this.setDatetimeLastchange(datetimeLastchange);
        return this;
    }

    public void setDatetimeLastchange(LocalDateTime datetimeLastchange) {
        this.datetimeLastchange = datetimeLastchange;
    }
}