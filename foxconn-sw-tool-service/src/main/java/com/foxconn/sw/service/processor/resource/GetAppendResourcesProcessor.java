package com.foxconn.sw.service.processor.resource;

import com.foxconn.sw.business.SwAppendResourceBusiness;
import com.foxconn.sw.business.account.UserBusiness;
import com.foxconn.sw.common.utils.ConvertUtils;
import com.foxconn.sw.common.utils.DateTimeUtils;
import com.foxconn.sw.common.utils.DomainRetrieval;
import com.foxconn.sw.common.utils.JsonUtils;
import com.foxconn.sw.data.dto.entity.ResourceVo;
import com.foxconn.sw.data.entity.SwAppendResource;
import com.foxconn.sw.data.entity.SwAppendResourceExample;
import com.google.common.collect.Lists;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class GetAppendResourcesProcessor {

    @Autowired
    SwAppendResourceBusiness appendResourceBusiness;
    @Autowired
    UserBusiness userBusiness;

    public List<ResourceVo> getAppendResourcesVo(String resourceIds) {
        if (StringUtils.isEmpty(resourceIds)) {
            return Lists.newArrayList();
        }
        List<Integer> resources = JsonUtils.deserialize(resourceIds, List.class, Integer.class);
        if (CollectionUtils.isEmpty(resources)) {
            return Lists.newArrayList();
        }
        return getAppendResourcesVo(resources);
    }

    public List<ResourceVo> getAppendResourcesVo(List<Integer> resourceIds) {
        if (CollectionUtils.isEmpty(resourceIds)) {
            return Lists.newArrayList();
        }
        List<SwAppendResource> resources = appendResourceBusiness.getAppendResources(resourceIds);
        List<ResourceVo> resourceVos = new ArrayList<>();
        Optional.ofNullable(resources).orElse(Lists.newArrayList()).forEach(e -> {
            ResourceVo resourceVo = new ResourceVo();
            resourceVo.setId(e.getId());
            resourceVo.setName(e.getOriginName());
            resourceVo.setUrl(ConvertUtils.urlPreFix(e.getId(), e.getFilePath()));
            resourceVo.setViewUrl(String.format("%s/upload/%s/%s", DomainRetrieval.getDomain(), e.getUploadType(), e.getFilePath()));
            resourceVo.setCreateTime(DateTimeUtils.format(e.getCreateTime()));
            resourceVo.setOperator(userBusiness.mapEmployee(e.getOperator()));
            resourceVos.add(resourceVo);
        });
        return resourceVos;
    }

}
