package com.foxconn.sw.data.mapper.auto;

import com.foxconn.sw.data.entity.SwMeetingMinutesDetail;
import com.foxconn.sw.data.entity.SwMeetingMinutesDetailExample.Criteria;
import com.foxconn.sw.data.entity.SwMeetingMinutesDetailExample.Criterion;
import com.foxconn.sw.data.entity.SwMeetingMinutesDetailExample;
import java.util.List;
import java.util.Map;
import org.apache.ibatis.jdbc.SQL;

public class SwMeetingMinutesDetailSqlProvider {

    public String countByExample(SwMeetingMinutesDetailExample example) {
        SQL sql = new SQL();
        sql.SELECT("count(*)").FROM("sw_meeting_minutes_detail");
        applyWhere(sql, example, false);
        return sql.toString();
    }

    public String deleteByExample(SwMeetingMinutesDetailExample example) {
        SQL sql = new SQL();
        sql.DELETE_FROM("sw_meeting_minutes_detail");
        applyWhere(sql, example, false);
        return sql.toString();
    }

    public String insertSelective(SwMeetingMinutesDetail record) {
        SQL sql = new SQL();
        sql.INSERT_INTO("sw_meeting_minutes_detail");
        
        if (record.getMeetingMinutesId() != null) {
            sql.VALUES("meeting_minutes_id", "#{meetingMinutesId,jdbcType=BIGINT}");
        }
        
        if (record.getItemType() != null) {
            sql.VALUES("item_type", "#{itemType,jdbcType=VARCHAR}");
        }
        
        if (record.getIndexNo() != null) {
            sql.VALUES("index_no", "#{indexNo,jdbcType=INTEGER}");
        }
        
        if (record.getItem() != null) {
            sql.VALUES("item", "#{item,jdbcType=VARCHAR}");
        }
        
        if (record.getDirectEno() != null) {
            sql.VALUES("direct_eno", "#{directEno,jdbcType=VARCHAR}");
        }
        
        if (record.getDueDate() != null) {
            sql.VALUES("due_date", "#{dueDate,jdbcType=VARCHAR}");
        }
        
        if (record.getStatus() != null) {
            sql.VALUES("status", "#{status,jdbcType=VARCHAR}");
        }
        
        if (record.getIsDelete() != null) {
            sql.VALUES("is_delete", "#{isDelete,jdbcType=INTEGER}");
        }
        
        if (record.getRemark() != null) {
            sql.VALUES("remark", "#{remark,jdbcType=VARCHAR}");
        }
        
        if (record.getCreateTime() != null) {
            sql.VALUES("create_time", "#{createTime,jdbcType=TIMESTAMP}");
        }
        
        if (record.getDatetimeLastchange() != null) {
            sql.VALUES("datetime_lastchange", "#{datetimeLastchange,jdbcType=TIMESTAMP}");
        }
        
        return sql.toString();
    }

    public String selectByExample(SwMeetingMinutesDetailExample example) {
        SQL sql = new SQL();
        if (example != null && example.isDistinct()) {
            sql.SELECT_DISTINCT("id");
        } else {
            sql.SELECT("id");
        }
        sql.SELECT("meeting_minutes_id");
        sql.SELECT("item_type");
        sql.SELECT("index_no");
        sql.SELECT("item");
        sql.SELECT("direct_eno");
        sql.SELECT("due_date");
        sql.SELECT("status");
        sql.SELECT("is_delete");
        sql.SELECT("remark");
        sql.SELECT("create_time");
        sql.SELECT("datetime_lastchange");
        sql.FROM("sw_meeting_minutes_detail");
        applyWhere(sql, example, false);
        
        if (example != null && example.getOrderByClause() != null) {
            sql.ORDER_BY(example.getOrderByClause());
        }
        
        return sql.toString();
    }

