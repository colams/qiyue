package com.foxconn.sw.data.mapper.extension.oa;

import com.foxconn.sw.data.entity.SwTaskEmployeeRelation;
import com.foxconn.sw.data.mapper.auto.SwTaskEmployeeRelationMapper;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.type.JdbcType;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SwTaskEmployeeRelationExtensionMapper extends SwTaskEmployeeRelationMapper {

    @Select({
            "select ",
            "id, task_id, employee_no, prev_id,role_flag, is_delete, create_time, datetime_lastchange",
            "from sw_task_employee_relation ",
            "where task_id = #{taskId,jdbcType=INTEGER} and is_delete=0",
            "and employee_no = #{employeeNo,jdbcType=VARCHAR}",
            "limit 1 "
    })
    @Results({
            @Result(column = "id", property = "id", jdbcType = JdbcType.INTEGER, id = true),
            @Result(column = "task_id", property = "taskId", jdbcType = JdbcType.INTEGER),
            @Result(column = "employee_no", property = "employeeNo", jdbcType = JdbcType.VARCHAR),
            @Result(column = "prev_id", property = "prevId", jdbcType = JdbcType.INTEGER),
            @Result(column = "role_flag", property = "roleFlag", jdbcType = JdbcType.INTEGER),
            @Result(column = "is_delete", property = "isDelete", jdbcType = JdbcType.INTEGER),
            @Result(column = "create_time", property = "createTime", jdbcType = JdbcType.TIMESTAMP),
            @Result(column = "datetime_lastchange", property = "datetimeLastchange", jdbcType = JdbcType.TIMESTAMP)
    })
    SwTaskEmployeeRelation queryRelation(@Param("taskId") Integer taskId, @Param("employeeNo") String employeeNo);

    @Select({
            "select *",
            "from sw_task_employee_relation ",
            "where task_id = #{taskId,jdbcType=INTEGER} and is_delete=0 "
    })
    @Results({
            @Result(column = "id", property = "id", jdbcType = JdbcType.INTEGER, id = true),
            @Result(column = "task_id", property = "taskId", jdbcType = JdbcType.INTEGER),
            @Result(column = "employee_no", property = "employeeNo", jdbcType = JdbcType.VARCHAR),
            @Result(column = "prev_id", property = "prevId", jdbcType = JdbcType.INTEGER),
            @Result(column = "role_flag", property = "roleFlag", jdbcType = JdbcType.INTEGER),
            @Result(column = "is_delete", property = "isDelete", jdbcType = JdbcType.INTEGER),
            @Result(column = "create_time", property = "createTime", jdbcType = JdbcType.TIMESTAMP),
            @Result(column = "datetime_lastchange", property = "datetimeLastchange", jdbcType = JdbcType.TIMESTAMP)
    })
    List<SwTaskEmployeeRelation> selectByTaskID(Integer taskId);

    @Update({
            "<script>",
            "update sw_task_employee_relation",
            "set is_delete=1",
            "where id in",
            "<foreach collection='ids' item='id' open='(' separator=',' close=')'>",
            "#{id,jdbcType=INTEGER}",
            "</foreach>",
            "</script>"
    })
    int deleteWatchRelation(@Param("ids") List<Integer> ids);


    @Select({
            "select r.*",
            "from sw_task_employee_relation r",
            "inner join sw_task s on r.task_id=s.id",
            "where r.is_delete=0 and s.task_no=#{taskNo,jdbcType=BIGINT}"
    })
    @Results({
            @Result(column = "id", property = "id", jdbcType = JdbcType.INTEGER, id = true),
            @Result(column = "task_id", property = "taskId", jdbcType = JdbcType.INTEGER),
            @Result(column = "employee_no", property = "employeeNo", jdbcType = JdbcType.VARCHAR),
            @Result(column = "prev_id", property = "prevId", jdbcType = JdbcType.INTEGER),
            @Result(column = "role_flag", property = "roleFlag", jdbcType = JdbcType.INTEGER),
            @Result(column = "is_delete", property = "isDelete", jdbcType = JdbcType.INTEGER),
            @Result(column = "create_time", property = "createTime", jdbcType = JdbcType.TIMESTAMP),
            @Result(column = "datetime_lastchange", property = "datetimeLastchange", jdbcType = JdbcType.TIMESTAMP)
    })
    List<SwTaskEmployeeRelation> selectByTaskNo(Long taskNo);
}
