package com.foxconn.sw.data.dto.entity.panel;

import lombok.Data;

@Data
public class TeamTaskVo {
    private Integer id;
    private String type;
    private String title;
    private String expiration_date;
    private Integer status;
    private String operator;
    private String duty;
    private String progress;
}
