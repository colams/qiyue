package com.foxconn.sw.service.processor.universal;

import com.foxconn.sw.business.tools.ToolCategoryBusiness;
import com.foxconn.sw.data.constants.enums.BasicPropertyEnums;
import com.foxconn.sw.data.dto.Header;
import com.foxconn.sw.data.dto.entity.CategoryDTO;
import com.foxconn.sw.data.dto.entity.system.BasicPropertyVo;
import com.foxconn.sw.data.dto.entity.system.PropertiesParams;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class PropertyProcessor {

    @Autowired
    ToolCategoryBusiness toolCategoryBusiness;


    public List<CategoryDTO> list(PropertiesParams data, Header head) {
        List<CategoryDTO> categories = toolCategoryBusiness.listByType(data.getCategory());
        return categories;
    }

    public List<BasicPropertyVo> properties(Header head) {
        List<BasicPropertyVo> basicPropertyVos = new ArrayList<>();
        for (BasicPropertyEnums value : BasicPropertyEnums.values()) {
            var vo = new BasicPropertyVo();
            vo.setCode(value.getCode());
            vo.setEnCode(value.getEnCode());
            vo.setPropertyName(value.getName());
            basicPropertyVos.add(vo);
        }
        return basicPropertyVos;
    }
}
