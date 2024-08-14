package com.foxconn.sw.data.dto.entity.acount;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;

public class MenuParams {

    @PositiveOrZero(message = "菜单ID不能为负数")
    private Integer menuId;

    @NotNull
    @PositiveOrZero(message = "模块号不能为负数")
    private Integer moduleNo;

    @PositiveOrZero(message = "父菜单ID不能为负数")
    private Integer parentId;

    @PositiveOrZero(message = "父菜单ID不能为负数")
    private Integer isModuleIndex;

    private String menuName;

    public Integer getMenuId() {
        return menuId;
    }

    public void setMenuId(Integer menuId) {
        this.menuId = menuId;
    }

    public Integer getModuleNo() {
        return moduleNo;
    }

    public void setModuleNo(Integer moduleNo) {
        this.moduleNo = moduleNo;
    }

    public Integer getParentId() {
        return parentId;
    }

    public void setParentId(Integer parentId) {
        this.parentId = parentId;
    }

    public Integer getIsModuleIndex() {
        return isModuleIndex;
    }

    public void setIsModuleIndex(Integer isModuleIndex) {
        this.isModuleIndex = isModuleIndex;
    }

    public String getMenuName() {
        return menuName;
    }

    public void setMenuName(String menuName) {
        this.menuName = menuName;
    }
}
