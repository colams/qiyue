package com.foxconn.sw.data.entity;

import java.time.LocalDateTime;

public class SwLog {
    private Integer id;

    private String operator;

    private String ip;

    private String operatetype;

    private String remark;

    private LocalDateTime createTime;

    public Integer getId() {
        return id;
    }

    public SwLog withId(Integer id) {
        this.setId(id);
        return this;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getOperator() {
        return operator;
    }

    public SwLog withOperator(String operator) {
        this.setOperator(operator);
        return this;
    }

    public void setOperator(String operator) {
        this.operator = operator == null ? null : operator.trim();
    }

    public String getIp() {
        return ip;
    }

    public SwLog withIp(String ip) {
        this.setIp(ip);
        return this;
    }

    public void setIp(String ip) {
        this.ip = ip == null ? null : ip.trim();
    }

    public String getOperatetype() {
        return operatetype;
    }

    public SwLog withOperatetype(String operatetype) {
        this.setOperatetype(operatetype);
        return this;
    }

    public void setOperatetype(String operatetype) {
        this.operatetype = operatetype == null ? null : operatetype.trim();
    }

    public String getRemark() {
        return remark;
    }

    public SwLog withRemark(String remark) {
        this.setRemark(remark);
        return this;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

    public LocalDateTime getCreateTime() {
        return createTime;
    }

    public SwLog withCreateTime(LocalDateTime createTime) {
        this.setCreateTime(createTime);
        return this;
    }

    public void setCreateTime(LocalDateTime createTime) {
        this.createTime = createTime;
    }
}