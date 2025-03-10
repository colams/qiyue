package com.foxconn.sw.data.dto.request.system;

public class CreateMenuParams {

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
     * 菜单模块号
     */
    private Integer moduleNo;

    /**
     * 菜单模块名
     */
    private String moduleName;

    /**
     * 是否是模块首页：1-是
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
}
