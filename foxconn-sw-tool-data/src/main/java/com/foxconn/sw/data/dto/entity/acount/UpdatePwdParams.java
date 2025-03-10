package com.foxconn.sw.data.dto.entity.acount;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public class UpdatePwdParams {

    @NotBlank(message = "工号不能为空！")
    @Size(min = 6, message = "工号长度最少六个字符！")
    @Pattern(regexp = "^(?!\\s*$)\\S+.*$", message = "工号不能全部为空格")
    private String employeeNo;

    @NotBlank(message = "密码必填！")
    @Size(min = 6, message = "密码长度最少六个字符！")
    @Pattern(regexp = "^(?!\\s*$)\\S+.*$", message = "密码不能全部为空格")
    private String oldPassword;

    @NotBlank(message = "密码必填！")
    @Size(min = 6, message = "密码长度最少六个字符！")
    @Pattern(regexp = "^(?!\\s*$)\\S+.*$", message = "密码不能全部为空格")
    private String password;

    public String getEmployeeNo() {
        return employeeNo;
    }

    public void setEmployeeNo(String employeeNo) {
        this.employeeNo = employeeNo;
    }

    public String getOldPassword() {
        return oldPassword;
    }

    public void setOldPassword(String oldPassword) {
        this.oldPassword = oldPassword;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
