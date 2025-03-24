package com.foxconn.sw.data.dto.entity.state;

import lombok.Data;

@Data
public class StateHandoverVo {
    private Integer id;

    private String nodeName;

    private Integer priority;

    private Long taskId;

    private Integer current;
}
