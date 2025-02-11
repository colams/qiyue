package com.foxconn.sw.data.mapper.auto;

import com.foxconn.sw.data.entity.SwMsgPool;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectKey;

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

    int insertSelective(SwMsgPool record);

    @Select({
        "select",
        "id, msg_type, object_id, status, create_time, datetime_lastchange",
        "from sw_msg_pool",
        "where id = #{id,jdbcType=INTEGER}"
    })
    @ResultMap("com.foxconn.sw.data.mapper.auto.SwMsgPoolMapper.BaseResultMap")
    SwMsgPool selectByPrimaryKey(Integer id);
}