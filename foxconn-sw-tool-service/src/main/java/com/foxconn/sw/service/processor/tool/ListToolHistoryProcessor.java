package com.foxconn.sw.service.processor.tool;

import com.foxconn.sw.business.tools.ToolsHistoryBusiness;
import com.foxconn.sw.data.dto.Header;
import com.foxconn.sw.data.dto.PageEntity;
import com.foxconn.sw.data.dto.PageParams;
import com.foxconn.sw.data.dto.entity.common.IntegerParams;
import com.foxconn.sw.data.dto.entity.tool.SwToolDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ListToolHistoryProcessor {

    @Autowired
    ToolsHistoryBusiness toolsHistoryBusiness;

    /**
     *
     * @param pageParams
     * @param header
     * @return
     */
    public PageEntity<SwToolDTO> listToolHistory(PageParams<IntegerParams> pageParams, Header header) {
        List<SwToolDTO> results = toolsHistoryBusiness.searchByToolId(pageParams);
        int totalCount = toolsHistoryBusiness.countByToolID(pageParams.getParams());
        return new PageEntity<>(totalCount, results);
    }

}
