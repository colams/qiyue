package com.foxconn.sw.data.entity;

import java.time.LocalDateTime;

public class SwDocument {
    private Integer id;

    private String documentName;

    private Integer category;

    private String creator;

    private String source;

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