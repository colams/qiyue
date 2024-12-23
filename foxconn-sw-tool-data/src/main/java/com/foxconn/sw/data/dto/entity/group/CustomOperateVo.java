package com.foxconn.sw.data.dto.entity.group;

import com.foxconn.sw.data.dto.entity.acount.EmployeeVo;

public class CustomOperateVo {

    private Integer id;
    private EmployeeVo employeeVo;
    private String remark;
    private String operateType;
    private String operateTime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public EmployeeVo getEmployeeVo() {
        return employeeVo;
    }

    public void setEmployeeVo(EmployeeVo employeeVo) {
        this.employeeVo = employeeVo;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getOperateType() {
        return operateType;
    }

    public void setOperateType(String operateType) {
        this.operateType = operateType;
    }

    public String getOperateTime() {
        return operateTime;
    }

    public void setOperateTime(String operateTime) {
        this.operateTime = operateTime;
    }
}
