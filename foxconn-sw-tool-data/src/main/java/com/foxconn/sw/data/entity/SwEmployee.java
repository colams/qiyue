package com.foxconn.sw.data.entity;

import java.time.LocalDateTime;

public class SwEmployee {
    private Integer id;

    private String employeeNo;

    private String name;

    private String firstName;

    private String lastName;

    private Integer gender;

    private Integer departmentId;

    private Integer postId;

    private String innerEmail;

    private String outerMail;

    private String landLine;

    private String phoneNumber;

    private String hireDate;

    private Integer position;

    private Integer status;

    private Integer outerWorkYears;

    private Integer outerAbcYears;

    private String positionStartDate;

    private String positionEndDate;

    private String signature;

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

    public Integer getGender() {
        return gender;
    }

    public SwEmployee withGender(Integer gender) {
        this.setGender(gender);
        return this;
    }

    public void setGender(Integer gender) {
        this.gender = gender;
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

    public Integer getPosition() {
        return position;
    }

    public SwEmployee withPosition(Integer position) {
        this.setPosition(position);
        return this;
    }

    public void setPosition(Integer position) {
        this.position = position;
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

    public String getSignature() {
        return signature;
    }

    public SwEmployee withSignature(String signature) {
        this.setSignature(signature);
        return this;
    }

    public void setSignature(String signature) {
        this.signature = signature == null ? null : signature.trim();
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