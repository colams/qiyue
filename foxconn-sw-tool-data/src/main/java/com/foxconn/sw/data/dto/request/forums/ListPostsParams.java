package com.foxconn.sw.data.dto.request.forums;

public class ListPostsParams {

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
