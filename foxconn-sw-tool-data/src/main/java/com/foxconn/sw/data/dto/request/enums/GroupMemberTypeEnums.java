package com.foxconn.sw.data.dto.request.enums;

import com.foxconn.sw.data.exception.BizException;

import static com.foxconn.sw.data.constants.enums.retcode.RetCode.ENUM_CONVERT_ERROR;

public enum GroupMemberTypeEnums {

    Member(0, "member", "成员"),
    Owner(1, "owner", "群主"),
    Admin(2, "admin", "管理员"),
    ;

    GroupMemberTypeEnums(int code, String enCode, String name) {
        this.code = code;
        this.enCode = enCode;
        this.name = name;
    }

    private int code;
    private String enCode;
    private String name;

    public int getCode() {
        return code;
    }

    public String getEnCode() {
        return enCode;
    }

    public String getName() {
        return name;
    }


    public static GroupMemberTypeEnums getEnumByCode(Integer code) {
        for (GroupMemberTypeEnums enums : GroupMemberTypeEnums.values()) {
            if (enums.getCode() == code) {
                return enums;
            }
        }
        throw new BizException(ENUM_CONVERT_ERROR);
    }

}
