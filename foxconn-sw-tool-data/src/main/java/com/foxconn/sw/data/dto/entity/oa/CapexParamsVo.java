package com.foxconn.sw.data.dto.entity.oa;

import java.util.List;

public class CapexParamsVo {

    private String sheetName;
    private List<CapexSetVo> capexSet;

    private List<CapexSetDetail2Vo> detail2Vos;

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

    public List<CapexSetDetail2Vo> getDetail2Vos() {
        return detail2Vos;
    }

    public void setDetail2Vos(List<CapexSetDetail2Vo> detail2Vos) {
        this.detail2Vos = detail2Vos;
    }
}
