package com.foxconn.sw.data.dto.entity.acount;

import jakarta.validation.constraints.Pattern;

public class EmployeeVo {

    @Pattern(regexp = "^\\S*$", message = "字符串不能全部为空格")
    private String name;

    @Pattern(regexp = "^\\S*$", message = "字符串不能全部为空格")
    private String employeeNo;

    private Integer departmentId;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmployeeNo() {
        return employeeNo;
    }

    public void setEmployeeNo(String employeeNo) {
        this.employeeNo = employeeNo;
    }

    public Integer getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(Integer departmentId) {
        this.departmentId = departmentId;
    }
}
