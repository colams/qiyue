package com.foxconn.sw.data.dto.request.meeting;

import io.swagger.v3.oas.annotations.media.Schema;

public class ListMeetingParams {

    //    @Pattern(regexp = "^\\S*$", message = "字符串不能全部为空格")
    @Schema(description = "会议查询日期，周一")
    private String searchDate;
//    @Schema(description = "主持人")
//    private String chairman;

    public String getSearchDate() {
        return searchDate;
    }

    public void setSearchDate(String searchDate) {
        this.searchDate = searchDate;
    }

//    public String getChairman() {
//        return chairman;
//    }
//
//    public void setChairman(String chairman) {
//        this.chairman = chairman;
//    }
}
