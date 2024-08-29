package com.foxconn.sw.data.entity;

import java.time.LocalDateTime;

public class SwContactGather {
    private Integer id;

    private String employeeNo;

    private String gatherEmployeeNo;

    private Integer status;

    private LocalDateTime createTime;

    private LocalDateTime datetimeLastchange;

    public Integer getId() {
        return id;
    }

    public SwContactGather withId(Integer id) {
        this.setId(id);
        return this;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getEmployeeNo() {
        return employeeNo;
    }

    public SwContactGather withEmployeeNo(String employeeNo) {
        this.setEmployeeNo(employeeNo);
        return this;
    }

    public void setEmployeeNo(String employeeNo) {
        this.employeeNo = employeeNo == null ? null : employeeNo.trim();
    }

    public String getGatherEmployeeNo() {
        return gatherEmployeeNo;
    }

    public SwContactGather withGatherEmployeeNo(String gatherEmployeeNo) {
        this.setGatherEmployeeNo(gatherEmployeeNo);
        return this;
    }

    public void setGatherEmployeeNo(String gatherEmployeeNo) {
        this.gatherEmployeeNo = gatherEmployeeNo == null ? null : gatherEmployeeNo.trim();
    }

    public Integer getStatus() {
        return status;
    }

    public SwContactGather withStatus(Integer status) {
        this.setStatus(status);
        return this;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public LocalDateTime getCreateTime() {
        return createTime;
    }

    public SwContactGather withCreateTime(LocalDateTime createTime) {
        this.setCreateTime(createTime);
        return this;
    }

    public void setCreateTime(LocalDateTime createTime) {
        this.createTime = createTime;
    }

    public LocalDateTime getDatetimeLastchange() {
        return datetimeLastchange;
    }

    public SwContactGather withDatetimeLastchange(LocalDateTime datetimeLastchange) {
        this.setDatetimeLastchange(datetimeLastchange);
        return this;
    }

    public void setDatetimeLastchange(LocalDateTime datetimeLastchange) {
        this.datetimeLastchange = datetimeLastchange;
    }
}