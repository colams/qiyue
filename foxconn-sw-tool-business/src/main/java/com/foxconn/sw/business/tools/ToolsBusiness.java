package com.foxconn.sw.business.tools;

import com.foxconn.sw.business.converter.SwToolsConverter;
import com.foxconn.sw.data.dto.PageParams;
import com.foxconn.sw.data.dto.entity.tool.SwToolDTO;
import com.foxconn.sw.data.dto.entity.tool.ToolSearchParams;
import com.foxconn.sw.data.entity.SwProperty;
import com.foxconn.sw.data.entity.SwToolRunResult;
import com.foxconn.sw.data.entity.SwTools;
import com.foxconn.sw.data.entity.extension.SwToolsExtension;
import com.foxconn.sw.data.mapper.extension.tool.SwToolCategoryExtensionMapper;
import com.foxconn.sw.data.mapper.extension.tool.SwToolRunResultExtensionMapper;
import com.foxconn.sw.data.mapper.extension.tool.SwToolsExtensionMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class ToolsBusiness {
    @Autowired
    SwToolsExtensionMapper swToolsExtensionMapper;
    @Autowired
    SwToolCategoryExtensionMapper swToolCategoryExtensionMapper;
    @Autowired
    ToolsHistoryBusiness toolsHistoryBusiness;
    @Autowired
    SwToolRunResultExtensionMapper runResultExtensionMapper;

    public SwToolDTO detail(Integer toolID) {
        SwTools tool = swToolsExtensionMapper.selectByPrimaryKey(toolID);
        SwProperty toolCategory = swToolCategoryExtensionMapper.selectByPrimaryKey(tool.getPropertyId());
        SwToolDTO toolDTO = SwToolsConverter.toDto(tool, toolCategory);
        return toolDTO;
    }

    public List<SwToolDTO> searchByParams(PageParams<ToolSearchParams> pageParams) {
        int start = (pageParams.getCurrentPage() - 1) * pageParams.getPageSize();
        List<SwToolsExtension> tools = swToolsExtensionMapper.searchByParams(start, pageParams.getPageSize(), pageParams.getParams());
        List<SwToolDTO> toolDTOs = SwToolsConverter.toDto(tools);
        return toolDTOs;
    }

    public int getTotalCountByParams(PageParams<ToolSearchParams> pageParams) {
        return swToolsExtensionMapper.getTotalCountByParams(pageParams.getParams());
    }

    public SwTools saveTool(SwToolDTO swToolDTO) {
        SwTools swTools = SwToolsConverter.toEntity(swToolDTO);
        int count = swToolsExtensionMapper.insertUpdate(swTools);
        if (count > 0 && Objects.nonNull(swToolDTO.getToolId()) && swToolDTO.getToolId() > 0) {
            SwTools swToolsTemp = swToolsExtensionMapper.selectByPrimaryKey(swToolDTO.getToolId());
            swTools.setId(swToolsTemp.getId());
            swTools.setToolIcon(swToolsTemp.getToolIcon());
        }
        return swTools;
    }

    public boolean saveRunResult(String toolName, String filepath, String operator, String remark, long interval) {
        SwToolRunResult log = new SwToolRunResult();
        log.setOperator(operator);
        log.setToolName(toolName);
        log.setRunResult(filepath);
        log.setRemark(remark);
        log.setIntervals(interval);
        int count = runResultExtensionMapper.insertSelective(log);
        return count > 0;
    }
}
