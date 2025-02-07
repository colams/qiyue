package com.foxconn.sw.data.mapper.auto;

import com.foxconn.sw.data.entity.SwSubTask;
import com.foxconn.sw.data.entity.SwSubTaskExample;
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
public interface SwSubTaskMapper {
    @DeleteProvider(type=SwSubTaskSqlProvider.class, method="deleteByExample")
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

    @InsertProvider(type=SwSubTaskSqlProvider.class, method="insertSelective")
    @SelectKey(statement="SELECT LAST_INSERT_ID()", keyProperty="id", before=false, resultType=Integer.class)
    int insertSelective(SwSubTask record);

    @SelectProvider(type=SwSubTaskSqlProvider.class, method="selectByExample")
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="task_id", property="taskId", jdbcType=JdbcType.INTEGER),
        @Result(column="task_no", property="taskNo", jdbcType=JdbcType.BIGINT),
        @Result(column="title", property="title", jdbcType=JdbcType.VARCHAR),
        @Result(column="level", property="level", jdbcType=JdbcType.VARCHAR),
        @Result(column="status", property="status", jdbcType=JdbcType.INTEGER),
        @Result(column="handle_no", property="handleNo", jdbcType=JdbcType.VARCHAR),
        @Result(column="dead_line", property="deadLine", jdbcType=JdbcType.VARCHAR),
        @Result(column="create_time", property="createTime", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="datetime_lastchange", property="datetimeLastchange", jdbcType=JdbcType.TIMESTAMP)
    })
    List<SwSubTask> selectByExampleWithRowbounds(SwSubTaskExample example, RowBounds rowBounds);

    @SelectProvider(type=SwSubTaskSqlProvider.class, method="selectByExample")
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="task_id", property="taskId", jdbcType=JdbcType.INTEGER),
        @Result(column="task_no", property="taskNo", jdbcType=JdbcType.BIGINT),
        @Result(column="title", property="title", jdbcType=JdbcType.VARCHAR),
        @Result(column="level", property="level", jdbcType=JdbcType.VARCHAR),
        @Result(column="status", property="status", jdbcType=JdbcType.INTEGER),
        @Result(column="handle_no", property="handleNo", jdbcType=JdbcType.VARCHAR),
        @Result(column="dead_line", property="deadLine", jdbcType=JdbcType.VARCHAR),
        @Result(column="create_time", property="createTime", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="datetime_lastchange", property="datetimeLastchange", jdbcType=JdbcType.TIMESTAMP)
    })
    List<SwSubTask> selectByExample(SwSubTaskExample example);

    @Select({
        "select",
        "id, task_id, task_no, title, level, status, handle_no, dead_line, create_time, ",
        "datetime_lastchange",
        "from sw_sub_task",
        "where id = #{id,jdbcType=INTEGER}"
    })
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="task_id", property="taskId", jdbcType=JdbcType.INTEGER),
        @Result(column="task_no", property="taskNo", jdbcType=JdbcType.BIGINT),
        @Result(column="title", property="title", jdbcType=JdbcType.VARCHAR),
        @Result(column="level", property="level", jdbcType=JdbcType.VARCHAR),
        @Result(column="status", property="status", jdbcType=JdbcType.INTEGER),
        @Result(column="handle_no", property="handleNo", jdbcType=JdbcType.VARCHAR),
        @Result(column="dead_line", property="deadLine", jdbcType=JdbcType.VARCHAR),
        @Result(column="create_time", property="createTime", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="datetime_lastchange", property="datetimeLastchange", jdbcType=JdbcType.TIMESTAMP)
    })
    SwSubTask selectByPrimaryKey(Integer id);

    @UpdateProvider(type=SwSubTaskSqlProvider.class, method="updateByExampleSelective")
    int updateByExampleSelective(@Param("record") SwSubTask record, @Param("example") SwSubTaskExample example);

    @UpdateProvider(type=SwSubTaskSqlProvider.class, method="updateByExample")
    int updateByExample(@Param("record") SwSubTask record, @Param("example") SwSubTaskExample example);

    @UpdateProvider(type=SwSubTaskSqlProvider.class, method="updateByPrimaryKeySelective")
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