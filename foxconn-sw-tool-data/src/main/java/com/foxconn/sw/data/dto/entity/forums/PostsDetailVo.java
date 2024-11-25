package com.foxconn.sw.data.dto.entity.forums;

import com.foxconn.sw.data.dto.entity.ResourceVo;
import com.foxconn.sw.data.dto.entity.acount.EmployeeVo;

import java.util.List;

public class PostsDetailVo {

    private Integer id;
    private EmployeeVo author;
    private String createTime;
    private String title;
    private String content;
    private List<ForumsParticipantVo> participants;

    private List<ResourceVo> resources;

    private Integer memberCount;
    private Integer commentCount;
    private Integer collectionStatus;

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

    public List<ForumsParticipantVo> getParticipants() {
        return participants;
    }

    public void setParticipants(List<ForumsParticipantVo> participants) {
        this.participants = participants;
    }

    public List<ResourceVo> getResources() {
        return resources;
    }

    public void setResources(List<ResourceVo> resources) {
        this.resources = resources;
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

    public Integer getCollectionStatus() {
        return collectionStatus;
    }

    public void setCollectionStatus(Integer collectionStatus) {
        this.collectionStatus = collectionStatus;
    }
}
