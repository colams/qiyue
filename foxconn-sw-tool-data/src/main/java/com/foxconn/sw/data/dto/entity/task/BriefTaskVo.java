package com.foxconn.sw.data.dto.entity.task;


import com.foxconn.sw.data.dto.entity.ResourceVo;
import com.foxconn.sw.data.dto.entity.oa.CapexParamsVo;
import com.foxconn.sw.data.entity.SwTask;

import java.util.List;

public class BriefTaskVo extends SwTask {

    private List<String> watchers;

    private List<String> managers;

    private Boolean isCollaboration;

    private List<Integer> resourceIds;

    private List<ResourceVo> resourceVos;

    private List<CapexParamsVo> capexParamsVos;

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

    public List<Integer> getResourceIds() {
        return resourceIds;
    }

    public void setResourceIds(List<Integer> resourceIds) {
        this.resourceIds = resourceIds;
    }

    public List<ResourceVo> getResourceVos() {
        return resourceVos;
    }

    public void setResourceVos(List<ResourceVo> resourceVos) {
        this.resourceVos = resourceVos;
    }

    public List<CapexParamsVo> getCapexParamsVos() {
        return capexParamsVos;
    }

    public void setCapexParamsVos(List<CapexParamsVo> capexParamsVo) {
        this.capexParamsVos = capexParamsVo;
    }
}
