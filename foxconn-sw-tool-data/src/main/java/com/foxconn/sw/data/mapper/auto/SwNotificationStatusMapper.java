package com.foxconn.sw.data.mapper.auto;

import com.foxconn.sw.data.entity.SwNotificationStatus;
import com.foxconn.sw.data.entity.SwNotificationStatusExample;
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
public interface SwNotificationStatusMapper {
    @SelectProvider(type=SwNotificationStatusSqlProvider.class, method="countByExample")
    long countByExample(SwNotificationStatusExample example);

    @DeleteProvider(type=SwNotificationStatusSqlProvider.class, method="deleteByExample")
    int deleteByExample(SwNotificationStatusExample example);

    @Delete({
        "delete from sw_notification_status",
        "where id = #{id,jdbcType=BIGINT}"
    })
    int deleteByPrimaryKey(Long id);

    @Insert({
        "insert into sw_notification_status (notification_id, employee_no, ",
        "status, create_time, ",
        "datetime_lastchange)",
        "values (#{notificationId,jdbcType=BIGINT}, #{employeeNo,jdbcType=VARCHAR}, ",
        "#{status,jdbcType=VARCHAR}, #{createTime,jdbcType=TIMESTAMP}, ",
        "#{datetimeLastchange,jdbcType=TIMESTAMP})"
    })
    @SelectKey(statement="SELECT LAST_INSERT_ID()", keyProperty="id", before=false, resultType=Long.class)
    int insert(SwNotificationStatus record);

    @InsertProvider(type=SwNotificationStatusSqlProvider.class, method="insertSelective")
    @SelectKey(statement="SELECT LAST_INSERT_ID()", keyProperty="id", before=false, resultType=Long.class)
    int insertSelective(SwNotificationStatus record);

    @SelectProvider(type=SwNotificationStatusSqlProvider.class, method="selectByExample")
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.BIGINT, id=true),
        @Result(column="notification_id", property="notificationId", jdbcType=JdbcType.BIGINT),
        @Result(column="employee_no", property="employeeNo", jdbcType=JdbcType.VARCHAR),
        @Result(column="status", property="status", jdbcType=JdbcType.VARCHAR),
        @Result(column="create_time", property="createTime", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="datetime_lastchange", property="datetimeLastchange", jdbcType=JdbcType.TIMESTAMP)
    })
    List<SwNotificationStatus> selectByExampleWithRowbounds(SwNotificationStatusExample example, RowBounds rowBounds);

    @SelectProvider(type=SwNotificationStatusSqlProvider.class, method="selectByExample")
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.BIGINT, id=true),
        @Result(column="notification_id", property="notificationId", jdbcType=JdbcType.BIGINT),
        @Result(column="employee_no", property="employeeNo", jdbcType=JdbcType.VARCHAR),
        @Result(column="status", property="status", jdbcType=JdbcType.VARCHAR),
        @Result(column="create_time", property="createTime", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="datetime_lastchange", property="datetimeLastchange", jdbcType=JdbcType.TIMESTAMP)
    })
    List<SwNotificationStatus> selectByExample(SwNotificationStatusExample example);

    @Select({
        "select",
        "id, notification_id, employee_no, status, create_time, datetime_lastchange",
        "from sw_notification_status",
        "where id = #{id,jdbcType=BIGINT}"
    })
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.BIGINT, id=true),
        @Result(column="notification_id", property="notificationId", jdbcType=JdbcType.BIGINT),
        @Result(column="employee_no", property="employeeNo", jdbcType=JdbcType.VARCHAR),
        @Result(column="status", property="status", jdbcType=JdbcType.VARCHAR),
        @Result(column="create_time", property="createTime", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="datetime_lastchange", property="datetimeLastchange", jdbcType=JdbcType.TIMESTAMP)
    })
    SwNotificationStatus selectByPrimaryKey(Long id);

    @UpdateProvider(type=SwNotificationStatusSqlProvider.class, method="updateByExampleSelective")
    int updateByExampleSelective(@Param("record") SwNotificationStatus record, @Param("example") SwNotificationStatusExample example);

    @UpdateProvider(type=SwNotificationStatusSqlProvider.class, method="updateByExample")
    int updateByExample(@Param("record") SwNotificationStatus record, @Param("example") SwNotificationStatusExample example);

    @UpdateProvider(type=SwNotificationStatusSqlProvider.class, method="updateByPrimaryKeySelective")
    int updateByPrimaryKeySelective(SwNotificationStatus record);

    @Update({
        "update sw_notification_status",
        "set notification_id = #{notificationId,jdbcType=BIGINT},",
          "employee_no = #{employeeNo,jdbcType=VARCHAR},",
          "status = #{status,jdbcType=VARCHAR},",
          "create_time = #{createTime,jdbcType=TIMESTAMP},",
          "datetime_lastchange = #{datetimeLastchange,jdbcType=TIMESTAMP}",
        "where id = #{id,jdbcType=BIGINT}"
    })
    int updateByPrimaryKey(SwNotificationStatus record);
}