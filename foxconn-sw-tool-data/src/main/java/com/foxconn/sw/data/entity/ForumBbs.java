package com.foxconn.sw.data.entity;

import java.time.LocalDateTime;

public class ForumBbs {
    private Integer id;

    private String project;

    private String title;

    private String authorNo;

    private Integer hidden;

    private Integer isDelete;

    private LocalDateTime createTime;

    private LocalDateTime datetimeLastchange;

    public Integer getId() {
        return id;
    }

    public ForumBbs withId(Integer id) {
        this.setId(id);
        return this;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getProject() {
        return project;
    }

    public ForumBbs withProject(String project) {
        this.setProject(project);
        return this;
    }

    public void setProject(String project) {
        this.project = project == null ? null : project.trim();
    }

    public String getTitle() {
        return title;
    }

    public ForumBbs withTitle(String title) {
        this.setTitle(title);
        return this;
    }

    public void setTitle(String title) {
        this.title = title == null ? null : title.trim();
    }

    public String getAuthorNo() {
        return authorNo;
    }

    public ForumBbs withAuthorNo(String authorNo) {
        this.setAuthorNo(authorNo);
        return this;
    }

    public void setAuthorNo(String authorNo) {
        this.authorNo = authorNo == null ? null : authorNo.trim();
    }

    public Integer getHidden() {
        return hidden;
    }

    public ForumBbs withHidden(Integer hidden) {
        this.setHidden(hidden);
        return this;
    }

    public void setHidden(Integer hidden) {
        this.hidden = hidden;
    }

    public Integer getIsDelete() {
        return isDelete;
    }

    public ForumBbs withIsDelete(Integer isDelete) {
        this.setIsDelete(isDelete);
        return this;
    }

    public void setIsDelete(Integer isDelete) {
        this.isDelete = isDelete;
    }

    public LocalDateTime getCreateTime() {
        return createTime;
    }

    public ForumBbs withCreateTime(LocalDateTime createTime) {
        this.setCreateTime(createTime);
        return this;
    }

    public void setCreateTime(LocalDateTime createTime) {
        this.createTime = createTime;
    }

    public LocalDateTime getDatetimeLastchange() {
        return datetimeLastchange;
    }

    public ForumBbs withDatetimeLastchange(LocalDateTime datetimeLastchange) {
        this.setDatetimeLastchange(datetimeLastchange);
        return this;
    }

    public void setDatetimeLastchange(LocalDateTime datetimeLastchange) {
        this.datetimeLastchange = datetimeLastchange;
    }
}