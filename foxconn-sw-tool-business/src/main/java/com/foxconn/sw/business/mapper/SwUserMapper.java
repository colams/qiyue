package com.foxconn.sw.business.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface SwUserMapper {
    SwUserMapper INSTANCE = Mappers.getMapper(SwUserMapper.class);


}
