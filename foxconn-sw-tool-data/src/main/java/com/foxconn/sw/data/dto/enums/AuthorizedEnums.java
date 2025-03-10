package com.foxconn.sw.data.dto.enums;

public enum AuthorizedEnums {


    PublicGroupsAuthorized(1, "publicGroups", "公开群组"),
    ProjectMenuAuthorized(2, "projectMenu", "专案管理"),
    AddEmployeeAuthorized(3, "addEmployee", "添加用户"),
    ;

    AuthorizedEnums(int code, String enCode, String name) {
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


}
