package com.foxconn.sw.data.mapper.auto;

import com.foxconn.sw.data.entity.SwCollaborationUser;
import com.foxconn.sw.data.entity.SwCollaborationUserExample;
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
public interface SwCollaborationUserMapper {
    int deleteByExample(SwCollaborationUserExample example);

    @Delete({
        "delete from sw_collaboration_user",
        "where id = #{id,jdbcType=BIGINT}"
    })
    int deleteByPrimaryKey(Long id);

    @Insert({
        "insert into sw_collaboration_user (task_id, employee_no, ",
        "is_delete, status, ",
        "create_time, datetime_lastchange)",
        "values (#{taskId,jdbcType=INTEGER}, #{employeeNo,jdbcType=VARCHAR}, ",
        "#{isDelete,jdbcType=INTEGER}, #{status,jdbcType=INTEGER}, ",
        "#{createTime,jdbcType=TIMESTAMP}, #{datetimeLastchange,jdbcType=TIMESTAMP})"
    })
    @SelectKey(statement="SELECT LAST_INSERT_ID()", keyProperty="id", before=false, resultType=Long.class)
    int insert(SwCollaborationUser record);

    int insertSelective(SwCollaborationUser record);

    List<SwCollaborationUser> selectByExampleWithRowbounds(SwCollaborationUserExample example, RowBounds rowBounds);

    List<SwCollaborationUser> selectByExample(SwCollaborationUserExample example);

    @Select({
        "select",
        "id, task_id, employee_no, is_delete, status, create_time, datetime_lastchange",
        "from sw_collaboration_user",
        "where id = #{id,jdbcType=BIGINT}"
    })
    @ResultMap("com.foxconn.sw.data.mapper.auto.SwCollaborationUserMapper.BaseResultMap")
    SwCollaborationUser selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") SwCollaborationUser record, @Param("example") SwCollaborationUserExample example);

    int updateByExample(@Param("record") SwCollaborationUser record, @Param("example") SwCollaborationUserExample example);

    int updateByPrimaryKeySelective(SwCollaborationUser record);

    @Update({
        "update sw_collaboration_user",
        "set task_id = #{taskId,jdbcType=INTEGER},",
          "employee_no = #{employeeNo,jdbcType=VARCHAR},",
          "is_delete = #{isDelete,jdbcType=INTEGER},",
          "status = #{status,jdbcType=INTEGER},",
          "create_time = #{createTime,jdbcType=TIMESTAMP},",
          "datetime_lastchange = #{datetimeLastchange,jdbcType=TIMESTAMP}",
        "where id = #{id,jdbcType=BIGINT}"
    })
    int updateByPrimaryKey(SwCollaborationUser record);
}