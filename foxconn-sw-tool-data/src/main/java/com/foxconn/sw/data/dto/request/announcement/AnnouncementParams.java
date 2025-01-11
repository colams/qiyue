package com.foxconn.sw.data.dto.request.announcement;

import com.foxconn.sw.data.dto.enums.AnnouncementStatusEnums;

public class AnnouncementParams {

    private Integer id;
    private String title;
    private String content;
    private String operator;
    private AnnouncementStatusEnums status;
    private String expiryDate;

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
}
