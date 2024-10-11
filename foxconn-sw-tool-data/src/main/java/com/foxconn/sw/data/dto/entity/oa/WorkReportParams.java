package com.foxconn.sw.data.dto.entity.oa;

import jakarta.validation.constraints.NotNull;

import java.util.List;

public class WorkReportParams {

    private Integer id;
    private Integer week;
    private List<String> projectCode;
    @NotNull(message = "内容不能为空")
    private String description;
    private Double day;
    private Integer target;
    private Integer current;
    private String remark;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getWeek() {
        return week;
    }

    public void setWeek(Integer week) {
        this.week = week;
    }

    public List<String> getProjectCode() {
        return projectCode;
    }

    public void setProjectCode(List<String> projectCode) {
        this.projectCode = projectCode;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Double getDay() {
        return day;
    }

    public void setDay(Double day) {
        this.day = day;
    }

    public Integer getTarget() {
        return target;
    }

    public void setTarget(Integer target) {
        this.target = target;
    }

    public Integer getCurrent() {
        return current;
    }

    public void setCurrent(Integer current) {
        this.current = current;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}
