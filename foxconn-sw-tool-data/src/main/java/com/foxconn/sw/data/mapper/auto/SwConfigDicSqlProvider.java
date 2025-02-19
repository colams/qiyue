package com.foxconn.sw.data.mapper.auto;

import com.foxconn.sw.data.entity.SwConfigDic;
import org.apache.ibatis.jdbc.SQL;

public class SwConfigDicSqlProvider {

    public String insertSelective(SwConfigDic record) {
        SQL sql = new SQL();
        sql.INSERT_INTO("sw_config_dic");
        
        if (record.getItem() != null) {
            sql.VALUES("item", "#{item,jdbcType=VARCHAR}");
        }
        
        if (record.getItemValue() != null) {
            sql.VALUES("item_value", "#{itemValue,jdbcType=VARCHAR}");
        }
        
        if (record.getRemark() != null) {
            sql.VALUES("remark", "#{remark,jdbcType=VARCHAR}");
        }
        
        if (record.getIsDisable() != null) {
            sql.VALUES("is_disable", "#{isDisable,jdbcType=INTEGER}");
        }
        
        if (record.getIsDelete() != null) {
            sql.VALUES("is_delete", "#{isDelete,jdbcType=INTEGER}");
        }
        
        if (record.getCreateTime() != null) {
            sql.VALUES("create_time", "#{createTime,jdbcType=TIMESTAMP}");
        }
        
        if (record.getDatetimeLastchange() != null) {
            sql.VALUES("datetime_lastchange", "#{datetimeLastchange,jdbcType=TIMESTAMP}");
        }
        
        return sql.toString();
    }

    public String updateByPrimaryKeySelective(SwConfigDic record) {
        SQL sql = new SQL();
        sql.UPDATE("sw_config_dic");
        
        if (record.getItem() != null) {
            sql.SET("item = #{item,jdbcType=VARCHAR}");
        }
        
        if (record.getItemValue() != null) {
            sql.SET("item_value = #{itemValue,jdbcType=VARCHAR}");
        }
        
        if (record.getRemark() != null) {
            sql.SET("remark = #{remark,jdbcType=VARCHAR}");
        }
        
        if (record.getIsDisable() != null) {
            sql.SET("is_disable = #{isDisable,jdbcType=INTEGER}");
        }
        
        if (record.getIsDelete() != null) {
            sql.SET("is_delete = #{isDelete,jdbcType=INTEGER}");
        }
        
        if (record.getCreateTime() != null) {
            sql.SET("create_time = #{createTime,jdbcType=TIMESTAMP}");
        }
        
        if (record.getDatetimeLastchange() != null) {
            sql.SET("datetime_lastchange = #{datetimeLastchange,jdbcType=TIMESTAMP}");
        }
        
        sql.WHERE("id = #{id,jdbcType=INTEGER}");
        
        return sql.toString();
    }
}