package com.foxconn.sw.data.constants.enums.oa;

import com.foxconn.sw.data.exception.BizException;

import static com.foxconn.sw.data.constants.enums.retcode.RetCode.ENUM_CONVERT_ERROR;

public enum TaskStatusEnums {

    DRAFT(0, "草稿", "#966400"), // 发布人可见
    PENDING(1, "待確認", "rgb(255,192,0)"),// 待确认-发布人&中间人；无DRI都是带确认
    PROCESSING(2, "處理中", "blue"),// 處理中-有DRI
    ACCEPTING(3, "待驗收", "#98FB98"),  //  负责人&中间人&发布人，更新狀態
    COMPLETED(4, "已完成", "#008000"),   // 负责人&中间人&发布人
    REVOKE(6, "已撤销", "gray"),
    CLOSED(7, "已關閉", "#d05667"),
    DELETE(8, "删除", "#d05667"),
    ;

    TaskStatusEnums(Integer code, String msg, String color) {
        this.code = code;
        this.msg = msg;
        this.color = color;
    }

    private Integer code;
    private String msg;
    private String color;

    public Integer getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }

    public String getColor() {
        return color;
    }

    public static TaskStatusEnums getStatusByCode(Integer code) {
        for (TaskStatusEnums status : TaskStatusEnums.values()) {
            if (status.getCode() == code) {
                return status;
            }
        }
        throw new BizException(ENUM_CONVERT_ERROR);
    }

    public boolean isMatch(TaskStatusEnums taskStatusEnums) {
        return this == taskStatusEnums;
    }
}
