package com.foxconn.sw.data.dto.request.meeting;

import io.swagger.v3.oas.annotations.media.Schema;

public class ListMeetingParams {
    @Schema(description = "会议查询日期，周一")
    private String searchStartDate;
    @Schema(description = "会议查询日期，周日")
    private String searchEndDate;

    public String getSearchStartDate() {
        return searchStartDate;
    }

    public void setSearchStartDate(String searchStartDate) {
        this.searchStartDate = searchStartDate;
    }

    public String getSearchEndDate() {
        return searchEndDate;
    }

    public void setSearchEndDate(String searchEndDate) {
        this.searchEndDate = searchEndDate;
    }
}
