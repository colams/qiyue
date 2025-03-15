package com.foxconn.sw.data.dto.request.project.settings;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

/**
 * 命令：添加專案子目錄
 */
@Data
public class AddSubDirParams {

    @Schema(description = "專案ID")
    @NotNull(message = "專案ID不能為空")
    private Integer projectId;

    @Schema(description = "父文件夾ID")
    @NotNull(message = "父文件夾ID不能為空")
    private Integer parentId;

    @Schema(description = "文件夾名稱")
    @NotBlank(message = "文件夾名稱不能為空")
    private String fileName;

    @Schema(description = "說明")
    private String description;

    @Schema(description = "0-不可改名，1-可改名")
    @NotNull(message = "是否可重命名標記不能為空")
    private Integer canRename;

    @Schema(description = "0-不可刪除，1-可刪除")
    @NotNull(message = "是否可刪除標記不能為空")
    private Integer canDel;

    @Schema(description = "排列序號")
    @NotNull(message = "排列序號不能為空")
    private Integer priority;
}
