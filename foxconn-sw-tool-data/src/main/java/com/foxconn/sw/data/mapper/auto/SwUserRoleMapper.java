package com.foxconn.sw.data.mapper.auto;

import com.foxconn.sw.data.entity.SwUserRole;
import com.foxconn.sw.data.entity.SwUserRoleExample;
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
public interface SwUserRoleMapper {
    @SelectProvider(type=SwUserRoleSqlProvider.class, method="countByExample")
    long countByExample(SwUserRoleExample example);

    @DeleteProvider(type=SwUserRoleSqlProvider.class, method="deleteByExample")
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

    @InsertProvider(type=SwUserRoleSqlProvider.class, method="insertSelective")
    @SelectKey(statement="SELECT LAST_INSERT_ID()", keyProperty="id", before=false, resultType=Integer.class)
    int insertSelective(SwUserRole record);

    @SelectProvider(type=SwUserRoleSqlProvider.class, method="selectByExample")
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="user_id", property="userId", jdbcType=JdbcType.INTEGER),
        @Result(column="role_id", property="roleId", jdbcType=JdbcType.INTEGER),
        @Result(column="create_time", property="createTime", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="datetime_lastchange", property="datetimeLastchange", jdbcType=JdbcType.TIMESTAMP)
    })
    List<SwUserRole> selectByExampleWithRowbounds(SwUserRoleExample example, RowBounds rowBounds);

    @SelectProvider(type=SwUserRoleSqlProvider.class, method="selectByExample")
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="user_id", property="userId", jdbcType=JdbcType.INTEGER),
        @Result(column="role_id", property="roleId", jdbcType=JdbcType.INTEGER),
        @Result(column="create_time", property="createTime", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="datetime_lastchange", property="datetimeLastchange", jdbcType=JdbcType.TIMESTAMP)
    })
    List<SwUserRole> selectByExample(SwUserRoleExample example);

    @Select({
        "select",
        "id, user_id, role_id, create_time, datetime_lastchange",
        "from sw_user_role",
        "where id = #{id,jdbcType=INTEGER}"
    })
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="user_id", property="userId", jdbcType=JdbcType.INTEGER),
        @Result(column="role_id", property="roleId", jdbcType=JdbcType.INTEGER),
        @Result(column="create_time", property="createTime", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="datetime_lastchange", property="datetimeLastchange", jdbcType=JdbcType.TIMESTAMP)
    })
    SwUserRole selectByPrimaryKey(Integer id);

    @UpdateProvider(type=SwUserRoleSqlProvider.class, method="updateByExampleSelective")
    int updateByExampleSelective(@Param("record") SwUserRole record, @Param("example") SwUserRoleExample example);

    @UpdateProvider(type=SwUserRoleSqlProvider.class, method="updateByExample")
    int updateByExample(@Param("record") SwUserRole record, @Param("example") SwUserRoleExample example);

    @UpdateProvider(type=SwUserRoleSqlProvider.class, method="updateByPrimaryKeySelective")
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