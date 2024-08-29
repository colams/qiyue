package com.foxconn.sw.business;

import com.foxconn.sw.common.utils.DomainRetrieval;
import com.foxconn.sw.data.entity.SwAppendResource;
import com.foxconn.sw.data.entity.SwAppendResourceExample;
import com.foxconn.sw.data.mapper.extension.SwAppendResourceExtensionMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Objects;

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

    public SwAppendResource getAppendResources(Integer resourceId) {
        return appendResourceExtensionMapper.selectByPrimaryKey(resourceId);
    }

    public String getResourceUrl(Integer resourceId) {
        SwAppendResource resource = getAppendResources(resourceId);
        if (Objects.isNull(resource)) {
            return "";
        }
        return String.format("%s/upload/%s/%s", DomainRetrieval.getDomain(), resource.getUploadType(), resource.getFilePath());
    }

    public Integer saveResource(String filePath, String originName, String uploadType) {
        SwAppendResource resource = new SwAppendResource();
        resource.setUploadType(uploadType);
        resource.setFilePath(filePath);
        resource.setOperator("sys");
        resource.setOriginName(originName);
        appendResourceExtensionMapper.insertSelective(resource);
        return resource.getId();
    }
}
