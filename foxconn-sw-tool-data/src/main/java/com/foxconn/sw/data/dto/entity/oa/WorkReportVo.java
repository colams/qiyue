package com.foxconn.sw.data.dto.entity.oa;

import com.foxconn.sw.data.dto.entity.acount.EmployeeVo;

import java.util.List;

public class WorkReportVo {

    private Integer week;
    private String yearWeek;
    private String employeeNo;
    private EmployeeVo employee;
    private List<WorkReportDetail> reportDetailList;
    private String message;

    public Integer getWeek() {
        return week;
    }

    public void setWeek(Integer week) {
        this.week = week;
    }

    public String getYearWeek() {
        return yearWeek;
    }

    public void setYearWeek(String yearWeek) {
        this.yearWeek = yearWeek;
    }

    public String getEmployeeNo() {
        return employeeNo;
    }

    public void setEmployeeNo(String employeeNo) {
        this.employeeNo = employeeNo;
    }

    public EmployeeVo getEmployee() {
        return employee;
    }

    public void setEmployee(EmployeeVo employee) {
        this.employee = employee;
    }

    public List<WorkReportDetail> getReportDetailList() {
        return reportDetailList;
    }

    public void setReportDetailList(List<WorkReportDetail> reportDetailList) {
        this.reportDetailList = reportDetailList;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
