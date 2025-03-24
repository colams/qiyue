package com.foxconn.sw.data.dto.entity.project;

import lombok.Data;

@Data
public class HandoverVo {
    private Integer id;

    private Integer number;

    private String item;

    private String type;

    private String chargePersonId;

    private String driId;

    private String uri;

    private Integer status;

    private Integer settingType;
}
