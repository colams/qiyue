package com.foxconn.sw.business.mapper;

import com.foxconn.sw.data.dto.entity.task.TaskLogVo;
import com.foxconn.sw.data.entity.SwTaskLog;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface SwTaskLogMapper {

    SwTaskLogMapper INSTANCE = Mappers.getMapper(SwTaskLogMapper.class);

    List<TaskLogVo> toTaskLogVos(List<SwTaskLog> taskLogList);

    @Mapping(target = "datetimeLastchange", ignore = true)
    SwTaskLog toTaskLog(TaskLogVo taskLogVo);


}
