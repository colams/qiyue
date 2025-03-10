package com.foxconn.sw.data.dto.enums;

public enum AgreeStatusEnums {


    Agree(1, "Agree", "同意/完成"),
    Deny(2, "Deny", "駁回"),
    ;

    AgreeStatusEnums(int code, String enCode, String name) {
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
