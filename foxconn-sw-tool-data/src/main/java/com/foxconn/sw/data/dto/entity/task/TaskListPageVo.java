package com.foxconn.sw.data.dto.entity.task;

import com.foxconn.sw.data.dto.PageEntity;

public class TaskListPageVo {

    private PageEntity<TaskBriefListVo> pageVo;

    private TaskListFilterVo filterVo;

    public PageEntity<TaskBriefListVo> getPageVo() {
        return pageVo;
    }

    public void setPageVo(PageEntity<TaskBriefListVo> pageVo) {
        this.pageVo = pageVo;
    }

    public TaskListFilterVo getFilterVo() {
        return filterVo;
    }

    public void setFilterVo(TaskListFilterVo filterVo) {
        this.filterVo = filterVo;
    }
}
