package com.foxconn.sw.data.dto.entity.meeting;

import com.foxconn.sw.data.dto.enums.MeetingItemTypeEnums;

public class MeetingMinuteItemVo {

    private Long id;
    private Integer index;
    private String itemTitle;
    private String director;
    private String dueDate;
    private String status;
    private String remark;
    private String project;
    private MeetingItemTypeEnums itemType;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getIndex() {
        return index;
    }

    public void setIndex(Integer index) {
        this.index = index;
    }

    public String getItemTitle() {
        return itemTitle;
    }

    public void setItemTitle(String itemTitle) {
        this.itemTitle = itemTitle;
    }

    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    public String getDueDate() {
        return dueDate;
    }

    public void setDueDate(String dueDate) {
        this.dueDate = dueDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getProject() {
        return project;
    }

    public void setProject(String project) {
        this.project = project;
    }

    public MeetingItemTypeEnums getItemType() {
        return itemType;
    }

    public void setItemType(MeetingItemTypeEnums itemType) {
        this.itemType = itemType;
    }
}
