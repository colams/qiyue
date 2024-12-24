package com.foxconn.sw.data.dto;

import io.swagger.v3.oas.annotations.media.Schema;

public class Header {

    @Schema(description = "登录token信息")
    private String token;

    @Schema(description = "多語言信息")
    private String locale;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getLocale() {
        return locale;
    }

    public void setLocale(String locale) {
        this.locale = locale;
    }
}
