package com.foxconn.sw.data.entity;

import java.time.LocalDateTime;

public class SwWorkReportScore {
    private Integer id;

    private String employeeNo;

    private String yearWeek;

    private Integer score;

    private String operator;

    private LocalDateTime createTime;

    private LocalDateTime datetimeLastchange;

    public Integer getId() {
        return id;
    }

    public SwWorkReportScore withId(Integer id) {
        this.setId(id);
        return this;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getEmployeeNo() {
        return employeeNo;
    }

    public SwWorkReportScore withEmployeeNo(String employeeNo) {
        this.setEmployeeNo(employeeNo);
        return this;
    }

    public void setEmployeeNo(String employeeNo) {
        this.employeeNo = employeeNo == null ? null : employeeNo.trim();
    }

    public String getYearWeek() {
        return yearWeek;
    }

    public SwWorkReportScore withYearWeek(String yearWeek) {
        this.setYearWeek(yearWeek);
        return this;
    }

    public void setYearWeek(String yearWeek) {
        this.yearWeek = yearWeek == null ? null : yearWeek.trim();
    }

    public Integer getScore() {
        return score;
    }

    public SwWorkReportScore withScore(Integer score) {
        this.setScore(score);
        return this;
    }

    public void setScore(Integer score) {
        this.score = score;
    }

    public String getOperator() {
        return operator;
    }

    public SwWorkReportScore withOperator(String operator) {
        this.setOperator(operator);
        return this;
    }

    public void setOperator(String operator) {
        this.operator = operator == null ? null : operator.trim();
    }

    public LocalDateTime getCreateTime() {
        return createTime;
    }

    public SwWorkReportScore withCreateTime(LocalDateTime createTime) {
        this.setCreateTime(createTime);
        return this;
    }

    public void setCreateTime(LocalDateTime createTime) {
        this.createTime = createTime;
    }

    public LocalDateTime getDatetimeLastchange() {
        return datetimeLastchange;
    }

    public SwWorkReportScore withDatetimeLastchange(LocalDateTime datetimeLastchange) {
        this.setDatetimeLastchange(datetimeLastchange);
        return this;
    }

    public void setDatetimeLastchange(LocalDateTime datetimeLastchange) {
        this.datetimeLastchange = datetimeLastchange;
    }
}