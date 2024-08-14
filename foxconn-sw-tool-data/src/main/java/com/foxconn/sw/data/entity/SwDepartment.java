package com.foxconn.sw.data.entity;

import java.time.LocalDateTime;

public class SwDepartment {
    private Integer id;

    private String name;

    private String description;

    private Integer parentId;

    private Integer status;

    private String startDate;

    private String endDate;

    private LocalDateTime datetimeLastchange;

    public Integer getId() {
        return id;
    }

    public SwDepartment withId(Integer id) {
        this.setId(id);
        return this;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public SwDepartment withName(String name) {
        this.setName(name);
        return this;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getDescription() {
        return description;
    }

    public SwDepartment withDescription(String description) {
        this.setDescription(description);
        return this;
    }

    public void setDescription(String description) {
        this.description = description == null ? null : description.trim();
    }

    public Integer getParentId() {
        return parentId;
    }

    public SwDepartment withParentId(Integer parentId) {
        this.setParentId(parentId);
        return this;
    }

    public void setParentId(Integer parentId) {
        this.parentId = parentId;
    }

    public Integer getStatus() {
        return status;
    }

    public SwDepartment withStatus(Integer status) {
        this.setStatus(status);
        return this;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getStartDate() {
        return startDate;
    }

    public SwDepartment withStartDate(String startDate) {
        this.setStartDate(startDate);
        return this;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate == null ? null : startDate.trim();
    }

    public String getEndDate() {
        return endDate;
    }

    public SwDepartment withEndDate(String endDate) {
        this.setEndDate(endDate);
        return this;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate == null ? null : endDate.trim();
    }

    public LocalDateTime getDatetimeLastchange() {
        return datetimeLastchange;
    }

    public SwDepartment withDatetimeLastchange(LocalDateTime datetimeLastchange) {
        this.setDatetimeLastchange(datetimeLastchange);
        return this;
    }

    public void setDatetimeLastchange(LocalDateTime datetimeLastchange) {
        this.datetimeLastchange = datetimeLastchange;
    }
}