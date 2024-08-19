package com.foxconn.sw.data.constants.enums;

/**
 * 文件分类
 */
public enum FileAttrTypeEnums {


    ICON("icon"),
    TOOL("tool"),
    GUIDE("guide"),
    RESULT("result"),
    OA("oa"),
    ;

    FileAttrTypeEnums(String symbol) {
        this.symbol = symbol;
    }


    private String symbol;

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

}
