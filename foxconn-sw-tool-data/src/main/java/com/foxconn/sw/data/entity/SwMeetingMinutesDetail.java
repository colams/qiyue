package com.foxconn.sw.data.entity;

import java.time.LocalDateTime;

public class SwMeetingMinutesDetail {
    private Long id;

    private String itemType;

    private Integer indexNo;

    private String item;

    private String directEno;

    private String dueDate;

    private String status;

    private Integer isDelete;

    private String remark;

    private LocalDateTime createTime;

    private LocalDateTime datetimeLastchange;

    public Long getId() {
        return id;
    }

    public SwMeetingMinutesDetail withId(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getItemType() {
        return itemType;
    }

    public SwMeetingMinutesDetail withItemType(String itemType) {
        this.setItemType(itemType);
        return this;
    }

    public void setItemType(String itemType) {
        this.itemType = itemType == null ? null : itemType.trim();
    }

    public Integer getIndexNo() {
        return indexNo;
    }

    public SwMeetingMinutesDetail withIndexNo(Integer indexNo) {
        this.setIndexNo(indexNo);
        return this;
    }

    public void setIndexNo(Integer indexNo) {
        this.indexNo = indexNo;
    }

    public String getItem() {
        return item;
    }

    public SwMeetingMinutesDetail withItem(String item) {
        this.setItem(item);
        return this;
    }

    public void setItem(String item) {
        this.item = item == null ? null : item.trim();
    }

    public String getDirectEno() {
        return directEno;
    }

    public SwMeetingMinutesDetail withDirectEno(String directEno) {
        this.setDirectEno(directEno);
        return this;
    }

    public void setDirectEno(String directEno) {
        this.directEno = directEno == null ? null : directEno.trim();
    }

    public String getDueDate() {
        return dueDate;
    }

    public SwMeetingMinutesDetail withDueDate(String dueDate) {
        this.setDueDate(dueDate);
        return this;
    }

    public void setDueDate(String dueDate) {
        this.dueDate = dueDate == null ? null : dueDate.trim();
    }

    public String getStatus() {
        return status;
    }

    public SwMeetingMinutesDetail withStatus(String status) {
        this.setStatus(status);
        return this;
    }

    public void setStatus(String status) {
        this.status = status == null ? null : status.trim();
    }

    public Integer getIsDelete() {
        return isDelete;
    }

    public SwMeetingMinutesDetail withIsDelete(Integer isDelete) {
        this.setIsDelete(isDelete);
        return this;
    }

    public void setIsDelete(Integer isDelete) {
        this.isDelete = isDelete;
    }

    public String getRemark() {
        return remark;
    }

    public SwMeetingMinutesDetail withRemark(String remark) {
        this.setRemark(remark);
        return this;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

    public LocalDateTime getCreateTime() {
        return createTime;
    }

    public SwMeetingMinutesDetail withCreateTime(LocalDateTime createTime) {
        this.setCreateTime(createTime);
        return this;
    }

    public void setCreateTime(LocalDateTime createTime) {
        this.createTime = createTime;
    }

    public LocalDateTime getDatetimeLastchange() {
        return datetimeLastchange;
    }

    public SwMeetingMinutesDetail withDatetimeLastchange(LocalDateTime datetimeLastchange) {
        this.setDatetimeLastchange(datetimeLastchange);
        return this;
    }

    public void setDatetimeLastchange(LocalDateTime datetimeLastchange) {
        this.datetimeLastchange = datetimeLastchange;
    }
}