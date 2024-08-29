package com.foxconn.sw.common.utils;

import org.apache.commons.lang3.StringUtils;
import org.springframework.util.DigestUtils;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

public class SecurityUtils {

    public static String encodeMD5(String input) {
        if (StringUtils.isBlank(input)) {
            return "";
        }
        return DigestUtils.md5DigestAsHex(input.getBytes(StandardCharsets.UTF_8));
    }

    public static String encodeURL(String input, String chartSet) {
        String output = null;
        try {
            output = URLEncoder.encode(input, chartSet);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            output = input;
        }
        return output;
    }

    public static String decodeURL(String input, String chartSet) {
        String output = null;
        try {
            output = URLDecoder.decode(input, chartSet);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            output = input;
        }
        return output;
    }

}
