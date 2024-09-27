package com.foxconn.sw.data.dto.request.meeting;

import io.swagger.v3.oas.annotations.media.Schema;

public class ListMeetingParams {

    @Schema(description = "会议查询日期，周一")
    private String searchDate;

    public String getSearchDate() {
        return searchDate;
    }

    public void setSearchDate(String searchDate) {
        this.searchDate = searchDate;
    }
}
