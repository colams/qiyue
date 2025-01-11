package com.foxconn.sw.data.entity;

import java.time.LocalDateTime;

public class SwChangeLog {
    private Integer id;

    private String operator;

    private String releaseVersion;

    private String releaseNote;

    private LocalDateTime createTime;

    private LocalDateTime datetimeLastchange;

    public Integer getId() {
        return id;
    }

    public SwChangeLog withId(Integer id) {
        this.setId(id);
        return this;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getOperator() {
        return operator;
    }

    public SwChangeLog withOperator(String operator) {
        this.setOperator(operator);
        return this;
    }

    public void setOperator(String operator) {
        this.operator = operator == null ? null : operator.trim();
    }

    public String getReleaseVersion() {
        return releaseVersion;
    }

    public SwChangeLog withReleaseVersion(String releaseVersion) {
        this.setReleaseVersion(releaseVersion);
        return this;
    }

    public void setReleaseVersion(String releaseVersion) {
        this.releaseVersion = releaseVersion == null ? null : releaseVersion.trim();
    }

    public String getReleaseNote() {
        return releaseNote;
    }

    public SwChangeLog withReleaseNote(String releaseNote) {
        this.setReleaseNote(releaseNote);
        return this;
    }

    public void setReleaseNote(String releaseNote) {
        this.releaseNote = releaseNote == null ? null : releaseNote.trim();
    }

    public LocalDateTime getCreateTime() {
        return createTime;
    }

    public SwChangeLog withCreateTime(LocalDateTime createTime) {
        this.setCreateTime(createTime);
        return this;
    }

    public void setCreateTime(LocalDateTime createTime) {
        this.createTime = createTime;
    }

    public LocalDateTime getDatetimeLastchange() {
        return datetimeLastchange;
    }

    public SwChangeLog withDatetimeLastchange(LocalDateTime datetimeLastchange) {
        this.setDatetimeLastchange(datetimeLastchange);
        return this;
    }

    public void setDatetimeLastchange(LocalDateTime datetimeLastchange) {
        this.datetimeLastchange = datetimeLastchange;
    }
}