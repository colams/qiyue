package com.foxconn.sw.data.dto.entity.oa;

import java.util.List;

public class CapexSetVo {

    private String type;
    private List<CapexSetDetail2Vo> capexSets;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public List<CapexSetDetail2Vo> getCapexSets() {
        return capexSets;
    }

    public void setCapexSets(List<CapexSetDetail2Vo> capexSets) {
        this.capexSets = capexSets;
    }
}
