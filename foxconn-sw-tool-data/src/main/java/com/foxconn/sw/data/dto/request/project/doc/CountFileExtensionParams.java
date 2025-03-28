package com.foxconn.sw.data.dto.request.project.doc;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.experimental.Accessors;

@Schema(description = "分類統計不同擴展名文件數量")
@Data
@Accessors(chain = true)
public class CountFileExtensionParams {

    @Schema(description = "專案ID")
    @NotNull(message = "專案ID不能為空")
    private Integer projectId;

    @Schema(description = "階段ID")
    @NotNull(message = "階段ID不能為空")
    private Integer phaseId;

    @Schema(description = "分類（分類樹節點）ID")
    @NotNull(message = "分類（分類樹節點）ID不能為空")
    private Integer treeNodeId;
}
