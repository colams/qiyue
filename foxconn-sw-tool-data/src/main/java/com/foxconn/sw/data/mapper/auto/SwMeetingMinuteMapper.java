package com.foxconn.sw.data.mapper.auto;

import com.foxconn.sw.data.entity.SwMeetingMinute;
import com.foxconn.sw.data.entity.SwMeetingMinuteExample;
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
public interface SwMeetingMinuteMapper {
    int deleteByExample(SwMeetingMinuteExample example);

    @Delete({
        "delete from sw_meeting_minute",
        "where id = #{id,jdbcType=BIGINT}"
    })
    int deleteByPrimaryKey(Long id);

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

    int insertSelective(SwMeetingMinute record);

    List<SwMeetingMinute> selectByExampleWithRowbounds(SwMeetingMinuteExample example, RowBounds rowBounds);

    List<SwMeetingMinute> selectByExample(SwMeetingMinuteExample example);

    @Select({
        "select",
        "id, meeting_id, room, title, meeting_date, start_time, end_time, webex_url, ",
        "resource_ids, create_time, datetime_lastchange",
        "from sw_meeting_minute",
        "where id = #{id,jdbcType=BIGINT}"
    })
    @ResultMap("com.foxconn.sw.data.mapper.auto.SwMeetingMinuteMapper.BaseResultMap")
    SwMeetingMinute selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") SwMeetingMinute record, @Param("example") SwMeetingMinuteExample example);

    int updateByExample(@Param("record") SwMeetingMinute record, @Param("example") SwMeetingMinuteExample example);

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