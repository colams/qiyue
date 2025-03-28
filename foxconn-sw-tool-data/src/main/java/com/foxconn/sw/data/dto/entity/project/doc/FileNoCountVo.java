package com.foxconn.sw.data.dto.entity.project.doc;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.experimental.Accessors;

@Schema(description = "分类（文件夹）中文件数量")
@Data
@Accessors(chain = true)
public class FileNoCountVo {

    @Schema(description = "子分类(文件夹)名称")
    private String name;

    @Schema(description = "数量")
    private Integer no;
}
