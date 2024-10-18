package com.foxconn.sw.business.mapper;


import com.foxconn.sw.data.dto.entity.oa.TaskBriefDetailVo;
import com.foxconn.sw.data.dto.entity.oa.TaskDetailVo;
import com.foxconn.sw.data.dto.request.task.UpdateTaskParams;
import com.foxconn.sw.data.entity.SwTask;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface TaskMapper {
    TaskMapper INSTANCE = Mappers.getMapper(TaskMapper.class);

    SwTask brief2SwTask(TaskBriefDetailVo taskDetailVo);

    SwTask taskParams2SwTask(UpdateTaskParams taskParams);

    TaskDetailVo toSwTaskDetailVo(SwTask task);

}
