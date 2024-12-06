package com.foxconn.sw.data.mapper.extension.oa;

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
            "stp.id, stp.task_id, operate_eid,se.name,progress, resource_ids, stp.content, stp.datetime_lastchange,ch.id as contentHistoryId",
            "from sw_task_progress stp left join sw_employee se on stp.operate_eid=se.employee_no ",
            "left join sw_task_content_history ch on ch.progress_id=stp.id",
            "where stp.task_id = #{id,jdbcType=INTEGER}",
            " order by stp.id  "
    })
    @Results({
            @Result(column = "id", property = "id", jdbcType = JdbcType.INTEGER, id = true),
            @Result(column = "task_id", property = "taskId", jdbcType = JdbcType.INTEGER),
            @Result(column = "operate_eid", property = "operateEid", jdbcType = JdbcType.VARCHAR),
            @Result(column = "name", property = "employeeName", jdbcType = JdbcType.VARCHAR),
            @Result(column = "progress", property = "progress", jdbcType = JdbcType.INTEGER),
            @Result(column = "resource_ids", property = "resourceIds", jdbcType = JdbcType.VARCHAR),
            @Result(column = "content", property = "content", jdbcType = JdbcType.VARCHAR),
            @Result(column = "datetime_lastchange", property = "datetimeLastchange", jdbcType = JdbcType.TIMESTAMP),
            @Result(column = "contentHistoryId", property = "contentHistoryId", jdbcType = JdbcType.INTEGER),
    })
    List<SwTaskProgressExtension> selectTaskProgressVo(Integer id);

}
