package com.foxconn.sw.data.mapper.extension.message;

import com.foxconn.sw.data.entity.SwMsgPool;
import com.foxconn.sw.data.mapper.auto.SwMsgPoolMapper;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.apache.ibatis.type.JdbcType;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SwMsgPoolExtMapper extends SwMsgPoolMapper {

    @Select({"<script>",
            "select * from sw_msg_pool",
            "where status=0 ",
            "</script>"
    })
    @Results({
            @Result(column = "id", property = "id", jdbcType = JdbcType.INTEGER, id = true),
            @Result(column = "msg_type", property = "msgType", jdbcType = JdbcType.VARCHAR),
            @Result(column = "object_id", property = "objectId", jdbcType = JdbcType.INTEGER),
            @Result(column = "status", property = "status", jdbcType = JdbcType.INTEGER),
    })
    List<SwMsgPool> select2Process();

    @Update({
            "update sw_msg_pool",
            "set status = 1",
            "where id = #{id,jdbcType=INTEGER}"
    })
    int closeMsgPool(Integer id);
}
