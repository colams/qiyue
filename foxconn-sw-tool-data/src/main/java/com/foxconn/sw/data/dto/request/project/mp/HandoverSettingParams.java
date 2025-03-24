package com.foxconn.sw.data.dto.request.project.mp;

import lombok.Data;

import java.util.List;

@Data
public class HandoverSettingParams {
    private Integer projectId;
    private Integer settingType;
    private List<HandoverParams> handoverParamsList;
}
