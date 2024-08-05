package com.foxconn.sw.data.dto.entity.acount;

import jakarta.validation.constraints.NotNull;
import org.hibernate.validator.constraints.Length;

public class UserBriefParams {

    private String nickName;

    @NotNull(message = "账号不能为空")
    @Length(min = 4, max = 15, message = "账号长度不符合")
    private String employeeNo;

    @NotNull(message = "密码不能为空")
    @Length(min = 4, max = 15, message = "密码长度不符合")
    private String password;

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

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
}
