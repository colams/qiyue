package com.foxconn.sw.business.mapper;


import com.foxconn.sw.common.utils.ConvertUtils;
import com.foxconn.sw.data.dto.entity.oa.TaskBriefDetailVo;
import com.foxconn.sw.data.dto.entity.oa.TaskDetailVo;
import com.foxconn.sw.data.entity.SwTask;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(uses = {
        ConvertUtils.class,
})
public interface TaskMapper {
    TaskMapper INSTANCE = Mappers.getMapper(TaskMapper.class);

    @Mapping(target = "resourceIds", source = "taskDetailVo.resourceIds", qualifiedByName = "listIntegerToString")
    SwTask brief2SwTask(TaskBriefDetailVo taskDetailVo);

    @Mapping(target = "resourceIds", source = "taskDetailVo.resourceIds", qualifiedByName = "stringToListInt")
    TaskDetailVo toSwTaskDetailVo(SwTask taskDetailVo);

}
