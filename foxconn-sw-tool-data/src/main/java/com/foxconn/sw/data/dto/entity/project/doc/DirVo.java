package com.foxconn.sw.data.dto.entity.project.doc;

import lombok.Data;

@Data
public class DirVo {
    private Integer id;

    private Integer projectId;

    private Integer parentId;

    private String fileName;

    private String fileKey;

    private Integer fileLevel;

    private Integer canRename;

    private Integer canDel;

    private Integer priority;
}
