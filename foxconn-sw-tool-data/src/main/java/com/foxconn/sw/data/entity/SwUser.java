package com.foxconn.sw.data.entity;

import java.time.LocalDateTime;

public class SwUser {
    private Integer id;

    private String userName;

    private String name;

    private String password;

    private String solt;

    private String phone;

    private String email;

    private Integer deptId;

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

    public String getUserName() {
        return userName;
    }

    public SwUser withUserName(String userName) {
        this.setUserName(userName);
        return this;
    }

    public void setUserName(String userName) {
        this.userName = userName == null ? null : userName.trim();
    }

    public String getName() {
        return name;
    }

    public SwUser withName(String name) {
        this.setName(name);
        return this;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
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

    public String getPhone() {
        return phone;
    }

    public SwUser withPhone(String phone) {
        this.setPhone(phone);
        return this;
    }

    public void setPhone(String phone) {
        this.phone = phone == null ? null : phone.trim();
    }

    public String getEmail() {
        return email;
    }

    public SwUser withEmail(String email) {
        this.setEmail(email);
        return this;
    }

    public void setEmail(String email) {
        this.email = email == null ? null : email.trim();
    }

    public Integer getDeptId() {
        return deptId;
    }

    public SwUser withDeptId(Integer deptId) {
        this.setDeptId(deptId);
        return this;
    }

    public void setDeptId(Integer deptId) {
        this.deptId = deptId;
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