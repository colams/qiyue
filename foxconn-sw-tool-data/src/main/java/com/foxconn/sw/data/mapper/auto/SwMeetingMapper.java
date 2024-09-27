package com.foxconn.sw.data.mapper.auto;

import com.foxconn.sw.data.entity.SwMeeting;
import com.foxconn.sw.data.entity.SwMeetingExample;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.session.RowBounds;
import org.apache.ibatis.type.JdbcType;

import java.util.List;

@Mapper
public interface SwMeetingMapper {
    @SelectProvider(type = SwMeetingSqlProvider.class, method = "countByExample")
    long countByExample(SwMeetingExample example);

    @DeleteProvider(type = SwMeetingSqlProvider.class, method = "deleteByExample")
    int deleteByExample(SwMeetingExample example);

    @Delete({
        "delete from sw_meeting",
        "where id = #{id,jdbcType=INTEGER}"
    })
    int deleteByPrimaryKey(Integer id);

    @Insert({
            "insert into sw_meeting (room, abc_meeting, ",
            "title, description, ",
            "meeting_date, start_time, ",
            "end_time, resource_ids, ",
            "is_repeat, cycle, ",
            "cycle_start, cycle_expire, ",
            "create_time, datetime_lastchange, ",
            "status, creator)",
            "values (#{room,jdbcType=VARCHAR}, #{abcMeeting,jdbcType=INTEGER}, ",
            "#{title,jdbcType=VARCHAR}, #{description,jdbcType=VARCHAR}, ",
            "#{meetingDate,jdbcType=VARCHAR}, #{startTime,jdbcType=VARCHAR}, ",
            "#{endTime,jdbcType=VARCHAR}, #{resourceIds,jdbcType=VARCHAR}, ",
            "#{isRepeat,jdbcType=INTEGER}, #{cycle,jdbcType=VARCHAR}, ",
            "#{cycleStart,jdbcType=VARCHAR}, #{cycleExpire,jdbcType=VARCHAR}, ",
            "#{createTime,jdbcType=TIMESTAMP}, #{datetimeLastchange,jdbcType=TIMESTAMP}, ",
            "#{status,jdbcType=INTEGER}, #{creator,jdbcType=VARCHAR})"
    })
    @SelectKey(statement="SELECT LAST_INSERT_ID()", keyProperty="id", before=false, resultType=Integer.class)
    int insert(SwMeeting record);

    @InsertProvider(type=SwMeetingSqlProvider.class, method="insertSelective")
    @SelectKey(statement="SELECT LAST_INSERT_ID()", keyProperty="id", before=false, resultType=Integer.class)
    int insertSelective(SwMeeting record);

    @SelectProvider(type=SwMeetingSqlProvider.class, method="selectByExample")
    @Results({
            @Result(column = "id", property = "id", jdbcType = JdbcType.INTEGER, id = true),
            @Result(column = "room", property = "room", jdbcType = JdbcType.VARCHAR),
            @Result(column = "abc_meeting", property = "abcMeeting", jdbcType = JdbcType.INTEGER),
            @Result(column = "title", property = "title", jdbcType = JdbcType.VARCHAR),
            @Result(column = "description", property = "description", jdbcType = JdbcType.VARCHAR),
            @Result(column = "meeting_date", property = "meetingDate", jdbcType = JdbcType.VARCHAR),
            @Result(column = "start_time", property = "startTime", jdbcType = JdbcType.VARCHAR),
            @Result(column = "end_time", property = "endTime", jdbcType = JdbcType.VARCHAR),
            @Result(column = "resource_ids", property = "resourceIds", jdbcType = JdbcType.VARCHAR),
            @Result(column = "is_repeat", property = "isRepeat", jdbcType = JdbcType.INTEGER),
            @Result(column = "cycle", property = "cycle", jdbcType = JdbcType.VARCHAR),
            @Result(column = "cycle_start", property = "cycleStart", jdbcType = JdbcType.VARCHAR),
            @Result(column = "cycle_expire", property = "cycleExpire", jdbcType = JdbcType.VARCHAR),
            @Result(column = "create_time", property = "createTime", jdbcType = JdbcType.TIMESTAMP),
            @Result(column = "datetime_lastchange", property = "datetimeLastchange", jdbcType = JdbcType.TIMESTAMP),
            @Result(column = "status", property = "status", jdbcType = JdbcType.INTEGER),
            @Result(column = "creator", property = "creator", jdbcType = JdbcType.VARCHAR)
    })
    List<SwMeeting> selectByExampleWithRowbounds(SwMeetingExample example, RowBounds rowBounds);

