package com.foxconn.sw.data.dto.entity.project.doc;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.experimental.Accessors;

@Schema(description = "根据文件扩展名统计数量")
@Data
@Accessors(chain = true)
public class FileExtensionNoVo {

    @Schema(description = "文件扩展名")
    private String fileExtension;

    @Schema(description = "数量")
    private Integer no;
}
