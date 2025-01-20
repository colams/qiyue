package com.foxconn.sw.data.dto.request.announcement;

import com.foxconn.sw.data.dto.enums.AnnouncementStatusEnums;

public class AnnouncementListParams {

    private String title;
    private AnnouncementStatusEnums status;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public AnnouncementStatusEnums getStatus() {
        return status;
    }

    public void setStatus(AnnouncementStatusEnums status) {
        this.status = status;
    }
}
