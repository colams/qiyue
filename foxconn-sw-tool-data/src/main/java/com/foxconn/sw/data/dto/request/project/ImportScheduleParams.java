package com.foxconn.sw.data.dto.request.project;

import lombok.Data;

@Data
public class ImportScheduleParams {
    private Integer projectId;

    public String npiBeginDate;

    public String npiEndDate;

    public String proBeginDate;

    public String proEndDate;
}
