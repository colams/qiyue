package com.foxconn.sw.data.mapper.auto;

import com.foxconn.sw.data.entity.SwTaskProgress;
import com.foxconn.sw.data.entity.SwTaskProgressExample;
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
public interface SwTaskProgressMapper {
    @SelectProvider(type=SwTaskProgressSqlProvider.class, method="countByExample")
    long countByExample(SwTaskProgressExample example);

    @DeleteProvider(type=SwTaskProgressSqlProvider.class, method="deleteByExample")
    int deleteByExample(SwTaskProgressExample example);

    @Delete({
        "delete from sw_task_progress",
        "where id = #{id,jdbcType=INTEGER}"
    })
    int deleteByPrimaryKey(Integer id);

    @Insert({
        "insert into sw_task_progress (operate_type, task_id, ",
        "operate_eid, resource_ids, ",
        "progress, content, ",
        "datetime_lastchange)",
        "values (#{operateType,jdbcType=VARCHAR}, #{taskId,jdbcType=INTEGER}, ",
        "#{operateEid,jdbcType=VARCHAR}, #{resourceIds,jdbcType=VARCHAR}, ",
        "#{progress,jdbcType=INTEGER}, #{content,jdbcType=VARCHAR}, ",
        "#{datetimeLastchange,jdbcType=TIMESTAMP})"
    })
    @SelectKey(statement="SELECT LAST_INSERT_ID()", keyProperty="id", before=false, resultType=Integer.class)
    int insert(SwTaskProgress record);

    @InsertProvider(type=SwTaskProgressSqlProvider.class, method="insertSelective")
    @SelectKey(statement="SELECT LAST_INSERT_ID()", keyProperty="id", before=false, resultType=Integer.class)
    int insertSelective(SwTaskProgress record);

    @SelectProvider(type=SwTaskProgressSqlProvider.class, method="selectByExample")
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="operate_type", property="operateType", jdbcType=JdbcType.VARCHAR),
        @Result(column="task_id", property="taskId", jdbcType=JdbcType.INTEGER),
        @Result(column="operate_eid", property="operateEid", jdbcType=JdbcType.VARCHAR),
        @Result(column="resource_ids", property="resourceIds", jdbcType=JdbcType.VARCHAR),
        @Result(column="progress", property="progress", jdbcType=JdbcType.INTEGER),
        @Result(column="content", property="content", jdbcType=JdbcType.VARCHAR),
        @Result(column="datetime_lastchange", property="datetimeLastchange", jdbcType=JdbcType.TIMESTAMP)
    })
    List<SwTaskProgress> selectByExampleWithRowbounds(SwTaskProgressExample example, RowBounds rowBounds);

    @SelectProvider(type=SwTaskProgressSqlProvider.class, method="selectByExample")
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="operate_type", property="operateType", jdbcType=JdbcType.VARCHAR),
        @Result(column="task_id", property="taskId", jdbcType=JdbcType.INTEGER),
        @Result(column="operate_eid", property="operateEid", jdbcType=JdbcType.VARCHAR),
        @Result(column="resource_ids", property="resourceIds", jdbcType=JdbcType.VARCHAR),
        @Result(column="progress", property="progress", jdbcType=JdbcType.INTEGER),
        @Result(column="content", property="content", jdbcType=JdbcType.VARCHAR),
        @Result(column="datetime_lastchange", property="datetimeLastchange", jdbcType=JdbcType.TIMESTAMP)
    })
    List<SwTaskProgress> selectByExample(SwTaskProgressExample example);

    @Select({
        "select",
        "id, operate_type, task_id, operate_eid, resource_ids, progress, content, datetime_lastchange",
        "from sw_task_progress",
        "where id = #{id,jdbcType=INTEGER}"
    })
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="operate_type", property="operateType", jdbcType=JdbcType.VARCHAR),
        @Result(column="task_id", property="taskId", jdbcType=JdbcType.INTEGER),
        @Result(column="operate_eid", property="operateEid", jdbcType=JdbcType.VARCHAR),
        @Result(column="resource_ids", property="resourceIds", jdbcType=JdbcType.VARCHAR),
        @Result(column="progress", property="progress", jdbcType=JdbcType.INTEGER),
        @Result(column="content", property="content", jdbcType=JdbcType.VARCHAR),
        @Result(column="datetime_lastchange", property="datetimeLastchange", jdbcType=JdbcType.TIMESTAMP)
    })
    SwTaskProgress selectByPrimaryKey(Integer id);

    @UpdateProvider(type=SwTaskProgressSqlProvider.class, method="updateByExampleSelective")
    int updateByExampleSelective(@Param("record") SwTaskProgress record, @Param("example") SwTaskProgressExample example);

    @UpdateProvider(type=SwTaskProgressSqlProvider.class, method="updateByExample")
    int updateByExample(@Param("record") SwTaskProgress record, @Param("example") SwTaskProgressExample example);

    @UpdateProvider(type=SwTaskProgressSqlProvider.class, method="updateByPrimaryKeySelective")
    int updateByPrimaryKeySelective(SwTaskProgress record);

    @Update({
        "update sw_task_progress",
        "set operate_type = #{operateType,jdbcType=VARCHAR},",
          "task_id = #{taskId,jdbcType=INTEGER},",
          "operate_eid = #{operateEid,jdbcType=VARCHAR},",
          "resource_ids = #{resourceIds,jdbcType=VARCHAR},",
          "progress = #{progress,jdbcType=INTEGER},",
          "content = #{content,jdbcType=VARCHAR},",
          "datetime_lastchange = #{datetimeLastchange,jdbcType=TIMESTAMP}",
        "where id = #{id,jdbcType=INTEGER}"
    })
    int updateByPrimaryKey(SwTaskProgress record);
}