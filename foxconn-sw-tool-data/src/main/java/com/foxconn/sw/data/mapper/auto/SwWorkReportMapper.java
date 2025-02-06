package com.foxconn.sw.data.mapper.auto;

import com.foxconn.sw.data.entity.SwWorkReport;
import com.foxconn.sw.data.entity.SwWorkReportExample;
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
public interface SwWorkReportMapper {
    long countByExample(SwWorkReportExample example);

    int deleteByExample(SwWorkReportExample example);

    @Delete({
        "delete from sw_work_report",
        "where id = #{id,jdbcType=INTEGER}"
    })
    int deleteByPrimaryKey(Integer id);

    @Insert({
        "insert into sw_work_report (report_type, employee_no, ",
        "year_week, week, ",
        "project, days, target, ",
        "current, status, ",
        "description, remark, ",
        "is_delete, create_time, ",
        "datetime_lastchange)",
        "values (#{reportType,jdbcType=INTEGER}, #{employeeNo,jdbcType=VARCHAR}, ",
        "#{yearWeek,jdbcType=VARCHAR}, #{week,jdbcType=INTEGER}, ",
        "#{project,jdbcType=VARCHAR}, #{days,jdbcType=DOUBLE}, #{target,jdbcType=INTEGER}, ",
        "#{current,jdbcType=INTEGER}, #{status,jdbcType=INTEGER}, ",
        "#{description,jdbcType=VARCHAR}, #{remark,jdbcType=VARCHAR}, ",
        "#{isDelete,jdbcType=INTEGER}, #{createTime,jdbcType=TIMESTAMP}, ",
        "#{datetimeLastchange,jdbcType=TIMESTAMP})"
    })
    @SelectKey(statement="SELECT LAST_INSERT_ID()", keyProperty="id", before=false, resultType=Integer.class)
    int insert(SwWorkReport record);

    int insertSelective(SwWorkReport record);

    List<SwWorkReport> selectByExampleWithRowbounds(SwWorkReportExample example, RowBounds rowBounds);

    List<SwWorkReport> selectByExample(SwWorkReportExample example);

    @Select({
        "select",
        "id, report_type, employee_no, year_week, week, project, days, target, current, ",
        "status, description, remark, is_delete, create_time, datetime_lastchange",
        "from sw_work_report",
        "where id = #{id,jdbcType=INTEGER}"
    })
    @ResultMap("com.foxconn.sw.data.mapper.auto.SwWorkReportMapper.BaseResultMap")
    SwWorkReport selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") SwWorkReport record, @Param("example") SwWorkReportExample example);

    int updateByExample(@Param("record") SwWorkReport record, @Param("example") SwWorkReportExample example);

    int updateByPrimaryKeySelective(SwWorkReport record);

    @Update({
        "update sw_work_report",
        "set report_type = #{reportType,jdbcType=INTEGER},",
          "employee_no = #{employeeNo,jdbcType=VARCHAR},",
          "year_week = #{yearWeek,jdbcType=VARCHAR},",
          "week = #{week,jdbcType=INTEGER},",
          "project = #{project,jdbcType=VARCHAR},",
          "days = #{days,jdbcType=DOUBLE},",
          "target = #{target,jdbcType=INTEGER},",
          "current = #{current,jdbcType=INTEGER},",
          "status = #{status,jdbcType=INTEGER},",
          "description = #{description,jdbcType=VARCHAR},",
          "remark = #{remark,jdbcType=VARCHAR},",
          "is_delete = #{isDelete,jdbcType=INTEGER},",
          "create_time = #{createTime,jdbcType=TIMESTAMP},",
          "datetime_lastchange = #{datetimeLastchange,jdbcType=TIMESTAMP}",
        "where id = #{id,jdbcType=INTEGER}"
    })
    int updateByPrimaryKey(SwWorkReport record);
}