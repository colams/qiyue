package com.foxconn.sw.data.entity;

import java.time.LocalDateTime;

public class SwAnnouncement {
    private Integer id;

    private String title;

    private String category;

    private String expiryDate;

    private String status;

    private String content;

    private Integer top;

    private String operator;

    private String lastUpdater;

    private String resourceids;

    private Integer isDelete;

    private LocalDateTime createTime;

    private LocalDateTime datetimeLastchange;

    public Integer getId() {
        return id;
    }

    public SwAnnouncement withId(Integer id) {
        this.setId(id);
        return this;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public SwAnnouncement withTitle(String title) {
        this.setTitle(title);
        return this;
    }

    public void setTitle(String title) {
        this.title = title == null ? null : title.trim();
    }

    public String getCategory() {
        return category;
    }

    public SwAnnouncement withCategory(String category) {
        this.setCategory(category);
        return this;
    }

    public void setCategory(String category) {
        this.category = category == null ? null : category.trim();
    }

    public String getExpiryDate() {
        return expiryDate;
    }

    public SwAnnouncement withExpiryDate(String expiryDate) {
        this.setExpiryDate(expiryDate);
        return this;
    }

    public void setExpiryDate(String expiryDate) {
        this.expiryDate = expiryDate == null ? null : expiryDate.trim();
    }

    public String getStatus() {
        return status;
    }

    public SwAnnouncement withStatus(String status) {
        this.setStatus(status);
        return this;
    }

    public void setStatus(String status) {
        this.status = status == null ? null : status.trim();
    }

    public String getContent() {
        return content;
    }

    public SwAnnouncement withContent(String content) {
        this.setContent(content);
        return this;
    }

    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }

    public Integer getTop() {
        return top;
    }

    public SwAnnouncement withTop(Integer top) {
        this.setTop(top);
        return this;
    }

    public void setTop(Integer top) {
        this.top = top;
    }

    public String getOperator() {
        return operator;
    }

    public SwAnnouncement withOperator(String operator) {
        this.setOperator(operator);
        return this;
    }

    public void setOperator(String operator) {
        this.operator = operator == null ? null : operator.trim();
    }

    public String getLastUpdater() {
        return lastUpdater;
    }

    public SwAnnouncement withLastUpdater(String lastUpdater) {
        this.setLastUpdater(lastUpdater);
        return this;
    }

    public void setLastUpdater(String lastUpdater) {
        this.lastUpdater = lastUpdater == null ? null : lastUpdater.trim();
    }

    public String getResourceids() {
        return resourceids;
    }

    public SwAnnouncement withResourceids(String resourceids) {
        this.setResourceids(resourceids);
        return this;
    }

    public void setResourceids(String resourceids) {
        this.resourceids = resourceids == null ? null : resourceids.trim();
    }

    public Integer getIsDelete() {
        return isDelete;
    }

    public SwAnnouncement withIsDelete(Integer isDelete) {
        this.setIsDelete(isDelete);
        return this;
    }

    public void setIsDelete(Integer isDelete) {
        this.isDelete = isDelete;
    }

    public LocalDateTime getCreateTime() {
        return createTime;
    }

    public SwAnnouncement withCreateTime(LocalDateTime createTime) {
        this.setCreateTime(createTime);
        return this;
    }

    public void setCreateTime(LocalDateTime createTime) {
        this.createTime = createTime;
    }

    public LocalDateTime getDatetimeLastchange() {
        return datetimeLastchange;
    }

    public SwAnnouncement withDatetimeLastchange(LocalDateTime datetimeLastchange) {
        this.setDatetimeLastchange(datetimeLastchange);
        return this;
    }

    public void setDatetimeLastchange(LocalDateTime datetimeLastchange) {
        this.datetimeLastchange = datetimeLastchange;
    }
}