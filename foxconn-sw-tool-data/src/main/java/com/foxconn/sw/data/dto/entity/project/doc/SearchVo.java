package com.foxconn.sw.data.dto.entity.project.doc;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class SearchVo {

    @Schema(description = "1-目錄，2-文件")
    private Integer type;

    @Schema(description = "文件ID")
    private Integer id;

    @Schema(description = "文件名")
    private String name;

    @Schema(description = "文件位置（全路徑）")
    private String location;
}
