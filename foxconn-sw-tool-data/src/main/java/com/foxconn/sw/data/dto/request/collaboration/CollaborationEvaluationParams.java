package com.foxconn.sw.data.dto.request.collaboration;

import java.util.List;

public class CollaborationEvaluationParams {
    private Integer taskID;

    private List<Long> idList;

    /**
     * 3-駁回，4-通過
     */
    private Integer evaluationType;

    public Integer getTaskID() {
        return taskID;
    }

    public void setTaskID(Integer taskID) {
        this.taskID = taskID;
    }

    public List<Long> getIdList() {
        return idList;
    }

    public void setIdList(List<Long> idList) {
        this.idList = idList;
    }

    public Integer getEvaluationType() {
        return evaluationType;
    }

    public void setEvaluationType(Integer evaluationType) {
        this.evaluationType = evaluationType;
    }
}
