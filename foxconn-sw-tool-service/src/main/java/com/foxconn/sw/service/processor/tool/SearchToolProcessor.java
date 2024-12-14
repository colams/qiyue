package com.foxconn.sw.service.processor.tool;

import com.foxconn.sw.business.tools.ToolsBusiness;
import com.foxconn.sw.common.utils.JsonUtils;
import com.foxconn.sw.data.dto.PageEntity;
import com.foxconn.sw.data.dto.PageParams;
import com.foxconn.sw.data.dto.entity.tool.SwToolDTO;
import com.foxconn.sw.data.dto.entity.tool.ToolSearchParams;
import com.foxconn.sw.service.controller.ToolController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class SearchToolProcessor {


    private final Logger LOGGER = LoggerFactory.getLogger(ToolController.class);
    @Autowired
    ToolsBusiness toolsBusiness;

    public PageEntity<SwToolDTO> search(PageParams<ToolSearchParams> pageParams) {
        System.out.println(JsonUtils.serialize(pageParams));
        List<SwToolDTO> toolDTOs = toolsBusiness.searchByParams(pageParams);
        Long totalCount = toolsBusiness.getTotalCountByParams(pageParams);
        PageEntity<SwToolDTO> pageEntity = new PageEntity<>(totalCount, toolDTOs);
        return pageEntity;
    }

}
