package com.foxconn.sw.data.dto.request.enums;

public enum AccessLevelEnums {

    PUBLIC(0, "public", "公开"),
    PRIVATE(1, "private", "私有"),
    ;

    AccessLevelEnums(int code, String enCode, String name) {
        this.code = code;
        this.enCode = enCode;
        this.name = name;
    }

    private int code;
    private String enCode;
    private String name;

    public int getCode() {
        return code;
    }

    public String getEnCode() {
        return enCode;
    }

    public String getName() {
        return name;
    }

}