    @SelectProvider(type=SwMeetingSqlProvider.class, method="selectByExample")
    @Results({
            @Result(column = "id", property = "id", jdbcType = JdbcType.INTEGER, id = true),
            @Result(column = "room", property = "room", jdbcType = JdbcType.VARCHAR),
            @Result(column = "abc_meeting", property = "abcMeeting", jdbcType = JdbcType.INTEGER),
            @Result(column = "title", property = "title", jdbcType = JdbcType.VARCHAR),
            @Result(column = "description", property = "description", jdbcType = JdbcType.VARCHAR),
            @Result(column = "meeting_date", property = "meetingDate", jdbcType = JdbcType.VARCHAR),
            @Result(column = "start_time", property = "startTime", jdbcType = JdbcType.VARCHAR),
            @Result(column = "end_time", property = "endTime", jdbcType = JdbcType.VARCHAR),
            @Result(column = "resource_ids", property = "resourceIds", jdbcType = JdbcType.VARCHAR),
            @Result(column = "is_repeat", property = "isRepeat", jdbcType = JdbcType.INTEGER),
            @Result(column = "cycle", property = "cycle", jdbcType = JdbcType.VARCHAR),
            @Result(column = "cycle_start", property = "cycleStart", jdbcType = JdbcType.VARCHAR),
            @Result(column = "cycle_expire", property = "cycleExpire", jdbcType = JdbcType.VARCHAR),
            @Result(column = "create_time", property = "createTime", jdbcType = JdbcType.TIMESTAMP),
            @Result(column = "datetime_lastchange", property = "datetimeLastchange", jdbcType = JdbcType.TIMESTAMP),
            @Result(column = "status", property = "status", jdbcType = JdbcType.INTEGER),
            @Result(column = "creator", property = "creator", jdbcType = JdbcType.VARCHAR)
    })
    List<SwMeeting> selectByExample(SwMeetingExample example);

    @Select({
            "select",
            "id, room, abc_meeting, title, description, meeting_date, start_time, end_time, ",
            "resource_ids, is_repeat, cycle, cycle_start, cycle_expire, create_time, datetime_lastchange, ",
            "status, creator",
            "from sw_meeting",
            "where id = #{id,jdbcType=INTEGER}"
    })
    @Results({
            @Result(column = "id", property = "id", jdbcType = JdbcType.INTEGER, id = true),
            @Result(column = "room", property = "room", jdbcType = JdbcType.VARCHAR),
            @Result(column = "abc_meeting", property = "abcMeeting", jdbcType = JdbcType.INTEGER),
            @Result(column = "title", property = "title", jdbcType = JdbcType.VARCHAR),
            @Result(column = "description", property = "description", jdbcType = JdbcType.VARCHAR),
            @Result(column = "meeting_date", property = "meetingDate", jdbcType = JdbcType.VARCHAR),
            @Result(column = "start_time", property = "startTime", jdbcType = JdbcType.VARCHAR),
            @Result(column = "end_time", property = "endTime", jdbcType = JdbcType.VARCHAR),
            @Result(column = "resource_ids", property = "resourceIds", jdbcType = JdbcType.VARCHAR),
            @Result(column = "is_repeat", property = "isRepeat", jdbcType = JdbcType.INTEGER),
            @Result(column = "cycle", property = "cycle", jdbcType = JdbcType.VARCHAR),
            @Result(column = "cycle_start", property = "cycleStart", jdbcType = JdbcType.VARCHAR),
            @Result(column = "cycle_expire", property = "cycleExpire", jdbcType = JdbcType.VARCHAR),
            @Result(column = "create_time", property = "createTime", jdbcType = JdbcType.TIMESTAMP),
            @Result(column = "datetime_lastchange", property = "datetimeLastchange", jdbcType = JdbcType.TIMESTAMP),
            @Result(column = "status", property = "status", jdbcType = JdbcType.INTEGER),
            @Result(column = "creator", property = "creator", jdbcType = JdbcType.VARCHAR)
    })
    SwMeeting selectByPrimaryKey(Integer id);

    @UpdateProvider(type=SwMeetingSqlProvider.class, method="updateByExampleSelective")
    int updateByExampleSelective(@Param("record") SwMeeting record, @Param("example") SwMeetingExample example);

    @UpdateProvider(type=SwMeetingSqlProvider.class, method="updateByExample")
    int updateByExample(@Param("record") SwMeeting record, @Param("example") SwMeetingExample example);

    @UpdateProvider(type=SwMeetingSqlProvider.class, method="updateByPrimaryKeySelective")
    int updateByPrimaryKeySelective(SwMeeting record);

    @Update({
            "update sw_meeting",
            "set room = #{room,jdbcType=VARCHAR},",
            "abc_meeting = #{abcMeeting,jdbcType=INTEGER},",
            "title = #{title,jdbcType=VARCHAR},",
            "description = #{description,jdbcType=VARCHAR},",
            "meeting_date = #{meetingDate,jdbcType=VARCHAR},",
            "start_time = #{startTime,jdbcType=VARCHAR},",
            "end_time = #{endTime,jdbcType=VARCHAR},",
            "resource_ids = #{resourceIds,jdbcType=VARCHAR},",
            "is_repeat = #{isRepeat,jdbcType=INTEGER},",
            "cycle = #{cycle,jdbcType=VARCHAR},",
            "cycle_start = #{cycleStart,jdbcType=VARCHAR},",
            "cycle_expire = #{cycleExpire,jdbcType=VARCHAR},",
            "create_time = #{createTime,jdbcType=TIMESTAMP},",
            "datetime_lastchange = #{datetimeLastchange,jdbcType=TIMESTAMP},",
            "status = #{status,jdbcType=INTEGER},",
            "creator = #{creator,jdbcType=VARCHAR}",
            "where id = #{id,jdbcType=INTEGER}"
    })
    int updateByPrimaryKey(SwMeeting record);
}