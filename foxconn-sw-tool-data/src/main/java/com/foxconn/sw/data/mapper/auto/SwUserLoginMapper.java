package com.foxconn.sw.data.mapper.auto;

import com.foxconn.sw.data.entity.SwUserLogin;
import com.foxconn.sw.data.entity.SwUserLoginExample;
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
public interface SwUserLoginMapper {
    @SelectProvider(type=SwUserLoginSqlProvider.class, method="countByExample")
    long countByExample(SwUserLoginExample example);

    @DeleteProvider(type=SwUserLoginSqlProvider.class, method="deleteByExample")
    int deleteByExample(SwUserLoginExample example);

    @Delete({
        "delete from sw_user_login",
        "where id = #{id,jdbcType=INTEGER}"
    })
    int deleteByPrimaryKey(Integer id);

    @Insert({
        "insert into sw_user_login (user_name, token, ",
        "expire_time, create_time, ",
        "datetime_lastchange)",
        "values (#{userName,jdbcType=VARCHAR}, #{token,jdbcType=VARCHAR}, ",
        "#{expireTime,jdbcType=TIMESTAMP}, #{createTime,jdbcType=TIMESTAMP}, ",
        "#{datetimeLastchange,jdbcType=TIMESTAMP})"
    })
    @SelectKey(statement="SELECT LAST_INSERT_ID()", keyProperty="id", before=false, resultType=Integer.class)
    int insert(SwUserLogin record);

    @InsertProvider(type=SwUserLoginSqlProvider.class, method="insertSelective")
    @SelectKey(statement="SELECT LAST_INSERT_ID()", keyProperty="id", before=false, resultType=Integer.class)
    int insertSelective(SwUserLogin record);

    @SelectProvider(type=SwUserLoginSqlProvider.class, method="selectByExample")
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="user_name", property="userName", jdbcType=JdbcType.VARCHAR),
        @Result(column="token", property="token", jdbcType=JdbcType.VARCHAR),
        @Result(column="expire_time", property="expireTime", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="create_time", property="createTime", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="datetime_lastchange", property="datetimeLastchange", jdbcType=JdbcType.TIMESTAMP)
    })
    List<SwUserLogin> selectByExampleWithRowbounds(SwUserLoginExample example, RowBounds rowBounds);

    @SelectProvider(type=SwUserLoginSqlProvider.class, method="selectByExample")
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="user_name", property="userName", jdbcType=JdbcType.VARCHAR),
        @Result(column="token", property="token", jdbcType=JdbcType.VARCHAR),
        @Result(column="expire_time", property="expireTime", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="create_time", property="createTime", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="datetime_lastchange", property="datetimeLastchange", jdbcType=JdbcType.TIMESTAMP)
    })
    List<SwUserLogin> selectByExample(SwUserLoginExample example);

    @Select({
        "select",
        "id, user_name, token, expire_time, create_time, datetime_lastchange",
        "from sw_user_login",
        "where id = #{id,jdbcType=INTEGER}"
    })
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="user_name", property="userName", jdbcType=JdbcType.VARCHAR),
        @Result(column="token", property="token", jdbcType=JdbcType.VARCHAR),
        @Result(column="expire_time", property="expireTime", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="create_time", property="createTime", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="datetime_lastchange", property="datetimeLastchange", jdbcType=JdbcType.TIMESTAMP)
    })
    SwUserLogin selectByPrimaryKey(Integer id);

    @UpdateProvider(type=SwUserLoginSqlProvider.class, method="updateByExampleSelective")
    int updateByExampleSelective(@Param("record") SwUserLogin record, @Param("example") SwUserLoginExample example);

    @UpdateProvider(type=SwUserLoginSqlProvider.class, method="updateByExample")
    int updateByExample(@Param("record") SwUserLogin record, @Param("example") SwUserLoginExample example);

    @UpdateProvider(type=SwUserLoginSqlProvider.class, method="updateByPrimaryKeySelective")
    int updateByPrimaryKeySelective(SwUserLogin record);

    @Update({
        "update sw_user_login",
        "set user_name = #{userName,jdbcType=VARCHAR},",
          "token = #{token,jdbcType=VARCHAR},",
          "expire_time = #{expireTime,jdbcType=TIMESTAMP},",
          "create_time = #{createTime,jdbcType=TIMESTAMP},",
          "datetime_lastchange = #{datetimeLastchange,jdbcType=TIMESTAMP}",
        "where id = #{id,jdbcType=INTEGER}"
    })
    int updateByPrimaryKey(SwUserLogin record);
}