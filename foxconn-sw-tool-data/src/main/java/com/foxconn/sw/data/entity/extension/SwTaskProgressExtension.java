package com.foxconn.sw.data.entity.extension;

import com.foxconn.sw.data.entity.SwTaskProgress;

public class SwTaskProgressExtension extends SwTaskProgress {
    private String employeeName;
    private Integer contentHistoryId;

    public String getEmployeeName() {
        return employeeName;
    }

    public void setEmployeeName(String employeeName) {
        this.employeeName = employeeName;
    }

    public Integer getContentHistoryId() {
        return contentHistoryId;
    }

    public void setContentHistoryId(Integer contentHistoryId) {
        this.contentHistoryId = contentHistoryId;
    }
}
