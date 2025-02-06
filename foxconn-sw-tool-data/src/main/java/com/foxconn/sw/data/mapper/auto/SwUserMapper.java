package com.foxconn.sw.data.mapper.auto;

import com.foxconn.sw.data.entity.SwUser;
import com.foxconn.sw.data.entity.SwUserExample;
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
public interface SwUserMapper {
    int deleteByExample(SwUserExample example);

    @Delete({
        "delete from sw_user",
        "where id = #{id,jdbcType=INTEGER}"
    })
    int deleteByPrimaryKey(Integer id);

    @Insert({
        "insert into sw_user (employee_no, password, ",
        "solt, password_bak, ",
        "avatar_id, signature, ",
        "create_time, datetime_lastchange)",
        "values (#{employeeNo,jdbcType=VARCHAR}, #{password,jdbcType=VARCHAR}, ",
        "#{solt,jdbcType=VARCHAR}, #{passwordBak,jdbcType=VARCHAR}, ",
        "#{avatarId,jdbcType=INTEGER}, #{signature,jdbcType=VARCHAR}, ",
        "#{createTime,jdbcType=TIMESTAMP}, #{datetimeLastchange,jdbcType=TIMESTAMP})"
    })
    @SelectKey(statement="SELECT LAST_INSERT_ID()", keyProperty="id", before=false, resultType=Integer.class)
    int insert(SwUser record);

    int insertSelective(SwUser record);

    List<SwUser> selectByExampleWithRowbounds(SwUserExample example, RowBounds rowBounds);

    List<SwUser> selectByExample(SwUserExample example);

    @Select({
        "select",
        "id, employee_no, password, solt, password_bak, avatar_id, signature, create_time, ",
        "datetime_lastchange",
        "from sw_user",
        "where id = #{id,jdbcType=INTEGER}"
    })
    @ResultMap("com.foxconn.sw.data.mapper.auto.SwUserMapper.BaseResultMap")
    SwUser selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") SwUser record, @Param("example") SwUserExample example);

    int updateByExample(@Param("record") SwUser record, @Param("example") SwUserExample example);

    int updateByPrimaryKeySelective(SwUser record);

    @Update({
        "update sw_user",
        "set employee_no = #{employeeNo,jdbcType=VARCHAR},",
          "password = #{password,jdbcType=VARCHAR},",
          "solt = #{solt,jdbcType=VARCHAR},",
          "password_bak = #{passwordBak,jdbcType=VARCHAR},",
          "avatar_id = #{avatarId,jdbcType=INTEGER},",
          "signature = #{signature,jdbcType=VARCHAR},",
          "create_time = #{createTime,jdbcType=TIMESTAMP},",
          "datetime_lastchange = #{datetimeLastchange,jdbcType=TIMESTAMP}",
        "where id = #{id,jdbcType=INTEGER}"
    })
    int updateByPrimaryKey(SwUser record);
}