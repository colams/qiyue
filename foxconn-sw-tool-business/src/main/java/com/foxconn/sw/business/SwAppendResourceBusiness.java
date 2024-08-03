package com.foxconn.sw.business;

import com.foxconn.sw.data.entity.SwAppendResource;
import com.foxconn.sw.data.entity.SwAppendResourceExample;
import com.foxconn.sw.data.mapper.extension.SwAppendResourceExtensionMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class SwAppendResourceBusiness {

    @Autowired
    SwAppendResourceExtensionMapper appendResourceExtensionMapper;

    public List<SwAppendResource> getAppendResources(List<Integer> resourceIds) {
        SwAppendResourceExample example = new SwAppendResourceExample();
        var criteria = example.createCriteria();
        criteria.andIdIn(resourceIds);
        return appendResourceExtensionMapper.selectByExample(example);
    }

    public Integer saveResource(String filePath, int sourceType) {
        SwAppendResource resource = new SwAppendResource();
        resource.setSourceType(sourceType);
        resource.setFilePath(filePath);
        resource.setOperator("sys");
        appendResourceExtensionMapper.insertSelective(resource);
        return resource.getId();
    }
}
