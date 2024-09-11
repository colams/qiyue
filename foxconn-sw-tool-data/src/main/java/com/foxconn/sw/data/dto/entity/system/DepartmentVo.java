package com.foxconn.sw.data.dto.entity.system;

import java.util.List;

public class DepartmentVo {

    private Integer id;
    private Integer parentId;
    private String name;
    private String managerNo;
    private List<DepartmentVo> children;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getParentId() {
        return parentId;
    }

    public void setParentId(Integer parentId) {
        this.parentId = parentId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getManagerNo() {
        return managerNo;
    }

    public void setManagerNo(String managerNo) {
        this.managerNo = managerNo;
    }

    public List<DepartmentVo> getChildren() {
        return children;
    }

    public void setChildren(List<DepartmentVo> children) {
        this.children = children;
    }
}
