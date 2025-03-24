package com.foxconn.sw.data.dto.entity.project.mp;

import lombok.Data;

@Data
public class HandoverListVo {
    private Integer id;

    private Integer number;

    private String item;

    private String type;

    private String chargePersonId;

    private String driId;

    private String url;

    private String fileName;
}
