package com.foxconn.sw.data.mapper.auto;

import com.foxconn.sw.data.entity.SwMeetingCycleDetail;
import com.foxconn.sw.data.entity.SwMeetingCycleDetailExample;
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
public interface SwMeetingCycleDetailMapper {
    long countByExample(SwMeetingCycleDetailExample example);

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
        "end_time, webex_url, ",
        "cancel, create_time, ",
        "datetime_lastchange)",
        "values (#{meetingId,jdbcType=INTEGER}, #{room,jdbcType=VARCHAR}, ",
        "#{title,jdbcType=VARCHAR}, #{description,jdbcType=VARCHAR}, ",
        "#{resourceIds,jdbcType=VARCHAR}, #{operator,jdbcType=VARCHAR}, ",
        "#{meetingDate,jdbcType=VARCHAR}, #{startTime,jdbcType=VARCHAR}, ",
        "#{endTime,jdbcType=VARCHAR}, #{webexUrl,jdbcType=VARCHAR}, ",
        "#{cancel,jdbcType=INTEGER}, #{createTime,jdbcType=TIMESTAMP}, ",
        "#{datetimeLastchange,jdbcType=TIMESTAMP})"
    })
    @SelectKey(statement="SELECT LAST_INSERT_ID()", keyProperty="id", before=false, resultType=Integer.class)
    int insert(SwMeetingCycleDetail record);

    int insertSelective(SwMeetingCycleDetail record);

    List<SwMeetingCycleDetail> selectByExampleWithRowbounds(SwMeetingCycleDetailExample example, RowBounds rowBounds);

    List<SwMeetingCycleDetail> selectByExample(SwMeetingCycleDetailExample example);

    @Select({
        "select",
        "id, meeting_id, room, title, description, resource_ids, operator, meeting_date, ",
        "start_time, end_time, webex_url, cancel, create_time, datetime_lastchange",
        "from sw_meeting_cycle_detail",
        "where id = #{id,jdbcType=INTEGER}"
    })
    @ResultMap("com.foxconn.sw.data.mapper.auto.SwMeetingCycleDetailMapper.BaseResultMap")
    SwMeetingCycleDetail selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") SwMeetingCycleDetail record, @Param("example") SwMeetingCycleDetailExample example);

    int updateByExample(@Param("record") SwMeetingCycleDetail record, @Param("example") SwMeetingCycleDetailExample example);

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
          "webex_url = #{webexUrl,jdbcType=VARCHAR},",
          "cancel = #{cancel,jdbcType=INTEGER},",
          "create_time = #{createTime,jdbcType=TIMESTAMP},",
          "datetime_lastchange = #{datetimeLastchange,jdbcType=TIMESTAMP}",
        "where id = #{id,jdbcType=INTEGER}"
    })
    int updateByPrimaryKey(SwMeetingCycleDetail record);
}