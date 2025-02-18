package com.foxconn.sw.data.mapper.auto;

import com.foxconn.sw.data.entity.SwScheduleInfo;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.SelectKey;

@Mapper
public interface SwScheduleInfoMapper {
    @Insert({
        "insert into sw_schedule_info (type, employee_no, ",
        "place, start_date, ",
        "end_date, is_delete, ",
        "create_time, datetime_lastchange)",
        "values (#{type,jdbcType=VARCHAR}, #{employeeNo,jdbcType=VARCHAR}, ",
        "#{place,jdbcType=VARCHAR}, #{startDate,jdbcType=VARCHAR}, ",
        "#{endDate,jdbcType=VARCHAR}, #{isDelete,jdbcType=INTEGER}, ",
        "#{createTime,jdbcType=TIMESTAMP}, #{datetimeLastchange,jdbcType=TIMESTAMP})"
    })
    @SelectKey(statement="SELECT LAST_INSERT_ID()", keyProperty="id", before=false, resultType=Long.class)
    int insert(SwScheduleInfo record);

    @InsertProvider(type=SwScheduleInfoSqlProvider.class, method="insertSelective")
    @SelectKey(statement="SELECT LAST_INSERT_ID()", keyProperty="id", before=false, resultType=Long.class)
    int insertSelective(SwScheduleInfo record);
}