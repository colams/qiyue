package com.foxconn.sw.data.mapper.auto;

import com.foxconn.sw.data.entity.SwUser;
import com.foxconn.sw.data.entity.SwUserExample;
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
public interface SwUserMapper {
    @SelectProvider(type=SwUserSqlProvider.class, method="countByExample")
    long countByExample(SwUserExample example);

    @DeleteProvider(type=SwUserSqlProvider.class, method="deleteByExample")
    int deleteByExample(SwUserExample example);

    @Delete({
        "delete from sw_user",
        "where id = #{id,jdbcType=INTEGER}"
    })
    int deleteByPrimaryKey(Integer id);

    @Insert({
        "insert into sw_user (employee_no, password, ",
        "solt, signature, ",
        "create_time, datetime_lastchange)",
        "values (#{employeeNo,jdbcType=VARCHAR}, #{password,jdbcType=VARCHAR}, ",
        "#{solt,jdbcType=VARCHAR}, #{signature,jdbcType=VARCHAR}, ",
        "#{createTime,jdbcType=TIMESTAMP}, #{datetimeLastchange,jdbcType=TIMESTAMP})"
    })
    @SelectKey(statement="SELECT LAST_INSERT_ID()", keyProperty="id", before=false, resultType=Integer.class)
    int insert(SwUser record);

    @InsertProvider(type=SwUserSqlProvider.class, method="insertSelective")
    @SelectKey(statement="SELECT LAST_INSERT_ID()", keyProperty="id", before=false, resultType=Integer.class)
    int insertSelective(SwUser record);

    @SelectProvider(type=SwUserSqlProvider.class, method="selectByExample")
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="employee_no", property="employeeNo", jdbcType=JdbcType.VARCHAR),
        @Result(column="password", property="password", jdbcType=JdbcType.VARCHAR),
        @Result(column="solt", property="solt", jdbcType=JdbcType.VARCHAR),
        @Result(column="signature", property="signature", jdbcType=JdbcType.VARCHAR),
        @Result(column="create_time", property="createTime", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="datetime_lastchange", property="datetimeLastchange", jdbcType=JdbcType.TIMESTAMP)
    })
    List<SwUser> selectByExampleWithRowbounds(SwUserExample example, RowBounds rowBounds);

    @SelectProvider(type=SwUserSqlProvider.class, method="selectByExample")
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="employee_no", property="employeeNo", jdbcType=JdbcType.VARCHAR),
        @Result(column="password", property="password", jdbcType=JdbcType.VARCHAR),
        @Result(column="solt", property="solt", jdbcType=JdbcType.VARCHAR),
        @Result(column="signature", property="signature", jdbcType=JdbcType.VARCHAR),
        @Result(column="create_time", property="createTime", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="datetime_lastchange", property="datetimeLastchange", jdbcType=JdbcType.TIMESTAMP)
    })
    List<SwUser> selectByExample(SwUserExample example);

    @Select({
        "select",
        "id, employee_no, password, solt, signature, create_time, datetime_lastchange",
        "from sw_user",
        "where id = #{id,jdbcType=INTEGER}"
    })
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="employee_no", property="employeeNo", jdbcType=JdbcType.VARCHAR),
        @Result(column="password", property="password", jdbcType=JdbcType.VARCHAR),
        @Result(column="solt", property="solt", jdbcType=JdbcType.VARCHAR),
        @Result(column="signature", property="signature", jdbcType=JdbcType.VARCHAR),
        @Result(column="create_time", property="createTime", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="datetime_lastchange", property="datetimeLastchange", jdbcType=JdbcType.TIMESTAMP)
    })
    SwUser selectByPrimaryKey(Integer id);

    @UpdateProvider(type=SwUserSqlProvider.class, method="updateByExampleSelective")
    int updateByExampleSelective(@Param("record") SwUser record, @Param("example") SwUserExample example);

    @UpdateProvider(type=SwUserSqlProvider.class, method="updateByExample")
    int updateByExample(@Param("record") SwUser record, @Param("example") SwUserExample example);

    @UpdateProvider(type=SwUserSqlProvider.class, method="updateByPrimaryKeySelective")
    int updateByPrimaryKeySelective(SwUser record);

    @Update({
        "update sw_user",
        "set employee_no = #{employeeNo,jdbcType=VARCHAR},",
          "password = #{password,jdbcType=VARCHAR},",
          "solt = #{solt,jdbcType=VARCHAR},",
          "signature = #{signature,jdbcType=VARCHAR},",
          "create_time = #{createTime,jdbcType=TIMESTAMP},",
          "datetime_lastchange = #{datetimeLastchange,jdbcType=TIMESTAMP}",
        "where id = #{id,jdbcType=INTEGER}"
    })
    int updateByPrimaryKey(SwUser record);
}