package com.foxconn.sw.data.dto.request.project.doc;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.Data;


@Data
public class ArchiveParams {

    @Schema(description = "OA文件ID")
    @NotNull(message = "OA文件ID不能為空")
    private Integer fileId;

    @Schema(description = "分類樹節點ID")
    @NotNull(message = "分類樹節點ID不能為空")
    private Integer treeNodeId;
}
