package com.foxconn.sw.data.dto.request.project.panel;

import lombok.Data;

@Data
public class TeamTaskParams {

    private Integer projectId;

    private String type;

    private Integer emergencyDegree;

    private Integer status;

    private String expirationDate;

    private String title;

    private String content;

    private String operator;

    private String duty;

    private String cc;

    private String progress;

}
