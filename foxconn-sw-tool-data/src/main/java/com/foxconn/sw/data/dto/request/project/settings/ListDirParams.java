package com.foxconn.sw.data.dto.request.project.settings;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class ListDirParams {

    @Schema(description = "專案ID")
    @NotNull(message = "專案ID不能為空")
    private Integer projectId;

    @Schema(description = "父節點Id，初始為0")
    private Integer parentId;
}
