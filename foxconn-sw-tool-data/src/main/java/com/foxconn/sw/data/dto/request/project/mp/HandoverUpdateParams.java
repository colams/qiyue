package com.foxconn.sw.data.dto.request.project.mp;

import lombok.Data;

@Data
public class HandoverUpdateParams {
    private Integer id;

    private Integer projectId;

    private Integer settingType;

    private String item;

    private String type;

    private String chargePersonId;

    private String driId;

    private String uri;

    private Integer status;
}
