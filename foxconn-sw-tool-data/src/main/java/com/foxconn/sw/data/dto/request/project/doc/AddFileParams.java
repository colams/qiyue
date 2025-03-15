package com.foxconn.sw.data.dto.request.project.doc;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class AddFileParams {
    @Schema(description = "父文件夾ID")
    @NotNull(message = "父文件夾ID不能為空")
    private Integer parentId;

    @Schema(description = "文件名稱")
    @NotBlank(message = "文件名稱不能為空")
    private String fileName;

    @Schema(description = "說明")
    private String description;
}
