package com.foxconn.sw.business.converter;

import com.foxconn.sw.common.utils.JsonUtils;
import com.foxconn.sw.data.dto.entity.tool.SwToolDTO;
import com.foxconn.sw.data.entity.SwTools;
import com.foxconn.sw.service.BaseTest;
import org.junit.jupiter.api.Test;

public class SwToolsConverterTest extends BaseTest {


    @Test
    public void toDto() {
        SwTools swTools = new SwTools();
        // SwToolDTO swToolDTO = SwToolsConverter.toDto(swTools);
        SwToolDTO swToolDTO = null;
        System.out.println(JsonUtils.serialize(swToolDTO));
    }
}