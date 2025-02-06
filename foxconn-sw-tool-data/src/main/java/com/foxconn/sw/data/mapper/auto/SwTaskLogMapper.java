package com.foxconn.sw.data.mapper.auto;

import com.foxconn.sw.data.entity.SwTaskLog;
import com.foxconn.sw.data.entity.SwTaskLogExample;
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
public interface SwTaskLogMapper {
    long countByExample(SwTaskLogExample example);

    int deleteByExample(SwTaskLogExample example);

    @Delete({
        "delete from sw_task_log",
        "where id = #{id,jdbcType=INTEGER}"
    })
    int deleteByPrimaryKey(Integer id);

    @Insert({
        "insert into sw_task_log (task_id, operator, ",
        "content, datetime_lastchange)",
        "values (#{taskId,jdbcType=INTEGER}, #{operator,jdbcType=VARCHAR}, ",
        "#{content,jdbcType=VARCHAR}, #{datetimeLastchange,jdbcType=TIMESTAMP})"
    })
    @SelectKey(statement="SELECT LAST_INSERT_ID()", keyProperty="id", before=false, resultType=Integer.class)
    int insert(SwTaskLog record);

    int insertSelective(SwTaskLog record);

    List<SwTaskLog> selectByExampleWithRowbounds(SwTaskLogExample example, RowBounds rowBounds);

    List<SwTaskLog> selectByExample(SwTaskLogExample example);

    @Select({
        "select",
        "id, task_id, operator, content, datetime_lastchange",
        "from sw_task_log",
        "where id = #{id,jdbcType=INTEGER}"
    })
    @ResultMap("com.foxconn.sw.data.mapper.auto.SwTaskLogMapper.BaseResultMap")
    SwTaskLog selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") SwTaskLog record, @Param("example") SwTaskLogExample example);

    int updateByExample(@Param("record") SwTaskLog record, @Param("example") SwTaskLogExample example);

    int updateByPrimaryKeySelective(SwTaskLog record);

    @Update({
        "update sw_task_log",
        "set task_id = #{taskId,jdbcType=INTEGER},",
          "operator = #{operator,jdbcType=VARCHAR},",
          "content = #{content,jdbcType=VARCHAR},",
          "datetime_lastchange = #{datetimeLastchange,jdbcType=TIMESTAMP}",
        "where id = #{id,jdbcType=INTEGER}"
    })
    int updateByPrimaryKey(SwTaskLog record);
}