package com.foxconn.sw.service.processor.system;

import com.foxconn.sw.business.system.PropertyBusiness;
import com.foxconn.sw.data.dto.PageEntity;
import com.foxconn.sw.data.dto.PageParams;
import com.foxconn.sw.data.dto.entity.system.PropertiesParams;
import com.foxconn.sw.data.dto.entity.system.PropertyVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class GetPropertiesProcessor {
    @Autowired
    PropertyBusiness propertyBusiness;


    public PageEntity<PropertyVo> getSwProperties(PageParams<PropertiesParams> data) {
        List<PropertyVo> propertyVos = propertyBusiness.getSwProperties(data);
        Integer totalCount = propertyBusiness.getTotalCount(data.getParams());
        return new PageEntity<>(totalCount, propertyVos);
    }
}
