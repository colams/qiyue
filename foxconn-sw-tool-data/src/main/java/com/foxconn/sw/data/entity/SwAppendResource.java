package com.foxconn.sw.data.entity;

import java.time.LocalDateTime;

public class SwAppendResource {
    private Integer id;

    private Integer sourceType;

    private String filePath;

    private String operator;

    private LocalDateTime datetimeLastchange;

    public Integer getId() {
        return id;
    }

    public SwAppendResource withId(Integer id) {
        this.setId(id);
        return this;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getSourceType() {
        return sourceType;
    }

    public SwAppendResource withSourceType(Integer sourceType) {
        this.setSourceType(sourceType);
        return this;
    }

    public void setSourceType(Integer sourceType) {
        this.sourceType = sourceType;
    }

    public String getFilePath() {
        return filePath;
    }

    public SwAppendResource withFilePath(String filePath) {
        this.setFilePath(filePath);
        return this;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath == null ? null : filePath.trim();
    }

    public String getOperator() {
        return operator;
    }

    public SwAppendResource withOperator(String operator) {
        this.setOperator(operator);
        return this;
    }

    public void setOperator(String operator) {
        this.operator = operator == null ? null : operator.trim();
    }

    public LocalDateTime getDatetimeLastchange() {
        return datetimeLastchange;
    }

    public SwAppendResource withDatetimeLastchange(LocalDateTime datetimeLastchange) {
        this.setDatetimeLastchange(datetimeLastchange);
        return this;
    }

    public void setDatetimeLastchange(LocalDateTime datetimeLastchange) {
        this.datetimeLastchange = datetimeLastchange;
    }
}