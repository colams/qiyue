package com.foxconn.sw.data.mapper.auto;

import com.foxconn.sw.data.entity.SwMsgPool;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectKey;
import org.apache.ibatis.type.JdbcType;

@Mapper
public interface SwMsgPoolMapper {
    @Insert({
        "insert into sw_msg_pool (msg_type, object_id, ",
        "status, create_time, ",
        "datetime_lastchange)",
        "values (#{msgType,jdbcType=VARCHAR}, #{objectId,jdbcType=INTEGER}, ",
        "#{status,jdbcType=INTEGER}, #{createTime,jdbcType=TIMESTAMP}, ",
        "#{datetimeLastchange,jdbcType=TIMESTAMP})"
    })
    @SelectKey(statement="SELECT LAST_INSERT_ID()", keyProperty="id", before=false, resultType=Integer.class)
    int insert(SwMsgPool record);

    @InsertProvider(type=SwMsgPoolSqlProvider.class, method="insertSelective")
    @SelectKey(statement="SELECT LAST_INSERT_ID()", keyProperty="id", before=false, resultType=Integer.class)
    int insertSelective(SwMsgPool record);

    @Select({
        "select",
        "id, msg_type, object_id, status, create_time, datetime_lastchange",
        "from sw_msg_pool",
        "where id = #{id,jdbcType=INTEGER}"
    })
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="msg_type", property="msgType", jdbcType=JdbcType.VARCHAR),
        @Result(column="object_id", property="objectId", jdbcType=JdbcType.INTEGER),
        @Result(column="status", property="status", jdbcType=JdbcType.INTEGER),
        @Result(column="create_time", property="createTime", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="datetime_lastchange", property="datetimeLastchange", jdbcType=JdbcType.TIMESTAMP)
    })
    SwMsgPool selectByPrimaryKey(Integer id);
}