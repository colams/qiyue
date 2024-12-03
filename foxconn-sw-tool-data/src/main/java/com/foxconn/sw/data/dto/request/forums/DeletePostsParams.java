package com.foxconn.sw.data.dto.request.forums;

public class DeletePostsParams {


    private Integer id;
    /**
     * 删除分类：1-删除帖子，2-删除评论
     */
    private Integer delType;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getDelType() {
        return delType;
    }

    public void setDelType(Integer delType) {
        this.delType = delType;
    }
}
