package com.foxconn.sw.data.dto.entity.panel;

import lombok.Data;

@Data
public class TeamTaskCountVo {
    private Integer total;
    private Integer runningCount;
    private Integer delayCount;
    private Integer verifieldCount;
}
