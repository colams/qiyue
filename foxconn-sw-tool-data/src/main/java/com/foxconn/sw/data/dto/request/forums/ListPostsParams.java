package com.foxconn.sw.data.dto.request.forums;

public class ListPostsParams {

    /**
     * 0-默认所有，1-我的帖子，2-我的收藏
     */
    private Integer postsType;

    private String words;

    public Integer getPostsType() {
        return postsType;
    }

    public void setPostsType(Integer postsType) {
        this.postsType = postsType;
    }

    public String getWords() {
        return words;
    }

    public void setWords(String words) {
        this.words = words;
    }
}
