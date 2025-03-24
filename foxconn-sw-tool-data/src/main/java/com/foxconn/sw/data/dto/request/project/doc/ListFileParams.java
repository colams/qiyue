package com.foxconn.sw.data.dto.request.project.doc;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class ListFileParams {

    @Schema(description = "專案ID")
    @NotNull(message = "專案ID不能為空")
    private Integer projectId;

    @Schema(description = "階段ID")
    @NotNull(message = "階段ID不能為空")
    private Integer phaseId;

    @Schema(description = "目錄樹節點ID")
    @NotNull(message = "目錄樹節點ID不能為空")
    private Integer treeNodeId;
}
