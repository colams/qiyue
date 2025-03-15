package com.foxconn.sw.data.dto.entity.project;

import lombok.Data;

@Data
public class SysProjectListVo {
    private Integer id;

    private Integer number;

    private Integer year;

    private String projecCode;

    private String customerName;

    private String status;

    private String driName;
}
