package com.foxconn.sw.data.dto.entity.project.doc;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.experimental.Accessors;

import java.util.List;

@Data
@Accessors(chain = true)
public class TotalUsageCountVo {

    @Schema(description = "總使用容量")
    private String totalUsage;

    @Schema(description = "各個階段的使用量佔比")
    private List<PhaseUsedCountVo> phaseUsedCountVoList;
}
