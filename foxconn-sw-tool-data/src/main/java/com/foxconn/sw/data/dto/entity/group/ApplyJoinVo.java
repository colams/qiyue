package com.foxconn.sw.data.dto.entity.group;

public class ApplyJoinVo {


    /**
     *
     */
    private boolean canJoin;
    private Integer status;
    private String description;

    public boolean isCanJoin() {
        return canJoin;
    }

    public void setCanJoin(boolean canJoin) {
        this.canJoin = canJoin;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
