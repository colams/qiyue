package com.foxconn.sw.data.mapper.auto;

import com.foxconn.sw.data.entity.SwNotification;
import org.apache.ibatis.jdbc.SQL;

public class SwNotificationSqlProvider {

    public String insertSelective(SwNotification record) {
        SQL sql = new SQL();
        sql.INSERT_INTO("sw_notification");
        
        if (record.getMsgType() != null) {
            sql.VALUES("msg_type", "#{msgType,jdbcType=VARCHAR}");
        }
        
        if (record.getContent() != null) {
            sql.VALUES("content", "#{content,jdbcType=VARCHAR}");
        }
        
        if (record.getReceiver() != null) {
            sql.VALUES("receiver", "#{receiver,jdbcType=VARCHAR}");
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