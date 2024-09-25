package com.foxconn.sw.data.mapper.auto;

import com.foxconn.sw.data.entity.SwWorkReportLock;
import com.foxconn.sw.data.entity.SwWorkReportLockExample;
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
public interface SwWorkReportLockMapper {
    @SelectProvider(type=SwWorkReportLockSqlProvider.class, method="countByExample")
    long countByExample(SwWorkReportLockExample example);

    @DeleteProvider(type=SwWorkReportLockSqlProvider.class, method="deleteByExample")
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

    @InsertProvider(type=SwWorkReportLockSqlProvider.class, method="insertSelective")
    @SelectKey(statement="SELECT LAST_INSERT_ID()", keyProperty="id", before=false, resultType=Integer.class)
    int insertSelective(SwWorkReportLock record);

    @SelectProvider(type=SwWorkReportLockSqlProvider.class, method="selectByExample")
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="year_week", property="yearWeek", jdbcType=JdbcType.VARCHAR),
        @Result(column="operator", property="operator", jdbcType=JdbcType.VARCHAR),
        @Result(column="lock_status", property="lockStatus", jdbcType=JdbcType.INTEGER),
        @Result(column="create_time", property="createTime", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="datetime_lastchange", property="datetimeLastchange", jdbcType=JdbcType.TIMESTAMP)
    })
    List<SwWorkReportLock> selectByExampleWithRowbounds(SwWorkReportLockExample example, RowBounds rowBounds);

    @SelectProvider(type=SwWorkReportLockSqlProvider.class, method="selectByExample")
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="year_week", property="yearWeek", jdbcType=JdbcType.VARCHAR),
        @Result(column="operator", property="operator", jdbcType=JdbcType.VARCHAR),
        @Result(column="lock_status", property="lockStatus", jdbcType=JdbcType.INTEGER),
        @Result(column="create_time", property="createTime", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="datetime_lastchange", property="datetimeLastchange", jdbcType=JdbcType.TIMESTAMP)
    })
    List<SwWorkReportLock> selectByExample(SwWorkReportLockExample example);

    @Select({
        "select",
        "id, year_week, operator, lock_status, create_time, datetime_lastchange",
        "from sw_work_report_lock",
        "where id = #{id,jdbcType=INTEGER}"
    })
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="year_week", property="yearWeek", jdbcType=JdbcType.VARCHAR),
        @Result(column="operator", property="operator", jdbcType=JdbcType.VARCHAR),
        @Result(column="lock_status", property="lockStatus", jdbcType=JdbcType.INTEGER),
        @Result(column="create_time", property="createTime", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="datetime_lastchange", property="datetimeLastchange", jdbcType=JdbcType.TIMESTAMP)
    })
    SwWorkReportLock selectByPrimaryKey(Integer id);

    @UpdateProvider(type=SwWorkReportLockSqlProvider.class, method="updateByExampleSelective")
    int updateByExampleSelective(@Param("record") SwWorkReportLock record, @Param("example") SwWorkReportLockExample example);

    @UpdateProvider(type=SwWorkReportLockSqlProvider.class, method="updateByExample")
    int updateByExample(@Param("record") SwWorkReportLock record, @Param("example") SwWorkReportLockExample example);

    @UpdateProvider(type=SwWorkReportLockSqlProvider.class, method="updateByPrimaryKeySelective")
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