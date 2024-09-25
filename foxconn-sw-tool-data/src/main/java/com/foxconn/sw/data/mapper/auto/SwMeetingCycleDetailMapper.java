package com.foxconn.sw.data.mapper.auto;

import com.foxconn.sw.data.entity.SwMeetingCycleDetail;
import com.foxconn.sw.data.entity.SwMeetingCycleDetailExample;
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
public interface SwMeetingCycleDetailMapper {
    @SelectProvider(type=SwMeetingCycleDetailSqlProvider.class, method="countByExample")
    long countByExample(SwMeetingCycleDetailExample example);

    @DeleteProvider(type=SwMeetingCycleDetailSqlProvider.class, method="deleteByExample")
    int deleteByExample(SwMeetingCycleDetailExample example);

    @Delete({
        "delete from sw_meeting_cycle_detail",
        "where id = #{id,jdbcType=INTEGER}"
    })
    int deleteByPrimaryKey(Integer id);

    @Insert({
        "insert into sw_meeting_cycle_detail (meeting_id, room, ",
        "title, description, ",
        "resource_ids, operator, ",
        "meeting_date, start_time, ",
        "end_time, cancel, ",
        "create_time, datetime_lastchange)",
        "values (#{meetingId,jdbcType=INTEGER}, #{room,jdbcType=VARCHAR}, ",
        "#{title,jdbcType=VARCHAR}, #{description,jdbcType=VARCHAR}, ",
        "#{resourceIds,jdbcType=VARCHAR}, #{operator,jdbcType=VARCHAR}, ",
        "#{meetingDate,jdbcType=VARCHAR}, #{startTime,jdbcType=VARCHAR}, ",
        "#{endTime,jdbcType=VARCHAR}, #{cancel,jdbcType=INTEGER}, ",
        "#{createTime,jdbcType=TIMESTAMP}, #{datetimeLastchange,jdbcType=TIMESTAMP})"
    })
    @SelectKey(statement="SELECT LAST_INSERT_ID()", keyProperty="id", before=false, resultType=Integer.class)
    int insert(SwMeetingCycleDetail record);

    @InsertProvider(type=SwMeetingCycleDetailSqlProvider.class, method="insertSelective")
    @SelectKey(statement="SELECT LAST_INSERT_ID()", keyProperty="id", before=false, resultType=Integer.class)
    int insertSelective(SwMeetingCycleDetail record);

