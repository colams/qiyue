package com.foxconn.sw.data.dto.communal;

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
}
