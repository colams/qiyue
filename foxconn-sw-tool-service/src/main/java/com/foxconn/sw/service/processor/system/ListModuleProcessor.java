package com.foxconn.sw.service.processor.system;

import com.foxconn.sw.business.system.PropertyBusiness;
import com.foxconn.sw.data.constants.enums.BasicPropertyEnums;
import com.foxconn.sw.data.dto.entity.system.ModuleVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ListModuleProcessor {

    @Autowired
    PropertyBusiness propertyBusiness;

    public List<ModuleVo> list() {
        List<ModuleVo> moduleVos = propertyBusiness.getModules(BasicPropertyEnums.MODULE.getCode());
        return moduleVos;
    }

}
