package com.foxconn.sw.data.dto.request.project.settings;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class BatchRenameParams {
    @Schema(description = "專案ID")
    @NotNull(message = "專案ID不能為空")
    private Integer projectId;

    @Schema(description = "要刪除的目錄級別")
    @NotNull(message = "目錄級別不能為空")
    private Integer dirLevel;

    @Schema(description = "舊目錄名稱")
    @NotNull(message = "舊目錄名稱不能為空")
    private String oldName;

    @Schema(description = "新目錄名稱")
    @NotNull(message = "新目錄名稱不能為空")
    private String newName;
}
