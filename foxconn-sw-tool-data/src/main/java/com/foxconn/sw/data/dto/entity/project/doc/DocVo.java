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

    @Schema(description = "1-目錄，2-文件")
    private Integer type;

    @Schema(description = "当type=2,即文件时，表示目錄树節點ID")
    private Integer treeNodeId;

    @Schema(description = "文件位置（全路徑）")
    private String location;
}
