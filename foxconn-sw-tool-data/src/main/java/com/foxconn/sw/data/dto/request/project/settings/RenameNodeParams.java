package com.foxconn.sw.data.dto.request.project.settings;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class RenameNodeParams {
    @Schema(description = "節點ID")
    @NotNull(message = "節點ID不能為空")
    private Integer id;

    @Schema(description = "新名稱")
    @NotNull(message = "新名稱不能為空")
    private String newName;
}
