package com.foxconn.sw.data.mapper.auto;

import com.foxconn.sw.data.entity.SwUserRole;
import com.foxconn.sw.data.entity.SwUserRoleExample;
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
public interface SwUserRoleMapper {
    int deleteByExample(SwUserRoleExample example);

    @Delete({
        "delete from sw_user_role",
        "where id = #{id,jdbcType=INTEGER}"
    })
    int deleteByPrimaryKey(Integer id);

    @Insert({
        "insert into sw_user_role (user_id, role_id, ",
        "create_time, datetime_lastchange)",
        "values (#{userId,jdbcType=INTEGER}, #{roleId,jdbcType=INTEGER}, ",
        "#{createTime,jdbcType=TIMESTAMP}, #{datetimeLastchange,jdbcType=TIMESTAMP})"
    })
    @SelectKey(statement="SELECT LAST_INSERT_ID()", keyProperty="id", before=false, resultType=Integer.class)
    int insert(SwUserRole record);

    int insertSelective(SwUserRole record);

    List<SwUserRole> selectByExampleWithRowbounds(SwUserRoleExample example, RowBounds rowBounds);

    List<SwUserRole> selectByExample(SwUserRoleExample example);

    @Select({
        "select",
        "id, user_id, role_id, create_time, datetime_lastchange",
        "from sw_user_role",
        "where id = #{id,jdbcType=INTEGER}"
    })
    @ResultMap("com.foxconn.sw.data.mapper.auto.SwUserRoleMapper.BaseResultMap")
    SwUserRole selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") SwUserRole record, @Param("example") SwUserRoleExample example);

    int updateByExample(@Param("record") SwUserRole record, @Param("example") SwUserRoleExample example);

    int updateByPrimaryKeySelective(SwUserRole record);

    @Update({
        "update sw_user_role",
        "set user_id = #{userId,jdbcType=INTEGER},",
          "role_id = #{roleId,jdbcType=INTEGER},",
          "create_time = #{createTime,jdbcType=TIMESTAMP},",
          "datetime_lastchange = #{datetimeLastchange,jdbcType=TIMESTAMP}",
        "where id = #{id,jdbcType=INTEGER}"
    })
    int updateByPrimaryKey(SwUserRole record);
}