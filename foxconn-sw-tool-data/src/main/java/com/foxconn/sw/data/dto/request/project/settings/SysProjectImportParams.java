package com.foxconn.sw.data.dto.request.project.settings;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class SysProjectImportParams {

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

    private String fNo;
}
