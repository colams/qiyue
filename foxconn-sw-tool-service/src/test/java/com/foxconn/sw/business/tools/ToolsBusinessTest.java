package com.foxconn.sw.business.tools;

import com.foxconn.sw.common.utils.DateTimeUtils;
import com.foxconn.sw.common.utils.JsonUtils;
import com.foxconn.sw.data.dto.entity.tool.SwToolDTO;
import com.foxconn.sw.data.dto.entity.tool.ToolSearchParams;
import com.foxconn.sw.data.entity.SwTools;
import com.foxconn.sw.service.BaseTest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

class ToolsBusinessTest extends BaseTest {

    @Autowired
    ToolsBusiness toolsBusiness;

    @Test
    public void saveTool() {
        SwToolDTO swToolDTO = initDto();
        SwTools tools = toolsBusiness.saveTool(swToolDTO);
    }

    private SwToolDTO initDto() {
        SwToolDTO dto = new SwToolDTO();
        dto.setOperator("zhangzt");
        dto.setToolName("工具名稱-" + 3);
        dto.setVersionNo("v0.0.0.1");
        dto.setFilePath("20240711151432944-ConsoleApp1.exe");
        dto.setIntroduction("介绍  -- 测试项目1" + DateTimeUtils.getTimeStamp());
        dto.setUpdateContent("更新内容：bug修复");
        dto.setUseGuide("使用指南：這是使用指南");
        return dto;
    }

    @Test
    public void searchByParams() {
        ToolSearchParams searchParams = new ToolSearchParams();
        searchParams.setPropertyId(1);
        searchParams.setKeyword("10");
        List<SwToolDTO> dtos = toolsBusiness.searchByParams(null);
    }
}