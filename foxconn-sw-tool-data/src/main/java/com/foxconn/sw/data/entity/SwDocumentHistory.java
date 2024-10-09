package com.foxconn.sw.data.entity;

import java.time.LocalDateTime;

public class SwDocumentHistory {
    private Integer id;

    private Integer documentId;

    private String creator;

    private String documentName;

    private LocalDateTime createTime;

    private LocalDateTime datetimeLastchange;

    public Integer getId() {
        return id;
    }

    public SwDocumentHistory withId(Integer id) {
        this.setId(id);
        return this;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getDocumentId() {
        return documentId;
    }

    public SwDocumentHistory withDocumentId(Integer documentId) {
        this.setDocumentId(documentId);
        return this;
    }

    public void setDocumentId(Integer documentId) {
        this.documentId = documentId;
    }

    public String getCreator() {
        return creator;
    }

    public SwDocumentHistory withCreator(String creator) {
        this.setCreator(creator);
        return this;
    }

    public void setCreator(String creator) {
        this.creator = creator == null ? null : creator.trim();
    }

    public String getDocumentName() {
        return documentName;
    }

    public SwDocumentHistory withDocumentName(String documentName) {
        this.setDocumentName(documentName);
        return this;
    }

    public void setDocumentName(String documentName) {
        this.documentName = documentName == null ? null : documentName.trim();
    }

    public LocalDateTime getCreateTime() {
        return createTime;
    }

    public SwDocumentHistory withCreateTime(LocalDateTime createTime) {
        this.setCreateTime(createTime);
        return this;
    }

    public void setCreateTime(LocalDateTime createTime) {
        this.createTime = createTime;
    }

    public LocalDateTime getDatetimeLastchange() {
        return datetimeLastchange;
    }

    public SwDocumentHistory withDatetimeLastchange(LocalDateTime datetimeLastchange) {
        this.setDatetimeLastchange(datetimeLastchange);
        return this;
    }

    public void setDatetimeLastchange(LocalDateTime datetimeLastchange) {
        this.datetimeLastchange = datetimeLastchange;
    }
}