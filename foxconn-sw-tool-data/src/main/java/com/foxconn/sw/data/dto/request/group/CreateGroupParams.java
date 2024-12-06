package com.foxconn.sw.data.dto.request.group;

import java.util.List;

public class CreateGroupParams {

    private String name;
    private Integer isPrivate;
    private String description;
    private List<MemberBrief> members;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getIsPrivate() {
        return isPrivate;
    }

    public void setIsPrivate(Integer isPrivate) {
        this.isPrivate = isPrivate;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<MemberBrief> getMembers() {
        return members;
    }

    public void setMembers(List<MemberBrief> members) {
        this.members = members;
    }
}
