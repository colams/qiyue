package com.foxconn.sw.data.dto.request.forums;

public class UpdateStatusParams {


    private Long id;
    /**
     * 删除分类：1-删除帖子，2-删除评论
     */
    private String status;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
