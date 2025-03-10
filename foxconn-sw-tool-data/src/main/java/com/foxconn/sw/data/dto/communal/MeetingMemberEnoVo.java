package com.foxconn.sw.data.dto.communal;

import com.foxconn.sw.data.dto.entity.group.GroupVo;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import java.util.List;

public class MeetingMemberEnoVo {

    @NotNull
    private String chairman;
    @NotNull
    @NotEmpty
    private List<String> maintainers;
    @NotNull
    @NotEmpty
    private List<String> members;

    private List<String> watchers;

    private List<GroupVo> groupCc;

    public String getChairman() {
        return chairman;
    }

    public void setChairman(String chairman) {
        this.chairman = chairman;
    }

    public List<String> getMaintainers() {
        return maintainers;
    }

    public void setMaintainers(List<String> maintainers) {
        this.maintainers = maintainers;
    }

    public List<String> getMembers() {
        return members;
    }

    public void setMembers(List<String> members) {
        this.members = members;
    }

    public List<String> getWatchers() {
        return watchers;
    }

    public void setWatchers(List<String> watchers) {
        this.watchers = watchers;
    }

    public List<GroupVo> getGroupCc() {
        return groupCc;
    }

    public void setGroupCc(List<GroupVo> groupCc) {
        this.groupCc = groupCc;
    }
}
