
package com.foxconn.sw.data.dto.response.announcement;

import com.foxconn.sw.data.dto.entity.ResourceVo;

import java.util.List;

public class AnnouncementVo {

    private Integer id;

    private String category;

    private String title;

    private String content;

    private String operator;

    private List<ResourceVo> resources;

    private String releaseTime;

    private String expiryDate;

    private Boolean isTop;

    private Boolean isRead;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getOperator() {
        return operator;
    }

    public void setOperator(String operator) {
        this.operator = operator;
    }

    public List<ResourceVo> getResources() {
        return resources;
    }

    public void setResources(List<ResourceVo> resources) {
        this.resources = resources;
    }

    public String getReleaseTime() {
        return releaseTime;
    }

    public void setReleaseTime(String releaseTime) {
        this.releaseTime = releaseTime;
    }

    public String getExpiryDate() {
        return expiryDate;
    }

    public void setExpiryDate(String expiryDate) {
        this.expiryDate = expiryDate;
    }

    public Boolean getTop() {
        return isTop;
    }

    public void setTop(Boolean top) {
        isTop = top;
    }

    public Boolean getRead() {
        return isRead;
    }

    public void setRead(Boolean read) {
        isRead = read;
    }
}
