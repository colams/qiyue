package com.foxconn.sw.data.dto.entity.project;

import lombok.Data;

@Data
public class SysProjectListVo {
    private Integer id;

    private Integer number;

    private Integer year;

    private String projectCode;

    private String customerName;

    private String status;

    private String driId;

    private String driName;

    private String opmId;

    private String opmName;
}
