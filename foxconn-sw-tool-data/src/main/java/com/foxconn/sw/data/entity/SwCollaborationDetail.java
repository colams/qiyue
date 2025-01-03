package com.foxconn.sw.data.entity;

import java.time.LocalDateTime;

public class SwCollaborationDetail {
    private Long id;

    private Long scuId;

    private Integer rowIndex;

    private Integer colIndex;

    private String item;

    private String itemValue;

    private LocalDateTime createTime;

    private LocalDateTime datetimeLastchange;

    private String spareValue;

    public Long getId() {
        return id;
    }

    public SwCollaborationDetail withId(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getScuId() {
        return scuId;
    }

    public SwCollaborationDetail withScuId(Long scuId) {
        this.setScuId(scuId);
        return this;
    }

    public void setScuId(Long scuId) {
        this.scuId = scuId;
    }

    public Integer getRowIndex() {
        return rowIndex;
    }

    public SwCollaborationDetail withRowIndex(Integer rowIndex) {
        this.setRowIndex(rowIndex);
        return this;
    }

    public void setRowIndex(Integer rowIndex) {
        this.rowIndex = rowIndex;
    }

    public Integer getColIndex() {
        return colIndex;
    }

    public SwCollaborationDetail withColIndex(Integer colIndex) {
        this.setColIndex(colIndex);
        return this;
    }

    public void setColIndex(Integer colIndex) {
        this.colIndex = colIndex;
    }

    public String getItem() {
        return item;
    }

    public SwCollaborationDetail withItem(String item) {
        this.setItem(item);
        return this;
    }

    public void setItem(String item) {
        this.item = item == null ? null : item.trim();
    }

    public String getItemValue() {
        return itemValue;
    }

    public SwCollaborationDetail withItemValue(String itemValue) {
        this.setItemValue(itemValue);
        return this;
    }

    public void setItemValue(String itemValue) {
        this.itemValue = itemValue == null ? null : itemValue.trim();
    }

    public LocalDateTime getCreateTime() {
        return createTime;
    }

    public SwCollaborationDetail withCreateTime(LocalDateTime createTime) {
        this.setCreateTime(createTime);
        return this;
    }

    public void setCreateTime(LocalDateTime createTime) {
        this.createTime = createTime;
    }

    public LocalDateTime getDatetimeLastchange() {
        return datetimeLastchange;
    }

    public SwCollaborationDetail withDatetimeLastchange(LocalDateTime datetimeLastchange) {
        this.setDatetimeLastchange(datetimeLastchange);
        return this;
    }

    public void setDatetimeLastchange(LocalDateTime datetimeLastchange) {
        this.datetimeLastchange = datetimeLastchange;
    }

    public String getSpareValue() {
        return spareValue;
    }

    public SwCollaborationDetail withSpareValue(String spareValue) {
        this.setSpareValue(spareValue);
        return this;
    }

    public void setSpareValue(String spareValue) {
        this.spareValue = spareValue == null ? null : spareValue.trim();
    }
}