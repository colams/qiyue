package com.foxconn.sw.data.dto.request.project.settings;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class AddSubParams {

    @Schema(description = "專案ID")
    @NotNull(message = "專案ID不能為空")
    private Integer projectId;

    @Schema(description = "父節點ID")
    @NotNull(message = "父節點ID不能為空")
    private Integer parentId;

    @Schema(description = "名稱")
    @NotNull(message = "名稱不能為空")
    private String nodeName;

    @Schema(description = "排序序号")
    private Integer priority;
}
