package com.foxconn.sw.data.dto.entity.oa;

import com.foxconn.sw.data.dto.enums.CapexSettingEnums;

public class CapexSetValueVo {

    private CapexSettingEnums valueType;
    private String setValue;

    public CapexSettingEnums getValueType() {
        return valueType;
    }

    public void setValueType(CapexSettingEnums valueType) {
        this.valueType = valueType;
    }

    public String getSetValue() {
        return setValue;
    }

    public void setSetValue(String setValue) {
        this.setValue = setValue;
    }
}
