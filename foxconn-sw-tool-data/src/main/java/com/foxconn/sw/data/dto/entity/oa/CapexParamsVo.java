package com.foxconn.sw.data.dto.entity.oa;

import java.util.List;

public class CapexParamsVo {

    private String sheetName;
    private List<CapexSetVo> capexSet;

    public String getSheetName() {
        return sheetName;
    }

    public void setSheetName(String sheetName) {
        this.sheetName = sheetName;
    }

    public List<CapexSetVo> getCapexSet() {
        return capexSet;
    }

    public void setCapexSet(List<CapexSetVo> capexSet) {
        this.capexSet = capexSet;
    }
}
