package com.foxconn.sw.common.utils;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

public class DomainRetrieval {

    public static String getDomain() {
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();

        String serverName = request.getServerName();
//        int serverPort = request.getServerPort();
//
//        String data;
//        if ("localhost".equals(serverName)) {
//            String localAddr = request.getLocalAddr();
//            data = localAddr + ":" + serverPort;
//        } else {
//            data = serverName + ":" + serverPort;
//        }

        return "http://" + serverName;
    }

}
