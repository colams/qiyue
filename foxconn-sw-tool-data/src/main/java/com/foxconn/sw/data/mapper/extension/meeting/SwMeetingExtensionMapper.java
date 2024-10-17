package com.foxconn.sw.data.mapper.extension.meeting;

import com.foxconn.sw.data.entity.SwMeeting;
import com.foxconn.sw.data.mapper.auto.SwMeetingMapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.type.JdbcType;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface SwMeetingExtensionMapper extends SwMeetingMapper {


    @Select({
            "select sm.*",
            "from sw_meeting sm ",
            "inner join sw_meeting_member smm on sm.id = smm.meeting_id ",
            "where status = 0 and smm.is_delete = 0",
            "and smm.employee_no = #{employeeNo,jdbcType=VARCHAR}",
            "and ((is_repeat = 1 and sm.cycle_start <= #{startDate,jdbcType=VARCHAR}) or (meeting_date >= #{startDate,jdbcType=VARCHAR} and meeting_date < #{endDate,jdbcType=VARCHAR}))"
    })
    @Results({
            @Result(column = "id", property = "id", jdbcType = JdbcType.INTEGER, id = true),
            @Result(column = "chairman", property = "chairman", jdbcType = JdbcType.VARCHAR),
            @Result(column = "room", property = "room", jdbcType = JdbcType.VARCHAR),
            @Result(column = "start_time", property = "startTime", jdbcType = JdbcType.VARCHAR),
            @Result(column = "end_time", property = "endTime", jdbcType = JdbcType.VARCHAR),
            @Result(column = "meeting_date", property = "meetingDate", jdbcType = JdbcType.VARCHAR),
            @Result(column = "title", property = "title", jdbcType = JdbcType.VARCHAR),
            @Result(column = "description", property = "description", jdbcType = JdbcType.VARCHAR),
            @Result(column = "resource_ids", property = "resourceIds", jdbcType = JdbcType.VARCHAR),
            @Result(column = "repeat", property = "repeat", jdbcType = JdbcType.INTEGER),
            @Result(column = "cycle", property = "cycle", jdbcType = JdbcType.VARCHAR),
            @Result(column = "status", property = "status", jdbcType = JdbcType.INTEGER),
            @Result(column = "create_time", property = "createTime", jdbcType = JdbcType.TIMESTAMP),
            @Result(column = "datetime_lastchange", property = "datetimeLastchange", jdbcType = JdbcType.TIMESTAMP)
    })
    List<SwMeeting> selectMeetings(@Param("startDate") LocalDate startDate, @Param("endDate") LocalDate endDate, @Param("employeeNo") String employeeNo);
}
