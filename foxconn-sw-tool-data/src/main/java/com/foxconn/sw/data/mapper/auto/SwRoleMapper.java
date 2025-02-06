package com.foxconn.sw.data.mapper.auto;

import com.foxconn.sw.data.entity.SwRole;
import com.foxconn.sw.data.entity.SwRoleExample;
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
public interface SwRoleMapper {
    long countByExample(SwRoleExample example);

    int deleteByExample(SwRoleExample example);

    @Delete({
        "delete from sw_role",
        "where id = #{id,jdbcType=INTEGER}"
    })
    int deleteByPrimaryKey(Integer id);

    @Insert({
        "insert into sw_role (role_name, role_status, ",
        "create_time, datetime_lastchange)",
        "values (#{roleName,jdbcType=VARCHAR}, #{roleStatus,jdbcType=INTEGER}, ",
        "#{createTime,jdbcType=TIMESTAMP}, #{datetimeLastchange,jdbcType=TIMESTAMP})"
    })
    @SelectKey(statement="SELECT LAST_INSERT_ID()", keyProperty="id", before=false, resultType=Integer.class)
    int insert(SwRole record);

    int insertSelective(SwRole record);

    List<SwRole> selectByExampleWithRowbounds(SwRoleExample example, RowBounds rowBounds);

    List<SwRole> selectByExample(SwRoleExample example);

    @Select({
        "select",
        "id, role_name, role_status, create_time, datetime_lastchange",
        "from sw_role",
        "where id = #{id,jdbcType=INTEGER}"
    })
    @ResultMap("com.foxconn.sw.data.mapper.auto.SwRoleMapper.BaseResultMap")
    SwRole selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") SwRole record, @Param("example") SwRoleExample example);

    int updateByExample(@Param("record") SwRole record, @Param("example") SwRoleExample example);

    int updateByPrimaryKeySelective(SwRole record);

    @Update({
        "update sw_role",
        "set role_name = #{roleName,jdbcType=VARCHAR},",
          "role_status = #{roleStatus,jdbcType=INTEGER},",
          "create_time = #{createTime,jdbcType=TIMESTAMP},",
          "datetime_lastchange = #{datetimeLastchange,jdbcType=TIMESTAMP}",
        "where id = #{id,jdbcType=INTEGER}"
    })
    int updateByPrimaryKey(SwRole record);
}