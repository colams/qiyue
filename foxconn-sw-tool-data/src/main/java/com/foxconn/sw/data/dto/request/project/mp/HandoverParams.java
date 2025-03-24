package com.foxconn.sw.data.dto.request.project.mp;

import lombok.Data;

@Data
public class HandoverParams {
    private Integer id;

    private String item;

    private String type;

    private String chargePersonId;

    private String driId;

    private String uri;

    private Integer status;
}
