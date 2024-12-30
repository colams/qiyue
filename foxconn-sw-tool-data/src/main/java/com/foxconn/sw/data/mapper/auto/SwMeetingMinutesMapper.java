package com.foxconn.sw.data.mapper.auto;

import com.foxconn.sw.data.entity.SwMeetingMinutes;
import com.foxconn.sw.data.entity.SwMeetingMinutesExample;
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
public interface SwMeetingMinutesMapper {
    @SelectProvider(type=SwMeetingMinutesSqlProvider.class, method="countByExample")
    long countByExample(SwMeetingMinutesExample example);

    @DeleteProvider(type=SwMeetingMinutesSqlProvider.class, method="deleteByExample")
    int deleteByExample(SwMeetingMinutesExample example);

    @Delete({
        "delete from sw_meeting_minutes",
        "where id = #{id,jdbcType=BIGINT}"
    })
    int deleteByPrimaryKey(Long id);

    @Insert({
        "insert into sw_meeting_minutes (room, title, ",
        "meeting_date, start_time, ",
        "end_time, webex_url, ",
        "create_time, datetime_lastchange)",
        "values (#{room,jdbcType=VARCHAR}, #{title,jdbcType=VARCHAR}, ",
        "#{meetingDate,jdbcType=VARCHAR}, #{startTime,jdbcType=VARCHAR}, ",
        "#{endTime,jdbcType=VARCHAR}, #{webexUrl,jdbcType=VARCHAR}, ",
        "#{createTime,jdbcType=TIMESTAMP}, #{datetimeLastchange,jdbcType=TIMESTAMP})"
    })
    @SelectKey(statement="SELECT LAST_INSERT_ID()", keyProperty="id", before=false, resultType=Long.class)
    int insert(SwMeetingMinutes record);

    @InsertProvider(type=SwMeetingMinutesSqlProvider.class, method="insertSelective")
    @SelectKey(statement="SELECT LAST_INSERT_ID()", keyProperty="id", before=false, resultType=Long.class)
    int insertSelective(SwMeetingMinutes record);

    @SelectProvider(type=SwMeetingMinutesSqlProvider.class, method="selectByExample")
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.BIGINT, id=true),
        @Result(column="room", property="room", jdbcType=JdbcType.VARCHAR),
        @Result(column="title", property="title", jdbcType=JdbcType.VARCHAR),
        @Result(column="meeting_date", property="meetingDate", jdbcType=JdbcType.VARCHAR),
        @Result(column="start_time", property="startTime", jdbcType=JdbcType.VARCHAR),
        @Result(column="end_time", property="endTime", jdbcType=JdbcType.VARCHAR),
        @Result(column="webex_url", property="webexUrl", jdbcType=JdbcType.VARCHAR),
        @Result(column="create_time", property="createTime", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="datetime_lastchange", property="datetimeLastchange", jdbcType=JdbcType.TIMESTAMP)
    })
    List<SwMeetingMinutes> selectByExampleWithRowbounds(SwMeetingMinutesExample example, RowBounds rowBounds);

    @SelectProvider(type=SwMeetingMinutesSqlProvider.class, method="selectByExample")
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.BIGINT, id=true),
        @Result(column="room", property="room", jdbcType=JdbcType.VARCHAR),
        @Result(column="title", property="title", jdbcType=JdbcType.VARCHAR),
        @Result(column="meeting_date", property="meetingDate", jdbcType=JdbcType.VARCHAR),
        @Result(column="start_time", property="startTime", jdbcType=JdbcType.VARCHAR),
        @Result(column="end_time", property="endTime", jdbcType=JdbcType.VARCHAR),
        @Result(column="webex_url", property="webexUrl", jdbcType=JdbcType.VARCHAR),
        @Result(column="create_time", property="createTime", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="datetime_lastchange", property="datetimeLastchange", jdbcType=JdbcType.TIMESTAMP)
    })
    List<SwMeetingMinutes> selectByExample(SwMeetingMinutesExample example);

    @Select({
        "select",
        "id, room, title, meeting_date, start_time, end_time, webex_url, create_time, ",
        "datetime_lastchange",
        "from sw_meeting_minutes",
        "where id = #{id,jdbcType=BIGINT}"
    })
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.BIGINT, id=true),
        @Result(column="room", property="room", jdbcType=JdbcType.VARCHAR),
        @Result(column="title", property="title", jdbcType=JdbcType.VARCHAR),
        @Result(column="meeting_date", property="meetingDate", jdbcType=JdbcType.VARCHAR),
        @Result(column="start_time", property="startTime", jdbcType=JdbcType.VARCHAR),
        @Result(column="end_time", property="endTime", jdbcType=JdbcType.VARCHAR),
        @Result(column="webex_url", property="webexUrl", jdbcType=JdbcType.VARCHAR),
        @Result(column="create_time", property="createTime", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="datetime_lastchange", property="datetimeLastchange", jdbcType=JdbcType.TIMESTAMP)
    })
    SwMeetingMinutes selectByPrimaryKey(Long id);

    @UpdateProvider(type=SwMeetingMinutesSqlProvider.class, method="updateByExampleSelective")
    int updateByExampleSelective(@Param("record") SwMeetingMinutes record, @Param("example") SwMeetingMinutesExample example);

    @UpdateProvider(type=SwMeetingMinutesSqlProvider.class, method="updateByExample")
    int updateByExample(@Param("record") SwMeetingMinutes record, @Param("example") SwMeetingMinutesExample example);

    @UpdateProvider(type=SwMeetingMinutesSqlProvider.class, method="updateByPrimaryKeySelective")
    int updateByPrimaryKeySelective(SwMeetingMinutes record);

    @Update({
        "update sw_meeting_minutes",
        "set room = #{room,jdbcType=VARCHAR},",
          "title = #{title,jdbcType=VARCHAR},",
          "meeting_date = #{meetingDate,jdbcType=VARCHAR},",
          "start_time = #{startTime,jdbcType=VARCHAR},",
          "end_time = #{endTime,jdbcType=VARCHAR},",
          "webex_url = #{webexUrl,jdbcType=VARCHAR},",
          "create_time = #{createTime,jdbcType=TIMESTAMP},",
          "datetime_lastchange = #{datetimeLastchange,jdbcType=TIMESTAMP}",
        "where id = #{id,jdbcType=BIGINT}"
    })
    int updateByPrimaryKey(SwMeetingMinutes record);
}