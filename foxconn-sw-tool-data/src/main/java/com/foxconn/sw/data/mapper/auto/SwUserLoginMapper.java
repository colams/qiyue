package com.foxconn.sw.data.mapper.auto;

import com.foxconn.sw.data.entity.SwUserLogin;
import com.foxconn.sw.data.entity.SwUserLoginExample;
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
public interface SwUserLoginMapper {
    int deleteByExample(SwUserLoginExample example);

    @Delete({
        "delete from sw_user_login",
        "where id = #{id,jdbcType=INTEGER}"
    })
    int deleteByPrimaryKey(Integer id);

    @Insert({
        "insert into sw_user_login (employee_no, token, ",
        "expire_time, ip, ",
        "create_time, datetime_lastchange)",
        "values (#{employeeNo,jdbcType=VARCHAR}, #{token,jdbcType=VARCHAR}, ",
        "#{expireTime,jdbcType=TIMESTAMP}, #{ip,jdbcType=VARCHAR}, ",
        "#{createTime,jdbcType=TIMESTAMP}, #{datetimeLastchange,jdbcType=TIMESTAMP})"
    })
    @SelectKey(statement="SELECT LAST_INSERT_ID()", keyProperty="id", before=false, resultType=Integer.class)
    int insert(SwUserLogin record);

    int insertSelective(SwUserLogin record);

    List<SwUserLogin> selectByExampleWithRowbounds(SwUserLoginExample example, RowBounds rowBounds);

    List<SwUserLogin> selectByExample(SwUserLoginExample example);

    @Select({
        "select",
        "id, employee_no, token, expire_time, ip, create_time, datetime_lastchange",
        "from sw_user_login",
        "where id = #{id,jdbcType=INTEGER}"
    })
    @ResultMap("com.foxconn.sw.data.mapper.auto.SwUserLoginMapper.BaseResultMap")
    SwUserLogin selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") SwUserLogin record, @Param("example") SwUserLoginExample example);

    int updateByExample(@Param("record") SwUserLogin record, @Param("example") SwUserLoginExample example);

    int updateByPrimaryKeySelective(SwUserLogin record);

    @Update({
        "update sw_user_login",
        "set employee_no = #{employeeNo,jdbcType=VARCHAR},",
          "token = #{token,jdbcType=VARCHAR},",
          "expire_time = #{expireTime,jdbcType=TIMESTAMP},",
          "ip = #{ip,jdbcType=VARCHAR},",
          "create_time = #{createTime,jdbcType=TIMESTAMP},",
          "datetime_lastchange = #{datetimeLastchange,jdbcType=TIMESTAMP}",
        "where id = #{id,jdbcType=INTEGER}"
    })
    int updateByPrimaryKey(SwUserLogin record);
}