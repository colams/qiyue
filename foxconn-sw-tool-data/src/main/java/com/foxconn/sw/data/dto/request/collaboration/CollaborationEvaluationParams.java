package com.foxconn.sw.data.dto.request.collaboration;

import java.util.List;

public class CollaborationEvaluationParams {

    private List<Integer> idList;

    /**
     * 3-駁回，4-通過
     */
    private Integer evaluationType;


    public List<Integer> getIdList() {
        return idList;
    }

    public void setIdList(List<Integer> idList) {
        this.idList = idList;
    }

    public Integer getEvaluationType() {
        return evaluationType;
    }

    public void setEvaluationType(Integer evaluationType) {
        this.evaluationType = evaluationType;
    }
}
