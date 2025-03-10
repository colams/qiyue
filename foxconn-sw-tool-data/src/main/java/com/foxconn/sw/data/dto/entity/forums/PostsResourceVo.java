package com.foxconn.sw.data.dto.entity.forums;

import com.foxconn.sw.data.dto.entity.ResourceVo;

public class PostsResourceVo extends ResourceVo {

    private Integer postsId;
    private Integer commentId;
    private boolean canDelete;

    public Integer getPostsId() {
        return postsId;
    }

    public void setPostsId(Integer postsId) {
        this.postsId = postsId;
    }

    public Integer getCommentId() {
        return commentId;
    }

    public void setCommentId(Integer commentId) {
        this.commentId = commentId;
    }

    public boolean isCanDelete() {
        return canDelete;
    }

    public void setCanDelete(boolean canDelete) {
        this.canDelete = canDelete;
    }
}
