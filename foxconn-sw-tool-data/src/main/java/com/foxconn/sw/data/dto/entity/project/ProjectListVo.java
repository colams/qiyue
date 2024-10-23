package com.foxconn.sw.data.dto.entity.project;

import java.util.List;

public class ProjectListVo {


    private List<List<HeaderVo>> header;


    private List<ProjectItemVo> projectItems;

    public List<List<HeaderVo>> getHeader() {
        return header;
    }

    public void setHeader(List<List<HeaderVo>> header) {
        this.header = header;
    }

    public List<ProjectItemVo> getProjectItems() {
        return projectItems;
    }

    public void setProjectItems(List<ProjectItemVo> projectItems) {
        this.projectItems = projectItems;
    }
}
