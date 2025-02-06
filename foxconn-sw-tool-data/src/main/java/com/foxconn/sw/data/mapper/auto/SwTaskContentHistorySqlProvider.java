package com.foxconn.sw.data.mapper.auto;

import com.foxconn.sw.data.entity.SwTaskContentHistory;
import com.foxconn.sw.data.entity.SwTaskContentHistoryExample.Criteria;
import com.foxconn.sw.data.entity.SwTaskContentHistoryExample.Criterion;
import com.foxconn.sw.data.entity.SwTaskContentHistoryExample;
import java.util.List;
import java.util.Map;
import org.apache.ibatis.jdbc.SQL;

public class SwTaskContentHistorySqlProvider {

    public String deleteByExample(SwTaskContentHistoryExample example) {
        SQL sql = new SQL();
        sql.DELETE_FROM("sw_task_content_history");
        applyWhere(sql, example, false);
        return sql.toString();
    }

    public String insertSelective(SwTaskContentHistory record) {
        SQL sql = new SQL();
        sql.INSERT_INTO("sw_task_content_history");
        
        if (record.getTaskId() != null) {
            sql.VALUES("task_id", "#{taskId,jdbcType=INTEGER}");
        }
        
        if (record.getProgressId() != null) {
            sql.VALUES("progress_id", "#{progressId,jdbcType=INTEGER}");
        }
        
        if (record.getOperator() != null) {
            sql.VALUES("operator", "#{operator,jdbcType=VARCHAR}");
        }
        
        if (record.getDatetimeLastchange() != null) {
            sql.VALUES("datetime_lastchange", "#{datetimeLastchange,jdbcType=TIMESTAMP}");
        }
        
        if (record.getOldContent() != null) {
            sql.VALUES("old_content", "#{oldContent,jdbcType=LONGVARCHAR}");
        }
        
        if (record.getNewContent() != null) {
            sql.VALUES("new_content", "#{newContent,jdbcType=LONGVARCHAR}");
        }
        
        return sql.toString();
    }

    public String selectByExampleWithBLOBs(SwTaskContentHistoryExample example) {
        SQL sql = new SQL();
        if (example != null && example.isDistinct()) {
            sql.SELECT_DISTINCT("id");
        } else {
            sql.SELECT("id");
        }
        sql.SELECT("task_id");
        sql.SELECT("progress_id");
        sql.SELECT("operator");
        sql.SELECT("datetime_lastchange");
        sql.SELECT("old_content");
        sql.SELECT("new_content");
        sql.FROM("sw_task_content_history");
        applyWhere(sql, example, false);
        
        if (example != null && example.getOrderByClause() != null) {
            sql.ORDER_BY(example.getOrderByClause());
        }
        
        return sql.toString();
    }

    public String selectByExample(SwTaskContentHistoryExample example) {
        SQL sql = new SQL();
        if (example != null && example.isDistinct()) {
            sql.SELECT_DISTINCT("id");
        } else {
            sql.SELECT("id");
        }
        sql.SELECT("task_id");
        sql.SELECT("progress_id");
        sql.SELECT("operator");
        sql.SELECT("datetime_lastchange");
        sql.FROM("sw_task_content_history");
        applyWhere(sql, example, false);
        
        if (example != null && example.getOrderByClause() != null) {
            sql.ORDER_BY(example.getOrderByClause());
        }
        
        return sql.toString();
    }

