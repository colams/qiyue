package com.foxconn.sw.data.dto.entity.state;

import lombok.Data;

@Data
public class StateInfoVo {
    private Integer id;

    private String nodeName;

    private Integer priority;

    private Integer canDel;
}
