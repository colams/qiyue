package com.foxconn.sw.data.entity;

import java.time.LocalDateTime;

public class SwWorkReportLock {
    private Integer id;

    private String yearWeek;

    private String operator;

    private Integer lockStatus;

    private LocalDateTime createTime;

    private LocalDateTime datetimeLastchange;

    public Integer getId() {
        return id;
    }

    public SwWorkReportLock withId(Integer id) {
        this.setId(id);
        return this;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getYearWeek() {
        return yearWeek;
    }

    public SwWorkReportLock withYearWeek(String yearWeek) {
        this.setYearWeek(yearWeek);
        return this;
    }

    public void setYearWeek(String yearWeek) {
        this.yearWeek = yearWeek == null ? null : yearWeek.trim();
    }

    public String getOperator() {
        return operator;
    }

    public SwWorkReportLock withOperator(String operator) {
        this.setOperator(operator);
        return this;
    }

    public void setOperator(String operator) {
        this.operator = operator == null ? null : operator.trim();
    }

    public Integer getLockStatus() {
        return lockStatus;
    }

    public SwWorkReportLock withLockStatus(Integer lockStatus) {
        this.setLockStatus(lockStatus);
        return this;
    }

    public void setLockStatus(Integer lockStatus) {
        this.lockStatus = lockStatus;
    }

    public LocalDateTime getCreateTime() {
        return createTime;
    }

    public SwWorkReportLock withCreateTime(LocalDateTime createTime) {
        this.setCreateTime(createTime);
        return this;
    }

    public void setCreateTime(LocalDateTime createTime) {
        this.createTime = createTime;
    }

    public LocalDateTime getDatetimeLastchange() {
        return datetimeLastchange;
    }

    public SwWorkReportLock withDatetimeLastchange(LocalDateTime datetimeLastchange) {
        this.setDatetimeLastchange(datetimeLastchange);
        return this;
    }

    public void setDatetimeLastchange(LocalDateTime datetimeLastchange) {
        this.datetimeLastchange = datetimeLastchange;
    }
}