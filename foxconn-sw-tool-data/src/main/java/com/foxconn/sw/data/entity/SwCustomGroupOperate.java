package com.foxconn.sw.data.entity;

import java.time.LocalDateTime;

public class SwCustomGroupOperate {
    private Integer id;

    private Integer customGroupId;

    private String operator;

    private String operateType;

    private String remark;

    private Integer isRead;

    private Integer status;

    private LocalDateTime createTime;

    private LocalDateTime datetimeLastchange;

    public Integer getId() {
        return id;
    }

    public SwCustomGroupOperate withId(Integer id) {
        this.setId(id);
        return this;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getCustomGroupId() {
        return customGroupId;
    }

    public SwCustomGroupOperate withCustomGroupId(Integer customGroupId) {
        this.setCustomGroupId(customGroupId);
        return this;
    }

    public void setCustomGroupId(Integer customGroupId) {
        this.customGroupId = customGroupId;
    }

    public String getOperator() {
        return operator;
    }

    public SwCustomGroupOperate withOperator(String operator) {
        this.setOperator(operator);
        return this;
    }

    public void setOperator(String operator) {
        this.operator = operator == null ? null : operator.trim();
    }

    public String getOperateType() {
        return operateType;
    }

    public SwCustomGroupOperate withOperateType(String operateType) {
        this.setOperateType(operateType);
        return this;
    }

    public void setOperateType(String operateType) {
        this.operateType = operateType == null ? null : operateType.trim();
    }

    public String getRemark() {
        return remark;
    }

    public SwCustomGroupOperate withRemark(String remark) {
        this.setRemark(remark);
        return this;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

    public Integer getIsRead() {
        return isRead;
    }

    public SwCustomGroupOperate withIsRead(Integer isRead) {
        this.setIsRead(isRead);
        return this;
    }

    public void setIsRead(Integer isRead) {
        this.isRead = isRead;
    }

    public Integer getStatus() {
        return status;
    }

    public SwCustomGroupOperate withStatus(Integer status) {
        this.setStatus(status);
        return this;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public LocalDateTime getCreateTime() {
        return createTime;
    }

    public SwCustomGroupOperate withCreateTime(LocalDateTime createTime) {
        this.setCreateTime(createTime);
        return this;
    }

    public void setCreateTime(LocalDateTime createTime) {
        this.createTime = createTime;
    }

    public LocalDateTime getDatetimeLastchange() {
        return datetimeLastchange;
    }

    public SwCustomGroupOperate withDatetimeLastchange(LocalDateTime datetimeLastchange) {
        this.setDatetimeLastchange(datetimeLastchange);
        return this;
    }

    public void setDatetimeLastchange(LocalDateTime datetimeLastchange) {
        this.datetimeLastchange = datetimeLastchange;
    }
}