package com.foxconn.sw.data.dto.entity.oa;

import java.util.List;

public class CapexSetDetailVo {

    private Integer index;
    private String setValue;
    private List<CapexSetValueVo> setValueVos;

    public Integer getIndex() {
        return index;
    }

    public void setIndex(Integer index) {
        this.index = index;
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
}
