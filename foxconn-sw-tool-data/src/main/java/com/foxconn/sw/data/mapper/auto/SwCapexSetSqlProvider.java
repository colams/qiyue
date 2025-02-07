package com.foxconn.sw.data.mapper.auto;

import com.foxconn.sw.data.entity.SwCapexSet;
import com.foxconn.sw.data.entity.SwCapexSetExample.Criteria;
import com.foxconn.sw.data.entity.SwCapexSetExample.Criterion;
import com.foxconn.sw.data.entity.SwCapexSetExample;
import java.util.List;
import java.util.Map;
import org.apache.ibatis.jdbc.SQL;

public class SwCapexSetSqlProvider {

    public String deleteByExample(SwCapexSetExample example) {
        SQL sql = new SQL();
        sql.DELETE_FROM("sw_capex_set");
        applyWhere(sql, example, false);
        return sql.toString();
    }

    public String insertSelective(SwCapexSet record) {
        SQL sql = new SQL();
        sql.INSERT_INTO("sw_capex_set");
        
        if (record.getTaskId() != null) {
            sql.VALUES("task_id", "#{taskId,jdbcType=INTEGER}");
        }
        
        if (record.getSheetName() != null) {
            sql.VALUES("sheet_name", "#{sheetName,jdbcType=VARCHAR}");
        }
        
        if (record.getType() != null) {
            sql.VALUES("type", "#{type,jdbcType=VARCHAR}");
        }
        
        if (record.getNumber() != null) {
            sql.VALUES("number", "#{number,jdbcType=INTEGER}");
        }
        
        if (record.getSetValue() != null) {
            sql.VALUES("set_value", "#{setValue,jdbcType=VARCHAR}");
        }
        
        if (record.getSetValueJson() != null) {
            sql.VALUES("set_value_json", "#{setValueJson,jdbcType=VARCHAR}");
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

    public String selectByExample(SwCapexSetExample example) {
        SQL sql = new SQL();
        if (example != null && example.isDistinct()) {
            sql.SELECT_DISTINCT("id");
        } else {
            sql.SELECT("id");
        }
        sql.SELECT("task_id");
        sql.SELECT("sheet_name");
        sql.SELECT("type");
        sql.SELECT("number");
        sql.SELECT("set_value");
        sql.SELECT("set_value_json");
        sql.SELECT("is_delete");
        sql.SELECT("create_time");
        sql.SELECT("datetime_lastchange");
        sql.FROM("sw_capex_set");
        applyWhere(sql, example, false);
        
        if (example != null && example.getOrderByClause() != null) {
            sql.ORDER_BY(example.getOrderByClause());
        }
        
        return sql.toString();
    }

    public String updateByExampleSelective(Map<String, Object> parameter) {
        SwCapexSet record = (SwCapexSet) parameter.get("record");
        SwCapexSetExample example = (SwCapexSetExample) parameter.get("example");
        
        SQL sql = new SQL();
        sql.UPDATE("sw_capex_set");
        
        if (record.getId() != null) {
            sql.SET("id = #{record.id,jdbcType=INTEGER}");
        }
        
        if (record.getTaskId() != null) {
            sql.SET("task_id = #{record.taskId,jdbcType=INTEGER}");
        }
        
        if (record.getSheetName() != null) {
            sql.SET("sheet_name = #{record.sheetName,jdbcType=VARCHAR}");
        }
        
        if (record.getType() != null) {
            sql.SET("type = #{record.type,jdbcType=VARCHAR}");
        }
        
        if (record.getNumber() != null) {
            sql.SET("number = #{record.number,jdbcType=INTEGER}");
        }
        
        if (record.getSetValue() != null) {
            sql.SET("set_value = #{record.setValue,jdbcType=VARCHAR}");
        }
        
        if (record.getSetValueJson() != null) {
            sql.SET("set_value_json = #{record.setValueJson,jdbcType=VARCHAR}");
        }
        
        if (record.getIsDelete() != null) {
            sql.SET("is_delete = #{record.isDelete,jdbcType=INTEGER}");
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
        sql.UPDATE("sw_capex_set");
        
        sql.SET("id = #{record.id,jdbcType=INTEGER}");
        sql.SET("task_id = #{record.taskId,jdbcType=INTEGER}");
        sql.SET("sheet_name = #{record.sheetName,jdbcType=VARCHAR}");
        sql.SET("type = #{record.type,jdbcType=VARCHAR}");
        sql.SET("number = #{record.number,jdbcType=INTEGER}");
        sql.SET("set_value = #{record.setValue,jdbcType=VARCHAR}");
        sql.SET("set_value_json = #{record.setValueJson,jdbcType=VARCHAR}");
        sql.SET("is_delete = #{record.isDelete,jdbcType=INTEGER}");
        sql.SET("create_time = #{record.createTime,jdbcType=TIMESTAMP}");
        sql.SET("datetime_lastchange = #{record.datetimeLastchange,jdbcType=TIMESTAMP}");
        
        SwCapexSetExample example = (SwCapexSetExample) parameter.get("example");
        applyWhere(sql, example, true);
        return sql.toString();
    }

    public String updateByPrimaryKeySelective(SwCapexSet record) {
        SQL sql = new SQL();
        sql.UPDATE("sw_capex_set");
        
        if (record.getTaskId() != null) {
            sql.SET("task_id = #{taskId,jdbcType=INTEGER}");
        }
        
        if (record.getSheetName() != null) {
            sql.SET("sheet_name = #{sheetName,jdbcType=VARCHAR}");
        }
        
        if (record.getType() != null) {
            sql.SET("type = #{type,jdbcType=VARCHAR}");
        }
        
        if (record.getNumber() != null) {
            sql.SET("number = #{number,jdbcType=INTEGER}");
        }
        
        if (record.getSetValue() != null) {
            sql.SET("set_value = #{setValue,jdbcType=VARCHAR}");
        }
        
        if (record.getSetValueJson() != null) {
            sql.SET("set_value_json = #{setValueJson,jdbcType=VARCHAR}");
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

    protected void applyWhere(SQL sql, SwCapexSetExample example, boolean includeExamplePhrase) {
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