package com.foxconn.sw.common.utils;

import net.sourceforge.pinyin4j.PinyinHelper;
import net.sourceforge.pinyin4j.format.HanyuPinyinCaseType;
import net.sourceforge.pinyin4j.format.HanyuPinyinOutputFormat;
import net.sourceforge.pinyin4j.format.HanyuPinyinToneType;
import net.sourceforge.pinyin4j.format.exception.BadHanyuPinyinOutputFormatCombination;

public class PinyinUtils {

    public static String toPinyin(String input) {
        input = input.toLowerCase();
        StringBuilder pinyinResult = new StringBuilder();
        try {
            HanyuPinyinOutputFormat format = new HanyuPinyinOutputFormat();
            format.setCaseType(HanyuPinyinCaseType.LOWERCASE);
            format.setToneType(HanyuPinyinToneType.WITHOUT_TONE);

            for (char c : input.toCharArray()) {
                if (!Character.isIdeographic(c)) {
                    pinyinResult.append(c);
                    continue;
                }
                String[] pinyinArray = PinyinHelper.toHanyuPinyinStringArray(c, format);
                if (pinyinArray != null) {
                    pinyinResult.append(pinyinArray[0]);
                } else {
                    pinyinResult.append(c);
                }
            }
        } catch (BadHanyuPinyinOutputFormatCombination e) {
            pinyinResult = new StringBuilder(input);
        }
        return pinyinResult.toString();
    }

    public static String firstLetter(String input) {
        input = input.toLowerCase();
        StringBuilder pinyinResult = new StringBuilder();
        try {
            HanyuPinyinOutputFormat format = new HanyuPinyinOutputFormat();
            format.setCaseType(HanyuPinyinCaseType.LOWERCASE);
            format.setToneType(HanyuPinyinToneType.WITHOUT_TONE);

            for (char c : input.toCharArray()) {
                if (!Character.isIdeographic(c)) {
                    pinyinResult.append(c);
                    continue;
                }
                String[] pinyinArray = PinyinHelper.toHanyuPinyinStringArray(c, format);
                if (pinyinArray != null) {
                    pinyinResult.append(pinyinArray[0]);
                } else {
                    pinyinResult.append(c);
                }
            }
        } catch (BadHanyuPinyinOutputFormatCombination e) {
            pinyinResult = new StringBuilder(input);
        }
        return pinyinResult.substring(0, 1);
    }
}
