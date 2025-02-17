package com.foxconn.sw.data.constants.enums;

/**
 * sw_msg_pool
 */
public enum MsgTypeEnums {
    CreateTask(1, "任务创建 消息"),
    UpdateTask(2, "任务更新 消息"),
    AssignTask(3, "任务分派 消息"),

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
