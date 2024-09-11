package com.foxconn.sw.data.dto.entity.oa;

import io.swagger.v3.oas.annotations.media.Schema;

public class ReportSearchParams {

    @Schema(description = "查詢類型：0/null- 默認查詢自己，1-查詢自己，2-查詢团队")
    private Integer searchType;

    /**
     * 每个周 的 周一
     */
    private String weekOfStart;

    private String employeeName;
    private String startDate;
    private String endDate;

    public Integer getSearchType() {
        return searchType;
    }

    public void setSearchType(Integer searchType) {
        this.searchType = searchType;
    }

    public String getEmployeeName() {
        return employeeName;
    }

    public void setEmployeeName(String employeeName) {
        this.employeeName = employeeName;
    }

    public String getWeekOfStart() {
        return weekOfStart;
    }

    public void setWeekOfStart(String weekOfStart) {
        this.weekOfStart = weekOfStart;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }
}