    public String updateByExampleSelective(Map<String, Object> parameter) {
        SwMeetingMinutesDetail record = (SwMeetingMinutesDetail) parameter.get("record");
        SwMeetingMinutesDetailExample example = (SwMeetingMinutesDetailExample) parameter.get("example");
        
        SQL sql = new SQL();
        sql.UPDATE("sw_meeting_minutes_detail");
        
        if (record.getId() != null) {
            sql.SET("id = #{record.id,jdbcType=BIGINT}");
        }
        
        if (record.getMeetingMinutesId() != null) {
            sql.SET("meeting_minutes_id = #{record.meetingMinutesId,jdbcType=BIGINT}");
        }
        
        if (record.getItemType() != null) {
            sql.SET("item_type = #{record.itemType,jdbcType=VARCHAR}");
        }
        
        if (record.getIndexNo() != null) {
            sql.SET("index_no = #{record.indexNo,jdbcType=INTEGER}");
        }
        
        if (record.getItem() != null) {
            sql.SET("item = #{record.item,jdbcType=VARCHAR}");
        }
        
        if (record.getDirectEno() != null) {
            sql.SET("direct_eno = #{record.directEno,jdbcType=VARCHAR}");
        }
        
        if (record.getDueDate() != null) {
            sql.SET("due_date = #{record.dueDate,jdbcType=VARCHAR}");
        }
        
        if (record.getStatus() != null) {
            sql.SET("status = #{record.status,jdbcType=VARCHAR}");
        }
        
        if (record.getIsDelete() != null) {
            sql.SET("is_delete = #{record.isDelete,jdbcType=INTEGER}");
        }
        
        if (record.getRemark() != null) {
            sql.SET("remark = #{record.remark,jdbcType=VARCHAR}");
        }
        
        if (record.getCreateTime() != null) {
            sql.SET("create_time = #{record.createTime,jdbcType=TIMESTAMP}");
        }
        
        if (record.getDatetimeLastchange() != null) {
            sql.SET("datetime_lastchange = #{record.datetimeLastchange,jdbcType=TIMESTAMP}");
        }
        
        applyWhere(sql, example, true);
        return sql.toString();
    }

    public String updateByExample(Map<String, Object> parameter) {
        SQL sql = new SQL();
        sql.UPDATE("sw_meeting_minutes_detail");
        
        sql.SET("id = #{record.id,jdbcType=BIGINT}");
        sql.SET("meeting_minutes_id = #{record.meetingMinutesId,jdbcType=BIGINT}");
        sql.SET("item_type = #{record.itemType,jdbcType=VARCHAR}");
        sql.SET("index_no = #{record.indexNo,jdbcType=INTEGER}");
        sql.SET("item = #{record.item,jdbcType=VARCHAR}");
        sql.SET("direct_eno = #{record.directEno,jdbcType=VARCHAR}");
        sql.SET("due_date = #{record.dueDate,jdbcType=VARCHAR}");
        sql.SET("status = #{record.status,jdbcType=VARCHAR}");
        sql.SET("is_delete = #{record.isDelete,jdbcType=INTEGER}");
        sql.SET("remark = #{record.remark,jdbcType=VARCHAR}");
        sql.SET("create_time = #{record.createTime,jdbcType=TIMESTAMP}");
        sql.SET("datetime_lastchange = #{record.datetimeLastchange,jdbcType=TIMESTAMP}");
        
        SwMeetingMinutesDetailExample example = (SwMeetingMinutesDetailExample) parameter.get("example");
        applyWhere(sql, example, true);
        return sql.toString();
    }

    public String updateByPrimaryKeySelective(SwMeetingMinutesDetail record) {
        SQL sql = new SQL();
        sql.UPDATE("sw_meeting_minutes_detail");
        
        if (record.getMeetingMinutesId() != null) {
            sql.SET("meeting_minutes_id = #{meetingMinutesId,jdbcType=BIGINT}");
        }
        
        if (record.getItemType() != null) {
            sql.SET("item_type = #{itemType,jdbcType=VARCHAR}");
        }
        
        if (record.getIndexNo() != null) {
            sql.SET("index_no = #{indexNo,jdbcType=INTEGER}");
        }
        
        if (record.getItem() != null) {
            sql.SET("item = #{item,jdbcType=VARCHAR}");
        }
        
        if (record.getDirectEno() != null) {
            sql.SET("direct_eno = #{directEno,jdbcType=VARCHAR}");
        }
        
        if (record.getDueDate() != null) {
            sql.SET("due_date = #{dueDate,jdbcType=VARCHAR}");
        }
        
        if (record.getStatus() != null) {
            sql.SET("status = #{status,jdbcType=VARCHAR}");
        }
        
        if (record.getIsDelete() != null) {
            sql.SET("is_delete = #{isDelete,jdbcType=INTEGER}");
        }
        
        if (record.getRemark() != null) {
            sql.SET("remark = #{remark,jdbcType=VARCHAR}");
        }
        
        if (record.getCreateTime() != null) {
            sql.SET("create_time = #{createTime,jdbcType=TIMESTAMP}");
        }
        
        if (record.getDatetimeLastchange() != null) {
            sql.SET("datetime_lastchange = #{datetimeLastchange,jdbcType=TIMESTAMP}");
        }
        
        sql.WHERE("id = #{id,jdbcType=BIGINT}");
        
        return sql.toString();
    }

