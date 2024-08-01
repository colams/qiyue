package com.foxconn.sw.data.entity;

import java.time.LocalDateTime;

public class SwUserLogin {
    private Integer id;

    private String userName;

    private String token;

    private LocalDateTime expireTime;

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

    public String getUserName() {
        return userName;
    }

    public SwUserLogin withUserName(String userName) {
        this.setUserName(userName);
        return this;
    }

    public void setUserName(String userName) {
        this.userName = userName == null ? null : userName.trim();
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