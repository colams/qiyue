package com.foxconn.sw.data.dto.entity.project;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class HandoverHistoryVo {
    private Integer id;

    private String url;

    private String fileName;

    private LocalDateTime createTime;
}
