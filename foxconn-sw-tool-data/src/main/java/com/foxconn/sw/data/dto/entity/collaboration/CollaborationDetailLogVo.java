package com.foxconn.sw.data.dto.entity.collaboration;

import com.foxconn.sw.data.dto.entity.acount.EmployeeVo;

import java.time.LocalDateTime;

public class CollaborationDetailLogVo {
    private Long id;

    private EmployeeVo employeeVo;

    private String remark;

    private LocalDateTime createTime;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
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

    public LocalDateTime getCreateTime() {
        return createTime;
    }

    public void setCreateTime(LocalDateTime createTime) {
        this.createTime = createTime;
    }
}
