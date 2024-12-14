package com.foxconn.sw.business.tools;

import com.foxconn.sw.business.converter.SwToolsConverter;
import com.foxconn.sw.business.converter.SwToolsHistoryConverter;
import com.foxconn.sw.data.dto.PageParams;
import com.foxconn.sw.data.dto.entity.universal.IntegerParams;
import com.foxconn.sw.data.dto.entity.tool.SwToolDTO;
import com.foxconn.sw.data.entity.SwTools;
import com.foxconn.sw.data.entity.SwToolsHistory;
import com.foxconn.sw.data.entity.extension.SwToolsExtension;
import com.foxconn.sw.data.mapper.extension.tool.SwToolsHistoryExtensionMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ToolsHistoryBusiness {


    @Autowired
    SwToolsHistoryExtensionMapper swToolsHistoryExtensionMapper;


    public int saveToolHistory(SwTools swTools) {
        SwToolsHistory swToolsHistory = SwToolsHistoryConverter.toEntity(swTools);
        int result = swToolsHistoryExtensionMapper.insertSelective(swToolsHistory);
        return result;
    }

    public List<SwToolDTO> searchByToolId(PageParams<IntegerParams> pageParams) {

        int start = (pageParams.getCurrentPage() - 1) * pageParams.getPageSize();
        List<SwToolsExtension> tools = swToolsHistoryExtensionMapper.searchByToolId(start,
                pageParams.getPageSize(), pageParams.getParams().getParams());
        List<SwToolDTO> toolDTOs = SwToolsConverter.toDto(tools);
        return toolDTOs;
    }

    public Long countByToolID(IntegerParams integerParams) {
        return swToolsHistoryExtensionMapper.countByToolID(integerParams.getParams());
    }
}
