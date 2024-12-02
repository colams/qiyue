package com.foxconn.sw.data.constants.enums;

public enum TaskOperateType {


    RELEASE("release"),
    ;

    TaskOperateType(String operateType) {
        this.operateType = operateType;
    }

    private String operateType;


    public String getOperateType() {
        return operateType;
    }

}