    @SelectProvider(type=SwMeetingCycleDetailSqlProvider.class, method="selectByExample")
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="meeting_id", property="meetingId", jdbcType=JdbcType.INTEGER),
        @Result(column="room", property="room", jdbcType=JdbcType.VARCHAR),
        @Result(column="title", property="title", jdbcType=JdbcType.VARCHAR),
        @Result(column="description", property="description", jdbcType=JdbcType.VARCHAR),
        @Result(column="resource_ids", property="resourceIds", jdbcType=JdbcType.VARCHAR),
        @Result(column="operator", property="operator", jdbcType=JdbcType.VARCHAR),
        @Result(column="meeting_date", property="meetingDate", jdbcType=JdbcType.VARCHAR),
        @Result(column="start_time", property="startTime", jdbcType=JdbcType.VARCHAR),
        @Result(column="end_time", property="endTime", jdbcType=JdbcType.VARCHAR),
        @Result(column="cancel", property="cancel", jdbcType=JdbcType.INTEGER),
        @Result(column="create_time", property="createTime", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="datetime_lastchange", property="datetimeLastchange", jdbcType=JdbcType.TIMESTAMP)
    })
    List<SwMeetingCycleDetail> selectByExampleWithRowbounds(SwMeetingCycleDetailExample example, RowBounds rowBounds);

    @SelectProvider(type=SwMeetingCycleDetailSqlProvider.class, method="selectByExample")
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="meeting_id", property="meetingId", jdbcType=JdbcType.INTEGER),
        @Result(column="room", property="room", jdbcType=JdbcType.VARCHAR),
        @Result(column="title", property="title", jdbcType=JdbcType.VARCHAR),
        @Result(column="description", property="description", jdbcType=JdbcType.VARCHAR),
        @Result(column="resource_ids", property="resourceIds", jdbcType=JdbcType.VARCHAR),
        @Result(column="operator", property="operator", jdbcType=JdbcType.VARCHAR),
        @Result(column="meeting_date", property="meetingDate", jdbcType=JdbcType.VARCHAR),
        @Result(column="start_time", property="startTime", jdbcType=JdbcType.VARCHAR),
        @Result(column="end_time", property="endTime", jdbcType=JdbcType.VARCHAR),
        @Result(column="cancel", property="cancel", jdbcType=JdbcType.INTEGER),
        @Result(column="create_time", property="createTime", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="datetime_lastchange", property="datetimeLastchange", jdbcType=JdbcType.TIMESTAMP)
    })
    List<SwMeetingCycleDetail> selectByExample(SwMeetingCycleDetailExample example);

    @Select({
        "select",
        "id, meeting_id, room, title, description, resource_ids, operator, meeting_date, ",
        "start_time, end_time, cancel, create_time, datetime_lastchange",
        "from sw_meeting_cycle_detail",
        "where id = #{id,jdbcType=INTEGER}"
    })
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="meeting_id", property="meetingId", jdbcType=JdbcType.INTEGER),
        @Result(column="room", property="room", jdbcType=JdbcType.VARCHAR),
        @Result(column="title", property="title", jdbcType=JdbcType.VARCHAR),
        @Result(column="description", property="description", jdbcType=JdbcType.VARCHAR),
        @Result(column="resource_ids", property="resourceIds", jdbcType=JdbcType.VARCHAR),
        @Result(column="operator", property="operator", jdbcType=JdbcType.VARCHAR),
        @Result(column="meeting_date", property="meetingDate", jdbcType=JdbcType.VARCHAR),
        @Result(column="start_time", property="startTime", jdbcType=JdbcType.VARCHAR),
        @Result(column="end_time", property="endTime", jdbcType=JdbcType.VARCHAR),
        @Result(column="cancel", property="cancel", jdbcType=JdbcType.INTEGER),
        @Result(column="create_time", property="createTime", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="datetime_lastchange", property="datetimeLastchange", jdbcType=JdbcType.TIMESTAMP)
    })
    SwMeetingCycleDetail selectByPrimaryKey(Integer id);

    @UpdateProvider(type=SwMeetingCycleDetailSqlProvider.class, method="updateByExampleSelective")
    int updateByExampleSelective(@Param("record") SwMeetingCycleDetail record, @Param("example") SwMeetingCycleDetailExample example);

    @UpdateProvider(type=SwMeetingCycleDetailSqlProvider.class, method="updateByExample")
    int updateByExample(@Param("record") SwMeetingCycleDetail record, @Param("example") SwMeetingCycleDetailExample example);

    @UpdateProvider(type=SwMeetingCycleDetailSqlProvider.class, method="updateByPrimaryKeySelective")
    int updateByPrimaryKeySelective(SwMeetingCycleDetail record);

    @Update({
        "update sw_meeting_cycle_detail",
        "set meeting_id = #{meetingId,jdbcType=INTEGER},",
          "room = #{room,jdbcType=VARCHAR},",
          "title = #{title,jdbcType=VARCHAR},",
          "description = #{description,jdbcType=VARCHAR},",
          "resource_ids = #{resourceIds,jdbcType=VARCHAR},",
          "operator = #{operator,jdbcType=VARCHAR},",
          "meeting_date = #{meetingDate,jdbcType=VARCHAR},",
          "start_time = #{startTime,jdbcType=VARCHAR},",
          "end_time = #{endTime,jdbcType=VARCHAR},",
          "cancel = #{cancel,jdbcType=INTEGER},",
          "create_time = #{createTime,jdbcType=TIMESTAMP},",
          "datetime_lastchange = #{datetimeLastchange,jdbcType=TIMESTAMP}",
        "where id = #{id,jdbcType=INTEGER}"
    })
    int updateByPrimaryKey(SwMeetingCycleDetail record);
}