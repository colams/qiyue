package com.foxconn.sw.business.mapper;

import com.foxconn.sw.common.utils.ConvertUtils;
import com.foxconn.sw.data.dto.entity.task.TaskProgressBriefParams;
import com.foxconn.sw.data.entity.SwTaskProgress;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(uses = {
        ConvertUtils.class,
})
public interface TaskProgressMapper {
    TaskProgressMapper INSTANCE = Mappers.getMapper(TaskProgressMapper.class);

    @Mapping(target = "resourceIds", source = "taskLogVo.resourceIds", qualifiedByName = "listIntegerToString")
    SwTaskProgress toTaskProcess(TaskProgressBriefParams taskLogVo);

}
