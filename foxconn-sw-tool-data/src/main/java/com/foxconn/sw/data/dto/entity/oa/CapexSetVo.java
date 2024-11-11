package com.foxconn.sw.data.dto.entity.oa;

import java.util.List;

public class CapexSetVo {

    private Integer type;
    private List<CapexSetDetailVo> capexSets;

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public List<CapexSetDetailVo> getCapexSets() {
        return capexSets;
    }

    public void setCapexSets(List<CapexSetDetailVo> capexSets) {
        this.capexSets = capexSets;
    }
}
