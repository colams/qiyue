package com.foxconn.sw.data.dto.request.announcement;

import com.foxconn.sw.data.dto.enums.AnnouncementStatusEnums;

import java.util.List;

public class AnnouncementParams {

    private Integer id;
    private String title;
    private String content;
    private String operator;
    private Integer top;
    private String category;
    private AnnouncementStatusEnums status;
    private String expiryDate;
    private List<Integer> resourceIds;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public Integer getTop() {
        return top;
    }

    public void setTop(Integer top) {
        this.top = top;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public AnnouncementStatusEnums getStatus() {
        return status;
    }

    public void setStatus(AnnouncementStatusEnums status) {
        this.status = status;
    }

    public String getExpiryDate() {
        return expiryDate;
    }

    public void setExpiryDate(String expiryDate) {
        this.expiryDate = expiryDate;
    }

    public List<Integer> getResourceIds() {
        return resourceIds;
    }

    public void setResourceIds(List<Integer> resourceIds) {
        this.resourceIds = resourceIds;
    }
}
