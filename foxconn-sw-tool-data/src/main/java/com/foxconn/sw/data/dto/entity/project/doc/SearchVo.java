package com.foxconn.sw.data.dto.entity.project.doc;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class SearchVo {
    private Integer fileId;
    private String fileName;
    private String location;
}
