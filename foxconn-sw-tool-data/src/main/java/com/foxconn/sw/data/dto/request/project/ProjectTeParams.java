package com.foxconn.sw.data.dto.request.project;

import lombok.Data;

@Data
public class ProjectTeParams {
    private Integer id;

    private String inter;

    private String speed;

    private String lightSource;

    private String testRelayLens;

    private String testDistance1;

    private String testDistance2;

    private String testDistance3;
}
