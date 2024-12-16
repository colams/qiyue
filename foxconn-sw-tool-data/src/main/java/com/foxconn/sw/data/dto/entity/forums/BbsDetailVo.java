package com.foxconn.sw.data.dto.entity.forums;

import java.util.List;

public class BbsDetailVo {

    private Integer id;
    private String title;
    private List<CommentsVo> commentsVos;

    private Integer collectionStatus;

    private Boolean canDel;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<CommentsVo> getCommentsVos() {
        return commentsVos;
    }

    public void setCommentsVos(List<CommentsVo> commentsVos) {
        this.commentsVos = commentsVos;
    }

    public Integer getCollectionStatus() {
        return collectionStatus;
    }

    public void setCollectionStatus(Integer collectionStatus) {
        this.collectionStatus = collectionStatus;
    }

    public Boolean getCanDel() {
        return canDel;
    }

    public void setCanDel(Boolean canDel) {
        this.canDel = canDel;
    }
}
