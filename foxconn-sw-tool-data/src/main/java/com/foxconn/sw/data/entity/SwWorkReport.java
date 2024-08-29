package com.foxconn.sw.data.entity;

import java.time.LocalDateTime;

public class SwWorkReport {
    private Integer id;

    private String employeeNo;

    private Integer week;

    private Integer num;

    private String project;

    private String projectItem;

    private Integer days;

    private Integer target;

    private Integer current;

    private Integer status;

    private String description;

    private String remark;

    private LocalDateTime createTime;

    private LocalDateTime datetimeLastchange;

    public Integer getId() {
        return id;
    }

    public SwWorkReport withId(Integer id) {
        this.setId(id);
        return this;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getEmployeeNo() {
        return employeeNo;
    }

    public SwWorkReport withEmployeeNo(String employeeNo) {
        this.setEmployeeNo(employeeNo);
        return this;
    }

    public void setEmployeeNo(String employeeNo) {
        this.employeeNo = employeeNo == null ? null : employeeNo.trim();
    }

    public Integer getWeek() {
        return week;
    }

    public SwWorkReport withWeek(Integer week) {
        this.setWeek(week);
        return this;
    }

    public void setWeek(Integer week) {
        this.week = week;
    }

    public Integer getNum() {
        return num;
    }

    public SwWorkReport withNum(Integer num) {
        this.setNum(num);
        return this;
    }

    public void setNum(Integer num) {
        this.num = num;
    }

    public String getProject() {
        return project;
    }

    public SwWorkReport withProject(String project) {
        this.setProject(project);
        return this;
    }

    public void setProject(String project) {
        this.project = project == null ? null : project.trim();
    }

    public String getProjectItem() {
        return projectItem;
    }

    public SwWorkReport withProjectItem(String projectItem) {
        this.setProjectItem(projectItem);
        return this;
    }

    public void setProjectItem(String projectItem) {
        this.projectItem = projectItem == null ? null : projectItem.trim();
    }

    public Integer getDays() {
        return days;
    }

    public SwWorkReport withDays(Integer days) {
        this.setDays(days);
        return this;
    }

    public void setDays(Integer days) {
        this.days = days;
    }

    public Integer getTarget() {
        return target;
    }

    public SwWorkReport withTarget(Integer target) {
        this.setTarget(target);
        return this;
    }

    public void setTarget(Integer target) {
        this.target = target;
    }

    public Integer getCurrent() {
        return current;
    }

    public SwWorkReport withCurrent(Integer current) {
        this.setCurrent(current);
        return this;
    }

    public void setCurrent(Integer current) {
        this.current = current;
    }

    public Integer getStatus() {
        return status;
    }

    public SwWorkReport withStatus(Integer status) {
        this.setStatus(status);
        return this;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getDescription() {
        return description;
    }

    public SwWorkReport withDescription(String description) {
        this.setDescription(description);
        return this;
    }

    public void setDescription(String description) {
        this.description = description == null ? null : description.trim();
    }

    public String getRemark() {
        return remark;
    }

    public SwWorkReport withRemark(String remark) {
        this.setRemark(remark);
        return this;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

    public LocalDateTime getCreateTime() {
        return createTime;
    }

    public SwWorkReport withCreateTime(LocalDateTime createTime) {
        this.setCreateTime(createTime);
        return this;
    }

    public void setCreateTime(LocalDateTime createTime) {
        this.createTime = createTime;
    }

    public LocalDateTime getDatetimeLastchange() {
        return datetimeLastchange;
    }

    public SwWorkReport withDatetimeLastchange(LocalDateTime datetimeLastchange) {
        this.setDatetimeLastchange(datetimeLastchange);
        return this;
    }

    public void setDatetimeLastchange(LocalDateTime datetimeLastchange) {
        this.datetimeLastchange = datetimeLastchange;
    }
}