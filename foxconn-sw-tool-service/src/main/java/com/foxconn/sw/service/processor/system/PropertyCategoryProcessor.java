package com.foxconn.sw.service.processor.system;

import com.foxconn.sw.data.constants.enums.BasicPropertyEnums;
import com.foxconn.sw.data.dto.entity.system.BasicPropertyVo;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PropertyCategoryProcessor {


    public List<BasicPropertyVo> propertyCategory() {
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
