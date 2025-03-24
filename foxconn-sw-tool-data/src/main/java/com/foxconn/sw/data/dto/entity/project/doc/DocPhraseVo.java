package com.foxconn.sw.data.dto.entity.project.doc;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class DocPhraseVo {
    @Schema(description = "階段ID")
    private Integer phraseId;

    @Schema(description = "階段名稱")
    private String phraseName;

    @Schema(description = "阶段排序序号")
    private Integer priority;
}
