package com.foxconn.sw.data.constants.enums.oa;

public enum ProposerTaskStatusEnums {

    DRAFT(0, "草稿"), // 发布人可见
    PENDING(1, "待确认","yellow"),// 待确认-发布人；新需求-中间人
    CONFIRMING(2, "确认中",""),// 确认中-发布人&中间人，新需求-负责人
    PROCESSING(3, "处理中","blue"),  //  负责人&中间人&发布人
    ACCEPTING(4, "待验收","rosybrown"),   // 负责人&中间人&发布人
    COMPLETED(5, "已完成","green"),
    CLOSED(6, "已关闭"),

    ;


    ProposerTaskStatusEnums(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    ProposerTaskStatusEnums(int code, String msg,String color) {
        this.code = code;
        this.msg = msg;
        this.color = color;
    }

    private int code;
    private String msg;
    private String color;

    public int getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }

    public String getColor() {
        return color;
    }

}
