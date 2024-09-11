package com.foxconn.sw.data.constants.enums;

public enum GenderEnums {

    MALE(1, "男", "M"),
    FEMALE(2, "女", "F"),
    ;

    GenderEnums(Integer code, String des, String enCode) {
        this.code = code;
        this.des = des;
        this.enCode = enCode;
    }

    public static String getGenderDes(String enCode) {
        if (MALE.getEnCode().equalsIgnoreCase(enCode)) {
            return MALE.getDes();
        } else if (FEMALE.getEnCode().equalsIgnoreCase(enCode)) {
            return FEMALE.getDes();
        }
        return "";
    }

    public static String getGenderDes(Integer code) {
        if (code == 1) {
            return MALE.getDes();
        } else if (code == 2) {
            return FEMALE.getDes();
        }
        return "";
    }

    private Integer code;
    private String enCode;
    private String des;

    public Integer getCode() {
        return code;
    }

    public String getEnCode() {
        return enCode;
    }

    public String getDes() {
        return des;
    }
}
