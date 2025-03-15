package com.foxconn.sw.data.dto.request.project.doc;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class SearchParams {

    @Schema(description = "專案ID")
    @NotNull(message = "專案ID不能為空")
    private Integer projectId;

    @Schema(description = "文件名稱")
    @NotBlank(message = "文件名稱不能為空")
    private String fileName;
}
