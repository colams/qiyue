package com.foxconn.sw.data.dto.entity.universal;

public class OperateEntity {

    private String operateName;
    private boolean enable;
    private String operateType;
    private Integer subOperateType;

    public String getOperateName() {
        return operateName;
    }

    public void setOperateName(String operateName) {
        this.operateName = operateName;
    }

    public boolean getEnable() {
        return enable;
    }

    public void setEnable(boolean enable) {
        this.enable = enable;
    }

    public String getOperateType() {
        return operateType;
    }

    public void setOperateType(String operateType) {
        this.operateType = operateType;
    }

    public Integer getSubOperateType() {
        return subOperateType;
    }

    public void setSubOperateType(Integer subOperateType) {
        this.subOperateType = subOperateType;
    }
}
