package com.foxconn.sw.data.mapper.auto;

import com.foxconn.sw.data.entity.SwMeetingMinuteDetail;
import com.foxconn.sw.data.entity.SwMeetingMinuteDetailExample;
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
public interface SwMeetingMinuteDetailMapper {
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

    @InsertProvider(type=SwMeetingMinuteDetailSqlProvider.class, method="insertSelective")
    @SelectKey(statement="SELECT LAST_INSERT_ID()", keyProperty="id", before=false, resultType=Long.class)
    int insertSelective(SwMeetingMinuteDetail record);

    @SelectProvider(type=SwMeetingMinuteDetailSqlProvider.class, method="selectByExample")
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.BIGINT, id=true),
        @Result(column="meeting_minutes_id", property="meetingMinutesId", jdbcType=JdbcType.BIGINT),
        @Result(column="item_type", property="itemType", jdbcType=JdbcType.VARCHAR),
        @Result(column="index_no", property="indexNo", jdbcType=JdbcType.INTEGER),
        @Result(column="item", property="item", jdbcType=JdbcType.VARCHAR),
        @Result(column="direct_eno", property="directEno", jdbcType=JdbcType.VARCHAR),
        @Result(column="due_date", property="dueDate", jdbcType=JdbcType.VARCHAR),
        @Result(column="status", property="status", jdbcType=JdbcType.VARCHAR),
        @Result(column="is_delete", property="isDelete", jdbcType=JdbcType.INTEGER),
        @Result(column="remark", property="remark", jdbcType=JdbcType.VARCHAR),
        @Result(column="create_time", property="createTime", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="datetime_lastchange", property="datetimeLastchange", jdbcType=JdbcType.TIMESTAMP)
    })
    List<SwMeetingMinuteDetail> selectByExampleWithRowbounds(SwMeetingMinuteDetailExample example, RowBounds rowBounds);

    @SelectProvider(type=SwMeetingMinuteDetailSqlProvider.class, method="selectByExample")
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.BIGINT, id=true),
        @Result(column="meeting_minutes_id", property="meetingMinutesId", jdbcType=JdbcType.BIGINT),
        @Result(column="item_type", property="itemType", jdbcType=JdbcType.VARCHAR),
        @Result(column="index_no", property="indexNo", jdbcType=JdbcType.INTEGER),
        @Result(column="item", property="item", jdbcType=JdbcType.VARCHAR),
        @Result(column="direct_eno", property="directEno", jdbcType=JdbcType.VARCHAR),
        @Result(column="due_date", property="dueDate", jdbcType=JdbcType.VARCHAR),
        @Result(column="status", property="status", jdbcType=JdbcType.VARCHAR),
        @Result(column="is_delete", property="isDelete", jdbcType=JdbcType.INTEGER),
        @Result(column="remark", property="remark", jdbcType=JdbcType.VARCHAR),
        @Result(column="create_time", property="createTime", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="datetime_lastchange", property="datetimeLastchange", jdbcType=JdbcType.TIMESTAMP)
    })
    List<SwMeetingMinuteDetail> selectByExample(SwMeetingMinuteDetailExample example);

    @Select({
        "select",
        "id, meeting_minutes_id, item_type, index_no, item, direct_eno, due_date, status, ",
        "is_delete, remark, create_time, datetime_lastchange",
        "from sw_meeting_minute_detail",
        "where id = #{id,jdbcType=BIGINT}"
    })
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.BIGINT, id=true),
        @Result(column="meeting_minutes_id", property="meetingMinutesId", jdbcType=JdbcType.BIGINT),
        @Result(column="item_type", property="itemType", jdbcType=JdbcType.VARCHAR),
        @Result(column="index_no", property="indexNo", jdbcType=JdbcType.INTEGER),
        @Result(column="item", property="item", jdbcType=JdbcType.VARCHAR),
        @Result(column="direct_eno", property="directEno", jdbcType=JdbcType.VARCHAR),
        @Result(column="due_date", property="dueDate", jdbcType=JdbcType.VARCHAR),
        @Result(column="status", property="status", jdbcType=JdbcType.VARCHAR),
        @Result(column="is_delete", property="isDelete", jdbcType=JdbcType.INTEGER),
        @Result(column="remark", property="remark", jdbcType=JdbcType.VARCHAR),
        @Result(column="create_time", property="createTime", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="datetime_lastchange", property="datetimeLastchange", jdbcType=JdbcType.TIMESTAMP)
    })
    SwMeetingMinuteDetail selectByPrimaryKey(Long id);

    @UpdateProvider(type=SwMeetingMinuteDetailSqlProvider.class, method="updateByExampleSelective")
    int updateByExampleSelective(@Param("record") SwMeetingMinuteDetail record, @Param("example") SwMeetingMinuteDetailExample example);

    @UpdateProvider(type=SwMeetingMinuteDetailSqlProvider.class, method="updateByExample")
    int updateByExample(@Param("record") SwMeetingMinuteDetail record, @Param("example") SwMeetingMinuteDetailExample example);

    @UpdateProvider(type=SwMeetingMinuteDetailSqlProvider.class, method="updateByPrimaryKeySelective")
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