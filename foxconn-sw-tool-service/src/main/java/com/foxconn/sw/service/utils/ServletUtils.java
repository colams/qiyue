package com.foxconn.sw.service.utils;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ServletUtils {


    @Autowired
    private HttpServletRequest servletRequest;

    public String getRemoteIp(){
        String remoteAddr = "";

        if (servletRequest != null) {
            remoteAddr = servletRequest.getHeader("X-FORWARDED-FOR");
            if (remoteAddr == null || "".equals(remoteAddr)) {
                remoteAddr = servletRequest.getRemoteAddr();
            }
        }

        return remoteAddr;
    }

}
