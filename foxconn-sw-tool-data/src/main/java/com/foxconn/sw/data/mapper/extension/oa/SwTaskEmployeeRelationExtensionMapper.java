package com.foxconn.sw.data.mapper.extension.oa;

import com.foxconn.sw.data.entity.SwTaskEmployeeRelation;
import com.foxconn.sw.data.mapper.auto.SwTaskEmployeeRelationMapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.type.JdbcType;
import org.springframework.stereotype.Repository;

@Repository
public interface SwTaskEmployeeRelationExtensionMapper extends SwTaskEmployeeRelationMapper {


    @Select({
            "select ",
            "id, task_id, employee_no, prev_id,role_flag, is_delete, create_time, datetime_lastchange",
            "from sw_task_employee_relation ",
            "where task_id = #{taskId,jdbcType=INTEGER}",
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
    SwTaskEmployeeRelation getRelation(SwTaskEmployeeRelation relation);


    @Select({
            "select ",
            "id, task_id, employee_no, prev_id,role_flag, is_delete, create_time, datetime_lastchange",
            "from sw_task_employee_relation ",
            "where task_id = #{taskId,jdbcType=INTEGER}",
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
}
