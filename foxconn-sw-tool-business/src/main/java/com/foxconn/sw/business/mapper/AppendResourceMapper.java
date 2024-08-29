package com.foxconn.sw.business.mapper;

import com.foxconn.sw.common.utils.ConvertUtils;
import com.foxconn.sw.data.dto.entity.ResourceVo;
import com.foxconn.sw.data.entity.SwAppendResource;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(uses = {
        ConvertUtils.class,
})
public interface AppendResourceMapper {

    AppendResourceMapper INSTANCE = Mappers.getMapper(AppendResourceMapper.class);

    List<ResourceVo> toAppendResources(List<SwAppendResource> appendResources);

    default ResourceVo toAppendResource(SwAppendResource appendResource) {
        if (appendResource == null) {
            return null;
        }

        ResourceVo resourceVo = new ResourceVo();

        resourceVo.setName(appendResource.getOriginName());
        resourceVo.setUrl(ConvertUtils.urlPreFix(appendResource.getId(), appendResource.getFilePath()));
        resourceVo.setId(appendResource.getId());

        return resourceVo;
    }

}
