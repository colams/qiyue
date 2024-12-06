package com.foxconn.sw.data.entity;

import java.time.LocalDateTime;

public class SwCustomGroupFavorite {
    private Integer id;

    private Integer customGroupId;

    private String operator;

    private Integer isDelete;

    private LocalDateTime createTime;

    private LocalDateTime datetimeLastchange;

    public Integer getId() {
        return id;
    }

    public SwCustomGroupFavorite withId(Integer id) {
        this.setId(id);
        return this;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getCustomGroupId() {
        return customGroupId;
    }

    public SwCustomGroupFavorite withCustomGroupId(Integer customGroupId) {
        this.setCustomGroupId(customGroupId);
        return this;
    }

    public void setCustomGroupId(Integer customGroupId) {
        this.customGroupId = customGroupId;
    }

    public String getOperator() {
        return operator;
    }

    public SwCustomGroupFavorite withOperator(String operator) {
        this.setOperator(operator);
        return this;
    }

    public void setOperator(String operator) {
        this.operator = operator == null ? null : operator.trim();
    }

    public Integer getIsDelete() {
        return isDelete;
    }

    public SwCustomGroupFavorite withIsDelete(Integer isDelete) {
        this.setIsDelete(isDelete);
        return this;
    }

    public void setIsDelete(Integer isDelete) {
        this.isDelete = isDelete;
    }

    public LocalDateTime getCreateTime() {
        return createTime;
    }

    public SwCustomGroupFavorite withCreateTime(LocalDateTime createTime) {
        this.setCreateTime(createTime);
        return this;
    }

    public void setCreateTime(LocalDateTime createTime) {
        this.createTime = createTime;
    }

    public LocalDateTime getDatetimeLastchange() {
        return datetimeLastchange;
    }

    public SwCustomGroupFavorite withDatetimeLastchange(LocalDateTime datetimeLastchange) {
        this.setDatetimeLastchange(datetimeLastchange);
        return this;
    }

    public void setDatetimeLastchange(LocalDateTime datetimeLastchange) {
        this.datetimeLastchange = datetimeLastchange;
    }
}