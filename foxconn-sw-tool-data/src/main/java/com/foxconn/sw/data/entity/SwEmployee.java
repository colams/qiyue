package com.foxconn.sw.data.entity;

import java.time.LocalDateTime;

public class SwEmployee {
    private Integer id;

    private String name;

    private String employeeNo;

    private Integer departmentId;

    private String jobTitle;

    private String email;

    private String phoneNumber;

    private String hireDate;

    private Integer status;

    private String positionStartDate;

    private String positionEndDate;

    private LocalDateTime datetimeLastchange;

    public Integer getId() {
        return id;
    }

    public SwEmployee withId(Integer id) {
        this.setId(id);
        return this;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public SwEmployee withName(String name) {
        this.setName(name);
        return this;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getEmployeeNo() {
        return employeeNo;
    }

    public SwEmployee withEmployeeNo(String employeeNo) {
        this.setEmployeeNo(employeeNo);
        return this;
    }

    public void setEmployeeNo(String employeeNo) {
        this.employeeNo = employeeNo == null ? null : employeeNo.trim();
    }

    public Integer getDepartmentId() {
        return departmentId;
    }

    public SwEmployee withDepartmentId(Integer departmentId) {
        this.setDepartmentId(departmentId);
        return this;
    }

    public void setDepartmentId(Integer departmentId) {
        this.departmentId = departmentId;
    }

    public String getJobTitle() {
        return jobTitle;
    }

    public SwEmployee withJobTitle(String jobTitle) {
        this.setJobTitle(jobTitle);
        return this;
    }

    public void setJobTitle(String jobTitle) {
        this.jobTitle = jobTitle == null ? null : jobTitle.trim();
    }

    public String getEmail() {
        return email;
    }

    public SwEmployee withEmail(String email) {
        this.setEmail(email);
        return this;
    }

    public void setEmail(String email) {
        this.email = email == null ? null : email.trim();
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public SwEmployee withPhoneNumber(String phoneNumber) {
        this.setPhoneNumber(phoneNumber);
        return this;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber == null ? null : phoneNumber.trim();
    }

    public String getHireDate() {
        return hireDate;
    }

    public SwEmployee withHireDate(String hireDate) {
        this.setHireDate(hireDate);
        return this;
    }

    public void setHireDate(String hireDate) {
        this.hireDate = hireDate == null ? null : hireDate.trim();
    }

    public Integer getStatus() {
        return status;
    }

    public SwEmployee withStatus(Integer status) {
        this.setStatus(status);
        return this;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getPositionStartDate() {
        return positionStartDate;
    }

    public SwEmployee withPositionStartDate(String positionStartDate) {
        this.setPositionStartDate(positionStartDate);
        return this;
    }

    public void setPositionStartDate(String positionStartDate) {
        this.positionStartDate = positionStartDate == null ? null : positionStartDate.trim();
    }

    public String getPositionEndDate() {
        return positionEndDate;
    }

    public SwEmployee withPositionEndDate(String positionEndDate) {
        this.setPositionEndDate(positionEndDate);
        return this;
    }

    public void setPositionEndDate(String positionEndDate) {
        this.positionEndDate = positionEndDate == null ? null : positionEndDate.trim();
    }

    public LocalDateTime getDatetimeLastchange() {
        return datetimeLastchange;
    }

    public SwEmployee withDatetimeLastchange(LocalDateTime datetimeLastchange) {
        this.setDatetimeLastchange(datetimeLastchange);
        return this;
    }

    public void setDatetimeLastchange(LocalDateTime datetimeLastchange) {
        this.datetimeLastchange = datetimeLastchange;
    }
}