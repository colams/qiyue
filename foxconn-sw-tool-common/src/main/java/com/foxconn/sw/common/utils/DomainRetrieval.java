package com.foxconn.sw.common.utils;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

public class DomainRetrieval {

    private static final String PRO_IP = "10.151.130.208";
    private static final String PRO_Domain = "rayplusoa.efoxconn.com";


    public static String getDomain() {
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();

        String serverName = request.getServerName();
        int serverPort = request.getServerPort();

        if (PRO_Domain.equalsIgnoreCase(serverName) || PRO_IP.equalsIgnoreCase(serverName)) {
            return String.format("https://%s", serverName);
        }
        String data;
        if ("localhost".equals(serverName)) {
            String localAddr = request.getLocalAddr();
            data = localAddr + ":" + serverPort;
        } else {
            data = serverName + ":" + serverPort;
        }
        return String.format("http://%s", data);
    }

}
