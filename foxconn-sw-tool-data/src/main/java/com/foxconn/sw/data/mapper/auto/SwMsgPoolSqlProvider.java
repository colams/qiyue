package com.foxconn.sw.data.mapper.auto;

import com.foxconn.sw.data.entity.SwMsgPool;
import org.apache.ibatis.jdbc.SQL;

public class SwMsgPoolSqlProvider {

    public String insertSelective(SwMsgPool record) {
        SQL sql = new SQL();
        sql.INSERT_INTO("sw_msg_pool");
        
        if (record.getMsgType() != null) {
            sql.VALUES("msg_type", "#{msgType,jdbcType=VARCHAR}");
        }
        
        if (record.getObjectId() != null) {
            sql.VALUES("object_id", "#{objectId,jdbcType=BIGINT}");
        }
        
        if (record.getStatus() != null) {
            sql.VALUES("status", "#{status,jdbcType=INTEGER}");
        }
        
        if (record.getCreateTime() != null) {
            sql.VALUES("create_time", "#{createTime,jdbcType=TIMESTAMP}");
        }
        
        if (record.getDatetimeLastchange() != null) {
            sql.VALUES("datetime_lastchange", "#{datetimeLastchange,jdbcType=TIMESTAMP}");
        }
        
        return sql.toString();
    }
}