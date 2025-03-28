package com.foxconn.sw.data.dto.entity.project.doc;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class PhaseUsedCountVo {

    @Schema(description = "階段ID")
    private Integer phaseId;

    @Schema(description = "階段名稱")
    private String phaseName;

    @Schema(description = "所佔百分比，保留小數點後1位")
    private String percentage;

}
