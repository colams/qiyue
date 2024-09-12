package com.foxconn.sw.data.dto.entity.oa;


import com.foxconn.sw.data.entity.SwTask;

import java.util.List;

public class BriefTaskVo extends SwTask {

    private List<String> watchers;

    public List<String> getWatchers() {
        return watchers;
    }

    public void setWatchers(List<String> watchers) {
        this.watchers = watchers;
    }
}
