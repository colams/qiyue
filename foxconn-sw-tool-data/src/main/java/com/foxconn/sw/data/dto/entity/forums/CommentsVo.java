package com.foxconn.sw.data.dto.entity.forums;

import com.foxconn.sw.data.dto.entity.acount.EmployeeVo;

import java.util.List;

public class CommentsVo {

    private Integer postsId;
    private Integer id;
    private Integer targetId;
    private String content;
    private EmployeeVo employee;

    private List<CommentsVo> replies;

    public Integer getPostsId() {
        return postsId;
    }

    public void setPostsId(Integer postsId) {
        this.postsId = postsId;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getTargetId() {
        return targetId;
    }

    public void setTargetId(Integer targetId) {
        this.targetId = targetId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public EmployeeVo getEmployee() {
        return employee;
    }

    public void setEmployee(EmployeeVo employee) {
        this.employee = employee;
    }

    public List<CommentsVo> getReplies() {
        return replies;
    }

    public void setReplies(List<CommentsVo> replies) {
        this.replies = replies;
    }
}
