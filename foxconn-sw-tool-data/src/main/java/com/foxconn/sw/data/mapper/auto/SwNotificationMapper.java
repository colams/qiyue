package com.foxconn.sw.data.mapper.auto;

import com.foxconn.sw.data.entity.SwNotification;
import com.foxconn.sw.data.entity.SwNotificationExample;
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
public interface SwNotificationMapper {
    @SelectProvider(type=SwNotificationSqlProvider.class, method="countByExample")
    long countByExample(SwNotificationExample example);

    @DeleteProvider(type=SwNotificationSqlProvider.class, method="deleteByExample")
    int deleteByExample(SwNotificationExample example);

    @Delete({
        "delete from sw_notification",
        "where id = #{id,jdbcType=INTEGER}"
    })
    int deleteByPrimaryKey(Integer id);

    @Insert({
        "insert into sw_notification (content, consumer, ",
        "status, create_time, ",
        "datetime_lastchange)",
        "values (#{content,jdbcType=VARCHAR}, #{consumer,jdbcType=VARCHAR}, ",
        "#{status,jdbcType=INTEGER}, #{createTime,jdbcType=TIMESTAMP}, ",
        "#{datetimeLastchange,jdbcType=TIMESTAMP})"
    })
    @SelectKey(statement="SELECT LAST_INSERT_ID()", keyProperty="id", before=false, resultType=Integer.class)
    int insert(SwNotification record);

    @InsertProvider(type=SwNotificationSqlProvider.class, method="insertSelective")
    @SelectKey(statement="SELECT LAST_INSERT_ID()", keyProperty="id", before=false, resultType=Integer.class)
    int insertSelective(SwNotification record);

    @SelectProvider(type=SwNotificationSqlProvider.class, method="selectByExample")
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="content", property="content", jdbcType=JdbcType.VARCHAR),
        @Result(column="consumer", property="consumer", jdbcType=JdbcType.VARCHAR),
        @Result(column="status", property="status", jdbcType=JdbcType.INTEGER),
        @Result(column="create_time", property="createTime", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="datetime_lastchange", property="datetimeLastchange", jdbcType=JdbcType.TIMESTAMP)
    })
    List<SwNotification> selectByExampleWithRowbounds(SwNotificationExample example, RowBounds rowBounds);

    @SelectProvider(type=SwNotificationSqlProvider.class, method="selectByExample")
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="content", property="content", jdbcType=JdbcType.VARCHAR),
        @Result(column="consumer", property="consumer", jdbcType=JdbcType.VARCHAR),
        @Result(column="status", property="status", jdbcType=JdbcType.INTEGER),
        @Result(column="create_time", property="createTime", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="datetime_lastchange", property="datetimeLastchange", jdbcType=JdbcType.TIMESTAMP)
    })
    List<SwNotification> selectByExample(SwNotificationExample example);

    @Select({
        "select",
        "id, content, consumer, status, create_time, datetime_lastchange",
        "from sw_notification",
        "where id = #{id,jdbcType=INTEGER}"
    })
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="content", property="content", jdbcType=JdbcType.VARCHAR),
        @Result(column="consumer", property="consumer", jdbcType=JdbcType.VARCHAR),
        @Result(column="status", property="status", jdbcType=JdbcType.INTEGER),
        @Result(column="create_time", property="createTime", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="datetime_lastchange", property="datetimeLastchange", jdbcType=JdbcType.TIMESTAMP)
    })
    SwNotification selectByPrimaryKey(Integer id);

    @UpdateProvider(type=SwNotificationSqlProvider.class, method="updateByExampleSelective")
    int updateByExampleSelective(@Param("record") SwNotification record, @Param("example") SwNotificationExample example);

    @UpdateProvider(type=SwNotificationSqlProvider.class, method="updateByExample")
    int updateByExample(@Param("record") SwNotification record, @Param("example") SwNotificationExample example);

    @UpdateProvider(type=SwNotificationSqlProvider.class, method="updateByPrimaryKeySelective")
    int updateByPrimaryKeySelective(SwNotification record);

    @Update({
        "update sw_notification",
        "set content = #{content,jdbcType=VARCHAR},",
          "consumer = #{consumer,jdbcType=VARCHAR},",
          "status = #{status,jdbcType=INTEGER},",
          "create_time = #{createTime,jdbcType=TIMESTAMP},",
          "datetime_lastchange = #{datetimeLastchange,jdbcType=TIMESTAMP}",
        "where id = #{id,jdbcType=INTEGER}"
    })
    int updateByPrimaryKey(SwNotification record);
}