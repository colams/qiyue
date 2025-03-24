package com.foxconn.sw.data.dto.request.project.settings;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class SortParams {

    @Schema(description = "节点ID")
    private Integer id;

    @Schema(description = "排序序号")
    private Integer priority;
}
