package com.foxconn.sw.data.dto.request.meeting;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;

public class DetailMeetingParams {

    @Schema(description = "会议ID")
    @NotNull(message = "会议ID不能为空")
    private Integer meetingID;

    @Schema(description = "会议查询日期，周一")
    private String searchDate;

    public String getSearchDate() {
        return searchDate;
    }

    public void setSearchDate(String searchDate) {
        this.searchDate = searchDate;
    }

    public Integer getMeetingID() {
        return meetingID;
    }

    public void setMeetingID(Integer meetingID) {
        this.meetingID = meetingID;
    }
}
