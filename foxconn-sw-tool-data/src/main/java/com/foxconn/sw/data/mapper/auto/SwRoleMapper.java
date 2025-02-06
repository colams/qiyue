package com.foxconn.sw.data.mapper.auto;

import com.foxconn.sw.data.entity.SwRole;
import com.foxconn.sw.data.entity.SwRoleExample;
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
public interface SwRoleMapper {
    @SelectProvider(type=SwRoleSqlProvider.class, method="countByExample")
    long countByExample(SwRoleExample example);

    @DeleteProvider(type=SwRoleSqlProvider.class, method="deleteByExample")
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

    @InsertProvider(type=SwRoleSqlProvider.class, method="insertSelective")
    @SelectKey(statement="SELECT LAST_INSERT_ID()", keyProperty="id", before=false, resultType=Integer.class)
    int insertSelective(SwRole record);

    @SelectProvider(type=SwRoleSqlProvider.class, method="selectByExample")
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="role_name", property="roleName", jdbcType=JdbcType.VARCHAR),
        @Result(column="role_status", property="roleStatus", jdbcType=JdbcType.INTEGER),
        @Result(column="create_time", property="createTime", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="datetime_lastchange", property="datetimeLastchange", jdbcType=JdbcType.TIMESTAMP)
    })
    List<SwRole> selectByExampleWithRowbounds(SwRoleExample example, RowBounds rowBounds);

    @SelectProvider(type=SwRoleSqlProvider.class, method="selectByExample")
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="role_name", property="roleName", jdbcType=JdbcType.VARCHAR),
        @Result(column="role_status", property="roleStatus", jdbcType=JdbcType.INTEGER),
        @Result(column="create_time", property="createTime", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="datetime_lastchange", property="datetimeLastchange", jdbcType=JdbcType.TIMESTAMP)
    })
    List<SwRole> selectByExample(SwRoleExample example);

    @Select({
        "select",
        "id, role_name, role_status, create_time, datetime_lastchange",
        "from sw_role",
        "where id = #{id,jdbcType=INTEGER}"
    })
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="role_name", property="roleName", jdbcType=JdbcType.VARCHAR),
        @Result(column="role_status", property="roleStatus", jdbcType=JdbcType.INTEGER),
        @Result(column="create_time", property="createTime", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="datetime_lastchange", property="datetimeLastchange", jdbcType=JdbcType.TIMESTAMP)
    })
    SwRole selectByPrimaryKey(Integer id);

    @UpdateProvider(type=SwRoleSqlProvider.class, method="updateByExampleSelective")
    int updateByExampleSelective(@Param("record") SwRole record, @Param("example") SwRoleExample example);

    @UpdateProvider(type=SwRoleSqlProvider.class, method="updateByExample")
    int updateByExample(@Param("record") SwRole record, @Param("example") SwRoleExample example);

    @UpdateProvider(type=SwRoleSqlProvider.class, method="updateByPrimaryKeySelective")
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