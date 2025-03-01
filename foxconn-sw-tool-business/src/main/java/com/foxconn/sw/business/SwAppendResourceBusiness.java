package com.foxconn.sw.business;

import com.foxconn.sw.business.account.UserBusiness;
import com.foxconn.sw.common.utils.ConvertUtils;
import com.foxconn.sw.common.utils.DateTimeUtils;
import com.foxconn.sw.common.utils.DomainRetrieval;
import com.foxconn.sw.common.utils.JsonUtils;
import com.foxconn.sw.data.dto.entity.ResourceVo;
import com.foxconn.sw.data.entity.SwAppendResource;
import com.foxconn.sw.data.entity.SwAppendResourceExample;
import com.foxconn.sw.data.mapper.extension.SwAppendResourceExtensionMapper;
import com.google.common.collect.Lists;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

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
        return getResourceUrl(resource);
    }

    public String getResourceUrl(SwAppendResource resource) {
        if (Objects.isNull(resource)) {
            return "";
        }
        return String.format("%s/upload/%s/%s", DomainRetrieval.getDomain(), resource.getUploadType(), resource.getFilePath());
    }

    public Integer saveResource(String filePath, String originName, String uploadType, String operator, long size) {
        SwAppendResource resource = new SwAppendResource();
        resource.setUploadType(uploadType);
        resource.setFilePath(filePath);
        resource.setOperator(operator);
        resource.setOriginName(originName);
        resource.setSize(size);
        appendResourceExtensionMapper.insertSelective(resource);
        return resource.getId();
    }


}
