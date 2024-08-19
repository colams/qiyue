package com.foxconn.sw.data.entity.extension;

import com.foxconn.sw.data.entity.SwTaskProgress;

public class SwTaskProgressExtension extends SwTaskProgress {
    private String employeeName;

    public String getEmployeeName() {
        return employeeName;
    }

    public void setEmployeeName(String employeeName) {
        this.employeeName = employeeName;
    }
}
