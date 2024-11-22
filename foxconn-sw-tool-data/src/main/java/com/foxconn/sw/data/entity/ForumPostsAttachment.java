package com.foxconn.sw.data.entity;

import java.time.LocalDateTime;

public class ForumPostsAttachment {
    private Integer id;

    private Integer postsId;

    private Integer resourceId;

    private Integer isDelete;

    private LocalDateTime createTime;

    private LocalDateTime datatimeLastchange;

    public Integer getId() {
        return id;
    }

    public ForumPostsAttachment withId(Integer id) {
        this.setId(id);
        return this;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getPostsId() {
        return postsId;
    }

    public ForumPostsAttachment withPostsId(Integer postsId) {
        this.setPostsId(postsId);
        return this;
    }

    public void setPostsId(Integer postsId) {
        this.postsId = postsId;
    }

    public Integer getResourceId() {
        return resourceId;
    }

    public ForumPostsAttachment withResourceId(Integer resourceId) {
        this.setResourceId(resourceId);
        return this;
    }

    public void setResourceId(Integer resourceId) {
        this.resourceId = resourceId;
    }

    public Integer getIsDelete() {
        return isDelete;
    }

    public ForumPostsAttachment withIsDelete(Integer isDelete) {
        this.setIsDelete(isDelete);
        return this;
    }

    public void setIsDelete(Integer isDelete) {
        this.isDelete = isDelete;
    }

    public LocalDateTime getCreateTime() {
        return createTime;
    }

    public ForumPostsAttachment withCreateTime(LocalDateTime createTime) {
        this.setCreateTime(createTime);
        return this;
    }

    public void setCreateTime(LocalDateTime createTime) {
        this.createTime = createTime;
    }

    public LocalDateTime getDatatimeLastchange() {
        return datatimeLastchange;
    }

    public ForumPostsAttachment withDatatimeLastchange(LocalDateTime datatimeLastchange) {
        this.setDatatimeLastchange(datatimeLastchange);
        return this;
    }

    public void setDatatimeLastchange(LocalDateTime datatimeLastchange) {
        this.datatimeLastchange = datatimeLastchange;
    }
}