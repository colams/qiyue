package com.foxconn.sw.service.processor.tool;

import com.foxconn.sw.business.tools.ToolsBusiness;
import com.foxconn.sw.business.tools.ToolsHistoryBusiness;
import com.foxconn.sw.data.dto.Header;
import com.foxconn.sw.data.dto.entity.acount.UserInfo;
import com.foxconn.sw.data.dto.entity.tool.SwToolDTO;
import com.foxconn.sw.data.entity.SwTools;
import com.foxconn.sw.service.processor.user.CommonUserUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
public class SaveToolProcessor {

    @Autowired
    ToolsBusiness toolsBusiness;
    @Autowired
    ToolsHistoryBusiness toolsHistoryBusiness;
    @Autowired
    CommonUserUtils commonUserUtils;

    public boolean toolSave(SwToolDTO toolDTO, Header head) {
        UserInfo userInfo = commonUserUtils.queryUserInfo(head.getToken());
        boolean result;
        if (Objects.nonNull(toolDTO.getToolId()) && toolDTO.getToolId() > 0) {
            result = createTool(toolDTO);
        } else {
            result = updateTool(toolDTO);
        }

        SwTools swTools = toolsBusiness.saveTool(toolDTO);
        int toolID = 0;
        if (Objects.nonNull(swTools) && swTools.getId() > 0) {
            toolsHistoryBusiness.saveToolHistory(swTools);
        }
        return toolID > 0;
    }

    private boolean updateTool(SwToolDTO toolDTO) {

        return false;

    }

    private boolean createTool(SwToolDTO toolDTO) {

        return false;

    }

}
