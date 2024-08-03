package com.foxconn.sw.service.processor.system;

import com.foxconn.sw.business.mapper.PropertyMapper;
import com.foxconn.sw.business.system.PropertyBusiness;
import com.foxconn.sw.data.dto.entity.system.PropertyVo;
import com.foxconn.sw.data.entity.SwProperty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class SavePropertyProcessor {

    @Autowired
    PropertyBusiness propertyBusiness;

    public boolean saveProperty(PropertyVo data) {
        boolean result;
        SwProperty property = PropertyMapper.INSTANCE.toSwProperty(data);
        if (data.getId() > 0) {
            result = propertyBusiness.updatePropertyByID(property);
        } else {
            result = propertyBusiness.createProperty(property);
        }
        return result;
    }
}
