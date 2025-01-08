package com.foxconn.sw.data.dto.request.account;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import org.hibernate.validator.constraints.Length;

public class CreateAccountParams {


    @NotNull(message = "姓名不能为空")
    @Pattern(regexp = "^\\S*$", message = "姓名不能全部为空格")
    private String name;

    @NotNull(message = "部门不能为空")
    @Min(1)
    private Integer departmentId;

    @NotNull(message = "账号不能为空")
    @Length(min = 4, max = 20, message = "账号长度需4-20位")
    private String employeeNo;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(Integer departmentId) {
        this.departmentId = departmentId;
    }


    public String getEmployeeNo() {
        return employeeNo;
    }

    public void setEmployeeNo(String employeeNo) {
        this.employeeNo = employeeNo;
    }

}
