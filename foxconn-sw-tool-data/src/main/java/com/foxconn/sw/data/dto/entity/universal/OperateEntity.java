package com.foxconn.sw.data.dto.entity.universal;

import io.swagger.v3.oas.annotations.media.Schema;

public class OperateEntity {

    private String operateName;
    private Boolean enable;
    @Schema(description = "取值范围：查看(view),更新(update),分派(assign),验收(check),撤销(revoke)", name = "操作类型")
    private String operateType;
    private String msg;

    public String getOperateName() {
        return operateName;
    }

    public void setOperateName(String operateName) {
        this.operateName = operateName;
    }

    public Boolean getEnable() {
        return enable;
    }

    public void setEnable(Boolean enable) {
        this.enable = enable;
    }

    public String getOperateType() {
        return operateType;
    }

    public void setOperateType(String operateType) {
        this.operateType = operateType;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
