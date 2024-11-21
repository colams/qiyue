package com.foxconn.sw.data.dto.entity.forums;

import com.foxconn.sw.data.dto.entity.acount.EmployeeVo;

public class PostsBriefVo {

    private Integer id;
    private EmployeeVo author;
    private String createTime;
    private String title;
    private String content;

    private Integer memberCount;
    private Integer commentCount;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public EmployeeVo getAuthor() {
        return author;
    }

    public void setAuthor(EmployeeVo author) {
        this.author = author;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Integer getMemberCount() {
        return memberCount;
    }

    public void setMemberCount(Integer memberCount) {
        this.memberCount = memberCount;
    }

    public Integer getCommentCount() {
        return commentCount;
    }

    public void setCommentCount(Integer commentCount) {
        this.commentCount = commentCount;
    }
}
