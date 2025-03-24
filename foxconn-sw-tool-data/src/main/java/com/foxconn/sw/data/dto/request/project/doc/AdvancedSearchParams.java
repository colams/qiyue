package com.foxconn.sw.data.dto.request.project.doc;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class AdvancedSearchParams {

    @Schema(description = "專案ID")
    @NotNull(message = "專案ID不能為空")
    private Integer projectId;

    @Schema(description = "目錄樹節點ID")
    private Integer treeNodeId;

    // ---------------------
    @Schema(description = "分類")
    private String category;

    @Schema(description = "來源")
    private String source;

    @Schema(description = "創建人")
    private String creator;

    @Schema(description = "作者")
    private String author;

    @Schema(description = "階段ID")
    private Integer phaseId;
    // ---------------------

    private String config;
    private String issueMode;

    @Schema(description = "製程")
    private String process;
    private String stage;
    private String mainPart;
    // ---------------------
    private String supplier;
}
