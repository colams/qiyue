package com.foxconn.sw.data.constants.enums;

public enum BasicPropertyEnums {

    TOOL_CATEGORY(1, "tool_category", "工具库工具分类"),
    MODULE(2, "system_module", "系统功能模块"),


    ;

    BasicPropertyEnums(int code, String enCode, String name) {
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
