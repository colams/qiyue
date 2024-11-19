package com.foxconn.sw.data.entity;

import java.time.LocalDateTime;

public class ForumFavorite {
    private Integer id;

    private String authorNo;

    private Integer postsId;

    private Integer isInvalid;

    private LocalDateTime createTime;

    private LocalDateTime datatimeLastchange;

    public Integer getId() {
        return id;
    }

    public ForumFavorite withId(Integer id) {
        this.setId(id);
        return this;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getAuthorNo() {
        return authorNo;
    }

    public ForumFavorite withAuthorNo(String authorNo) {
        this.setAuthorNo(authorNo);
        return this;
    }

    public void setAuthorNo(String authorNo) {
        this.authorNo = authorNo == null ? null : authorNo.trim();
    }

    public Integer getPostsId() {
        return postsId;
    }

    public ForumFavorite withPostsId(Integer postsId) {
        this.setPostsId(postsId);
        return this;
    }

    public void setPostsId(Integer postsId) {
        this.postsId = postsId;
    }

    public Integer getIsInvalid() {
        return isInvalid;
    }

    public ForumFavorite withIsInvalid(Integer isInvalid) {
        this.setIsInvalid(isInvalid);
        return this;
    }

    public void setIsInvalid(Integer isInvalid) {
        this.isInvalid = isInvalid;
    }

    public LocalDateTime getCreateTime() {
        return createTime;
    }

    public ForumFavorite withCreateTime(LocalDateTime createTime) {
        this.setCreateTime(createTime);
        return this;
    }

    public void setCreateTime(LocalDateTime createTime) {
        this.createTime = createTime;
    }

    public LocalDateTime getDatatimeLastchange() {
        return datatimeLastchange;
    }

    public ForumFavorite withDatatimeLastchange(LocalDateTime datatimeLastchange) {
        this.setDatatimeLastchange(datatimeLastchange);
        return this;
    }

    public void setDatatimeLastchange(LocalDateTime datatimeLastchange) {
        this.datatimeLastchange = datatimeLastchange;
    }
}