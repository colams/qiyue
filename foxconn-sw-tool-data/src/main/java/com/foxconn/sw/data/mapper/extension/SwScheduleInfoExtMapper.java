package com.foxconn.sw.data.mapper.extension;

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
            "select *",
            "from sw_schedule_info ",
            "where date &gt;= #{startOfMonth,jdbcType=VARCHAR} ",
            "  and date &lt;= #{endOfMonth,jdbcType=VARCHAR}",
            "and employee_no in ",
            "<foreach collection='departmentIds' item='id' open='(' separator=',' close=')'>",
            "#{id,jdbcType=VARCHAR}",
            "</foreach>",
            "</script>"
    })
    List<SwScheduleInfo> getTeamScheduleInfos(List<String> employeeNos, String startDate, String endDate);

    @Update({
            "update sw_schedule_info",
            "set place = #{place,jdbcType=VARCHAR}",
            " ,type = #{type,jdbcType=VARCHAR} ",
            "where id = #{id,jdbcType=BIGINT}"
    })
    int updateScheduleInfo(SwScheduleInfo scheduleInfo);
}
