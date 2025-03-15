package com.foxconn.sw.data.dto.entity.project;

import lombok.Data;

@Data
public class SysMoreProjectListVo {
    private Integer id;

    private Integer years;

    private String projectCode;

    private String customerName;

    private String fullName;

    private String manufacturingModel;

    private String status;

    private String rfqTime;

    private String customer;

    private String customerPartNo;

    private String application;

    private String pixel;

    private String resolution;

    private String sensorActiveArea;

    private String fNo;
}
