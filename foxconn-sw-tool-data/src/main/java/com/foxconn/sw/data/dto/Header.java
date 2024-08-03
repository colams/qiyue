package com.foxconn.sw.data.dto;

import io.swagger.v3.oas.annotations.media.Schema;

public class Header {

    @Schema(description = "登录token信息")
    private String token;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
