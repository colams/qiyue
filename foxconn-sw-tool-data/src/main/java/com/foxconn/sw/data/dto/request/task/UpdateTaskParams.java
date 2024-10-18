package com.foxconn.sw.data.dto.request.task;

import com.foxconn.sw.data.dto.entity.oa.BriefTaskVo;
import io.swagger.v3.oas.annotations.media.Schema;

public class UpdateTaskParams {


    @Schema(description = "修改字段信息")
    private String fieldInfo;

    private BriefTaskVo briefTaskVo;

    public String getFieldInfo() {
        return fieldInfo;
    }

    public void setFieldInfo(String fieldInfo) {
        this.fieldInfo = fieldInfo;
    }

    public BriefTaskVo getBriefTaskVo() {
        return briefTaskVo;
    }

    public void setBriefTaskVo(BriefTaskVo briefTaskVo) {
        this.briefTaskVo = briefTaskVo;
    }
}
