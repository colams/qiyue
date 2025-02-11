package com.foxconn.sw.data.constants.enums;

public enum MsgTypeEnums {
    TaskNotification(1, "消息处理,sw_msg_pool"),

    ;

    MsgTypeEnums(int code, String tableName) {
        this.code = code;
        this.tableName = tableName;
    }

    private int code;
    /**
     * 关联表信息
     */
    private String tableName;

    public int getCode() {
        return code;
    }


    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

}
