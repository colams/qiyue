package com.foxconn.sw.data.constants.enums.oa;

import com.foxconn.sw.data.exception.BizException;

import static com.foxconn.sw.data.constants.enums.retcode.RetCode.ENUM_CONVERT_ERROR;

public enum TaskStatusEnums {

    DRAFT(0, "草稿"), // 发布人可见
    PENDING(1, "待确认"),// 待确认-发布人；新需求-中间人
    CONFIRMING(2, "确认中"),// 确认中-发布人&中间人，新需求-负责人
    PROCESSING(3, "处理中"),  //  负责人&中间人&发布人
    ACCEPTING(4, "待验收"),   // 负责人&中间人&发布人
    COMPLETED(5, "已完成"),
    CLOSED(6, "已关闭"),

    ;

    TaskStatusEnums(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    private Integer code;
    private String msg;

    public Integer getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }

    public static TaskStatusEnums getStatusByCode(Integer code) {
        for (TaskStatusEnums status : TaskStatusEnums.values()) {
            if (status.getCode() == code) {
                return status;
            }
        }
        throw new BizException(ENUM_CONVERT_ERROR);
    }


}
