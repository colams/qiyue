package com.foxconn.sw.data.entity;

import java.time.LocalDateTime;

public class SwMenu {
    private Integer id;

    private String menuName;

    private String menuUrl;

    private String route;

    private Integer moduleNo;

    private Integer isModuleIndex;

    private Integer isMenu;

    private Integer parentId;

    private Integer orderBy;

    private Integer status;

    private LocalDateTime createTime;

    private LocalDateTime datetimeLastchange;

    public Integer getId() {
        return id;
    }

    public SwMenu withId(Integer id) {
        this.setId(id);
        return this;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getMenuName() {
        return menuName;
    }

    public SwMenu withMenuName(String menuName) {
        this.setMenuName(menuName);
        return this;
    }

    public void setMenuName(String menuName) {
        this.menuName = menuName == null ? null : menuName.trim();
    }

    public String getMenuUrl() {
        return menuUrl;
    }

    public SwMenu withMenuUrl(String menuUrl) {
        this.setMenuUrl(menuUrl);
        return this;
    }

    public void setMenuUrl(String menuUrl) {
        this.menuUrl = menuUrl == null ? null : menuUrl.trim();
    }

    public String getRoute() {
        return route;
    }

    public SwMenu withRoute(String route) {
        this.setRoute(route);
        return this;
    }

    public void setRoute(String route) {
        this.route = route == null ? null : route.trim();
    }

    public Integer getModuleNo() {
        return moduleNo;
    }

    public SwMenu withModuleNo(Integer moduleNo) {
        this.setModuleNo(moduleNo);
        return this;
    }

    public void setModuleNo(Integer moduleNo) {
        this.moduleNo = moduleNo;
    }

    public Integer getIsModuleIndex() {
        return isModuleIndex;
    }

    public SwMenu withIsModuleIndex(Integer isModuleIndex) {
        this.setIsModuleIndex(isModuleIndex);
        return this;
    }

    public void setIsModuleIndex(Integer isModuleIndex) {
        this.isModuleIndex = isModuleIndex;
    }

    public Integer getIsMenu() {
        return isMenu;
    }

    public SwMenu withIsMenu(Integer isMenu) {
        this.setIsMenu(isMenu);
        return this;
    }

    public void setIsMenu(Integer isMenu) {
        this.isMenu = isMenu;
    }

    public Integer getParentId() {
        return parentId;
    }

    public SwMenu withParentId(Integer parentId) {
        this.setParentId(parentId);
        return this;
    }

    public void setParentId(Integer parentId) {
        this.parentId = parentId;
    }

    public Integer getOrderBy() {
        return orderBy;
    }

    public SwMenu withOrderBy(Integer orderBy) {
        this.setOrderBy(orderBy);
        return this;
    }

    public void setOrderBy(Integer orderBy) {
        this.orderBy = orderBy;
    }

    public Integer getStatus() {
        return status;
    }

    public SwMenu withStatus(Integer status) {
        this.setStatus(status);
        return this;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public LocalDateTime getCreateTime() {
        return createTime;
    }

    public SwMenu withCreateTime(LocalDateTime createTime) {
        this.setCreateTime(createTime);
        return this;
    }

    public void setCreateTime(LocalDateTime createTime) {
        this.createTime = createTime;
    }

    public LocalDateTime getDatetimeLastchange() {
        return datetimeLastchange;
    }

    public SwMenu withDatetimeLastchange(LocalDateTime datetimeLastchange) {
        this.setDatetimeLastchange(datetimeLastchange);
        return this;
    }

    public void setDatetimeLastchange(LocalDateTime datetimeLastchange) {
        this.datetimeLastchange = datetimeLastchange;
    }
}