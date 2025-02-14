package com.foxconn.sw.data.mapper.auto;

import com.foxconn.sw.data.entity.SwNotification;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectKey;
import org.apache.ibatis.type.JdbcType;

@Mapper
public interface SwNotificationMapper {
    @Insert({
        "insert into sw_notification (msg_type, content, ",
        "receiver, create_time, ",
        "datetime_lastchange)",
        "values (#{msgType,jdbcType=VARCHAR}, #{content,jdbcType=VARCHAR}, ",
        "#{receiver,jdbcType=VARCHAR}, #{createTime,jdbcType=TIMESTAMP}, ",
        "#{datetimeLastchange,jdbcType=TIMESTAMP})"
    })
    @SelectKey(statement="SELECT LAST_INSERT_ID()", keyProperty="id", before=false, resultType=Long.class)
    int insert(SwNotification record);

    @InsertProvider(type=SwNotificationSqlProvider.class, method="insertSelective")
    @SelectKey(statement="SELECT LAST_INSERT_ID()", keyProperty="id", before=false, resultType=Long.class)
    int insertSelective(SwNotification record);

    @Select({
        "select",
        "id, msg_type, content, receiver, create_time, datetime_lastchange",
        "from sw_notification",
        "where id = #{id,jdbcType=BIGINT}"
    })
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.BIGINT, id=true),
        @Result(column="msg_type", property="msgType", jdbcType=JdbcType.VARCHAR),
        @Result(column="content", property="content", jdbcType=JdbcType.VARCHAR),
        @Result(column="receiver", property="receiver", jdbcType=JdbcType.VARCHAR),
        @Result(column="create_time", property="createTime", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="datetime_lastchange", property="datetimeLastchange", jdbcType=JdbcType.TIMESTAMP)
    })
    SwNotification selectByPrimaryKey(Long id);
}