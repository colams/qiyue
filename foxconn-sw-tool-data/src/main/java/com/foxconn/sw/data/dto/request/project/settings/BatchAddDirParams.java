package com.foxconn.sw.data.dto.request.project.settings;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class BatchAddDirParams {

    @Schema(description = "專案ID")
    @NotNull(message = "專案ID不能為空")
    private Integer projectId;

    @Schema(description = "父目錄級別")
    @NotNull(message = "父目錄級別不能為空")
    private Integer parentDirLevel;

    @Schema(description = "父目錄名稱")
    @NotNull(message = "父目錄名稱不能為空")
    private String parentDirName;

    @Schema(description = "目錄名稱")
    @NotNull(message = "目錄名稱不能為空")
    private String dirName;
}
