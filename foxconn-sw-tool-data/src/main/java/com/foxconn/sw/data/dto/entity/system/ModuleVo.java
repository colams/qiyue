package com.foxconn.sw.data.dto.entity.system;

public class ModuleVo {


    /**
     * 模块号
     */
    private Integer moduleNo;

    /**
     * 模块名字
     */
    private String moduleName;

    /**
     * 模块首页url
     */
    private String moduleUrl;

    private String moduleIcon;

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

    public String getModuleUrl() {
        return moduleUrl;
    }

    public void setModuleUrl(String moduleUrl) {
        this.moduleUrl = moduleUrl;
    }

    public String getModuleIcon() {
        return moduleIcon;
    }

    public void setModuleIcon(String moduleIcon) {
        this.moduleIcon = moduleIcon;
    }
}
