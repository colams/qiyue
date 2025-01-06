package com.foxconn.sw.data.dto.entity.oa;

import java.util.List;

public class CapexSetDetail2Vo {

    private Integer index;
    private String type;
    private String setValue;
    private List<CapexSetValueVo> setValueVos;
    private String extra;

    public Integer getIndex() {
        return index;
    }

    public void setIndex(Integer index) {
        this.index = index;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getSetValue() {
        return setValue;
    }

    public void setSetValue(String setValue) {
        this.setValue = setValue;
    }

    public List<CapexSetValueVo> getSetValueVos() {
        return setValueVos;
    }

    public void setSetValueVos(List<CapexSetValueVo> setValueVos) {
        this.setValueVos = setValueVos;
    }

    public String getExtra() {
        return extra;
    }

    public void setExtra(String extra) {
        this.extra = extra;
    }
}
