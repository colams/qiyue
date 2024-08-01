package com.foxconn.sw.common.utils.cipher;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;

public class URLCoderUtils {

    public static String encode(String input, String chartSet) {
        String output = null;
        try {
            output = URLEncoder.encode(input, chartSet);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            output = input;
        }
        return output;
    }

    public static String decode(String input, String chartSet) {
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
