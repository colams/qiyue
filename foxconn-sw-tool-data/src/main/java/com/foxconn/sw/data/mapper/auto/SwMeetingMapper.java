package com.foxconn.sw.data.mapper.auto;

import com.foxconn.sw.data.entity.SwMeeting;
import com.foxconn.sw.data.entity.SwMeetingExample;
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
public interface SwMeetingMapper {
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
        "status, webex_url, ",
        "create_time, datetime_lastchange)",
        "values (#{room,jdbcType=VARCHAR}, #{abcMeeting,jdbcType=INTEGER}, ",
        "#{title,jdbcType=VARCHAR}, #{description,jdbcType=VARCHAR}, ",
        "#{meetingDate,jdbcType=VARCHAR}, #{startTime,jdbcType=VARCHAR}, ",
        "#{endTime,jdbcType=VARCHAR}, #{resourceIds,jdbcType=VARCHAR}, ",
        "#{isRepeat,jdbcType=INTEGER}, #{cycle,jdbcType=VARCHAR}, ",
        "#{cycleStart,jdbcType=VARCHAR}, #{cycleExpire,jdbcType=VARCHAR}, ",
        "#{status,jdbcType=INTEGER}, #{webexUrl,jdbcType=VARCHAR}, ",
        "#{createTime,jdbcType=TIMESTAMP}, #{datetimeLastchange,jdbcType=TIMESTAMP})"
    })
    @SelectKey(statement="SELECT LAST_INSERT_ID()", keyProperty="id", before=false, resultType=Integer.class)
    int insert(SwMeeting record);

    int insertSelective(SwMeeting record);

    List<SwMeeting> selectByExampleWithRowbounds(SwMeetingExample example, RowBounds rowBounds);

    List<SwMeeting> selectByExample(SwMeetingExample example);

    @Select({
        "select",
        "id, room, abc_meeting, title, description, meeting_date, start_time, end_time, ",
        "resource_ids, is_repeat, cycle, cycle_start, cycle_expire, status, webex_url, ",
        "create_time, datetime_lastchange",
        "from sw_meeting",
        "where id = #{id,jdbcType=INTEGER}"
    })
    @ResultMap("com.foxconn.sw.data.mapper.auto.SwMeetingMapper.BaseResultMap")
    SwMeeting selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") SwMeeting record, @Param("example") SwMeetingExample example);

    int updateByExample(@Param("record") SwMeeting record, @Param("example") SwMeetingExample example);

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
          "status = #{status,jdbcType=INTEGER},",
          "webex_url = #{webexUrl,jdbcType=VARCHAR},",
          "create_time = #{createTime,jdbcType=TIMESTAMP},",
          "datetime_lastchange = #{datetimeLastchange,jdbcType=TIMESTAMP}",
        "where id = #{id,jdbcType=INTEGER}"
    })
    int updateByPrimaryKey(SwMeeting record);
}