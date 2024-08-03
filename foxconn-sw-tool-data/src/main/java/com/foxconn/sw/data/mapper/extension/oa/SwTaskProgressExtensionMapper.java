package com.foxconn.sw.data.mapper.extension.oa;

import com.foxconn.sw.data.entity.SwTaskProgress;
import com.foxconn.sw.data.mapper.auto.SwTaskProgressMapper;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.type.JdbcType;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SwTaskProgressExtensionMapper extends SwTaskProgressMapper {


    @Select({
            "select",
            "id, task_id, operate_eid, resource_ids, content, remark, datetime_lastchange",
            "from sw_task_progress",
            "where task_id = #{id,jdbcType=INTEGER}"
    })
    @Results({
            @Result(column = "id", property = "id", jdbcType = JdbcType.INTEGER, id = true),
            @Result(column = "task_id", property = "taskId", jdbcType = JdbcType.INTEGER),
            @Result(column = "operate_eid", property = "operateEid", jdbcType = JdbcType.VARCHAR),
            @Result(column = "resource_ids", property = "resourceIds", jdbcType = JdbcType.VARCHAR),
            @Result(column = "content", property = "content", jdbcType = JdbcType.VARCHAR),
            @Result(column = "remark", property = "remark", jdbcType = JdbcType.VARCHAR),
            @Result(column = "datetime_lastchange", property = "datetimeLastchange", jdbcType = JdbcType.TIMESTAMP)
    })
    List<SwTaskProgress> selectTaskProgressVo(Integer id);

}
