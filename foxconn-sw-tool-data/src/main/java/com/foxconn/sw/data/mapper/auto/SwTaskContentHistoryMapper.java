package com.foxconn.sw.data.mapper.auto;

import com.foxconn.sw.data.entity.SwTaskContentHistory;
import com.foxconn.sw.data.entity.SwTaskContentHistoryExample;
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
public interface SwTaskContentHistoryMapper {
    int deleteByExample(SwTaskContentHistoryExample example);

    @Delete({
        "delete from sw_task_content_history",
        "where id = #{id,jdbcType=INTEGER}"
    })
    int deleteByPrimaryKey(Integer id);

    @Insert({
        "insert into sw_task_content_history (task_id, progress_id, ",
        "operator, datetime_lastchange, ",
        "old_content, new_content)",
        "values (#{taskId,jdbcType=INTEGER}, #{progressId,jdbcType=INTEGER}, ",
        "#{operator,jdbcType=VARCHAR}, #{datetimeLastchange,jdbcType=TIMESTAMP}, ",
        "#{oldContent,jdbcType=LONGVARCHAR}, #{newContent,jdbcType=LONGVARCHAR})"
    })
    @SelectKey(statement="SELECT LAST_INSERT_ID()", keyProperty="id", before=false, resultType=Integer.class)
    int insert(SwTaskContentHistory record);

    int insertSelective(SwTaskContentHistory record);

    List<SwTaskContentHistory> selectByExampleWithBLOBsWithRowbounds(SwTaskContentHistoryExample example, RowBounds rowBounds);

    List<SwTaskContentHistory> selectByExampleWithBLOBs(SwTaskContentHistoryExample example);

    List<SwTaskContentHistory> selectByExampleWithRowbounds(SwTaskContentHistoryExample example, RowBounds rowBounds);

    List<SwTaskContentHistory> selectByExample(SwTaskContentHistoryExample example);

    @Select({
        "select",
        "id, task_id, progress_id, operator, datetime_lastchange, old_content, new_content",
        "from sw_task_content_history",
        "where id = #{id,jdbcType=INTEGER}"
    })
    @ResultMap("com.foxconn.sw.data.mapper.auto.SwTaskContentHistoryMapper.ResultMapWithBLOBs")
    SwTaskContentHistory selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") SwTaskContentHistory record, @Param("example") SwTaskContentHistoryExample example);

    int updateByExampleWithBLOBs(@Param("record") SwTaskContentHistory record, @Param("example") SwTaskContentHistoryExample example);

    int updateByExample(@Param("record") SwTaskContentHistory record, @Param("example") SwTaskContentHistoryExample example);

    int updateByPrimaryKeySelective(SwTaskContentHistory record);

    @Update({
        "update sw_task_content_history",
        "set task_id = #{taskId,jdbcType=INTEGER},",
          "progress_id = #{progressId,jdbcType=INTEGER},",
          "operator = #{operator,jdbcType=VARCHAR},",
          "datetime_lastchange = #{datetimeLastchange,jdbcType=TIMESTAMP},",
          "old_content = #{oldContent,jdbcType=LONGVARCHAR},",
          "new_content = #{newContent,jdbcType=LONGVARCHAR}",
        "where id = #{id,jdbcType=INTEGER}"
    })
    int updateByPrimaryKeyWithBLOBs(SwTaskContentHistory record);

    @Update({
        "update sw_task_content_history",
        "set task_id = #{taskId,jdbcType=INTEGER},",
          "progress_id = #{progressId,jdbcType=INTEGER},",
          "operator = #{operator,jdbcType=VARCHAR},",
          "datetime_lastchange = #{datetimeLastchange,jdbcType=TIMESTAMP}",
        "where id = #{id,jdbcType=INTEGER}"
    })
    int updateByPrimaryKey(SwTaskContentHistory record);
}