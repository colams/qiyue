package com.foxconn.sw.data.dto.entity.universal;

import io.swagger.v3.oas.annotations.media.Schema;

public class OperateEntity {

    private String operateName;
    private Boolean enable;
    // @Schema(description = "取值范围：查看(view),更新(update),分派(assign),验收(check),撤销(revoke)", name = "操作类型")
    @Schema(description = "取值范围：查看(view),更新(update),跟催(going),撤销(revoke)", name = "操作类型")
    private String operateType;
    private Integer subOperateType;

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

    public Integer getSubOperateType() {
        return subOperateType;
    }

    public void setSubOperateType(Integer subOperateType) {
        this.subOperateType = subOperateType;
    }
}
