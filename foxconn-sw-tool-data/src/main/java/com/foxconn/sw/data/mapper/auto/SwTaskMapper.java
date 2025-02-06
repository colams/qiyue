package com.foxconn.sw.data.mapper.auto;

import com.foxconn.sw.data.entity.SwTask;
import com.foxconn.sw.data.entity.SwTaskExample;
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
public interface SwTaskMapper {
    long countByExample(SwTaskExample example);

    int deleteByExample(SwTaskExample example);

    @Delete({
        "delete from sw_task",
        "where id = #{id,jdbcType=INTEGER}"
    })
    int deleteByPrimaryKey(Integer id);

    @Insert({
        "insert into sw_task (task_no, top_category, ",
        "category, title, ",
        "top_project, project, ",
        "level, progress_percent, ",
        "status, reject_status, ",
        "proposer_eid, manager_eid, ",
        "handle_eid, dead_line, ",
        "reflection, parent_id, ",
        "finish_time, create_time, ",
        "datetime_lastchange, description)",
        "values (#{taskNo,jdbcType=BIGINT}, #{topCategory,jdbcType=VARCHAR}, ",
        "#{category,jdbcType=VARCHAR}, #{title,jdbcType=VARCHAR}, ",
        "#{topProject,jdbcType=VARCHAR}, #{project,jdbcType=VARCHAR}, ",
        "#{level,jdbcType=INTEGER}, #{progressPercent,jdbcType=INTEGER}, ",
        "#{status,jdbcType=INTEGER}, #{rejectStatus,jdbcType=INTEGER}, ",
        "#{proposerEid,jdbcType=VARCHAR}, #{managerEid,jdbcType=VARCHAR}, ",
        "#{handleEid,jdbcType=VARCHAR}, #{deadLine,jdbcType=VARCHAR}, ",
        "#{reflection,jdbcType=VARCHAR}, #{parentId,jdbcType=INTEGER}, ",
        "#{finishTime,jdbcType=VARCHAR}, #{createTime,jdbcType=TIMESTAMP}, ",
        "#{datetimeLastchange,jdbcType=TIMESTAMP}, #{description,jdbcType=LONGVARCHAR})"
    })
    @SelectKey(statement="SELECT LAST_INSERT_ID()", keyProperty="id", before=false, resultType=Integer.class)
    int insert(SwTask record);

    int insertSelective(SwTask record);

    List<SwTask> selectByExampleWithBLOBsWithRowbounds(SwTaskExample example, RowBounds rowBounds);

    List<SwTask> selectByExampleWithBLOBs(SwTaskExample example);

    List<SwTask> selectByExampleWithRowbounds(SwTaskExample example, RowBounds rowBounds);

    List<SwTask> selectByExample(SwTaskExample example);

    @Select({
        "select",
        "id, task_no, top_category, category, title, top_project, project, level, progress_percent, ",
        "status, reject_status, proposer_eid, manager_eid, handle_eid, dead_line, reflection, ",
        "parent_id, finish_time, create_time, datetime_lastchange, description",
        "from sw_task",
        "where id = #{id,jdbcType=INTEGER}"
    })
    @ResultMap("com.foxconn.sw.data.mapper.auto.SwTaskMapper.ResultMapWithBLOBs")
    SwTask selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") SwTask record, @Param("example") SwTaskExample example);

    int updateByExampleWithBLOBs(@Param("record") SwTask record, @Param("example") SwTaskExample example);

    int updateByExample(@Param("record") SwTask record, @Param("example") SwTaskExample example);

    int updateByPrimaryKeySelective(SwTask record);

    @Update({
        "update sw_task",
        "set task_no = #{taskNo,jdbcType=BIGINT},",
          "top_category = #{topCategory,jdbcType=VARCHAR},",
          "category = #{category,jdbcType=VARCHAR},",
          "title = #{title,jdbcType=VARCHAR},",
          "top_project = #{topProject,jdbcType=VARCHAR},",
          "project = #{project,jdbcType=VARCHAR},",
          "level = #{level,jdbcType=INTEGER},",
          "progress_percent = #{progressPercent,jdbcType=INTEGER},",
          "status = #{status,jdbcType=INTEGER},",
          "reject_status = #{rejectStatus,jdbcType=INTEGER},",
          "proposer_eid = #{proposerEid,jdbcType=VARCHAR},",
          "manager_eid = #{managerEid,jdbcType=VARCHAR},",
          "handle_eid = #{handleEid,jdbcType=VARCHAR},",
          "dead_line = #{deadLine,jdbcType=VARCHAR},",
          "reflection = #{reflection,jdbcType=VARCHAR},",
          "parent_id = #{parentId,jdbcType=INTEGER},",
          "finish_time = #{finishTime,jdbcType=VARCHAR},",
          "create_time = #{createTime,jdbcType=TIMESTAMP},",
          "datetime_lastchange = #{datetimeLastchange,jdbcType=TIMESTAMP},",
          "description = #{description,jdbcType=LONGVARCHAR}",
        "where id = #{id,jdbcType=INTEGER}"
    })
    int updateByPrimaryKeyWithBLOBs(SwTask record);

    @Update({
        "update sw_task",
        "set task_no = #{taskNo,jdbcType=BIGINT},",
          "top_category = #{topCategory,jdbcType=VARCHAR},",
          "category = #{category,jdbcType=VARCHAR},",
          "title = #{title,jdbcType=VARCHAR},",
          "top_project = #{topProject,jdbcType=VARCHAR},",
          "project = #{project,jdbcType=VARCHAR},",
          "level = #{level,jdbcType=INTEGER},",
          "progress_percent = #{progressPercent,jdbcType=INTEGER},",
          "status = #{status,jdbcType=INTEGER},",
          "reject_status = #{rejectStatus,jdbcType=INTEGER},",
          "proposer_eid = #{proposerEid,jdbcType=VARCHAR},",
          "manager_eid = #{managerEid,jdbcType=VARCHAR},",
          "handle_eid = #{handleEid,jdbcType=VARCHAR},",
          "dead_line = #{deadLine,jdbcType=VARCHAR},",
          "reflection = #{reflection,jdbcType=VARCHAR},",
          "parent_id = #{parentId,jdbcType=INTEGER},",
          "finish_time = #{finishTime,jdbcType=VARCHAR},",
          "create_time = #{createTime,jdbcType=TIMESTAMP},",
          "datetime_lastchange = #{datetimeLastchange,jdbcType=TIMESTAMP}",
        "where id = #{id,jdbcType=INTEGER}"
    })
    int updateByPrimaryKey(SwTask record);
}