    protected void applyWhere(SQL sql, SwMeetingMinutesDetailExample example, boolean includeExamplePhrase) {
        if (example == null) {
            return;
        }
        
        String parmPhrase1;
        String parmPhrase1_th;
        String parmPhrase2;
        String parmPhrase2_th;
        String parmPhrase3;
        String parmPhrase3_th;
        if (includeExamplePhrase) {
            parmPhrase1 = "%s #{example.oredCriteria[%d].allCriteria[%d].value}";
            parmPhrase1_th = "%s #{example.oredCriteria[%d].allCriteria[%d].value,typeHandler=%s}";
            parmPhrase2 = "%s #{example.oredCriteria[%d].allCriteria[%d].value} and #{example.oredCriteria[%d].criteria[%d].secondValue}";
            parmPhrase2_th = "%s #{example.oredCriteria[%d].allCriteria[%d].value,typeHandler=%s} and #{example.oredCriteria[%d].criteria[%d].secondValue,typeHandler=%s}";
            parmPhrase3 = "#{example.oredCriteria[%d].allCriteria[%d].value[%d]}";
            parmPhrase3_th = "#{example.oredCriteria[%d].allCriteria[%d].value[%d],typeHandler=%s}";
        } else {
            parmPhrase1 = "%s #{oredCriteria[%d].allCriteria[%d].value}";
            parmPhrase1_th = "%s #{oredCriteria[%d].allCriteria[%d].value,typeHandler=%s}";
            parmPhrase2 = "%s #{oredCriteria[%d].allCriteria[%d].value} and #{oredCriteria[%d].criteria[%d].secondValue}";
            parmPhrase2_th = "%s #{oredCriteria[%d].allCriteria[%d].value,typeHandler=%s} and #{oredCriteria[%d].criteria[%d].secondValue,typeHandler=%s}";
            parmPhrase3 = "#{oredCriteria[%d].allCriteria[%d].value[%d]}";
            parmPhrase3_th = "#{oredCriteria[%d].allCriteria[%d].value[%d],typeHandler=%s}";
        }
        
        StringBuilder sb = new StringBuilder();
        List<Criteria> oredCriteria = example.getOredCriteria();
        boolean firstCriteria = true;
        for (int i = 0; i < oredCriteria.size(); i++) {
            Criteria criteria = oredCriteria.get(i);
            if (criteria.isValid()) {
                if (firstCriteria) {
                    firstCriteria = false;
                } else {
                    sb.append(" or ");
                }
                
                sb.append('(');
                List<Criterion> criterions = criteria.getAllCriteria();
                boolean firstCriterion = true;
                for (int j = 0; j < criterions.size(); j++) {
                    Criterion criterion = criterions.get(j);
                    if (firstCriterion) {
                        firstCriterion = false;
                    } else {
                        sb.append(" and ");
                    }
                    
                    if (criterion.isNoValue()) {
                        sb.append(criterion.getCondition());
                    } else if (criterion.isSingleValue()) {
                        if (criterion.getTypeHandler() == null) {
                            sb.append(String.format(parmPhrase1, criterion.getCondition(), i, j));
                        } else {
                            sb.append(String.format(parmPhrase1_th, criterion.getCondition(), i, j,criterion.getTypeHandler()));
                        }
                    } else if (criterion.isBetweenValue()) {
                        if (criterion.getTypeHandler() == null) {
                            sb.append(String.format(parmPhrase2, criterion.getCondition(), i, j, i, j));
                        } else {
                            sb.append(String.format(parmPhrase2_th, criterion.getCondition(), i, j, criterion.getTypeHandler(), i, j, criterion.getTypeHandler()));
                        }
                    } else if (criterion.isListValue()) {
                        sb.append(criterion.getCondition());
                        sb.append(" (");
                        List<?> listItems = (List<?>) criterion.getValue();
                        boolean comma = false;
                        for (int k = 0; k < listItems.size(); k++) {
                            if (comma) {
                                sb.append(", ");
                            } else {
                                comma = true;
                            }
                            if (criterion.getTypeHandler() == null) {
                                sb.append(String.format(parmPhrase3, i, j, k));
                            } else {
                                sb.append(String.format(parmPhrase3_th, i, j, k, criterion.getTypeHandler()));
                            }
                        }
                        sb.append(')');
                    }
                }
                sb.append(')');
            }
        }
        
        if (sb.length() > 0) {
            sql.WHERE(sb.toString());
        }
    }
}