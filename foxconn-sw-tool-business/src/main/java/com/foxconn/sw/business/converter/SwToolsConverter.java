package com.foxconn.sw.business.converter;

import com.foxconn.sw.common.utils.DomainRetrieval;
import com.foxconn.sw.data.dto.entity.tool.SwToolDTO;
import com.foxconn.sw.data.entity.SwProperty;
import com.foxconn.sw.data.entity.SwTools;
import com.foxconn.sw.data.entity.extension.SwToolsExtension;
import org.apache.commons.lang3.StringUtils;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class SwToolsConverter {

    public static List<SwToolDTO> toDto(List<SwToolsExtension> swTools) {
        if (CollectionUtils.isEmpty(swTools)) {
            return null;
        }
        List<SwToolDTO> list = new ArrayList<>();
        swTools.forEach(e -> {
            SwToolDTO swToolDTO = toDto(e);
            if (Objects.nonNull(swToolDTO)) {
                list.add(swToolDTO);
            }
        });
        return list;
    }

    public static SwToolDTO toDto(SwToolsExtension swTools) {
        if (Objects.isNull(swTools)) {
            return null;
        }
        SwToolDTO swToolDTO = new SwToolDTO();
        swToolDTO.setToolId(swTools.getToolId());
        swToolDTO.setHistoryId(swTools.getHistoryId());
        swToolDTO.setToolName(swTools.getToolName());
        swToolDTO.setToolIcon(DomainRetrieval.getDomain() + "/upload/icon/" + swTools.getToolIcon());
        swToolDTO.setPropertyId(swTools.getPropertyId());
        swToolDTO.setCategoryName(swTools.getCategoryName());
        swToolDTO.setVersionNo(swTools.getVersionNo());
        swToolDTO.setFilePath(swTools.getFilePath());
        swToolDTO.setToolSize(swTools.getToolSize());
        swToolDTO.setIntroduction(swTools.getIntroduction());
        swToolDTO.setUpdateContent(swTools.getUpdateContent());
        swToolDTO.setUseGuide(DomainRetrieval.getDomain() + "/upload/guide/" + swTools.getUseGuide());
        swToolDTO.setOperator(swTools.getOperator());
        swToolDTO.setDatetimeLastchange(swTools.getDatetimeLastchange());
        return swToolDTO;
    }

    public static SwTools toEntity(SwToolDTO swToolDTO) {
        SwTools swTools = new SwTools();
        swTools.setId(swToolDTO.getToolId());
        swTools.setToolName(swToolDTO.getToolName());
        swTools.setToolIcon(swToolDTO.getToolIcon());
        swTools.setPropertyId(swToolDTO.getPropertyId());
        swTools.setVersionNo(swToolDTO.getVersionNo());
        swTools.setFilePath(swToolDTO.getFilePath());
        swTools.setResourceId(swToolDTO.getResourceId());
        swTools.setToolSize(swToolDTO.getToolSize());
        swTools.setIntroduction(swToolDTO.getIntroduction());
        swTools.setUpdateContent(swToolDTO.getUpdateContent());
        swTools.setUseGuide(swToolDTO.getUseGuide());
        swTools.setOperator(swToolDTO.getOperator());
        if (StringUtils.isBlank(swTools.getOperator())) {
            swTools.setOperator("system");
        }
        return swTools;
    }

    public static SwToolDTO toDto(SwTools swTools, SwProperty property) {
        if (Objects.isNull(swTools)) {
            return null;
        }
        SwToolDTO swToolDTO = new SwToolDTO();
        swToolDTO.setToolId(swTools.getId());
        swToolDTO.setToolName(swTools.getToolName());
        swToolDTO.setToolIcon(DomainRetrieval.getDomain() + "/upload/icon/" + swTools.getToolIcon());
        swToolDTO.setPropertyId(swTools.getId());
        swToolDTO.setCategoryName(property.getPropertyName());
        swToolDTO.setVersionNo(swTools.getVersionNo());
        swToolDTO.setFilePath(swTools.getFilePath());
        swToolDTO.setToolSize(swTools.getToolSize());
        swToolDTO.setIntroduction(swTools.getIntroduction());
        swToolDTO.setUpdateContent(swTools.getUpdateContent());
        swToolDTO.setUseGuide(DomainRetrieval.getDomain() + "/upload/guide/" + swTools.getUseGuide());
        swToolDTO.setOperator(swTools.getOperator());
        swToolDTO.setDatetimeLastchange(swTools.getDatetimeLastchange());
        return swToolDTO;
    }
}
