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
            "update sw_task_employee_relation",
            "set is_delete = 1",
            "where role_flag & 4 > 0 ",
            "and employee_no not in (#{employeeNos}) ",
            "and task_id = #{taskId,jdbcType=INTEGER} "
    })
    boolean deleteWatchRelation(@Param("employeeNos") List<String> employeeNos, @Param("taskId") Integer taskId);
}
