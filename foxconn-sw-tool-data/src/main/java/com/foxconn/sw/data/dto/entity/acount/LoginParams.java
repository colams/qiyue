package com.foxconn.sw.data.dto.entity.acount;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public class LoginParams {

    @NotBlank(message = "工号不能为空！")
    @Size(min = 6, message = "工号长度最少六个字符！")
    @Pattern(regexp = "^(?!\\s*$)\\S+.*$", message = "工号不能全部为空格")
    private String employeeNo;

    @NotBlank(message = "密码必填！")
    @Size(min = 6, message = "密码长度最少六个字符！")
    @Pattern(regexp = "^(?!\\s*$)\\S+.*$", message = "密码不能全部为空格")
    private String password;

    private String authCode;

    public String getEmployeeNo() {
        return employeeNo;
    }

    public void setEmployeeNo(String employeeNo) {
        this.employeeNo = employeeNo;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getAuthCode() {
        return authCode;
    }

    public void setAuthCode(String authCode) {
        this.authCode = authCode;
    }
}
