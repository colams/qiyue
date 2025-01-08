package com.foxconn.sw.data.entity;

import java.time.LocalDateTime;

public class SwEmployee {
    private Integer id;

    private String employeeNo;

    private String assistant;

    private String name;

    private String firstName;

    private String lastName;

    private String gender;

    private Integer departmentId;

    private Integer postId;

    private String innerEmail;

    private String outerMail;

    private String landLine;

    private String phoneNumber;

    private String hireDate;

    private Integer status;

    private Integer outerWorkYears;

    private Integer outerAbcYears;

    private Integer managerLevel;

    private LocalDateTime datetimeLastchange;

    private Integer isComplete;

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

    public String getAssistant() {
        return assistant;
    }

    public SwEmployee withAssistant(String assistant) {
        this.setAssistant(assistant);
        return this;
    }

    public void setAssistant(String assistant) {
        this.assistant = assistant == null ? null : assistant.trim();
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

    public String getFirstName() {
        return firstName;
    }

    public SwEmployee withFirstName(String firstName) {
        this.setFirstName(firstName);
        return this;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName == null ? null : firstName.trim();
    }

    public String getLastName() {
        return lastName;
    }

    public SwEmployee withLastName(String lastName) {
        this.setLastName(lastName);
        return this;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName == null ? null : lastName.trim();
    }

    public String getGender() {
        return gender;
    }

    public SwEmployee withGender(String gender) {
        this.setGender(gender);
        return this;
    }

    public void setGender(String gender) {
        this.gender = gender == null ? null : gender.trim();
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

    public Integer getPostId() {
        return postId;
    }

    public SwEmployee withPostId(Integer postId) {
        this.setPostId(postId);
        return this;
    }

    public void setPostId(Integer postId) {
        this.postId = postId;
    }

    public String getInnerEmail() {
        return innerEmail;
    }

    public SwEmployee withInnerEmail(String innerEmail) {
        this.setInnerEmail(innerEmail);
        return this;
    }

    public void setInnerEmail(String innerEmail) {
        this.innerEmail = innerEmail == null ? null : innerEmail.trim();
    }

    public String getOuterMail() {
        return outerMail;
    }

    public SwEmployee withOuterMail(String outerMail) {
        this.setOuterMail(outerMail);
        return this;
    }

    public void setOuterMail(String outerMail) {
        this.outerMail = outerMail == null ? null : outerMail.trim();
    }

    public String getLandLine() {
        return landLine;
    }

    public SwEmployee withLandLine(String landLine) {
        this.setLandLine(landLine);
        return this;
    }

    public void setLandLine(String landLine) {
        this.landLine = landLine == null ? null : landLine.trim();
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

    public Integer getOuterWorkYears() {
        return outerWorkYears;
    }

    public SwEmployee withOuterWorkYears(Integer outerWorkYears) {
        this.setOuterWorkYears(outerWorkYears);
        return this;
    }

    public void setOuterWorkYears(Integer outerWorkYears) {
        this.outerWorkYears = outerWorkYears;
    }

    public Integer getOuterAbcYears() {
        return outerAbcYears;
    }

    public SwEmployee withOuterAbcYears(Integer outerAbcYears) {
        this.setOuterAbcYears(outerAbcYears);
        return this;
    }

    public void setOuterAbcYears(Integer outerAbcYears) {
        this.outerAbcYears = outerAbcYears;
    }

    public Integer getManagerLevel() {
        return managerLevel;
    }

    public SwEmployee withManagerLevel(Integer managerLevel) {
        this.setManagerLevel(managerLevel);
        return this;
    }

    public void setManagerLevel(Integer managerLevel) {
        this.managerLevel = managerLevel;
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

    public Integer getIsComplete() {
        return isComplete;
    }

    public SwEmployee withIsComplete(Integer isComplete) {
        this.setIsComplete(isComplete);
        return this;
    }

    public void setIsComplete(Integer isComplete) {
        this.isComplete = isComplete;
    }
}