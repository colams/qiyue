package com.foxconn.sw.data.entity;

import java.time.LocalDateTime;

public class SwDocument {
    private Integer id;

    private String documentName;

    private Integer resourceId;

    private Integer category;

    private String source;

    private Integer department;

    private String creator;

    private Integer isDelete;

    private LocalDateTime createTime;

    private LocalDateTime datetimeLastchange;

    public Integer getId() {
        return id;
    }

    public SwDocument withId(Integer id) {
        this.setId(id);
        return this;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDocumentName() {
        return documentName;
    }

    public SwDocument withDocumentName(String documentName) {
        this.setDocumentName(documentName);
        return this;
    }

    public void setDocumentName(String documentName) {
        this.documentName = documentName == null ? null : documentName.trim();
    }

    public Integer getResourceId() {
        return resourceId;
    }

    public SwDocument withResourceId(Integer resourceId) {
        this.setResourceId(resourceId);
        return this;
    }

    public void setResourceId(Integer resourceId) {
        this.resourceId = resourceId;
    }

    public Integer getCategory() {
        return category;
    }

    public SwDocument withCategory(Integer category) {
        this.setCategory(category);
        return this;
    }

    public void setCategory(Integer category) {
        this.category = category;
    }

    public String getSource() {
        return source;
    }

    public SwDocument withSource(String source) {
        this.setSource(source);
        return this;
    }

    public void setSource(String source) {
        this.source = source == null ? null : source.trim();
    }

    public Integer getDepartment() {
        return department;
    }

    public SwDocument withDepartment(Integer department) {
        this.setDepartment(department);
        return this;
    }

    public void setDepartment(Integer department) {
        this.department = department;
    }

    public String getCreator() {
        return creator;
    }

    public SwDocument withCreator(String creator) {
        this.setCreator(creator);
        return this;
    }

    public void setCreator(String creator) {
        this.creator = creator == null ? null : creator.trim();
    }

    public Integer getIsDelete() {
        return isDelete;
    }

    public SwDocument withIsDelete(Integer isDelete) {
        this.setIsDelete(isDelete);
        return this;
    }

    public void setIsDelete(Integer isDelete) {
        this.isDelete = isDelete;
    }

    public LocalDateTime getCreateTime() {
        return createTime;
    }

    public SwDocument withCreateTime(LocalDateTime createTime) {
        this.setCreateTime(createTime);
        return this;
    }

    public void setCreateTime(LocalDateTime createTime) {
        this.createTime = createTime;
    }

    public LocalDateTime getDatetimeLastchange() {
        return datetimeLastchange;
    }

    public SwDocument withDatetimeLastchange(LocalDateTime datetimeLastchange) {
        this.setDatetimeLastchange(datetimeLastchange);
        return this;
    }

    public void setDatetimeLastchange(LocalDateTime datetimeLastchange) {
        this.datetimeLastchange = datetimeLastchange;
    }
}