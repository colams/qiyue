package com.foxconn.sw.data.entity;

import java.time.LocalDateTime;

public class ForumAttachment {
    private Integer id;

    private Integer fbId;

    private Integer commentId;

    private Integer resourceId;

    private Integer isDelete;

    private LocalDateTime createTime;

    private LocalDateTime datatimeLastchange;

    public Integer getId() {
        return id;
    }

    public ForumAttachment withId(Integer id) {
        this.setId(id);
        return this;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getFbId() {
        return fbId;
    }

    public ForumAttachment withFbId(Integer fbId) {
        this.setFbId(fbId);
        return this;
    }

    public void setFbId(Integer fbId) {
        this.fbId = fbId;
    }

    public Integer getCommentId() {
        return commentId;
    }

    public ForumAttachment withCommentId(Integer commentId) {
        this.setCommentId(commentId);
        return this;
    }

    public void setCommentId(Integer commentId) {
        this.commentId = commentId;
    }

    public Integer getResourceId() {
        return resourceId;
    }

    public ForumAttachment withResourceId(Integer resourceId) {
        this.setResourceId(resourceId);
        return this;
    }

    public void setResourceId(Integer resourceId) {
        this.resourceId = resourceId;
    }

    public Integer getIsDelete() {
        return isDelete;
    }

    public ForumAttachment withIsDelete(Integer isDelete) {
        this.setIsDelete(isDelete);
        return this;
    }

    public void setIsDelete(Integer isDelete) {
        this.isDelete = isDelete;
    }

    public LocalDateTime getCreateTime() {
        return createTime;
    }

    public ForumAttachment withCreateTime(LocalDateTime createTime) {
        this.setCreateTime(createTime);
        return this;
    }

    public void setCreateTime(LocalDateTime createTime) {
        this.createTime = createTime;
    }

    public LocalDateTime getDatatimeLastchange() {
        return datatimeLastchange;
    }

    public ForumAttachment withDatatimeLastchange(LocalDateTime datatimeLastchange) {
        this.setDatatimeLastchange(datatimeLastchange);
        return this;
    }

    public void setDatatimeLastchange(LocalDateTime datatimeLastchange) {
        this.datatimeLastchange = datatimeLastchange;
    }
}