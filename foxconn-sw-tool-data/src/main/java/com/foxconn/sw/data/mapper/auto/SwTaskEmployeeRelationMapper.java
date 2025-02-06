package com.foxconn.sw.data.mapper.auto;

import com.foxconn.sw.data.entity.SwTaskEmployeeRelation;
import com.foxconn.sw.data.entity.SwTaskEmployeeRelationExample;
import java.util.List;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectKey;
import org.apache.ibatis.annotations.Update;
import org.apache.ibatis.session.RowBounds;

@Mapper
public interface SwTaskEmployeeRelationMapper {
    int deleteByExample(SwTaskEmployeeRelationExample example);

    @Delete({
        "delete from sw_task_employee_relation",
        "where id = #{id,jdbcType=INTEGER}"
    })
    int deleteByPrimaryKey(Integer id);

    @Insert({
        "insert into sw_task_employee_relation (task_id, employee_no, ",
        "prev_id, role_flag, ",
        "is_active, is_inspector, ",
        "is_read, process_status, ",
        "is_delete, create_time, ",
        "datetime_lastchange)",
        "values (#{taskId,jdbcType=INTEGER}, #{employeeNo,jdbcType=VARCHAR}, ",
        "#{prevId,jdbcType=INTEGER}, #{roleFlag,jdbcType=INTEGER}, ",
        "#{isActive,jdbcType=INTEGER}, #{isInspector,jdbcType=INTEGER}, ",
        "#{isRead,jdbcType=INTEGER}, #{processStatus,jdbcType=INTEGER}, ",
        "#{isDelete,jdbcType=INTEGER}, #{createTime,jdbcType=TIMESTAMP}, ",
        "#{datetimeLastchange,jdbcType=TIMESTAMP})"
    })
    @SelectKey(statement="SELECT LAST_INSERT_ID()", keyProperty="id", before=false, resultType=Integer.class)
    int insert(SwTaskEmployeeRelation record);

    int insertSelective(SwTaskEmployeeRelation record);

    List<SwTaskEmployeeRelation> selectByExampleWithRowbounds(SwTaskEmployeeRelationExample example, RowBounds rowBounds);

    List<SwTaskEmployeeRelation> selectByExample(SwTaskEmployeeRelationExample example);

    @Select({
        "select",
        "id, task_id, employee_no, prev_id, role_flag, is_active, is_inspector, is_read, ",
        "process_status, is_delete, create_time, datetime_lastchange",
        "from sw_task_employee_relation",
        "where id = #{id,jdbcType=INTEGER}"
    })
    @ResultMap("com.foxconn.sw.data.mapper.auto.SwTaskEmployeeRelationMapper.BaseResultMap")
    SwTaskEmployeeRelation selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") SwTaskEmployeeRelation record, @Param("example") SwTaskEmployeeRelationExample example);

    int updateByExample(@Param("record") SwTaskEmployeeRelation record, @Param("example") SwTaskEmployeeRelationExample example);

    int updateByPrimaryKeySelective(SwTaskEmployeeRelation record);

    @Update({
        "update sw_task_employee_relation",
        "set task_id = #{taskId,jdbcType=INTEGER},",
          "employee_no = #{employeeNo,jdbcType=VARCHAR},",
          "prev_id = #{prevId,jdbcType=INTEGER},",
          "role_flag = #{roleFlag,jdbcType=INTEGER},",
          "is_active = #{isActive,jdbcType=INTEGER},",
          "is_inspector = #{isInspector,jdbcType=INTEGER},",
          "is_read = #{isRead,jdbcType=INTEGER},",
          "process_status = #{processStatus,jdbcType=INTEGER},",
          "is_delete = #{isDelete,jdbcType=INTEGER},",
          "create_time = #{createTime,jdbcType=TIMESTAMP},",
          "datetime_lastchange = #{datetimeLastchange,jdbcType=TIMESTAMP}",
        "where id = #{id,jdbcType=INTEGER}"
    })
    int updateByPrimaryKey(SwTaskEmployeeRelation record);
}