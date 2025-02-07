package com.foxconn.sw.common.utils;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ServletUtils {


    @Autowired
    private HttpServletRequest servletRequest;

    public String getRemoteIp() {
        String remoteAdd = "";

        if (servletRequest != null) {
            remoteAdd = servletRequest.getHeader("X-FORWARDED-FOR");
            if (remoteAdd == null || "".equals(remoteAdd)) {
                remoteAdd = servletRequest.getRemoteAddr();
            }
        }
        return remoteAdd;
    }


    public String getRequestURL() {
        return servletRequest.getRequestURL().toString();
    }

    public Cookie[] getCookies() {
        return servletRequest.getCookies();
    }


}
