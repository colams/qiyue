package com.foxconn.sw.data.mapper.auto;

import com.foxconn.sw.data.entity.SwWorkReportScore;
import com.foxconn.sw.data.entity.SwWorkReportScoreExample;
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
public interface SwWorkReportScoreMapper {
    int deleteByExample(SwWorkReportScoreExample example);

    @Delete({
        "delete from sw_work_report_score",
        "where id = #{id,jdbcType=INTEGER}"
    })
    int deleteByPrimaryKey(Integer id);

    @Insert({
        "insert into sw_work_report_score (employee_no, year_week, ",
        "score, operator, ",
        "create_time, datetime_lastchange)",
        "values (#{employeeNo,jdbcType=VARCHAR}, #{yearWeek,jdbcType=VARCHAR}, ",
        "#{score,jdbcType=INTEGER}, #{operator,jdbcType=VARCHAR}, ",
        "#{createTime,jdbcType=TIMESTAMP}, #{datetimeLastchange,jdbcType=TIMESTAMP})"
    })
    @SelectKey(statement="SELECT LAST_INSERT_ID()", keyProperty="id", before=false, resultType=Integer.class)
    int insert(SwWorkReportScore record);

    int insertSelective(SwWorkReportScore record);

    List<SwWorkReportScore> selectByExampleWithRowbounds(SwWorkReportScoreExample example, RowBounds rowBounds);

    List<SwWorkReportScore> selectByExample(SwWorkReportScoreExample example);

    @Select({
        "select",
        "id, employee_no, year_week, score, operator, create_time, datetime_lastchange",
        "from sw_work_report_score",
        "where id = #{id,jdbcType=INTEGER}"
    })
    @ResultMap("com.foxconn.sw.data.mapper.auto.SwWorkReportScoreMapper.BaseResultMap")
    SwWorkReportScore selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") SwWorkReportScore record, @Param("example") SwWorkReportScoreExample example);

    int updateByExample(@Param("record") SwWorkReportScore record, @Param("example") SwWorkReportScoreExample example);

    int updateByPrimaryKeySelective(SwWorkReportScore record);

    @Update({
        "update sw_work_report_score",
        "set employee_no = #{employeeNo,jdbcType=VARCHAR},",
          "year_week = #{yearWeek,jdbcType=VARCHAR},",
          "score = #{score,jdbcType=INTEGER},",
          "operator = #{operator,jdbcType=VARCHAR},",
          "create_time = #{createTime,jdbcType=TIMESTAMP},",
          "datetime_lastchange = #{datetimeLastchange,jdbcType=TIMESTAMP}",
        "where id = #{id,jdbcType=INTEGER}"
    })
    int updateByPrimaryKey(SwWorkReportScore record);
}