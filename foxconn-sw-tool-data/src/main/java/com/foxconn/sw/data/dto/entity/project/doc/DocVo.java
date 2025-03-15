package com.foxconn.sw.data.dto.entity.project.doc;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class DocVo {
    private Integer id;

    private Integer parentId;

    private String fileName;

    private Integer fileType;

    private Integer projectId;

    private Integer canRename;

    private Integer canDel;

    private String description;

    private String filePath;

    private Integer priority;
}
