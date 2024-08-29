package com.foxconn.sw.business.mapper;


import com.foxconn.sw.data.dto.entity.system.PropertyVo;
import com.foxconn.sw.data.entity.SwProperty;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface PropertyMapper {

    PropertyMapper INSTANCE = Mappers.getMapper(PropertyMapper.class);

    @Mapping(target = "createTime", ignore = true)
    SwProperty toSwProperty(PropertyVo propertyVo);

}
