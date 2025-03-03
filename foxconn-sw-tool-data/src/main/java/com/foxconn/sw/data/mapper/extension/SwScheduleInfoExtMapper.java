package com.foxconn.sw.data.mapper.extension;

import com.foxconn.sw.data.dto.request.schedule.ScheduleListParams;
import com.foxconn.sw.data.entity.SwScheduleInfo;
import com.foxconn.sw.data.mapper.auto.SwScheduleInfoMapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SwScheduleInfoExtMapper extends SwScheduleInfoMapper {


    @Select({"<script>",
            "select *",
            "from sw_schedule_info ",
            "where employee_no = #{employeeNo,jdbcType=VARCHAR} ",
            "  and date &gt;= #{startOfMonth,jdbcType=VARCHAR} ",
            "  and date &lt;= #{endOfMonth,jdbcType=VARCHAR}",
            "</script>"
    })
    List<SwScheduleInfo> getMyScheduleInfos(String employeeNo, String startOfMonth, String endOfMonth);

    @Select({"<script>",
            "select s.*",
            "from sw_schedule_info s inner join sw_employee e on s.employee_no=e.employee_no",
            "where s.date &gt;= #{data.startDate,jdbcType=VARCHAR} ",
            "  and s.date &lt;= #{data.endDate,jdbcType=VARCHAR}",

            "<if test='data.stationedPlace!=null and data.stationedPlace!=\"\"' >",
            "and e.stationed_place=#{data.stationedPlace,jdbcType=VARCHAR}",
            "</if> ",

            "<if test='data.identityOfCadre != null and data.identityOfCadre.length > 0' >",
            "and e.stationed_place in ",
            "<foreach collection='data.identityOfCadre' item='ioc' open='(' separator=',' close=')'>",
            "#{ioc,jdbcType=VARCHAR}",
            "</foreach>",
            "</if> ",

            "<choose>",
            "<when test='data.employeeNo!=null and data.employeeNo!=\"\"'>",
            "and s.employee_no = #{data.employeeNo,jdbcType=VARCHAR}",
            "</when>",
            "<otherwise>",
            "and s.employee_no in ",
            "<foreach collection='employeeNos' item='eno' open='(' separator=',' close=')'>",
            "#{eno,jdbcType=VARCHAR}",
            "</foreach>",
            "</otherwise>",
            "</choose>",

            "</script>"
    })
    List<SwScheduleInfo> getTeamScheduleInfos(List<String> employeeNos, ScheduleListParams data);

    @Update({
            "update sw_schedule_info",
            "set place = #{place,jdbcType=VARCHAR}",
            " ,type = #{type,jdbcType=VARCHAR} ",
            "where id = #{id,jdbcType=BIGINT}"
    })
    int updateScheduleInfo(SwScheduleInfo scheduleInfo);
}
