package com.foxconn.sw.data.entity;

import java.time.LocalDateTime;

public class ForumPosts {
    private Integer id;

    private String title;

    private String authorNo;

    private String resourceIds;

    private Integer purview;

    private Integer isDelete;

    private LocalDateTime createTime;

    private LocalDateTime lastchangeDatetime;

    private String description;

    public Integer getId() {
        return id;
    }

    public ForumPosts withId(Integer id) {
        this.setId(id);
        return this;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public ForumPosts withTitle(String title) {
        this.setTitle(title);
        return this;
    }

    public void setTitle(String title) {
        this.title = title == null ? null : title.trim();
    }

    public String getAuthorNo() {
        return authorNo;
    }

    public ForumPosts withAuthorNo(String authorNo) {
        this.setAuthorNo(authorNo);
        return this;
    }

    public void setAuthorNo(String authorNo) {
        this.authorNo = authorNo == null ? null : authorNo.trim();
    }

    public String getResourceIds() {
        return resourceIds;
    }

    public ForumPosts withResourceIds(String resourceIds) {
        this.setResourceIds(resourceIds);
        return this;
    }

    public void setResourceIds(String resourceIds) {
        this.resourceIds = resourceIds == null ? null : resourceIds.trim();
    }

    public Integer getPurview() {
        return purview;
    }

    public ForumPosts withPurview(Integer purview) {
        this.setPurview(purview);
        return this;
    }

    public void setPurview(Integer purview) {
        this.purview = purview;
    }

    public Integer getIsDelete() {
        return isDelete;
    }

    public ForumPosts withIsDelete(Integer isDelete) {
        this.setIsDelete(isDelete);
        return this;
    }

    public void setIsDelete(Integer isDelete) {
        this.isDelete = isDelete;
    }

    public LocalDateTime getCreateTime() {
        return createTime;
    }

    public ForumPosts withCreateTime(LocalDateTime createTime) {
        this.setCreateTime(createTime);
        return this;
    }

    public void setCreateTime(LocalDateTime createTime) {
        this.createTime = createTime;
    }

    public LocalDateTime getLastchangeDatetime() {
        return lastchangeDatetime;
    }

    public ForumPosts withLastchangeDatetime(LocalDateTime lastchangeDatetime) {
        this.setLastchangeDatetime(lastchangeDatetime);
        return this;
    }

    public void setLastchangeDatetime(LocalDateTime lastchangeDatetime) {
        this.lastchangeDatetime = lastchangeDatetime;
    }

    public String getDescription() {
        return description;
    }

    public ForumPosts withDescription(String description) {
        this.setDescription(description);
        return this;
    }

    public void setDescription(String description) {
        this.description = description == null ? null : description.trim();
    }
}