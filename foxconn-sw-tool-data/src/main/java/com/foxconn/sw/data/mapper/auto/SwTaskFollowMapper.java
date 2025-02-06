package com.foxconn.sw.data.mapper.auto;

import com.foxconn.sw.data.entity.SwTaskFollow;
import com.foxconn.sw.data.entity.SwTaskFollowExample;
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
public interface SwTaskFollowMapper {
    long countByExample(SwTaskFollowExample example);

    int deleteByExample(SwTaskFollowExample example);

    @Delete({
        "delete from sw_task_follow",
        "where id = #{id,jdbcType=INTEGER}"
    })
    int deleteByPrimaryKey(Integer id);

    @Insert({
        "insert into sw_task_follow (task_id, content, ",
        "status, operator, ",
        "create_time, datetime_lastchange)",
        "values (#{taskId,jdbcType=INTEGER}, #{content,jdbcType=VARCHAR}, ",
        "#{status,jdbcType=INTEGER}, #{operator,jdbcType=VARCHAR}, ",
        "#{createTime,jdbcType=TIMESTAMP}, #{datetimeLastchange,jdbcType=TIMESTAMP})"
    })
    @SelectKey(statement="SELECT LAST_INSERT_ID()", keyProperty="id", before=false, resultType=Integer.class)
    int insert(SwTaskFollow record);

    int insertSelective(SwTaskFollow record);

    List<SwTaskFollow> selectByExampleWithRowbounds(SwTaskFollowExample example, RowBounds rowBounds);

    List<SwTaskFollow> selectByExample(SwTaskFollowExample example);

    @Select({
        "select",
        "id, task_id, content, status, operator, create_time, datetime_lastchange",
        "from sw_task_follow",
        "where id = #{id,jdbcType=INTEGER}"
    })
    @ResultMap("com.foxconn.sw.data.mapper.auto.SwTaskFollowMapper.BaseResultMap")
    SwTaskFollow selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") SwTaskFollow record, @Param("example") SwTaskFollowExample example);

    int updateByExample(@Param("record") SwTaskFollow record, @Param("example") SwTaskFollowExample example);

    int updateByPrimaryKeySelective(SwTaskFollow record);

    @Update({
        "update sw_task_follow",
        "set task_id = #{taskId,jdbcType=INTEGER},",
          "content = #{content,jdbcType=VARCHAR},",
          "status = #{status,jdbcType=INTEGER},",
          "operator = #{operator,jdbcType=VARCHAR},",
          "create_time = #{createTime,jdbcType=TIMESTAMP},",
          "datetime_lastchange = #{datetimeLastchange,jdbcType=TIMESTAMP}",
        "where id = #{id,jdbcType=INTEGER}"
    })
    int updateByPrimaryKey(SwTaskFollow record);
}