package com.foxconn.sw.data.dto.request.announcement;

import com.foxconn.sw.data.dto.enums.AnnouncementStatusEnums;

public class AnnouncementListParams {

    private String title;
    private String category;
    private AnnouncementStatusEnums status;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
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
}
