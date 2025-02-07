package com.foxconn.sw.data.entity;

import java.time.LocalDateTime;

public class ForumFavorite {
    private Integer id;

    private String operator;

    private Integer fbId;

    private Integer isValid;

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

    public String getOperator() {
        return operator;
    }

    public ForumFavorite withOperator(String operator) {
        this.setOperator(operator);
        return this;
    }

    public void setOperator(String operator) {
        this.operator = operator == null ? null : operator.trim();
    }

    public Integer getFbId() {
        return fbId;
    }

    public ForumFavorite withFbId(Integer fbId) {
        this.setFbId(fbId);
        return this;
    }

    public void setFbId(Integer fbId) {
        this.fbId = fbId;
    }

    public Integer getIsValid() {
        return isValid;
    }

    public ForumFavorite withIsValid(Integer isValid) {
        this.setIsValid(isValid);
        return this;
    }

    public void setIsValid(Integer isValid) {
        this.isValid = isValid;
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