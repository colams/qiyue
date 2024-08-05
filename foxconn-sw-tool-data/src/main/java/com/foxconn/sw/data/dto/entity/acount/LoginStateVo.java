package com.foxconn.sw.data.dto.entity.acount;

import java.time.LocalDateTime;

public class LoginStateVo {

    private String token;
    private LocalDateTime localDateTime;
    private UserInfo user;


    public static LoginStateVo init(String token, LocalDateTime localDateTime, UserInfo user) {
        LoginStateVo login = new LoginStateVo();
        login.token = token;
        login.localDateTime = localDateTime;
        login.user = user;
        return login;
    }


    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public LocalDateTime getLocalDateTime() {
        return localDateTime;
    }

    public void setLocalDateTime(LocalDateTime localDateTime) {
        this.localDateTime = localDateTime;
    }

    public UserInfo getUser() {
        return user;
    }

    public void setUser(UserInfo user) {
        this.user = user;
    }
}
