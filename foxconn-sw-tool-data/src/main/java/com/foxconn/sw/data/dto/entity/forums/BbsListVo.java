package com.foxconn.sw.data.dto.entity.forums;

import com.foxconn.sw.data.dto.entity.acount.EmployeeVo;

public class BbsListVo {

    private Integer id;
    private EmployeeVo author;
    private String createTime;
    private String title;
    private String content;
    private EmployeeVo discussantVo;
    private boolean isRead;
    private int newCount;
    private String archive;

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

    public EmployeeVo getDiscussantVo() {
        return discussantVo;
    }

    public void setDiscussantVo(EmployeeVo discussantVo) {
        this.discussantVo = discussantVo;
    }

    public Integer getNewCount() {
        return newCount;
    }

    public void setNewCount(int newCount) {
        this.newCount = newCount;
    }

    public boolean isRead() {
        return isRead;
    }

    public void setRead(boolean read) {
        isRead = read;
    }

    public String getArchive() {
        return archive;
    }

    public void setArchive(String archive) {
        this.archive = archive;
    }
}
