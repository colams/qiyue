package com.foxconn.sw.data.dto.entity.task;

import com.foxconn.sw.data.dto.entity.universal.OptionsVo;

import java.util.List;

public class TaskListFilterVo {

    private List<OptionsVo> projectFilter;

    private List<OptionsVo> categoryFilter;

    private List<OptionsVo> stateFilter;

    private List<OptionsVo> proposeFilter;

    private List<OptionsVo> supervisorFilter;

    public List<OptionsVo> getProjectFilter() {
        return projectFilter;
    }

    public void setProjectFilter(List<OptionsVo> projectFilter) {
        this.projectFilter = projectFilter;
    }

    public List<OptionsVo> getCategoryFilter() {
        return categoryFilter;
    }

    public void setCategoryFilter(List<OptionsVo> categoryFilter) {
        this.categoryFilter = categoryFilter;
    }

    public List<OptionsVo> getStateFilter() {
        return stateFilter;
    }

    public void setStateFilter(List<OptionsVo> stateFilter) {
        this.stateFilter = stateFilter;
    }

    public List<OptionsVo> getProposeFilter() {
        return proposeFilter;
    }

    public void setProposeFilter(List<OptionsVo> proposeFilter) {
        this.proposeFilter = proposeFilter;
    }

    public List<OptionsVo> getSupervisorFilter() {
        return supervisorFilter;
    }

    public void setSupervisorFilter(List<OptionsVo> supervisorFilter) {
        this.supervisorFilter = supervisorFilter;
    }
}
