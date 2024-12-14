package com.foxconn.sw.data.dto.request.forums;

import com.foxconn.sw.data.dto.request.enums.PostsCategoryEnums;

public class ListPostsParams {

    /**
     * 0-默认所有，1-我的帖子，2-我的收藏
     */
    private PostsCategoryEnums postsType;

    private String words;

    public PostsCategoryEnums getPostsType() {
        return postsType;
    }

    public void setPostsType(PostsCategoryEnums postsType) {
        this.postsType = postsType;
    }

    public String getWords() {
        return words;
    }

    public void setWords(String words) {
        this.words = words;
    }

}
