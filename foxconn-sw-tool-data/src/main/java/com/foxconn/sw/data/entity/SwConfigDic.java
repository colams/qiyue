package com.foxconn.sw.data.entity;

import java.time.LocalDateTime;

public class SwConfigDic {
    private Integer id;

    private String item;

    private String itemValue;

    private Integer isDisable;

    private Integer isDelete;

    private LocalDateTime createTime;

    private LocalDateTime datetimeLastchange;

    public Integer getId() {
        return id;
    }

    public SwConfigDic withId(Integer id) {
        this.setId(id);
        return this;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getItem() {
        return item;
    }

    public SwConfigDic withItem(String item) {
        this.setItem(item);
        return this;
    }

    public void setItem(String item) {
        this.item = item == null ? null : item.trim();
    }

    public String getItemValue() {
        return itemValue;
    }

    public SwConfigDic withItemValue(String itemValue) {
        this.setItemValue(itemValue);
        return this;
    }

    public void setItemValue(String itemValue) {
        this.itemValue = itemValue == null ? null : itemValue.trim();
    }

    public Integer getIsDisable() {
        return isDisable;
    }

    public SwConfigDic withIsDisable(Integer isDisable) {
        this.setIsDisable(isDisable);
        return this;
    }

    public void setIsDisable(Integer isDisable) {
        this.isDisable = isDisable;
    }

    public Integer getIsDelete() {
        return isDelete;
    }

    public SwConfigDic withIsDelete(Integer isDelete) {
        this.setIsDelete(isDelete);
        return this;
    }

    public void setIsDelete(Integer isDelete) {
        this.isDelete = isDelete;
    }

    public LocalDateTime getCreateTime() {
        return createTime;
    }

    public SwConfigDic withCreateTime(LocalDateTime createTime) {
        this.setCreateTime(createTime);
        return this;
    }

    public void setCreateTime(LocalDateTime createTime) {
        this.createTime = createTime;
    }

    public LocalDateTime getDatetimeLastchange() {
        return datetimeLastchange;
    }

    public SwConfigDic withDatetimeLastchange(LocalDateTime datetimeLastchange) {
        this.setDatetimeLastchange(datetimeLastchange);
        return this;
    }

    public void setDatetimeLastchange(LocalDateTime datetimeLastchange) {
        this.datetimeLastchange = datetimeLastchange;
    }
}