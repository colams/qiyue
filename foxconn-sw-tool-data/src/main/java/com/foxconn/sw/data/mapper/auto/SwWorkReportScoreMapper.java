package com.foxconn.sw.data.mapper.auto;

import com.foxconn.sw.data.entity.SwWorkReportScore;
import com.foxconn.sw.data.entity.SwWorkReportScoreExample;
import java.util.List;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.DeleteProvider;
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
public interface SwWorkReportScoreMapper {
    @DeleteProvider(type=SwWorkReportScoreSqlProvider.class, method="deleteByExample")
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

    @InsertProvider(type=SwWorkReportScoreSqlProvider.class, method="insertSelective")
    @SelectKey(statement="SELECT LAST_INSERT_ID()", keyProperty="id", before=false, resultType=Integer.class)
    int insertSelective(SwWorkReportScore record);

    @SelectProvider(type=SwWorkReportScoreSqlProvider.class, method="selectByExample")
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="employee_no", property="employeeNo", jdbcType=JdbcType.VARCHAR),
        @Result(column="year_week", property="yearWeek", jdbcType=JdbcType.VARCHAR),
        @Result(column="score", property="score", jdbcType=JdbcType.INTEGER),
        @Result(column="operator", property="operator", jdbcType=JdbcType.VARCHAR),
        @Result(column="create_time", property="createTime", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="datetime_lastchange", property="datetimeLastchange", jdbcType=JdbcType.TIMESTAMP)
    })
    List<SwWorkReportScore> selectByExampleWithRowbounds(SwWorkReportScoreExample example, RowBounds rowBounds);

    @SelectProvider(type=SwWorkReportScoreSqlProvider.class, method="selectByExample")
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="employee_no", property="employeeNo", jdbcType=JdbcType.VARCHAR),
        @Result(column="year_week", property="yearWeek", jdbcType=JdbcType.VARCHAR),
        @Result(column="score", property="score", jdbcType=JdbcType.INTEGER),
        @Result(column="operator", property="operator", jdbcType=JdbcType.VARCHAR),
        @Result(column="create_time", property="createTime", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="datetime_lastchange", property="datetimeLastchange", jdbcType=JdbcType.TIMESTAMP)
    })
    List<SwWorkReportScore> selectByExample(SwWorkReportScoreExample example);

    @Select({
        "select",
        "id, employee_no, year_week, score, operator, create_time, datetime_lastchange",
        "from sw_work_report_score",
        "where id = #{id,jdbcType=INTEGER}"
    })
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="employee_no", property="employeeNo", jdbcType=JdbcType.VARCHAR),
        @Result(column="year_week", property="yearWeek", jdbcType=JdbcType.VARCHAR),
        @Result(column="score", property="score", jdbcType=JdbcType.INTEGER),
        @Result(column="operator", property="operator", jdbcType=JdbcType.VARCHAR),
        @Result(column="create_time", property="createTime", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="datetime_lastchange", property="datetimeLastchange", jdbcType=JdbcType.TIMESTAMP)
    })
    SwWorkReportScore selectByPrimaryKey(Integer id);

    @UpdateProvider(type=SwWorkReportScoreSqlProvider.class, method="updateByExampleSelective")
    int updateByExampleSelective(@Param("record") SwWorkReportScore record, @Param("example") SwWorkReportScoreExample example);

    @UpdateProvider(type=SwWorkReportScoreSqlProvider.class, method="updateByExample")
    int updateByExample(@Param("record") SwWorkReportScore record, @Param("example") SwWorkReportScoreExample example);

    @UpdateProvider(type=SwWorkReportScoreSqlProvider.class, method="updateByPrimaryKeySelective")
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