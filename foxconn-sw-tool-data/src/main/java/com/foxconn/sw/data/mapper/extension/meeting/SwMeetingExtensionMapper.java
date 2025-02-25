package com.foxconn.sw.data.mapper.extension.meeting;

import com.foxconn.sw.data.dto.request.meeting.ListMeetingV2Params;
import com.foxconn.sw.data.entity.SwMeeting;
import com.foxconn.sw.data.entity.extension.MeetingEntity;
import com.foxconn.sw.data.entity.extension.MeetingTjEntity;
import com.foxconn.sw.data.entity.extension.SwMeetingExtension;
import com.foxconn.sw.data.mapper.auto.SwMeetingMapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.type.JdbcType;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface SwMeetingExtensionMapper extends SwMeetingMapper {


    @Select({
            "select sm.*",
            "from sw_meeting sm ",
            "inner join sw_meeting_member smm on sm.id = smm.meeting_id ",
            "where status = 0 and smm.is_delete = 0",
            "and smm.employee_no = #{employeeNo,jdbcType=VARCHAR}",
            "and ((sm.cycle_start <= #{nowDate,jdbcType=VARCHAR} and (sm.cycle_expire >= #{nowDate,jdbcType=VARCHAR} ||sm.cycle_expire = '' || sm.cycle_expire is null )) or (meeting_date >= #{startDate,jdbcType=VARCHAR} and meeting_date < #{endDate,jdbcType=VARCHAR}))"
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
    List<SwMeeting> selectMeetings(@Param("nowDate") LocalDate nowDate,
                                   @Param("startDate") LocalDate startDate,
                                   @Param("endDate") LocalDate endDate,
                                   @Param("employeeNo") String employeeNo);


    @Select({
            "<script>",
            "select sm.*,smcd.id as smcdID,smcd.meeting_date as meetingDate2,smcd.start_time as startTime2,smcd.end_time as endTime2 ",
            "from sw_meeting sm ",
            "inner join sw_meeting_member smm on sm.id = smm.meeting_id ",
            "left join sw_meeting_cycle_detail smcd on sm.id = smcd.meeting_id ",
            "where status = 0 and smm.is_delete = 0",
            "and smm.employee_no = #{employeeNo,jdbcType=VARCHAR}",
            "and sm.create_time &gt;= #{createTime,jdbcType=VARCHAR}",
//   "and ((sm.cycle_start <= #{nowDate,jdbcType=VARCHAR} and (sm.cycle_expire >= #{nowDate,jdbcType=VARCHAR} ||sm.cycle_expire = '' || sm.cycle_expire is null )) or (meeting_date >= #{startDate,jdbcType=VARCHAR} and meeting_date < #{endDate,jdbcType=VARCHAR}))",
            "<if test='data.value!=null and data.value!=\"\"' >",
            " and sm.title like CONCAT('%', #{data.value,jdbcType=VARCHAR}, '%') ",
            "</if> ",
            "</script>"
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
    })
    List<SwMeetingExtension> selectMeetingList(@Param("employeeNo") String employeeNo, @Param("createTime") LocalDateTime createTime, @Param("data") ListMeetingV2Params data);


    @Select({"<script>",
            "select s.id meetingId, ",
            "s.title, ",
            "s.cycle, ",
            "s.start_time, ",
            "s.end_time, ",
            "s.meeting_date, ",
            "s.cycle_start, ",
            "s.cycle_expire, ",
            "s.room, ",
            "s.meeting_date,",
            "s.resource_ids",
            "from sw_meeting s ",
            " inner join sw_meeting_member m on s.id = m.meeting_id",
            "where s.status = 0 ",
            "and m.is_delete = 0 ",
            "and m.employee_no = #{employeeNo,jdbcType=VARCHAR} ",
            "<if test='data.value!=null and data.value!=\"\"' >",
            "and s.title like CONCAT('%', #{data.value,jdbcType=VARCHAR}, '%') ",
            "</if> ",
            "and ((s.cycle ='' and s.meeting_date &gt;= #{searchStart,jdbcType=VARCHAR} and s.meeting_date &lt;= #{searchEnd,jdbcType=VARCHAR}) or ",
            "   (s.cycle !='' and (s.cycle_expire &gt;= #{searchStart,jdbcType=VARCHAR} or s.cycle_expire='')))",
            "</script>"
    })
    @Results({
            @Result(column = "meetingId", property = "meetingId", jdbcType = JdbcType.INTEGER),
            @Result(column = "title", property = "title", jdbcType = JdbcType.VARCHAR),
            @Result(column = "cycle", property = "cycle", jdbcType = JdbcType.VARCHAR),
            @Result(column = "cycle_start", property = "cycleStart", jdbcType = JdbcType.VARCHAR),
            @Result(column = "cycle_expire", property = "cycleExpire", jdbcType = JdbcType.VARCHAR),
            @Result(column = "start_time", property = "startTime", jdbcType = JdbcType.VARCHAR),
            @Result(column = "end_time", property = "endTime", jdbcType = JdbcType.VARCHAR),
            @Result(column = "room", property = "room", jdbcType = JdbcType.VARCHAR),
            @Result(column = "meeting_date", property = "meetingDate", jdbcType = JdbcType.VARCHAR),
            @Result(column = "resource_ids", property = "resources", jdbcType = JdbcType.VARCHAR),
    })
    List<MeetingEntity> selectMeetingList2(@Param("employeeNo") String employeeNo,
                                           @Param("searchStart") String searchStart,
                                           @Param("searchEnd") String searchEnd,
                                           @Param("data") ListMeetingV2Params data);


    @Select({"<script>",
            "select m.id, m.meeting_date as mDate, m.cycle, c.meeting_date cDate, c.cancel ",
            "from sw_meeting m ",
            "         inner join sw_meeting_member sm ",
            "                    on m.id = sm.meeting_id ",
            "         left join sw_meeting_cycle_detail c on m.id = c.meeting_id ",
            "where employee_no = #{employeeNo,jdbcType=VARCHAR} ",
            "  and m.status = 0 ",
            "  and (m.meeting_date = #{currentDay,jdbcType=VARCHAR} ",
            "    or (m.cycle_start &lt;= #{currentDay,jdbcType=VARCHAR} ",
            "and (m.cycle_expire &gt;= #{currentDay,jdbcType=VARCHAR} or m.cycle_expire = '')) and m.cycle_start &gt; '')",
            "</script>"
    })
    @Results({
            @Result(column = "id", property = "id", jdbcType = JdbcType.INTEGER),
            @Result(column = "mDate", property = "meetingDate", jdbcType = JdbcType.VARCHAR),
            @Result(column = "cycle", property = "cycle", jdbcType = JdbcType.VARCHAR),
            @Result(column = "cDate", property = "detailMeetingDate", jdbcType = JdbcType.VARCHAR),
            @Result(column = "cancel", property = "cancel", jdbcType = JdbcType.INTEGER),
    })
    List<MeetingTjEntity> getMeetingCount(String employeeNo, String currentDay);
}
