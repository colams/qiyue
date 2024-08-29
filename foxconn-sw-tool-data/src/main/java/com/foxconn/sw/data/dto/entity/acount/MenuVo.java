package com.foxconn.sw.data.dto.entity.acount;

import java.util.List;

public class MenuVo {

    /**
     * 主键ID
     */
    private Integer id;

    /**
     * 菜单名
     */
    private String menuName;

    /**
     * 菜单地址
     */
    private String menuUrl;

    /**
     * 菜单路由
     */
    private String route;

    /**
     * 菜单模块
     */
    private Integer moduleNo;

    private String moduleName;

    /**
     * 是否index页面：1-是
     */
    private Integer isModuleIndex;

    /**
     * 是否菜单：1-是
     */
    private Integer isMenu;

    /**
     * 父节点ID
     */
    private Integer parentId;

    /**
     * 排序信息
     */
    private Integer orderBy;

    /**
     * 有效性：1-有效
     */
    private Integer status;


    private List<MenuVo> children;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getMenuName() {
        return menuName;
    }

    public void setMenuName(String menuName) {
        this.menuName = menuName;
    }

    public String getMenuUrl() {
        return menuUrl;
    }

    public void setMenuUrl(String menuUrl) {
        this.menuUrl = menuUrl;
    }

    public String getRoute() {
        return route;
    }

    public void setRoute(String route) {
        this.route = route;
    }

    public Integer getModuleNo() {
        return moduleNo;
    }

    public void setModuleNo(Integer moduleNo) {
        this.moduleNo = moduleNo;
    }

    public String getModuleName() {
        return moduleName;
    }

    public void setModuleName(String moduleName) {
        this.moduleName = moduleName;
    }

    public Integer getIsModuleIndex() {
        return isModuleIndex;
    }

    public void setIsModuleIndex(Integer isModuleIndex) {
        this.isModuleIndex = isModuleIndex;
    }

    public Integer getIsMenu() {
        return isMenu;
    }

    public void setIsMenu(Integer isMenu) {
        this.isMenu = isMenu;
    }

    public Integer getParentId() {
        return parentId;
    }

    public void setParentId(Integer parentId) {
        this.parentId = parentId;
    }

    public Integer getOrderBy() {
        return orderBy;
    }

    public void setOrderBy(Integer orderBy) {
        this.orderBy = orderBy;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public List<MenuVo> getChildren() {
        return children;
    }

    public void setChildren(List<MenuVo> children) {
        this.children = children;
    }
}
