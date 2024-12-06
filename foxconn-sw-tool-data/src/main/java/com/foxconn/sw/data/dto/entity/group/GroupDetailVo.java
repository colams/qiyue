package com.foxconn.sw.data.dto.entity.group;

import com.foxconn.sw.data.dto.entity.acount.EmployeeVo;

public class GroupDetailVo {

    private Integer id;
    private String name;
    private EmployeeVo owner;
    private GroupType groupType;
    private String createTime;
    private String description;

    /**
     * true -已收藏，0- 未收藏
     */
    private boolean collectStatus;

    /**
     *
     */
    private boolean canJoin;

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

    public GroupType getGroupType() {
        return groupType;
    }

    public void setGroupType(GroupType groupType) {
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

    public boolean isCanJoin() {
        return canJoin;
    }

    public void setCanJoin(boolean canJoin) {
        this.canJoin = canJoin;
    }
}
