package com.foxconn.sw.data.dto.entity.project.doc;

import com.foxconn.sw.data.dto.entity.document.DocumentDetailVo;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

@Schema(description = "搜索结果")
@Data
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = true)
public class DocVo extends DocumentDetailVo {

    @Schema(description = "1-分类，2-文件")
    private Integer type;

    @Schema(description = "动态子分類：当type=2,即文件时，表示分类树節點ID")
    private Integer treeNodeId;

    @Schema(description = "子分類名稱")
    private String treeNodeName;

    @Schema(description = "文件位置（全路徑）")
    private String location;
}
