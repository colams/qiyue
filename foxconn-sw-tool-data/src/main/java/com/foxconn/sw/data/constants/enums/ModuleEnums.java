package com.foxconn.sw.data.constants.enums;

public enum ModuleEnums {

    Forum(1, "论坛", "forum_bbs_comment"),
    Task(2, "工作任務", "sw_task"),
    Notification(3, "通知消息", "sw_notification"),

    ;

    ModuleEnums(int code, String name, String tableName) {
        this.code = code;
        this.name = name;
        this.tableName = tableName;
    }

    private int code;
    private String name;
    /**
     * 关联表信息
     */
    private String tableName;

    public int getCode() {
        return code;
    }

    public String getName() {
        return name;
    }

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

}
