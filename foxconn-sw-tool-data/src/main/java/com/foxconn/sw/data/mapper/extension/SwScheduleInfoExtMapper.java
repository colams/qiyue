package com.foxconn.sw.data.mapper.extension;

import com.foxconn.sw.data.entity.SwScheduleInfo;
import com.foxconn.sw.data.mapper.auto.SwScheduleInfoMapper;
import org.apache.ibatis.annotations.Select;
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
    List<SwScheduleInfo> getScheduleInfos(String employeeNo, String startOfMonth, String endOfMonth);
}
