package com.foxconn.sw.data.dto.request.project;

import lombok.Data;

@Data
public class SysIndexProjectUpdateParams {
    private Integer projectId;

    private String status;

    private String driId;

    private String opmId;
}
