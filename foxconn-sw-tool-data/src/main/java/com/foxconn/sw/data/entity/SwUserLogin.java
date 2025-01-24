package com.foxconn.sw.data.entity;

import java.time.LocalDateTime;

public class SwUserLogin {
    private Integer id;

    private String employeeNo;

    private String token;

    private LocalDateTime expireTime;

    private String ip;

    private LocalDateTime createTime;

    private LocalDateTime datetimeLastchange;

    public Integer getId() {
        return id;
    }

    public SwUserLogin withId(Integer id) {
        this.setId(id);
        return this;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getEmployeeNo() {
        return employeeNo;
    }

    public SwUserLogin withEmployeeNo(String employeeNo) {
        this.setEmployeeNo(employeeNo);
        return this;
    }

    public void setEmployeeNo(String employeeNo) {
        this.employeeNo = employeeNo == null ? null : employeeNo.trim();
    }

    public String getToken() {
        return token;
    }

    public SwUserLogin withToken(String token) {
        this.setToken(token);
        return this;
    }

    public void setToken(String token) {
        this.token = token == null ? null : token.trim();
    }

    public LocalDateTime getExpireTime() {
        return expireTime;
    }

    public SwUserLogin withExpireTime(LocalDateTime expireTime) {
        this.setExpireTime(expireTime);
        return this;
    }

    public void setExpireTime(LocalDateTime expireTime) {
        this.expireTime = expireTime;
    }

    public String getIp() {
        return ip;
    }

    public SwUserLogin withIp(String ip) {
        this.setIp(ip);
        return this;
    }

    public void setIp(String ip) {
        this.ip = ip == null ? null : ip.trim();
    }

    public LocalDateTime getCreateTime() {
        return createTime;
    }

    public SwUserLogin withCreateTime(LocalDateTime createTime) {
        this.setCreateTime(createTime);
        return this;
    }

    public void setCreateTime(LocalDateTime createTime) {
        this.createTime = createTime;
    }

    public LocalDateTime getDatetimeLastchange() {
        return datetimeLastchange;
    }

    public SwUserLogin withDatetimeLastchange(LocalDateTime datetimeLastchange) {
        this.setDatetimeLastchange(datetimeLastchange);
        return this;
    }

    public void setDatetimeLastchange(LocalDateTime datetimeLastchange) {
        this.datetimeLastchange = datetimeLastchange;
    }
}