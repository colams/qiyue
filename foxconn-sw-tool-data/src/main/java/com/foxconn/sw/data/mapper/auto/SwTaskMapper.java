package com.foxconn.sw.data.mapper.auto;

import com.foxconn.sw.data.entity.SwTask;
import com.foxconn.sw.data.entity.SwTaskExample;
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
public interface SwTaskMapper {
    @SelectProvider(type=SwTaskSqlProvider.class, method="countByExample")
    long countByExample(SwTaskExample example);

    @DeleteProvider(type=SwTaskSqlProvider.class, method="deleteByExample")
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
            "resource_ids, start_date, ",
            "end_date, reflection, ",
            "create_time, datetime_lastchange, ",
            "description)",
            "values (#{taskNo,jdbcType=BIGINT}, #{topCategory,jdbcType=VARCHAR}, ",
            "#{category,jdbcType=VARCHAR}, #{title,jdbcType=VARCHAR}, ",
            "#{topProject,jdbcType=VARCHAR}, #{project,jdbcType=VARCHAR}, ",
            "#{level,jdbcType=VARCHAR}, #{progressPercent,jdbcType=INTEGER}, ",
            "#{status,jdbcType=INTEGER}, #{rejectStatus,jdbcType=INTEGER}, ",
            "#{proposerEid,jdbcType=VARCHAR}, #{managerEid,jdbcType=VARCHAR}, ",
            "#{handleEid,jdbcType=VARCHAR}, #{deadLine,jdbcType=VARCHAR}, ",
            "#{resourceIds,jdbcType=VARCHAR}, #{startDate,jdbcType=VARCHAR}, ",
            "#{endDate,jdbcType=VARCHAR}, #{reflection,jdbcType=VARCHAR}, ",
            "#{createTime,jdbcType=TIMESTAMP}, #{datetimeLastchange,jdbcType=TIMESTAMP}, ",
            "#{description,jdbcType=LONGVARCHAR})"
    })
    @SelectKey(statement = "SELECT LAST_INSERT_ID()", keyProperty = "id", before = false, resultType = Integer.class)
    int insert(SwTask record);

    @InsertProvider(type = SwTaskSqlProvider.class, method = "insertSelective")
    @SelectKey(statement = "SELECT LAST_INSERT_ID()", keyProperty = "id", before = false, resultType = Integer.class)
    int insertSelective(SwTask record);

    @SelectProvider(type = SwTaskSqlProvider.class, method = "selectByExampleWithBLOBs")
    @Results({
            @Result(column = "id", property = "id", jdbcType = JdbcType.INTEGER, id = true),
            @Result(column = "task_no", property = "taskNo", jdbcType = JdbcType.BIGINT),
            @Result(column = "top_category", property = "topCategory", jdbcType = JdbcType.VARCHAR),
            @Result(column = "category", property = "category", jdbcType = JdbcType.VARCHAR),
            @Result(column = "title", property = "title", jdbcType = JdbcType.VARCHAR),
            @Result(column = "top_project", property = "topProject", jdbcType = JdbcType.VARCHAR),
            @Result(column = "project", property = "project", jdbcType = JdbcType.VARCHAR),
            @Result(column = "level", property = "level", jdbcType = JdbcType.VARCHAR),
            @Result(column = "progress_percent", property = "progressPercent", jdbcType = JdbcType.INTEGER),
            @Result(column = "status", property = "status", jdbcType = JdbcType.INTEGER),
            @Result(column = "reject_status", property = "rejectStatus", jdbcType = JdbcType.INTEGER),
            @Result(column = "proposer_eid", property = "proposerEid", jdbcType = JdbcType.VARCHAR),
            @Result(column = "manager_eid", property = "managerEid", jdbcType = JdbcType.VARCHAR),
            @Result(column = "handle_eid", property = "handleEid", jdbcType = JdbcType.VARCHAR),
            @Result(column = "dead_line", property = "deadLine", jdbcType = JdbcType.VARCHAR),
            @Result(column = "resource_ids", property = "resourceIds", jdbcType = JdbcType.VARCHAR),
            @Result(column = "start_date", property = "startDate", jdbcType = JdbcType.VARCHAR),
            @Result(column = "end_date", property = "endDate", jdbcType = JdbcType.VARCHAR),
            @Result(column = "reflection", property = "reflection", jdbcType = JdbcType.VARCHAR),
            @Result(column = "create_time", property = "createTime", jdbcType = JdbcType.TIMESTAMP),
            @Result(column = "datetime_lastchange", property = "datetimeLastchange", jdbcType = JdbcType.TIMESTAMP),
            @Result(column = "description", property = "description", jdbcType = JdbcType.LONGVARCHAR)
    })
    List<SwTask> selectByExampleWithBLOBsWithRowbounds(SwTaskExample example, RowBounds rowBounds);

    @SelectProvider(type = SwTaskSqlProvider.class, method = "selectByExampleWithBLOBs")
    @Results({
            @Result(column = "id", property = "id", jdbcType = JdbcType.INTEGER, id = true),
            @Result(column = "task_no", property = "taskNo", jdbcType = JdbcType.BIGINT),
            @Result(column = "top_category", property = "topCategory", jdbcType = JdbcType.VARCHAR),
            @Result(column = "category", property = "category", jdbcType = JdbcType.VARCHAR),
            @Result(column = "title", property = "title", jdbcType = JdbcType.VARCHAR),
            @Result(column = "top_project", property = "topProject", jdbcType = JdbcType.VARCHAR),
            @Result(column = "project", property = "project", jdbcType = JdbcType.VARCHAR),
            @Result(column = "level", property = "level", jdbcType = JdbcType.VARCHAR),
            @Result(column = "progress_percent", property = "progressPercent", jdbcType = JdbcType.INTEGER),
            @Result(column = "status", property = "status", jdbcType = JdbcType.INTEGER),
            @Result(column = "reject_status", property = "rejectStatus", jdbcType = JdbcType.INTEGER),
            @Result(column = "proposer_eid", property = "proposerEid", jdbcType = JdbcType.VARCHAR),
            @Result(column = "manager_eid", property = "managerEid", jdbcType = JdbcType.VARCHAR),
            @Result(column = "handle_eid", property = "handleEid", jdbcType = JdbcType.VARCHAR),
            @Result(column = "dead_line", property = "deadLine", jdbcType = JdbcType.VARCHAR),
            @Result(column = "resource_ids", property = "resourceIds", jdbcType = JdbcType.VARCHAR),
            @Result(column = "start_date", property = "startDate", jdbcType = JdbcType.VARCHAR),
            @Result(column = "end_date", property = "endDate", jdbcType = JdbcType.VARCHAR),
            @Result(column = "reflection", property = "reflection", jdbcType = JdbcType.VARCHAR),
            @Result(column = "create_time", property = "createTime", jdbcType = JdbcType.TIMESTAMP),
            @Result(column = "datetime_lastchange", property = "datetimeLastchange", jdbcType = JdbcType.TIMESTAMP),
            @Result(column = "description", property = "description", jdbcType = JdbcType.LONGVARCHAR)
    })
    List<SwTask> selectByExampleWithBLOBs(SwTaskExample example);

    @SelectProvider(type = SwTaskSqlProvider.class, method = "selectByExample")
    @Results({
            @Result(column = "id", property = "id", jdbcType = JdbcType.INTEGER, id = true),
            @Result(column = "task_no", property = "taskNo", jdbcType = JdbcType.BIGINT),
            @Result(column = "top_category", property = "topCategory", jdbcType = JdbcType.VARCHAR),
            @Result(column = "category", property = "category", jdbcType = JdbcType.VARCHAR),
            @Result(column = "title", property = "title", jdbcType = JdbcType.VARCHAR),
            @Result(column = "top_project", property = "topProject", jdbcType = JdbcType.VARCHAR),
            @Result(column = "project", property = "project", jdbcType = JdbcType.VARCHAR),
            @Result(column = "level", property = "level", jdbcType = JdbcType.VARCHAR),
            @Result(column = "progress_percent", property = "progressPercent", jdbcType = JdbcType.INTEGER),
            @Result(column = "status", property = "status", jdbcType = JdbcType.INTEGER),
            @Result(column = "reject_status", property = "rejectStatus", jdbcType = JdbcType.INTEGER),
            @Result(column = "proposer_eid", property = "proposerEid", jdbcType = JdbcType.VARCHAR),
            @Result(column = "manager_eid", property = "managerEid", jdbcType = JdbcType.VARCHAR),
            @Result(column = "handle_eid", property = "handleEid", jdbcType = JdbcType.VARCHAR),
            @Result(column = "dead_line", property = "deadLine", jdbcType = JdbcType.VARCHAR),
            @Result(column = "resource_ids", property = "resourceIds", jdbcType = JdbcType.VARCHAR),
            @Result(column = "start_date", property = "startDate", jdbcType = JdbcType.VARCHAR),
        @Result(column="end_date", property="endDate", jdbcType=JdbcType.VARCHAR),
        @Result(column="reflection", property="reflection", jdbcType=JdbcType.VARCHAR),
        @Result(column="create_time", property="createTime", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="datetime_lastchange", property="datetimeLastchange", jdbcType=JdbcType.TIMESTAMP)
    })
    List<SwTask> selectByExampleWithRowbounds(SwTaskExample example, RowBounds rowBounds);

    @SelectProvider(type=SwTaskSqlProvider.class, method="selectByExample")
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="task_no", property="taskNo", jdbcType=JdbcType.BIGINT),
        @Result(column="top_category", property="topCategory", jdbcType=JdbcType.VARCHAR),
        @Result(column="category", property="category", jdbcType=JdbcType.VARCHAR),
        @Result(column="title", property="title", jdbcType=JdbcType.VARCHAR),
        @Result(column="top_project", property="topProject", jdbcType=JdbcType.VARCHAR),
        @Result(column="project", property="project", jdbcType=JdbcType.VARCHAR),
        @Result(column="level", property="level", jdbcType=JdbcType.VARCHAR),
        @Result(column="progress_percent", property="progressPercent", jdbcType=JdbcType.INTEGER),
        @Result(column="status", property="status", jdbcType=JdbcType.INTEGER),
        @Result(column="reject_status", property="rejectStatus", jdbcType=JdbcType.INTEGER),
        @Result(column="proposer_eid", property="proposerEid", jdbcType=JdbcType.VARCHAR),
        @Result(column="manager_eid", property="managerEid", jdbcType=JdbcType.VARCHAR),
        @Result(column="handle_eid", property="handleEid", jdbcType=JdbcType.VARCHAR),
        @Result(column="dead_line", property="deadLine", jdbcType=JdbcType.VARCHAR),
        @Result(column="resource_ids", property="resourceIds", jdbcType=JdbcType.VARCHAR),
        @Result(column="start_date", property="startDate", jdbcType=JdbcType.VARCHAR),
        @Result(column="end_date", property="endDate", jdbcType=JdbcType.VARCHAR),
        @Result(column="reflection", property="reflection", jdbcType=JdbcType.VARCHAR),
        @Result(column="create_time", property="createTime", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="datetime_lastchange", property="datetimeLastchange", jdbcType=JdbcType.TIMESTAMP)
    })
    List<SwTask> selectByExample(SwTaskExample example);

    @Select({
            "select",
            "id, task_no, top_category, category, title, top_project, project, level, progress_percent, ",
            "status, reject_status, proposer_eid, manager_eid, handle_eid, dead_line, resource_ids, ",
            "start_date, end_date, reflection, create_time, datetime_lastchange, description",
            "from sw_task",
            "where id = #{id,jdbcType=INTEGER}"
    })
    @Results({
            @Result(column = "id", property = "id", jdbcType = JdbcType.INTEGER, id = true),
            @Result(column = "task_no", property = "taskNo", jdbcType = JdbcType.BIGINT),
            @Result(column = "top_category", property = "topCategory", jdbcType = JdbcType.VARCHAR),
            @Result(column = "category", property = "category", jdbcType = JdbcType.VARCHAR),
            @Result(column = "title", property = "title", jdbcType = JdbcType.VARCHAR),
            @Result(column = "top_project", property = "topProject", jdbcType = JdbcType.VARCHAR),
            @Result(column = "project", property = "project", jdbcType = JdbcType.VARCHAR),
            @Result(column = "level", property = "level", jdbcType = JdbcType.VARCHAR),
            @Result(column = "progress_percent", property = "progressPercent", jdbcType = JdbcType.INTEGER),
            @Result(column = "status", property = "status", jdbcType = JdbcType.INTEGER),
            @Result(column = "reject_status", property = "rejectStatus", jdbcType = JdbcType.INTEGER),
            @Result(column = "proposer_eid", property = "proposerEid", jdbcType = JdbcType.VARCHAR),
            @Result(column = "manager_eid", property = "managerEid", jdbcType = JdbcType.VARCHAR),
            @Result(column = "handle_eid", property = "handleEid", jdbcType = JdbcType.VARCHAR),
            @Result(column = "dead_line", property = "deadLine", jdbcType = JdbcType.VARCHAR),
            @Result(column = "resource_ids", property = "resourceIds", jdbcType = JdbcType.VARCHAR),
            @Result(column = "start_date", property = "startDate", jdbcType = JdbcType.VARCHAR),
            @Result(column = "end_date", property = "endDate", jdbcType = JdbcType.VARCHAR),
            @Result(column = "reflection", property = "reflection", jdbcType = JdbcType.VARCHAR),
            @Result(column = "create_time", property = "createTime", jdbcType = JdbcType.TIMESTAMP),
            @Result(column = "datetime_lastchange", property = "datetimeLastchange", jdbcType = JdbcType.TIMESTAMP),
            @Result(column = "description", property = "description", jdbcType = JdbcType.LONGVARCHAR)
    })
    SwTask selectByPrimaryKey(Integer id);

    @UpdateProvider(type = SwTaskSqlProvider.class, method = "updateByExampleSelective")
    int updateByExampleSelective(@Param("record") SwTask record, @Param("example") SwTaskExample example);

    @UpdateProvider(type = SwTaskSqlProvider.class, method = "updateByExampleWithBLOBs")
    int updateByExampleWithBLOBs(@Param("record") SwTask record, @Param("example") SwTaskExample example);

    @UpdateProvider(type = SwTaskSqlProvider.class, method = "updateByExample")
    int updateByExample(@Param("record") SwTask record, @Param("example") SwTaskExample example);

    @UpdateProvider(type = SwTaskSqlProvider.class, method = "updateByPrimaryKeySelective")
    int updateByPrimaryKeySelective(SwTask record);

    @Update({
            "update sw_task",
            "set task_no = #{taskNo,jdbcType=BIGINT},",
            "top_category = #{topCategory,jdbcType=VARCHAR},",
            "category = #{category,jdbcType=VARCHAR},",
            "title = #{title,jdbcType=VARCHAR},",
            "top_project = #{topProject,jdbcType=VARCHAR},",
            "project = #{project,jdbcType=VARCHAR},",
            "level = #{level,jdbcType=VARCHAR},",
            "progress_percent = #{progressPercent,jdbcType=INTEGER},",
            "status = #{status,jdbcType=INTEGER},",
            "reject_status = #{rejectStatus,jdbcType=INTEGER},",
            "proposer_eid = #{proposerEid,jdbcType=VARCHAR},",
            "manager_eid = #{managerEid,jdbcType=VARCHAR},",
            "handle_eid = #{handleEid,jdbcType=VARCHAR},",
            "dead_line = #{deadLine,jdbcType=VARCHAR},",
            "resource_ids = #{resourceIds,jdbcType=VARCHAR},",
            "start_date = #{startDate,jdbcType=VARCHAR},",
            "end_date = #{endDate,jdbcType=VARCHAR},",
            "reflection = #{reflection,jdbcType=VARCHAR},",
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
            "level = #{level,jdbcType=VARCHAR},",
            "progress_percent = #{progressPercent,jdbcType=INTEGER},",
            "status = #{status,jdbcType=INTEGER},",
            "reject_status = #{rejectStatus,jdbcType=INTEGER},",
            "proposer_eid = #{proposerEid,jdbcType=VARCHAR},",
            "manager_eid = #{managerEid,jdbcType=VARCHAR},",
            "handle_eid = #{handleEid,jdbcType=VARCHAR},",
            "dead_line = #{deadLine,jdbcType=VARCHAR},",
            "resource_ids = #{resourceIds,jdbcType=VARCHAR},",
            "start_date = #{startDate,jdbcType=VARCHAR},",
            "end_date = #{endDate,jdbcType=VARCHAR},",
            "reflection = #{reflection,jdbcType=VARCHAR},",
            "create_time = #{createTime,jdbcType=TIMESTAMP},",
            "datetime_lastchange = #{datetimeLastchange,jdbcType=TIMESTAMP}",
            "where id = #{id,jdbcType=INTEGER}"
    })
    int updateByPrimaryKey(SwTask record);
}