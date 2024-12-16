package com.foxconn.sw.data.entity;

import java.time.LocalDateTime;

public class ForumBbsRead {
    private Integer id;

    private Integer commentId;

    private String employeeNo;

    private Integer isRead;

    private LocalDateTime createTime;

    private LocalDateTime datetimeLastchange;

    public Integer getId() {
        return id;
    }

    public ForumBbsRead withId(Integer id) {
        this.setId(id);
        return this;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getCommentId() {
        return commentId;
    }

    public ForumBbsRead withCommentId(Integer commentId) {
        this.setCommentId(commentId);
        return this;
    }

    public void setCommentId(Integer commentId) {
        this.commentId = commentId;
    }

    public String getEmployeeNo() {
        return employeeNo;
    }

    public ForumBbsRead withEmployeeNo(String employeeNo) {
        this.setEmployeeNo(employeeNo);
        return this;
    }

    public void setEmployeeNo(String employeeNo) {
        this.employeeNo = employeeNo == null ? null : employeeNo.trim();
    }

    public Integer getIsRead() {
        return isRead;
    }

    public ForumBbsRead withIsRead(Integer isRead) {
        this.setIsRead(isRead);
        return this;
    }

    public void setIsRead(Integer isRead) {
        this.isRead = isRead;
    }

    public LocalDateTime getCreateTime() {
        return createTime;
    }

    public ForumBbsRead withCreateTime(LocalDateTime createTime) {
        this.setCreateTime(createTime);
        return this;
    }

    public void setCreateTime(LocalDateTime createTime) {
        this.createTime = createTime;
    }

    public LocalDateTime getDatetimeLastchange() {
        return datetimeLastchange;
    }

    public ForumBbsRead withDatetimeLastchange(LocalDateTime datetimeLastchange) {
        this.setDatetimeLastchange(datetimeLastchange);
        return this;
    }

    public void setDatetimeLastchange(LocalDateTime datetimeLastchange) {
        this.datetimeLastchange = datetimeLastchange;
    }
}