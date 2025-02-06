package com.foxconn.sw.data.mapper.auto;

import com.foxconn.sw.data.entity.SwMeetingMinute;
import com.foxconn.sw.data.entity.SwMeetingMinuteExample;
import java.util.List;
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
public interface SwMeetingMinuteMapper {
    @Insert({
        "insert into sw_meeting_minute (meeting_id, room, ",
        "title, meeting_date, ",
        "start_time, end_time, ",
        "webex_url, resource_ids, ",
        "create_time, datetime_lastchange)",
        "values (#{meetingId,jdbcType=INTEGER}, #{room,jdbcType=VARCHAR}, ",
        "#{title,jdbcType=VARCHAR}, #{meetingDate,jdbcType=VARCHAR}, ",
        "#{startTime,jdbcType=VARCHAR}, #{endTime,jdbcType=VARCHAR}, ",
        "#{webexUrl,jdbcType=VARCHAR}, #{resourceIds,jdbcType=VARCHAR}, ",
        "#{createTime,jdbcType=TIMESTAMP}, #{datetimeLastchange,jdbcType=TIMESTAMP})"
    })
    @SelectKey(statement="SELECT LAST_INSERT_ID()", keyProperty="id", before=false, resultType=Long.class)
    int insert(SwMeetingMinute record);

    @InsertProvider(type=SwMeetingMinuteSqlProvider.class, method="insertSelective")
    @SelectKey(statement="SELECT LAST_INSERT_ID()", keyProperty="id", before=false, resultType=Long.class)
    int insertSelective(SwMeetingMinute record);

    @SelectProvider(type=SwMeetingMinuteSqlProvider.class, method="selectByExample")
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.BIGINT, id=true),
        @Result(column="meeting_id", property="meetingId", jdbcType=JdbcType.INTEGER),
        @Result(column="room", property="room", jdbcType=JdbcType.VARCHAR),
        @Result(column="title", property="title", jdbcType=JdbcType.VARCHAR),
        @Result(column="meeting_date", property="meetingDate", jdbcType=JdbcType.VARCHAR),
        @Result(column="start_time", property="startTime", jdbcType=JdbcType.VARCHAR),
        @Result(column="end_time", property="endTime", jdbcType=JdbcType.VARCHAR),
        @Result(column="webex_url", property="webexUrl", jdbcType=JdbcType.VARCHAR),
        @Result(column="resource_ids", property="resourceIds", jdbcType=JdbcType.VARCHAR),
        @Result(column="create_time", property="createTime", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="datetime_lastchange", property="datetimeLastchange", jdbcType=JdbcType.TIMESTAMP)
    })
    List<SwMeetingMinute> selectByExampleWithRowbounds(SwMeetingMinuteExample example, RowBounds rowBounds);

    @SelectProvider(type=SwMeetingMinuteSqlProvider.class, method="selectByExample")
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.BIGINT, id=true),
        @Result(column="meeting_id", property="meetingId", jdbcType=JdbcType.INTEGER),
        @Result(column="room", property="room", jdbcType=JdbcType.VARCHAR),
        @Result(column="title", property="title", jdbcType=JdbcType.VARCHAR),
        @Result(column="meeting_date", property="meetingDate", jdbcType=JdbcType.VARCHAR),
        @Result(column="start_time", property="startTime", jdbcType=JdbcType.VARCHAR),
        @Result(column="end_time", property="endTime", jdbcType=JdbcType.VARCHAR),
        @Result(column="webex_url", property="webexUrl", jdbcType=JdbcType.VARCHAR),
        @Result(column="resource_ids", property="resourceIds", jdbcType=JdbcType.VARCHAR),
        @Result(column="create_time", property="createTime", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="datetime_lastchange", property="datetimeLastchange", jdbcType=JdbcType.TIMESTAMP)
    })
    List<SwMeetingMinute> selectByExample(SwMeetingMinuteExample example);

    @Select({
        "select",
        "id, meeting_id, room, title, meeting_date, start_time, end_time, webex_url, ",
        "resource_ids, create_time, datetime_lastchange",
        "from sw_meeting_minute",
        "where id = #{id,jdbcType=BIGINT}"
    })
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.BIGINT, id=true),
        @Result(column="meeting_id", property="meetingId", jdbcType=JdbcType.INTEGER),
        @Result(column="room", property="room", jdbcType=JdbcType.VARCHAR),
        @Result(column="title", property="title", jdbcType=JdbcType.VARCHAR),
        @Result(column="meeting_date", property="meetingDate", jdbcType=JdbcType.VARCHAR),
        @Result(column="start_time", property="startTime", jdbcType=JdbcType.VARCHAR),
        @Result(column="end_time", property="endTime", jdbcType=JdbcType.VARCHAR),
        @Result(column="webex_url", property="webexUrl", jdbcType=JdbcType.VARCHAR),
        @Result(column="resource_ids", property="resourceIds", jdbcType=JdbcType.VARCHAR),
        @Result(column="create_time", property="createTime", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="datetime_lastchange", property="datetimeLastchange", jdbcType=JdbcType.TIMESTAMP)
    })
    SwMeetingMinute selectByPrimaryKey(Long id);

    @UpdateProvider(type=SwMeetingMinuteSqlProvider.class, method="updateByExampleSelective")
    int updateByExampleSelective(@Param("record") SwMeetingMinute record, @Param("example") SwMeetingMinuteExample example);

    @UpdateProvider(type=SwMeetingMinuteSqlProvider.class, method="updateByExample")
    int updateByExample(@Param("record") SwMeetingMinute record, @Param("example") SwMeetingMinuteExample example);

    @UpdateProvider(type=SwMeetingMinuteSqlProvider.class, method="updateByPrimaryKeySelective")
    int updateByPrimaryKeySelective(SwMeetingMinute record);

    @Update({
        "update sw_meeting_minute",
        "set meeting_id = #{meetingId,jdbcType=INTEGER},",
          "room = #{room,jdbcType=VARCHAR},",
          "title = #{title,jdbcType=VARCHAR},",
          "meeting_date = #{meetingDate,jdbcType=VARCHAR},",
          "start_time = #{startTime,jdbcType=VARCHAR},",
          "end_time = #{endTime,jdbcType=VARCHAR},",
          "webex_url = #{webexUrl,jdbcType=VARCHAR},",
          "resource_ids = #{resourceIds,jdbcType=VARCHAR},",
          "create_time = #{createTime,jdbcType=TIMESTAMP},",
          "datetime_lastchange = #{datetimeLastchange,jdbcType=TIMESTAMP}",
        "where id = #{id,jdbcType=BIGINT}"
    })
    int updateByPrimaryKey(SwMeetingMinute record);
}