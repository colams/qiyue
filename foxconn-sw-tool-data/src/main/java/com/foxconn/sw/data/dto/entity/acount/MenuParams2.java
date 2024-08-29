package com.foxconn.sw.data.dto.entity.acount;

import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.PositiveOrZero;

public class MenuParams2 {

    @PositiveOrZero(message = "模块号不能为负数")
    private Integer moduleNo;

    @PositiveOrZero(message = "父菜单ID不能为负数")
    private Integer parentId;

    @Pattern(regexp = "0|1", message = "有效性只能赋值：0,1")
    private String isModuleIndex;

    @Pattern(regexp = "^\\S*$", message = "菜单名不能全部为空格")
    private String menuName;

    @Pattern(regexp = "0|1", message = "有效性只能赋值：0,1")
    private String status;

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

    public String getIsModuleIndex() {
        return isModuleIndex;
    }

    public void setIsModuleIndex(String isModuleIndex) {
        this.isModuleIndex = isModuleIndex;
    }

    public String getMenuName() {
        return menuName;
    }

    public void setMenuName(String menuName) {
        this.menuName = menuName;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
