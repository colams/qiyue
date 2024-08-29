package com.foxconn.sw.business.system;

import com.foxconn.sw.data.dto.PageParams;
import com.foxconn.sw.data.dto.entity.system.ModuleVo;
import com.foxconn.sw.data.dto.entity.system.PropertiesParams;
import com.foxconn.sw.data.dto.entity.system.PropertyVo;
import com.foxconn.sw.data.entity.SwProperty;
import com.foxconn.sw.data.mapper.extension.system.SwPropertyExtensionMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class PropertyBusiness {

    @Autowired
    SwPropertyExtensionMapper propertyExtensionMapper;

    public List<PropertyVo> getSwPropertiesPage(PageParams<PropertiesParams> data) {
        int start = (data.getCurrentPage() - 1) * data.getPageSize();
        return propertyExtensionMapper.getPropertyListPage(start, data.getPageSize(), data.getParams());
    }

    public List<PropertyVo> getSwProperties(PropertiesParams data) {
        return propertyExtensionMapper.getPropertyList(data);
    }

    public List<ModuleVo> getModules(Integer module) {
        return propertyExtensionMapper.getModules(module);
    }

    public Integer getTotalCount(PropertiesParams params) {
        return propertyExtensionMapper.getTotalCountByParams(params);
    }

    public boolean updatePropertyByID(SwProperty property) {
        return propertyExtensionMapper.updateByPrimaryKeySelective(property) > 0;
    }

    public boolean createProperty(SwProperty property) {
        return propertyExtensionMapper.insertSelective(property) > 0;
    }
}
