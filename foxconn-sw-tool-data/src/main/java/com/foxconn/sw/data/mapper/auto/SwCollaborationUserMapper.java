package com.foxconn.sw.data.mapper.auto;

import com.foxconn.sw.data.entity.SwCollaborationUser;
import com.foxconn.sw.data.entity.SwCollaborationUserExample;
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
public interface SwCollaborationUserMapper {
    @SelectProvider(type=SwCollaborationUserSqlProvider.class, method="countByExample")
    long countByExample(SwCollaborationUserExample example);

    @DeleteProvider(type=SwCollaborationUserSqlProvider.class, method="deleteByExample")
    int deleteByExample(SwCollaborationUserExample example);

    @Delete({
        "delete from sw_collaboration_user",
        "where id = #{id,jdbcType=INTEGER}"
    })
    int deleteByPrimaryKey(Integer id);

    @Insert({
        "insert into sw_collaboration_user (task_id, employee_no, ",
        "is_delete, status, ",
        "create_time, datetime_lastchange)",
        "values (#{taskId,jdbcType=INTEGER}, #{employeeNo,jdbcType=VARCHAR}, ",
        "#{isDelete,jdbcType=INTEGER}, #{status,jdbcType=INTEGER}, ",
        "#{createTime,jdbcType=TIMESTAMP}, #{datetimeLastchange,jdbcType=TIMESTAMP})"
    })
    @SelectKey(statement="SELECT LAST_INSERT_ID()", keyProperty="id", before=false, resultType=Integer.class)
    int insert(SwCollaborationUser record);

    @InsertProvider(type=SwCollaborationUserSqlProvider.class, method="insertSelective")
    @SelectKey(statement="SELECT LAST_INSERT_ID()", keyProperty="id", before=false, resultType=Integer.class)
    int insertSelective(SwCollaborationUser record);

    @SelectProvider(type=SwCollaborationUserSqlProvider.class, method="selectByExample")
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="task_id", property="taskId", jdbcType=JdbcType.INTEGER),
        @Result(column="employee_no", property="employeeNo", jdbcType=JdbcType.VARCHAR),
        @Result(column="is_delete", property="isDelete", jdbcType=JdbcType.INTEGER),
        @Result(column="status", property="status", jdbcType=JdbcType.INTEGER),
        @Result(column="create_time", property="createTime", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="datetime_lastchange", property="datetimeLastchange", jdbcType=JdbcType.TIMESTAMP)
    })
    List<SwCollaborationUser> selectByExampleWithRowbounds(SwCollaborationUserExample example, RowBounds rowBounds);

    @SelectProvider(type=SwCollaborationUserSqlProvider.class, method="selectByExample")
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="task_id", property="taskId", jdbcType=JdbcType.INTEGER),
        @Result(column="employee_no", property="employeeNo", jdbcType=JdbcType.VARCHAR),
        @Result(column="is_delete", property="isDelete", jdbcType=JdbcType.INTEGER),
        @Result(column="status", property="status", jdbcType=JdbcType.INTEGER),
        @Result(column="create_time", property="createTime", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="datetime_lastchange", property="datetimeLastchange", jdbcType=JdbcType.TIMESTAMP)
    })
    List<SwCollaborationUser> selectByExample(SwCollaborationUserExample example);

    @Select({
        "select",
        "id, task_id, employee_no, is_delete, status, create_time, datetime_lastchange",
        "from sw_collaboration_user",
        "where id = #{id,jdbcType=INTEGER}"
    })
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="task_id", property="taskId", jdbcType=JdbcType.INTEGER),
        @Result(column="employee_no", property="employeeNo", jdbcType=JdbcType.VARCHAR),
        @Result(column="is_delete", property="isDelete", jdbcType=JdbcType.INTEGER),
        @Result(column="status", property="status", jdbcType=JdbcType.INTEGER),
        @Result(column="create_time", property="createTime", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="datetime_lastchange", property="datetimeLastchange", jdbcType=JdbcType.TIMESTAMP)
    })
    SwCollaborationUser selectByPrimaryKey(Integer id);

    @UpdateProvider(type=SwCollaborationUserSqlProvider.class, method="updateByExampleSelective")
    int updateByExampleSelective(@Param("record") SwCollaborationUser record, @Param("example") SwCollaborationUserExample example);

    @UpdateProvider(type=SwCollaborationUserSqlProvider.class, method="updateByExample")
    int updateByExample(@Param("record") SwCollaborationUser record, @Param("example") SwCollaborationUserExample example);

    @UpdateProvider(type=SwCollaborationUserSqlProvider.class, method="updateByPrimaryKeySelective")
    int updateByPrimaryKeySelective(SwCollaborationUser record);

    @Update({
        "update sw_collaboration_user",
        "set task_id = #{taskId,jdbcType=INTEGER},",
          "employee_no = #{employeeNo,jdbcType=VARCHAR},",
          "is_delete = #{isDelete,jdbcType=INTEGER},",
          "status = #{status,jdbcType=INTEGER},",
          "create_time = #{createTime,jdbcType=TIMESTAMP},",
          "datetime_lastchange = #{datetimeLastchange,jdbcType=TIMESTAMP}",
        "where id = #{id,jdbcType=INTEGER}"
    })
    int updateByPrimaryKey(SwCollaborationUser record);
}