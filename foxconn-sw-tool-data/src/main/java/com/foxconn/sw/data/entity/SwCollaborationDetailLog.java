package com.foxconn.sw.data.entity;

import java.time.LocalDateTime;

public class SwCollaborationDetailLog {
    private Long id;

    private Long detailId;

    private Integer rowIndex;

    private Integer colIndex;

    private String remark;

    private LocalDateTime createTime;

    private LocalDateTime datetimeLastchange;

    public Long getId() {
        return id;
    }

    public SwCollaborationDetailLog withId(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getDetailId() {
        return detailId;
    }

    public SwCollaborationDetailLog withDetailId(Long detailId) {
        this.setDetailId(detailId);
        return this;
    }

    public void setDetailId(Long detailId) {
        this.detailId = detailId;
    }

    public Integer getRowIndex() {
        return rowIndex;
    }

    public SwCollaborationDetailLog withRowIndex(Integer rowIndex) {
        this.setRowIndex(rowIndex);
        return this;
    }

    public void setRowIndex(Integer rowIndex) {
        this.rowIndex = rowIndex;
    }

    public Integer getColIndex() {
        return colIndex;
    }

    public SwCollaborationDetailLog withColIndex(Integer colIndex) {
        this.setColIndex(colIndex);
        return this;
    }

    public void setColIndex(Integer colIndex) {
        this.colIndex = colIndex;
    }

    public String getRemark() {
        return remark;
    }

    public SwCollaborationDetailLog withRemark(String remark) {
        this.setRemark(remark);
        return this;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

    public LocalDateTime getCreateTime() {
        return createTime;
    }

    public SwCollaborationDetailLog withCreateTime(LocalDateTime createTime) {
        this.setCreateTime(createTime);
        return this;
    }

    public void setCreateTime(LocalDateTime createTime) {
        this.createTime = createTime;
    }

    public LocalDateTime getDatetimeLastchange() {
        return datetimeLastchange;
    }

    public SwCollaborationDetailLog withDatetimeLastchange(LocalDateTime datetimeLastchange) {
        this.setDatetimeLastchange(datetimeLastchange);
        return this;
    }

    public void setDatetimeLastchange(LocalDateTime datetimeLastchange) {
        this.datetimeLastchange = datetimeLastchange;
    }
}