    public String updateByExampleSelective(Map<String, Object> parameter) {
        SwTaskContentHistory record = (SwTaskContentHistory) parameter.get("record");
        SwTaskContentHistoryExample example = (SwTaskContentHistoryExample) parameter.get("example");
        
        SQL sql = new SQL();
        sql.UPDATE("sw_task_content_history");
        
        if (record.getId() != null) {
            sql.SET("id = #{record.id,jdbcType=INTEGER}");
        }
        
        if (record.getTaskId() != null) {
            sql.SET("task_id = #{record.taskId,jdbcType=INTEGER}");
        }
        
        if (record.getProgressId() != null) {
            sql.SET("progress_id = #{record.progressId,jdbcType=INTEGER}");
        }
        
        if (record.getOperator() != null) {
            sql.SET("operator = #{record.operator,jdbcType=VARCHAR}");
        }
        
        if (record.getDatetimeLastchange() != null) {
            sql.SET("datetime_lastchange = #{record.datetimeLastchange,jdbcType=TIMESTAMP}");
        }
        
        if (record.getOldContent() != null) {
            sql.SET("old_content = #{record.oldContent,jdbcType=LONGVARCHAR}");
        }
        
        if (record.getNewContent() != null) {
            sql.SET("new_content = #{record.newContent,jdbcType=LONGVARCHAR}");
        }
        
        applyWhere(sql, example, true);
        return sql.toString();
    }

    public String updateByExampleWithBLOBs(Map<String, Object> parameter) {
        SQL sql = new SQL();
        sql.UPDATE("sw_task_content_history");
        
        sql.SET("id = #{record.id,jdbcType=INTEGER}");
        sql.SET("task_id = #{record.taskId,jdbcType=INTEGER}");
        sql.SET("progress_id = #{record.progressId,jdbcType=INTEGER}");
        sql.SET("operator = #{record.operator,jdbcType=VARCHAR}");
        sql.SET("datetime_lastchange = #{record.datetimeLastchange,jdbcType=TIMESTAMP}");
        sql.SET("old_content = #{record.oldContent,jdbcType=LONGVARCHAR}");
        sql.SET("new_content = #{record.newContent,jdbcType=LONGVARCHAR}");
        
        SwTaskContentHistoryExample example = (SwTaskContentHistoryExample) parameter.get("example");
        applyWhere(sql, example, true);
        return sql.toString();
    }

    public String updateByExample(Map<String, Object> parameter) {
        SQL sql = new SQL();
        sql.UPDATE("sw_task_content_history");
        
        sql.SET("id = #{record.id,jdbcType=INTEGER}");
        sql.SET("task_id = #{record.taskId,jdbcType=INTEGER}");
        sql.SET("progress_id = #{record.progressId,jdbcType=INTEGER}");
        sql.SET("operator = #{record.operator,jdbcType=VARCHAR}");
        sql.SET("datetime_lastchange = #{record.datetimeLastchange,jdbcType=TIMESTAMP}");
        
        SwTaskContentHistoryExample example = (SwTaskContentHistoryExample) parameter.get("example");
        applyWhere(sql, example, true);
        return sql.toString();
    }

    public String updateByPrimaryKeySelective(SwTaskContentHistory record) {
        SQL sql = new SQL();
        sql.UPDATE("sw_task_content_history");
        
        if (record.getTaskId() != null) {
            sql.SET("task_id = #{taskId,jdbcType=INTEGER}");
        }
        
        if (record.getProgressId() != null) {
            sql.SET("progress_id = #{progressId,jdbcType=INTEGER}");
        }
        
        if (record.getOperator() != null) {
            sql.SET("operator = #{operator,jdbcType=VARCHAR}");
        }
        
        if (record.getDatetimeLastchange() != null) {
            sql.SET("datetime_lastchange = #{datetimeLastchange,jdbcType=TIMESTAMP}");
        }
        
        if (record.getOldContent() != null) {
            sql.SET("old_content = #{oldContent,jdbcType=LONGVARCHAR}");
        }
        
        if (record.getNewContent() != null) {
            sql.SET("new_content = #{newContent,jdbcType=LONGVARCHAR}");
        }
        
        sql.WHERE("id = #{id,jdbcType=INTEGER}");
        
        return sql.toString();
    }

    protected void applyWhere(SQL sql, SwTaskContentHistoryExample example, boolean includeExamplePhrase) {
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