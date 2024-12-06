package com.foxconn.sw.data.entity;

import java.time.LocalDateTime;

public class SwCustomGroupApply {
    private Integer id;

    private Integer customGroupId;

    private String applyEmployeeNo;

    private String remark;

    private Integer status;

    private LocalDateTime createTime;

    private LocalDateTime datetimeLastchange;

    public Integer getId() {
        return id;
    }

    public SwCustomGroupApply withId(Integer id) {
        this.setId(id);
        return this;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getCustomGroupId() {
        return customGroupId;
    }

    public SwCustomGroupApply withCustomGroupId(Integer customGroupId) {
        this.setCustomGroupId(customGroupId);
        return this;
    }

    public void setCustomGroupId(Integer customGroupId) {
        this.customGroupId = customGroupId;
    }

    public String getApplyEmployeeNo() {
        return applyEmployeeNo;
    }

    public SwCustomGroupApply withApplyEmployeeNo(String applyEmployeeNo) {
        this.setApplyEmployeeNo(applyEmployeeNo);
        return this;
    }

    public void setApplyEmployeeNo(String applyEmployeeNo) {
        this.applyEmployeeNo = applyEmployeeNo == null ? null : applyEmployeeNo.trim();
    }

    public String getRemark() {
        return remark;
    }

    public SwCustomGroupApply withRemark(String remark) {
        this.setRemark(remark);
        return this;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

    public Integer getStatus() {
        return status;
    }

    public SwCustomGroupApply withStatus(Integer status) {
        this.setStatus(status);
        return this;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public LocalDateTime getCreateTime() {
        return createTime;
    }

    public SwCustomGroupApply withCreateTime(LocalDateTime createTime) {
        this.setCreateTime(createTime);
        return this;
    }

    public void setCreateTime(LocalDateTime createTime) {
        this.createTime = createTime;
    }

    public LocalDateTime getDatetimeLastchange() {
        return datetimeLastchange;
    }

    public SwCustomGroupApply withDatetimeLastchange(LocalDateTime datetimeLastchange) {
        this.setDatetimeLastchange(datetimeLastchange);
        return this;
    }

    public void setDatetimeLastchange(LocalDateTime datetimeLastchange) {
        this.datetimeLastchange = datetimeLastchange;
    }
}