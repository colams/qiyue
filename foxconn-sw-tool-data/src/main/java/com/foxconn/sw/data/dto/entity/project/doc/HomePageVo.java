package com.foxconn.sw.data.dto.entity.project.doc;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.experimental.Accessors;

import java.util.List;

@Data
@Accessors(chain = true)
public class HomePageVo {

    @Schema(description = "階段列表")
    List<DocPhraseVo> docPhrases;

    @Schema(description = "未归档文件数量")
    private Integer noArchiveNo;
}

