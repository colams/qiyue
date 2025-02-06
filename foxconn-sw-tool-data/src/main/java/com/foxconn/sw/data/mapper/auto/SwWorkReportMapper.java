package com.foxconn.sw.data.mapper.auto;

import com.foxconn.sw.data.entity.SwWorkReport;
import com.foxconn.sw.data.entity.SwWorkReportExample;
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
public interface SwWorkReportMapper {
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

    @InsertProvider(type=SwWorkReportSqlProvider.class, method="insertSelective")
    @SelectKey(statement="SELECT LAST_INSERT_ID()", keyProperty="id", before=false, resultType=Integer.class)
    int insertSelective(SwWorkReport record);

    @SelectProvider(type=SwWorkReportSqlProvider.class, method="selectByExample")
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="report_type", property="reportType", jdbcType=JdbcType.INTEGER),
        @Result(column="employee_no", property="employeeNo", jdbcType=JdbcType.VARCHAR),
        @Result(column="year_week", property="yearWeek", jdbcType=JdbcType.VARCHAR),
        @Result(column="week", property="week", jdbcType=JdbcType.INTEGER),
        @Result(column="project", property="project", jdbcType=JdbcType.VARCHAR),
        @Result(column="days", property="days", jdbcType=JdbcType.DOUBLE),
        @Result(column="target", property="target", jdbcType=JdbcType.INTEGER),
        @Result(column="current", property="current", jdbcType=JdbcType.INTEGER),
        @Result(column="status", property="status", jdbcType=JdbcType.INTEGER),
        @Result(column="description", property="description", jdbcType=JdbcType.VARCHAR),
        @Result(column="remark", property="remark", jdbcType=JdbcType.VARCHAR),
        @Result(column="is_delete", property="isDelete", jdbcType=JdbcType.INTEGER),
        @Result(column="create_time", property="createTime", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="datetime_lastchange", property="datetimeLastchange", jdbcType=JdbcType.TIMESTAMP)
    })
    List<SwWorkReport> selectByExampleWithRowbounds(SwWorkReportExample example, RowBounds rowBounds);

    @SelectProvider(type=SwWorkReportSqlProvider.class, method="selectByExample")
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="report_type", property="reportType", jdbcType=JdbcType.INTEGER),
        @Result(column="employee_no", property="employeeNo", jdbcType=JdbcType.VARCHAR),
        @Result(column="year_week", property="yearWeek", jdbcType=JdbcType.VARCHAR),
        @Result(column="week", property="week", jdbcType=JdbcType.INTEGER),
        @Result(column="project", property="project", jdbcType=JdbcType.VARCHAR),
        @Result(column="days", property="days", jdbcType=JdbcType.DOUBLE),
        @Result(column="target", property="target", jdbcType=JdbcType.INTEGER),
        @Result(column="current", property="current", jdbcType=JdbcType.INTEGER),
        @Result(column="status", property="status", jdbcType=JdbcType.INTEGER),
        @Result(column="description", property="description", jdbcType=JdbcType.VARCHAR),
        @Result(column="remark", property="remark", jdbcType=JdbcType.VARCHAR),
        @Result(column="is_delete", property="isDelete", jdbcType=JdbcType.INTEGER),
        @Result(column="create_time", property="createTime", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="datetime_lastchange", property="datetimeLastchange", jdbcType=JdbcType.TIMESTAMP)
    })
    List<SwWorkReport> selectByExample(SwWorkReportExample example);

    @Select({
        "select",
        "id, report_type, employee_no, year_week, week, project, days, target, current, ",
        "status, description, remark, is_delete, create_time, datetime_lastchange",
        "from sw_work_report",
        "where id = #{id,jdbcType=INTEGER}"
    })
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="report_type", property="reportType", jdbcType=JdbcType.INTEGER),
        @Result(column="employee_no", property="employeeNo", jdbcType=JdbcType.VARCHAR),
        @Result(column="year_week", property="yearWeek", jdbcType=JdbcType.VARCHAR),
        @Result(column="week", property="week", jdbcType=JdbcType.INTEGER),
        @Result(column="project", property="project", jdbcType=JdbcType.VARCHAR),
        @Result(column="days", property="days", jdbcType=JdbcType.DOUBLE),
        @Result(column="target", property="target", jdbcType=JdbcType.INTEGER),
        @Result(column="current", property="current", jdbcType=JdbcType.INTEGER),
        @Result(column="status", property="status", jdbcType=JdbcType.INTEGER),
        @Result(column="description", property="description", jdbcType=JdbcType.VARCHAR),
        @Result(column="remark", property="remark", jdbcType=JdbcType.VARCHAR),
        @Result(column="is_delete", property="isDelete", jdbcType=JdbcType.INTEGER),
        @Result(column="create_time", property="createTime", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="datetime_lastchange", property="datetimeLastchange", jdbcType=JdbcType.TIMESTAMP)
    })
    SwWorkReport selectByPrimaryKey(Integer id);

    @UpdateProvider(type=SwWorkReportSqlProvider.class, method="updateByExampleSelective")
    int updateByExampleSelective(@Param("record") SwWorkReport record, @Param("example") SwWorkReportExample example);

    @UpdateProvider(type=SwWorkReportSqlProvider.class, method="updateByExample")
    int updateByExample(@Param("record") SwWorkReport record, @Param("example") SwWorkReportExample example);

    @UpdateProvider(type=SwWorkReportSqlProvider.class, method="updateByPrimaryKeySelective")
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