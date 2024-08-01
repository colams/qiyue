package com.foxconn.sw.data.constants.enums.oa;

public enum ThingsItemEnums {

    /**
     * 待辦事項
     */
    SCHEDULE("待辦事項"),
    /**
     * 草稿箱
     */
    DRAFT("草稿箱"),
    /**
     * 待審批
     */
    APPROVALS("待審批"),
    /**
     * 工作郵件
     */
    EMAIL("工作郵件"),
    /**
     * 代理工作
     */
    URGENT_ITEM("代理工作"),
    /**
     * 未讀消息
     */
    UN_READ("未讀消息"),
    /**
     * 緊急事項
     */
    AGENCY_WORK("緊急事項"),
    ;


    ThingsItemEnums(String description) {
        this.description = description;
    }

    private String description;

    public String getDescription() {
        return description;
    }
}
