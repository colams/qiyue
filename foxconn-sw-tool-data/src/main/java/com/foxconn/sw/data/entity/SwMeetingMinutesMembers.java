package com.foxconn.sw.data.entity;

import java.time.LocalDateTime;

public class SwMeetingMinutesMembers {
    private Integer id;

    private Long mmId;

    private String employeeNo;

    private Integer role;

    private String name;

    private Integer isDelete;

    private LocalDateTime createTime;

    private LocalDateTime datetimeLastchange;

    public Integer getId() {
        return id;
    }

    public SwMeetingMinutesMembers withId(Integer id) {
        this.setId(id);
        return this;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Long getMmId() {
        return mmId;
    }

    public SwMeetingMinutesMembers withMmId(Long mmId) {
        this.setMmId(mmId);
        return this;
    }

    public void setMmId(Long mmId) {
        this.mmId = mmId;
    }

    public String getEmployeeNo() {
        return employeeNo;
    }

    public SwMeetingMinutesMembers withEmployeeNo(String employeeNo) {
        this.setEmployeeNo(employeeNo);
        return this;
    }

    public void setEmployeeNo(String employeeNo) {
        this.employeeNo = employeeNo == null ? null : employeeNo.trim();
    }

    public Integer getRole() {
        return role;
    }

    public SwMeetingMinutesMembers withRole(Integer role) {
        this.setRole(role);
        return this;
    }

    public void setRole(Integer role) {
        this.role = role;
    }

    public String getName() {
        return name;
    }

    public SwMeetingMinutesMembers withName(String name) {
        this.setName(name);
        return this;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public Integer getIsDelete() {
        return isDelete;
    }

    public SwMeetingMinutesMembers withIsDelete(Integer isDelete) {
        this.setIsDelete(isDelete);
        return this;
    }

    public void setIsDelete(Integer isDelete) {
        this.isDelete = isDelete;
    }

    public LocalDateTime getCreateTime() {
        return createTime;
    }

    public SwMeetingMinutesMembers withCreateTime(LocalDateTime createTime) {
        this.setCreateTime(createTime);
        return this;
    }

    public void setCreateTime(LocalDateTime createTime) {
        this.createTime = createTime;
    }

    public LocalDateTime getDatetimeLastchange() {
        return datetimeLastchange;
    }

    public SwMeetingMinutesMembers withDatetimeLastchange(LocalDateTime datetimeLastchange) {
        this.setDatetimeLastchange(datetimeLastchange);
        return this;
    }

    public void setDatetimeLastchange(LocalDateTime datetimeLastchange) {
        this.datetimeLastchange = datetimeLastchange;
    }
}