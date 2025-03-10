package com.foxconn.sw.data.dto.entity.group;

import com.foxconn.sw.data.dto.entity.acount.EmployeeVo;
import com.foxconn.sw.data.dto.entity.oa.InfoColorVo;

public class GroupBriefVo {

    private Integer id;
    private String name;
    private EmployeeVo owner;
    private InfoColorVo groupType;
    private String createTime;
    private String description;

    /**
     * true -已收藏，false- 未收藏
     */
    private boolean collectStatus;

    private ApplyJoinVo joinVo;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public EmployeeVo getOwner() {
        return owner;
    }

    public void setOwner(EmployeeVo owner) {
        this.owner = owner;
    }

    public InfoColorVo getGroupType() {
        return groupType;
    }

    public void setGroupType(InfoColorVo groupType) {
        this.groupType = groupType;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isCollectStatus() {
        return collectStatus;
    }

    public void setCollectStatus(boolean collectStatus) {
        this.collectStatus = collectStatus;
    }

    public ApplyJoinVo getJoinVo() {
        return joinVo;
    }

    public void setJoinVo(ApplyJoinVo joinVo) {
        this.joinVo = joinVo;
    }
}
