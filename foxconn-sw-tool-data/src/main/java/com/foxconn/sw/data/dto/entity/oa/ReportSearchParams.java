package com.foxconn.sw.data.dto.entity.oa;

import io.swagger.v3.oas.annotations.media.Schema;

public class ReportSearchParams {

    private Integer nearly;
    private Integer weekOfYear;
    @Schema(description = "查詢類型：0/null- 默認查詢自己，1-查詢團隊")
    private Integer searchType;

    public Integer getNearly() {
        return nearly;
    }

    public void setNearly(Integer nearly) {
        this.nearly = nearly;
    }

    public Integer getWeekOfYear() {
        return weekOfYear;
    }

    public void setWeekOfYear(Integer weekOfYear) {
        this.weekOfYear = weekOfYear;
    }

    public Integer getSearchType() {
        return searchType;
    }

    public void setSearchType(Integer searchType) {
        this.searchType = searchType;
    }
}
