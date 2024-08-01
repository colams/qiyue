package com.foxconn.sw.common.utils.cipher;

import org.apache.commons.lang3.StringUtils;
import org.springframework.util.DigestUtils;

import java.nio.charset.StandardCharsets;

public class CipherUtils {

    public static String encodeMD5(String input) {
        if (StringUtils.isBlank(input)) {
            return "";
        }
        return DigestUtils.md5DigestAsHex(input.getBytes(StandardCharsets.UTF_8));
    }

}
