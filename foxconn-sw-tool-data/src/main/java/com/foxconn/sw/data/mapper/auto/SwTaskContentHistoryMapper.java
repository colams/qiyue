package com.foxconn.sw.data.mapper.auto;

import com.foxconn.sw.data.entity.SwTaskContentHistory;
import com.foxconn.sw.data.entity.SwTaskContentHistoryExample;
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
public interface SwTaskContentHistoryMapper {
    @SelectProvider(type=SwTaskContentHistorySqlProvider.class, method="countByExample")
    long countByExample(SwTaskContentHistoryExample example);

    @DeleteProvider(type=SwTaskContentHistorySqlProvider.class, method="deleteByExample")
    int deleteByExample(SwTaskContentHistoryExample example);

    @Delete({
        "delete from sw_task_content_history",
        "where id = #{id,jdbcType=INTEGER}"
    })
    int deleteByPrimaryKey(Integer id);

    @Insert({
        "insert into sw_task_content_history (task_id, progress_id, ",
        "operator, datetime_lastchange, ",
        "content)",
        "values (#{taskId,jdbcType=INTEGER}, #{progressId,jdbcType=INTEGER}, ",
        "#{operator,jdbcType=VARCHAR}, #{datetimeLastchange,jdbcType=TIMESTAMP}, ",
        "#{content,jdbcType=LONGVARCHAR})"
    })
    @SelectKey(statement="SELECT LAST_INSERT_ID()", keyProperty="id", before=false, resultType=Integer.class)
    int insert(SwTaskContentHistory record);

    @InsertProvider(type=SwTaskContentHistorySqlProvider.class, method="insertSelective")
    @SelectKey(statement="SELECT LAST_INSERT_ID()", keyProperty="id", before=false, resultType=Integer.class)
    int insertSelective(SwTaskContentHistory record);

    @SelectProvider(type=SwTaskContentHistorySqlProvider.class, method="selectByExampleWithBLOBs")
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="task_id", property="taskId", jdbcType=JdbcType.INTEGER),
        @Result(column="progress_id", property="progressId", jdbcType=JdbcType.INTEGER),
        @Result(column="operator", property="operator", jdbcType=JdbcType.VARCHAR),
        @Result(column="datetime_lastchange", property="datetimeLastchange", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="content", property="content", jdbcType=JdbcType.LONGVARCHAR)
    })
    List<SwTaskContentHistory> selectByExampleWithBLOBsWithRowbounds(SwTaskContentHistoryExample example, RowBounds rowBounds);

    @SelectProvider(type=SwTaskContentHistorySqlProvider.class, method="selectByExampleWithBLOBs")
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="task_id", property="taskId", jdbcType=JdbcType.INTEGER),
        @Result(column="progress_id", property="progressId", jdbcType=JdbcType.INTEGER),
        @Result(column="operator", property="operator", jdbcType=JdbcType.VARCHAR),
        @Result(column="datetime_lastchange", property="datetimeLastchange", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="content", property="content", jdbcType=JdbcType.LONGVARCHAR)
    })
    List<SwTaskContentHistory> selectByExampleWithBLOBs(SwTaskContentHistoryExample example);

    @SelectProvider(type=SwTaskContentHistorySqlProvider.class, method="selectByExample")
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="task_id", property="taskId", jdbcType=JdbcType.INTEGER),
        @Result(column="progress_id", property="progressId", jdbcType=JdbcType.INTEGER),
        @Result(column="operator", property="operator", jdbcType=JdbcType.VARCHAR),
        @Result(column="datetime_lastchange", property="datetimeLastchange", jdbcType=JdbcType.TIMESTAMP)
    })
    List<SwTaskContentHistory> selectByExampleWithRowbounds(SwTaskContentHistoryExample example, RowBounds rowBounds);

    @SelectProvider(type=SwTaskContentHistorySqlProvider.class, method="selectByExample")
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="task_id", property="taskId", jdbcType=JdbcType.INTEGER),
        @Result(column="progress_id", property="progressId", jdbcType=JdbcType.INTEGER),
        @Result(column="operator", property="operator", jdbcType=JdbcType.VARCHAR),
        @Result(column="datetime_lastchange", property="datetimeLastchange", jdbcType=JdbcType.TIMESTAMP)
    })
    List<SwTaskContentHistory> selectByExample(SwTaskContentHistoryExample example);

    @Select({
        "select",
        "id, task_id, progress_id, operator, datetime_lastchange, content",
        "from sw_task_content_history",
        "where id = #{id,jdbcType=INTEGER}"
    })
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="task_id", property="taskId", jdbcType=JdbcType.INTEGER),
        @Result(column="progress_id", property="progressId", jdbcType=JdbcType.INTEGER),
        @Result(column="operator", property="operator", jdbcType=JdbcType.VARCHAR),
        @Result(column="datetime_lastchange", property="datetimeLastchange", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="content", property="content", jdbcType=JdbcType.LONGVARCHAR)
    })
    SwTaskContentHistory selectByPrimaryKey(Integer id);

    @UpdateProvider(type=SwTaskContentHistorySqlProvider.class, method="updateByExampleSelective")
    int updateByExampleSelective(@Param("record") SwTaskContentHistory record, @Param("example") SwTaskContentHistoryExample example);

    @UpdateProvider(type=SwTaskContentHistorySqlProvider.class, method="updateByExampleWithBLOBs")
    int updateByExampleWithBLOBs(@Param("record") SwTaskContentHistory record, @Param("example") SwTaskContentHistoryExample example);

    @UpdateProvider(type=SwTaskContentHistorySqlProvider.class, method="updateByExample")
    int updateByExample(@Param("record") SwTaskContentHistory record, @Param("example") SwTaskContentHistoryExample example);

    @UpdateProvider(type=SwTaskContentHistorySqlProvider.class, method="updateByPrimaryKeySelective")
    int updateByPrimaryKeySelective(SwTaskContentHistory record);

    @Update({
        "update sw_task_content_history",
        "set task_id = #{taskId,jdbcType=INTEGER},",
          "progress_id = #{progressId,jdbcType=INTEGER},",
          "operator = #{operator,jdbcType=VARCHAR},",
          "datetime_lastchange = #{datetimeLastchange,jdbcType=TIMESTAMP},",
          "content = #{content,jdbcType=LONGVARCHAR}",
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