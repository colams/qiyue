package com.foxconn.sw.data.mapper.extension.oa;

import com.foxconn.sw.data.entity.SwTaskProgress;
import com.foxconn.sw.data.entity.extension.SwTaskProgressExtension;
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
            "stp.id, task_id, operate_eid,se.name,progress, resource_ids, content, stp.datetime_lastchange",
            "from sw_task_progress stp left join sw_employee se on stp.operate_eid=se.employee_no ",
            "where task_id = #{id,jdbcType=INTEGER}"
    })
    @Results({
            @Result(column = "id", property = "id", jdbcType = JdbcType.INTEGER, id = true),
            @Result(column = "task_id", property = "taskId", jdbcType = JdbcType.INTEGER),
            @Result(column = "operate_eid", property = "operateEid", jdbcType = JdbcType.VARCHAR),
            @Result(column = "name", property = "employeeName", jdbcType = JdbcType.VARCHAR),
            @Result(column = "progress", property = "progress", jdbcType = JdbcType.INTEGER),
            @Result(column = "resource_ids", property = "resourceIds", jdbcType = JdbcType.VARCHAR),
            @Result(column = "content", property = "content", jdbcType = JdbcType.VARCHAR),
            @Result(column = "datetime_lastchange", property = "datetimeLastchange", jdbcType = JdbcType.TIMESTAMP)
    })
    List<SwTaskProgressExtension> selectTaskProgressVo(Integer id);

}
