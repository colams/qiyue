package com.foxconn.sw.data.dto.entity.oa;


import com.foxconn.sw.data.entity.SwTask;

import java.util.List;

public class BriefTaskVo extends SwTask {

    private List<String> watchers;

    private List<String> managers;

    private Boolean isCollaboration;

    public List<String> getWatchers() {
        return watchers;
    }

    public void setWatchers(List<String> watchers) {
        this.watchers = watchers;
    }


    public Boolean getCollaboration() {
        return isCollaboration;
    }

    public void setCollaboration(Boolean collaboration) {
        isCollaboration = collaboration;
    }

    public List<String> getManagers() {
        return managers;
    }

    public void setManagers(List<String> managers) {
        this.managers = managers;
    }
}
