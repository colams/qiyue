package com.foxconn.sw.data.entity;

import java.time.LocalDateTime;

public class SwUser {
    private Integer id;

    private String employeeNo;

    private String password;

    private String solt;

    private String passwordBak;

    private Integer avatarId;

    private String signature;

    private LocalDateTime createTime;

    private LocalDateTime datetimeLastchange;

    public Integer getId() {
        return id;
    }

    public SwUser withId(Integer id) {
        this.setId(id);
        return this;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getEmployeeNo() {
        return employeeNo;
    }

    public SwUser withEmployeeNo(String employeeNo) {
        this.setEmployeeNo(employeeNo);
        return this;
    }

    public void setEmployeeNo(String employeeNo) {
        this.employeeNo = employeeNo == null ? null : employeeNo.trim();
    }

    public String getPassword() {
        return password;
    }

    public SwUser withPassword(String password) {
        this.setPassword(password);
        return this;
    }

    public void setPassword(String password) {
        this.password = password == null ? null : password.trim();
    }

    public String getSolt() {
        return solt;
    }

    public SwUser withSolt(String solt) {
        this.setSolt(solt);
        return this;
    }

    public void setSolt(String solt) {
        this.solt = solt == null ? null : solt.trim();
    }

    public String getPasswordBak() {
        return passwordBak;
    }

    public SwUser withPasswordBak(String passwordBak) {
        this.setPasswordBak(passwordBak);
        return this;
    }

    public void setPasswordBak(String passwordBak) {
        this.passwordBak = passwordBak == null ? null : passwordBak.trim();
    }

    public Integer getAvatarId() {
        return avatarId;
    }

    public SwUser withAvatarId(Integer avatarId) {
        this.setAvatarId(avatarId);
        return this;
    }

    public void setAvatarId(Integer avatarId) {
        this.avatarId = avatarId;
    }

    public String getSignature() {
        return signature;
    }

    public SwUser withSignature(String signature) {
        this.setSignature(signature);
        return this;
    }

    public void setSignature(String signature) {
        this.signature = signature == null ? null : signature.trim();
    }

    public LocalDateTime getCreateTime() {
        return createTime;
    }

    public SwUser withCreateTime(LocalDateTime createTime) {
        this.setCreateTime(createTime);
        return this;
    }

    public void setCreateTime(LocalDateTime createTime) {
        this.createTime = createTime;
    }

    public LocalDateTime getDatetimeLastchange() {
        return datetimeLastchange;
    }

    public SwUser withDatetimeLastchange(LocalDateTime datetimeLastchange) {
        this.setDatetimeLastchange(datetimeLastchange);
        return this;
    }

    public void setDatetimeLastchange(LocalDateTime datetimeLastchange) {
        this.datetimeLastchange = datetimeLastchange;
    }
}