package com.foxconn.sw.data.dto.request.project.settings;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class DelNodeParams {

    @Schema(description = "節點ID")
    @NotNull(message = "節點ID不能為空")
    private Integer id;
}
