package com.foxconn.sw.data.mapper.auto;

import com.foxconn.sw.data.entity.SwTaskEmployeeRelation;
import com.foxconn.sw.data.entity.SwTaskEmployeeRelationExample;
import java.util.List;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.DeleteProvider;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectKey;
import org.apache.ibatis.annotations.SelectProvider;
import org.apache.ibatis.annotations.Update;
import org.apache.ibatis.annotations.UpdateProvider;
import org.apache.ibatis.session.RowBounds;
import org.apache.ibatis.type.JdbcType;

@Mapper
public interface SwTaskEmployeeRelationMapper {
    @SelectProvider(type=SwTaskEmployeeRelationSqlProvider.class, method="countByExample")
    long countByExample(SwTaskEmployeeRelationExample example);

    @DeleteProvider(type=SwTaskEmployeeRelationSqlProvider.class, method="deleteByExample")
    int deleteByExample(SwTaskEmployeeRelationExample example);

    @Delete({
        "delete from sw_task_employee_relation",
        "where id = #{id,jdbcType=INTEGER}"
    })
    int deleteByPrimaryKey(Integer id);

    @Insert({
        "insert into sw_task_employee_relation (task_id, employee_no, ",
        "prev_id, role_flag, ",
        "is_active, is_delete, ",
        "create_time, datetime_lastchange)",
        "values (#{taskId,jdbcType=INTEGER}, #{employeeNo,jdbcType=VARCHAR}, ",
        "#{prevId,jdbcType=INTEGER}, #{roleFlag,jdbcType=INTEGER}, ",
        "#{isActive,jdbcType=INTEGER}, #{isDelete,jdbcType=INTEGER}, ",
        "#{createTime,jdbcType=TIMESTAMP}, #{datetimeLastchange,jdbcType=TIMESTAMP})"
    })
    @SelectKey(statement="SELECT LAST_INSERT_ID()", keyProperty="id", before=false, resultType=Integer.class)
    int insert(SwTaskEmployeeRelation record);

    @InsertProvider(type=SwTaskEmployeeRelationSqlProvider.class, method="insertSelective")
    @SelectKey(statement="SELECT LAST_INSERT_ID()", keyProperty="id", before=false, resultType=Integer.class)
    int insertSelective(SwTaskEmployeeRelation record);

    @SelectProvider(type=SwTaskEmployeeRelationSqlProvider.class, method="selectByExample")
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="task_id", property="taskId", jdbcType=JdbcType.INTEGER),
        @Result(column="employee_no", property="employeeNo", jdbcType=JdbcType.VARCHAR),
        @Result(column="prev_id", property="prevId", jdbcType=JdbcType.INTEGER),
        @Result(column="role_flag", property="roleFlag", jdbcType=JdbcType.INTEGER),
        @Result(column="is_active", property="isActive", jdbcType=JdbcType.INTEGER),
        @Result(column="is_delete", property="isDelete", jdbcType=JdbcType.INTEGER),
        @Result(column="create_time", property="createTime", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="datetime_lastchange", property="datetimeLastchange", jdbcType=JdbcType.TIMESTAMP)
    })
    List<SwTaskEmployeeRelation> selectByExampleWithRowbounds(SwTaskEmployeeRelationExample example, RowBounds rowBounds);

    @SelectProvider(type=SwTaskEmployeeRelationSqlProvider.class, method="selectByExample")
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="task_id", property="taskId", jdbcType=JdbcType.INTEGER),
        @Result(column="employee_no", property="employeeNo", jdbcType=JdbcType.VARCHAR),
        @Result(column="prev_id", property="prevId", jdbcType=JdbcType.INTEGER),
        @Result(column="role_flag", property="roleFlag", jdbcType=JdbcType.INTEGER),
        @Result(column="is_active", property="isActive", jdbcType=JdbcType.INTEGER),
        @Result(column="is_delete", property="isDelete", jdbcType=JdbcType.INTEGER),
        @Result(column="create_time", property="createTime", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="datetime_lastchange", property="datetimeLastchange", jdbcType=JdbcType.TIMESTAMP)
    })
    List<SwTaskEmployeeRelation> selectByExample(SwTaskEmployeeRelationExample example);

    @Select({
        "select",
        "id, task_id, employee_no, prev_id, role_flag, is_active, is_delete, create_time, ",
        "datetime_lastchange",
        "from sw_task_employee_relation",
        "where id = #{id,jdbcType=INTEGER}"
    })
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="task_id", property="taskId", jdbcType=JdbcType.INTEGER),
        @Result(column="employee_no", property="employeeNo", jdbcType=JdbcType.VARCHAR),
        @Result(column="prev_id", property="prevId", jdbcType=JdbcType.INTEGER),
        @Result(column="role_flag", property="roleFlag", jdbcType=JdbcType.INTEGER),
        @Result(column="is_active", property="isActive", jdbcType=JdbcType.INTEGER),
        @Result(column="is_delete", property="isDelete", jdbcType=JdbcType.INTEGER),
        @Result(column="create_time", property="createTime", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="datetime_lastchange", property="datetimeLastchange", jdbcType=JdbcType.TIMESTAMP)
    })
    SwTaskEmployeeRelation selectByPrimaryKey(Integer id);

    @UpdateProvider(type=SwTaskEmployeeRelationSqlProvider.class, method="updateByExampleSelective")
    int updateByExampleSelective(@Param("record") SwTaskEmployeeRelation record, @Param("example") SwTaskEmployeeRelationExample example);

    @UpdateProvider(type=SwTaskEmployeeRelationSqlProvider.class, method="updateByExample")
    int updateByExample(@Param("record") SwTaskEmployeeRelation record, @Param("example") SwTaskEmployeeRelationExample example);

    @UpdateProvider(type=SwTaskEmployeeRelationSqlProvider.class, method="updateByPrimaryKeySelective")
    int updateByPrimaryKeySelective(SwTaskEmployeeRelation record);

    @Update({
        "update sw_task_employee_relation",
        "set task_id = #{taskId,jdbcType=INTEGER},",
          "employee_no = #{employeeNo,jdbcType=VARCHAR},",
          "prev_id = #{prevId,jdbcType=INTEGER},",
          "role_flag = #{roleFlag,jdbcType=INTEGER},",
          "is_active = #{isActive,jdbcType=INTEGER},",
          "is_delete = #{isDelete,jdbcType=INTEGER},",
          "create_time = #{createTime,jdbcType=TIMESTAMP},",
          "datetime_lastchange = #{datetimeLastchange,jdbcType=TIMESTAMP}",
        "where id = #{id,jdbcType=INTEGER}"
    })
    int updateByPrimaryKey(SwTaskEmployeeRelation record);
}