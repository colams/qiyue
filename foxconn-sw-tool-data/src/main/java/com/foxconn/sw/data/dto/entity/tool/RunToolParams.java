package com.foxconn.sw.data.dto.entity.tool;

import jakarta.validation.constraints.Pattern;

public class RunToolParams {

    @Pattern(regexp = "^\\S*$", message = "字符串不能全部为空格")
    private String toolPath;
    @Pattern(regexp = "^\\S*$", message = "字符串不能全部为空格")
    private String toolParams;

    public String getToolPath() {
        return toolPath;
    }

    public void setToolPath(String toolPath) {
        this.toolPath = toolPath;
    }

    public String getToolParams() {
        return toolParams;
    }

    public void setToolParams(String toolParams) {
        this.toolParams = toolParams;
    }
}
