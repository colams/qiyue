package com.foxconn.sw.business.converter;

import com.foxconn.sw.data.entity.SwTools;
import com.foxconn.sw.data.entity.SwToolsHistory;

public class SwToolsHistoryConverter {

    public static SwToolsHistory toEntity(SwTools swToolDTO) {
        SwToolsHistory swToolsHistory = new SwToolsHistory();
        swToolsHistory.setToolId(swToolDTO.getId());
        swToolsHistory.setToolName(swToolDTO.getToolName());
        swToolsHistory.setToolIcon(swToolDTO.getToolIcon());
        swToolsHistory.setPropertyId(swToolDTO.getPropertyId());
        swToolsHistory.setVersionNo(swToolDTO.getVersionNo());
        swToolsHistory.setFilePath(swToolDTO.getFilePath());
        swToolsHistory.setToolSize(swToolDTO.getToolSize());
        swToolsHistory.setIntroduction(swToolDTO.getIntroduction());
        swToolsHistory.setUpdateContent(swToolDTO.getUpdateContent());
        swToolsHistory.setUseGuide(swToolDTO.getUseGuide());
        swToolsHistory.setOperator(swToolDTO.getOperator());
        return swToolsHistory;
    }
}
