package com.foxconn.sw.data.mapper.auto;

import com.foxconn.sw.data.entity.SwMeetingMinuteDetail;
import com.foxconn.sw.data.entity.SwMeetingMinuteDetailExample;
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
public interface SwMeetingMinuteDetailMapper {
    int deleteByExample(SwMeetingMinuteDetailExample example);

    @Delete({
        "delete from sw_meeting_minute_detail",
        "where id = #{id,jdbcType=BIGINT}"
    })
    int deleteByPrimaryKey(Long id);

    @Insert({
        "insert into sw_meeting_minute_detail (meeting_minutes_id, item_type, ",
        "index_no, item, direct_eno, ",
        "due_date, status, ",
        "is_delete, remark, ",
        "create_time, datetime_lastchange)",
        "values (#{meetingMinutesId,jdbcType=BIGINT}, #{itemType,jdbcType=VARCHAR}, ",
        "#{indexNo,jdbcType=INTEGER}, #{item,jdbcType=VARCHAR}, #{directEno,jdbcType=VARCHAR}, ",
        "#{dueDate,jdbcType=VARCHAR}, #{status,jdbcType=VARCHAR}, ",
        "#{isDelete,jdbcType=INTEGER}, #{remark,jdbcType=VARCHAR}, ",
        "#{createTime,jdbcType=TIMESTAMP}, #{datetimeLastchange,jdbcType=TIMESTAMP})"
    })
    @SelectKey(statement="SELECT LAST_INSERT_ID()", keyProperty="id", before=false, resultType=Long.class)
    int insert(SwMeetingMinuteDetail record);

    int insertSelective(SwMeetingMinuteDetail record);

    List<SwMeetingMinuteDetail> selectByExampleWithRowbounds(SwMeetingMinuteDetailExample example, RowBounds rowBounds);

    List<SwMeetingMinuteDetail> selectByExample(SwMeetingMinuteDetailExample example);

    @Select({
        "select",
        "id, meeting_minutes_id, item_type, index_no, item, direct_eno, due_date, status, ",
        "is_delete, remark, create_time, datetime_lastchange",
        "from sw_meeting_minute_detail",
        "where id = #{id,jdbcType=BIGINT}"
    })
    @ResultMap("com.foxconn.sw.data.mapper.auto.SwMeetingMinuteDetailMapper.BaseResultMap")
    SwMeetingMinuteDetail selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") SwMeetingMinuteDetail record, @Param("example") SwMeetingMinuteDetailExample example);

    int updateByExample(@Param("record") SwMeetingMinuteDetail record, @Param("example") SwMeetingMinuteDetailExample example);

    int updateByPrimaryKeySelective(SwMeetingMinuteDetail record);

    @Update({
        "update sw_meeting_minute_detail",
        "set meeting_minutes_id = #{meetingMinutesId,jdbcType=BIGINT},",
          "item_type = #{itemType,jdbcType=VARCHAR},",
          "index_no = #{indexNo,jdbcType=INTEGER},",
          "item = #{item,jdbcType=VARCHAR},",
          "direct_eno = #{directEno,jdbcType=VARCHAR},",
          "due_date = #{dueDate,jdbcType=VARCHAR},",
          "status = #{status,jdbcType=VARCHAR},",
          "is_delete = #{isDelete,jdbcType=INTEGER},",
          "remark = #{remark,jdbcType=VARCHAR},",
          "create_time = #{createTime,jdbcType=TIMESTAMP},",
          "datetime_lastchange = #{datetimeLastchange,jdbcType=TIMESTAMP}",
        "where id = #{id,jdbcType=BIGINT}"
    })
    int updateByPrimaryKey(SwMeetingMinuteDetail record);
}