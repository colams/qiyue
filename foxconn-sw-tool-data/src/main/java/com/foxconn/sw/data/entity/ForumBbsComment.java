package com.foxconn.sw.data.entity;

import java.time.LocalDateTime;

public class ForumBbsComment {
    private Integer id;

    private Integer fbId;

    private Integer parentId;

    private Integer targetId;

    private String authorNo;

    private Integer isDelete;

    private LocalDateTime createTime;

    private LocalDateTime datetimeLastchange;

    private String content;

    public Integer getId() {
        return id;
    }

    public ForumBbsComment withId(Integer id) {
        this.setId(id);
        return this;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getFbId() {
        return fbId;
    }

    public ForumBbsComment withFbId(Integer fbId) {
        this.setFbId(fbId);
        return this;
    }

    public void setFbId(Integer fbId) {
        this.fbId = fbId;
    }

    public Integer getParentId() {
        return parentId;
    }

    public ForumBbsComment withParentId(Integer parentId) {
        this.setParentId(parentId);
        return this;
    }

    public void setParentId(Integer parentId) {
        this.parentId = parentId;
    }

    public Integer getTargetId() {
        return targetId;
    }

    public ForumBbsComment withTargetId(Integer targetId) {
        this.setTargetId(targetId);
        return this;
    }

    public void setTargetId(Integer targetId) {
        this.targetId = targetId;
    }

    public String getAuthorNo() {
        return authorNo;
    }

    public ForumBbsComment withAuthorNo(String authorNo) {
        this.setAuthorNo(authorNo);
        return this;
    }

    public void setAuthorNo(String authorNo) {
        this.authorNo = authorNo == null ? null : authorNo.trim();
    }

    public Integer getIsDelete() {
        return isDelete;
    }

    public ForumBbsComment withIsDelete(Integer isDelete) {
        this.setIsDelete(isDelete);
        return this;
    }

    public void setIsDelete(Integer isDelete) {
        this.isDelete = isDelete;
    }

    public LocalDateTime getCreateTime() {
        return createTime;
    }

    public ForumBbsComment withCreateTime(LocalDateTime createTime) {
        this.setCreateTime(createTime);
        return this;
    }

    public void setCreateTime(LocalDateTime createTime) {
        this.createTime = createTime;
    }

    public LocalDateTime getDatetimeLastchange() {
        return datetimeLastchange;
    }

    public ForumBbsComment withDatetimeLastchange(LocalDateTime datetimeLastchange) {
        this.setDatetimeLastchange(datetimeLastchange);
        return this;
    }

    public void setDatetimeLastchange(LocalDateTime datetimeLastchange) {
        this.datetimeLastchange = datetimeLastchange;
    }

    public String getContent() {
        return content;
    }

    public ForumBbsComment withContent(String content) {
        this.setContent(content);
        return this;
    }

    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }
}