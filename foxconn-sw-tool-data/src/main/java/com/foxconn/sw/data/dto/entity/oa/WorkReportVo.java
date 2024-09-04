package com.foxconn.sw.data.dto.entity.oa;

import com.foxconn.sw.data.dto.entity.acount.EmployeeVo;
import jakarta.validation.constraints.NotNull;

public class WorkReportVo {

    private int id;
    private int week;
    private int num;
    private EmployeeVo employee;
    @NotNull(message = "内容不能为空")
    private String project;
    @NotNull(message = "内容不能为空")
    private String description;
    private int day;

    private int target;

    @NotNull(message = "内容不能为空")
    private int current;

    private String remark;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getWeek() {
        return week;
    }

    public void setWeek(int week) {
        this.week = week;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public EmployeeVo getEmployee() {
        return employee;
    }

    public void setEmployee(EmployeeVo employee) {
        this.employee = employee;
    }

    public String getProject() {
        return project;
    }

    public void setProject(String project) {
        this.project = project;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getDay() {
        return day;
    }

    public void setDay(int day) {
        this.day = day;
    }

    public int getTarget() {
        return target;
    }

    public void setTarget(int target) {
        this.target = target;
    }

    public int getCurrent() {
        return current;
    }

    public void setCurrent(int current) {
        this.current = current;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}
