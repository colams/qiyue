package com.foxconn.sw.data.dto.request.project.doc;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.Data;


@Data
public class ArchiveParams {

    @Schema(description = "文件ID")
    @NotNull(message = "文件ID不能為空")
    private Integer fileId;

    @Schema(description = "目標目錄ID")
    @NotNull(message = "目標目錄ID不能為空")
    private Integer targetDirId;
}
