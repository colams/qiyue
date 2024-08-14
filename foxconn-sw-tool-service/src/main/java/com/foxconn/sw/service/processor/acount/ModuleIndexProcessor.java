package com.foxconn.sw.service.processor.acount;

import com.foxconn.sw.business.system.PropertyBusiness;
import com.foxconn.sw.data.constants.enums.BasicPropertyEnums;
import com.foxconn.sw.data.dto.entity.system.ModuleVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ModuleIndexProcessor {

    @Autowired
    PropertyBusiness propertyBusiness;

    public List<ModuleVo> list() {
        List<ModuleVo> moduleVos = propertyBusiness.getModules(BasicPropertyEnums.MODULE.getCode());
        return moduleVos;
    }
}
