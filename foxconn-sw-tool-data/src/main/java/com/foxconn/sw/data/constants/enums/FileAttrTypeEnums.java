package com.foxconn.sw.data.constants.enums;

public enum FileAttrTypeEnums {


    ICON("icon"),
    TOOL("tool"),
    GUIDE("guide"),
    RESULT("result"),
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
