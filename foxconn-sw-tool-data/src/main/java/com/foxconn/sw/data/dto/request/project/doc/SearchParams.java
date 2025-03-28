package com.foxconn.sw.data.dto.request.project.doc;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.List;

@Data
public class SearchParams {

    @Schema(description = "專案ID")
    @NotNull(message = "專案ID不能為空")
    private Integer projectId;

    @Schema(description = "階段ID，全局搜索不传")
    private Integer phaseId;

    @Schema(description = "分类樹節點ID，有则传")
    private Integer treeNodeId;

    @Schema(description = "文件or目錄名稱")
    @NotBlank(message = "文件or目錄名稱不能為空")
    private String name;

    // ---------------------
    @Schema(description = "前端忽略")
    private List<Integer> subNodeIdList;

    @Schema(description = "前端忽略")
    private String nodeKey;
}
