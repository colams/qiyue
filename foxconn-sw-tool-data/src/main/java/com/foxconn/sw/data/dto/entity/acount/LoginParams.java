package com.foxconn.sw.data.dto.entity.acount;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class LoginParams {

    @NotBlank(message = "用户名必填！")
    @Size(min = 6, message = "用户名长度最少六个字符！")
    private String userName;

    @NotBlank(message = "密码必填！")
    @Size(min = 6, message = "密码长度最少六个字符！")
    private String password;

    private String authCode;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
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
