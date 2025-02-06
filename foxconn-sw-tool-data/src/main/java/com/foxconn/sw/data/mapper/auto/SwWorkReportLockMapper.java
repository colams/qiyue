package com.foxconn.sw.data.mapper.auto;

import com.foxconn.sw.data.entity.SwWorkReportLock;
import com.foxconn.sw.data.entity.SwWorkReportLockExample;
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
public interface SwWorkReportLockMapper {
    int deleteByExample(SwWorkReportLockExample example);

    @Delete({
        "delete from sw_work_report_lock",
        "where id = #{id,jdbcType=INTEGER}"
    })
    int deleteByPrimaryKey(Integer id);

    @Insert({
        "insert into sw_work_report_lock (year_week, operator, ",
        "lock_status, create_time, ",
        "datetime_lastchange)",
        "values (#{yearWeek,jdbcType=VARCHAR}, #{operator,jdbcType=VARCHAR}, ",
        "#{lockStatus,jdbcType=INTEGER}, #{createTime,jdbcType=TIMESTAMP}, ",
        "#{datetimeLastchange,jdbcType=TIMESTAMP})"
    })
    @SelectKey(statement="SELECT LAST_INSERT_ID()", keyProperty="id", before=false, resultType=Integer.class)
    int insert(SwWorkReportLock record);

    int insertSelective(SwWorkReportLock record);

    List<SwWorkReportLock> selectByExampleWithRowbounds(SwWorkReportLockExample example, RowBounds rowBounds);

    List<SwWorkReportLock> selectByExample(SwWorkReportLockExample example);

    @Select({
        "select",
        "id, year_week, operator, lock_status, create_time, datetime_lastchange",
        "from sw_work_report_lock",
        "where id = #{id,jdbcType=INTEGER}"
    })
    @ResultMap("com.foxconn.sw.data.mapper.auto.SwWorkReportLockMapper.BaseResultMap")
    SwWorkReportLock selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") SwWorkReportLock record, @Param("example") SwWorkReportLockExample example);

    int updateByExample(@Param("record") SwWorkReportLock record, @Param("example") SwWorkReportLockExample example);

    int updateByPrimaryKeySelective(SwWorkReportLock record);

    @Update({
        "update sw_work_report_lock",
        "set year_week = #{yearWeek,jdbcType=VARCHAR},",
          "operator = #{operator,jdbcType=VARCHAR},",
          "lock_status = #{lockStatus,jdbcType=INTEGER},",
          "create_time = #{createTime,jdbcType=TIMESTAMP},",
          "datetime_lastchange = #{datetimeLastchange,jdbcType=TIMESTAMP}",
        "where id = #{id,jdbcType=INTEGER}"
    })
    int updateByPrimaryKey(SwWorkReportLock record);
}