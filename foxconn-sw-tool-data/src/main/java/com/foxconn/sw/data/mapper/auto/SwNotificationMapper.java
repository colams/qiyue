package com.foxconn.sw.data.mapper.auto;

import com.foxconn.sw.data.entity.SwNotification;
import com.foxconn.sw.data.entity.SwNotificationExample;
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
public interface SwNotificationMapper {
    long countByExample(SwNotificationExample example);

    int deleteByExample(SwNotificationExample example);

    @Delete({
        "delete from sw_notification",
        "where id = #{id,jdbcType=BIGINT}"
    })
    int deleteByPrimaryKey(Long id);

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

    int insertSelective(SwNotification record);

    List<SwNotification> selectByExampleWithRowbounds(SwNotificationExample example, RowBounds rowBounds);

    List<SwNotification> selectByExample(SwNotificationExample example);

    @Select({
        "select",
        "id, msg_type, content, receiver, create_time, datetime_lastchange",
        "from sw_notification",
        "where id = #{id,jdbcType=BIGINT}"
    })
    @ResultMap("com.foxconn.sw.data.mapper.auto.SwNotificationMapper.BaseResultMap")
    SwNotification selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") SwNotification record, @Param("example") SwNotificationExample example);

    int updateByExample(@Param("record") SwNotification record, @Param("example") SwNotificationExample example);

    int updateByPrimaryKeySelective(SwNotification record);

    @Update({
        "update sw_notification",
        "set msg_type = #{msgType,jdbcType=VARCHAR},",
          "content = #{content,jdbcType=VARCHAR},",
          "receiver = #{receiver,jdbcType=VARCHAR},",
          "create_time = #{createTime,jdbcType=TIMESTAMP},",
          "datetime_lastchange = #{datetimeLastchange,jdbcType=TIMESTAMP}",
        "where id = #{id,jdbcType=BIGINT}"
    })
    int updateByPrimaryKey(SwNotification record);
}