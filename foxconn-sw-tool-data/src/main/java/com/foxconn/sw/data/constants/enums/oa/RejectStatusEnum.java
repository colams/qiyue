package com.foxconn.sw.data.constants.enums.oa;

import com.foxconn.sw.data.exception.BizException;

import static com.foxconn.sw.data.constants.enums.retcode.RetCode.ENUM_CONVERT_ERROR;

public enum RejectStatusEnum {

    DEFAULT(0, "默认"), // 無駁回
    RELEASE_REJECT(1, "發佈駁回"),// 發佈駁回
    ACCEPTING_REJECT(2, "驗收駁回"),// 驗收駁回
    ;

    RejectStatusEnum(Integer code, String msg) {
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

    public static RejectStatusEnum getStatusByCode(Integer code) {
        for (RejectStatusEnum status : RejectStatusEnum.values()) {
            if (status.getCode() == code) {
                return status;
            }
        }
        throw new BizException(ENUM_CONVERT_ERROR);
    }

    public boolean test(Integer code) {
        return getCode() == code;
    }

}
