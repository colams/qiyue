package com.foxconn.sw.data.dto.request.group;

import com.foxconn.sw.data.dto.enums.AgreeStatusEnums;

public class ProcessApplyParams {

    private Integer applyID;

    private AgreeStatusEnums agree;

    public Integer getApplyID() {
        return applyID;
    }

    public void setApplyID(Integer applyID) {
        this.applyID = applyID;
    }

    public AgreeStatusEnums getAgree() {
        return agree;
    }

    public void setAgree(AgreeStatusEnums agree) {
        this.agree = agree;
    }
}
