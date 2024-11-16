package com.foxconn.sw.data.entity;

import java.time.LocalDateTime;

public class ForumParticipant {
    private Integer id;

    private Integer postsId;

    private String employeeNo;

    private LocalDateTime createTime;

    private LocalDateTime datetimeLastchange;

    public Integer getId() {
        return id;
    }

    public ForumParticipant withId(Integer id) {
        this.setId(id);
        return this;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getPostsId() {
        return postsId;
    }

    public ForumParticipant withPostsId(Integer postsId) {
        this.setPostsId(postsId);
        return this;
    }

    public void setPostsId(Integer postsId) {
        this.postsId = postsId;
    }

    public String getEmployeeNo() {
        return employeeNo;
    }

    public ForumParticipant withEmployeeNo(String employeeNo) {
        this.setEmployeeNo(employeeNo);
        return this;
    }

    public void setEmployeeNo(String employeeNo) {
        this.employeeNo = employeeNo == null ? null : employeeNo.trim();
    }

    public LocalDateTime getCreateTime() {
        return createTime;
    }

    public ForumParticipant withCreateTime(LocalDateTime createTime) {
        this.setCreateTime(createTime);
        return this;
    }

    public void setCreateTime(LocalDateTime createTime) {
        this.createTime = createTime;
    }

    public LocalDateTime getDatetimeLastchange() {
        return datetimeLastchange;
    }

    public ForumParticipant withDatetimeLastchange(LocalDateTime datetimeLastchange) {
        this.setDatetimeLastchange(datetimeLastchange);
        return this;
    }

    public void setDatetimeLastchange(LocalDateTime datetimeLastchange) {
        this.datetimeLastchange = datetimeLastchange;
    }
}