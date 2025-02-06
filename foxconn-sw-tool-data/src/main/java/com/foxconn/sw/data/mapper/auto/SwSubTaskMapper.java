package com.foxconn.sw.data.mapper.auto;

import com.foxconn.sw.data.entity.SwSubTask;
import com.foxconn.sw.data.entity.SwSubTaskExample;
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
public interface SwSubTaskMapper {
    long countByExample(SwSubTaskExample example);

    int deleteByExample(SwSubTaskExample example);

    @Delete({
        "delete from sw_sub_task",
        "where id = #{id,jdbcType=INTEGER}"
    })
    int deleteByPrimaryKey(Integer id);

    @Insert({
        "insert into sw_sub_task (task_id, task_no, ",
        "title, level, status, ",
        "handle_no, dead_line, ",
        "create_time, datetime_lastchange)",
        "values (#{taskId,jdbcType=INTEGER}, #{taskNo,jdbcType=BIGINT}, ",
        "#{title,jdbcType=VARCHAR}, #{level,jdbcType=VARCHAR}, #{status,jdbcType=INTEGER}, ",
        "#{handleNo,jdbcType=VARCHAR}, #{deadLine,jdbcType=VARCHAR}, ",
        "#{createTime,jdbcType=TIMESTAMP}, #{datetimeLastchange,jdbcType=TIMESTAMP})"
    })
    @SelectKey(statement="SELECT LAST_INSERT_ID()", keyProperty="id", before=false, resultType=Integer.class)
    int insert(SwSubTask record);

    int insertSelective(SwSubTask record);

    List<SwSubTask> selectByExampleWithRowbounds(SwSubTaskExample example, RowBounds rowBounds);

    List<SwSubTask> selectByExample(SwSubTaskExample example);

    @Select({
        "select",
        "id, task_id, task_no, title, level, status, handle_no, dead_line, create_time, ",
        "datetime_lastchange",
        "from sw_sub_task",
        "where id = #{id,jdbcType=INTEGER}"
    })
    @ResultMap("com.foxconn.sw.data.mapper.auto.SwSubTaskMapper.BaseResultMap")
    SwSubTask selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") SwSubTask record, @Param("example") SwSubTaskExample example);

    int updateByExample(@Param("record") SwSubTask record, @Param("example") SwSubTaskExample example);

    int updateByPrimaryKeySelective(SwSubTask record);

    @Update({
        "update sw_sub_task",
        "set task_id = #{taskId,jdbcType=INTEGER},",
          "task_no = #{taskNo,jdbcType=BIGINT},",
          "title = #{title,jdbcType=VARCHAR},",
          "level = #{level,jdbcType=VARCHAR},",
          "status = #{status,jdbcType=INTEGER},",
          "handle_no = #{handleNo,jdbcType=VARCHAR},",
          "dead_line = #{deadLine,jdbcType=VARCHAR},",
          "create_time = #{createTime,jdbcType=TIMESTAMP},",
          "datetime_lastchange = #{datetimeLastchange,jdbcType=TIMESTAMP}",
        "where id = #{id,jdbcType=INTEGER}"
    })
    int updateByPrimaryKey(SwSubTask record);
}