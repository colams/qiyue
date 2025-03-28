package com.foxconn.sw.data.dto.request.group;

import com.foxconn.sw.data.dto.enums.AccessLevelEnums;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.util.List;

public class CreateGroupParams {

    private String name;
    private AccessLevelEnums accessLevel;
    private String description;

    @NotNull
    @Size(min = 1)
    private List<MemberBrief> members;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public AccessLevelEnums getAccessLevel() {
        return accessLevel;
    }

    public void setAccessLevel(AccessLevelEnums accessLevel) {
        this.accessLevel = accessLevel;
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
