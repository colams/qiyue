package com.foxconn.sw.data.mapper.auto;

import com.foxconn.sw.data.entity.SwToolRunResult;
import com.foxconn.sw.data.entity.SwToolRunResultExample.Criteria;
import com.foxconn.sw.data.entity.SwToolRunResultExample.Criterion;
import com.foxconn.sw.data.entity.SwToolRunResultExample;
import java.util.List;
import java.util.Map;
import org.apache.ibatis.jdbc.SQL;

public class SwToolRunResultSqlProvider {

    public String countByExample(SwToolRunResultExample example) {
        SQL sql = new SQL();
        sql.SELECT("count(*)").FROM("sw_tool_run_result");
        applyWhere(sql, example, false);
        return sql.toString();
    }

    public String deleteByExample(SwToolRunResultExample example) {
        SQL sql = new SQL();
        sql.DELETE_FROM("sw_tool_run_result");
        applyWhere(sql, example, false);
        return sql.toString();
    }

    public String insertSelective(SwToolRunResult record) {
        SQL sql = new SQL();
        sql.INSERT_INTO("sw_tool_run_result");
        
        if (record.getOperator() != null) {
            sql.VALUES("operator", "#{operator,jdbcType=VARCHAR}");
        }
        
        if (record.getToolName() != null) {
            sql.VALUES("tool_name", "#{toolName,jdbcType=VARCHAR}");
        }
        
        if (record.getRunResult() != null) {
            sql.VALUES("run_result", "#{runResult,jdbcType=VARCHAR}");
        }
        
        if (record.getIntervals() != null) {
            sql.VALUES("intervals", "#{intervals,jdbcType=BIGINT}");
        }
        
        if (record.getRemark() != null) {
            sql.VALUES("remark", "#{remark,jdbcType=VARCHAR}");
        }
        
        if (record.getCreateTime() != null) {
            sql.VALUES("create_time", "#{createTime,jdbcType=TIMESTAMP}");
        }
        
        return sql.toString();
    }

    public String selectByExample(SwToolRunResultExample example) {
        SQL sql = new SQL();
        if (example != null && example.isDistinct()) {
            sql.SELECT_DISTINCT("id");
        } else {
            sql.SELECT("id");
        }
        sql.SELECT("operator");
        sql.SELECT("tool_name");
        sql.SELECT("run_result");
        sql.SELECT("intervals");
        sql.SELECT("remark");
        sql.SELECT("create_time");
        sql.FROM("sw_tool_run_result");
        applyWhere(sql, example, false);
        
        if (example != null && example.getOrderByClause() != null) {
            sql.ORDER_BY(example.getOrderByClause());
        }
        
        return sql.toString();
    }

    public String updateByExampleSelective(Map<String, Object> parameter) {
        SwToolRunResult record = (SwToolRunResult) parameter.get("record");
        SwToolRunResultExample example = (SwToolRunResultExample) parameter.get("example");
        
        SQL sql = new SQL();
        sql.UPDATE("sw_tool_run_result");
        
        if (record.getId() != null) {
            sql.SET("id = #{record.id,jdbcType=INTEGER}");
        }
        
        if (record.getOperator() != null) {
            sql.SET("operator = #{record.operator,jdbcType=VARCHAR}");
        }
        
        if (record.getToolName() != null) {
            sql.SET("tool_name = #{record.toolName,jdbcType=VARCHAR}");
        }
        
        if (record.getRunResult() != null) {
            sql.SET("run_result = #{record.runResult,jdbcType=VARCHAR}");
        }
        
        if (record.getIntervals() != null) {
            sql.SET("intervals = #{record.intervals,jdbcType=BIGINT}");
        }
        
        if (record.getRemark() != null) {
            sql.SET("remark = #{record.remark,jdbcType=VARCHAR}");
        }
        
        if (record.getCreateTime() != null) {
            sql.SET("create_time = #{record.createTime,jdbcType=TIMESTAMP}");
        }
        
        applyWhere(sql, example, true);
        return sql.toString();
    }

    public String updateByExample(Map<String, Object> parameter) {
        SQL sql = new SQL();
        sql.UPDATE("sw_tool_run_result");
        
        sql.SET("id = #{record.id,jdbcType=INTEGER}");
        sql.SET("operator = #{record.operator,jdbcType=VARCHAR}");
        sql.SET("tool_name = #{record.toolName,jdbcType=VARCHAR}");
        sql.SET("run_result = #{record.runResult,jdbcType=VARCHAR}");
        sql.SET("intervals = #{record.intervals,jdbcType=BIGINT}");
        sql.SET("remark = #{record.remark,jdbcType=VARCHAR}");
        sql.SET("create_time = #{record.createTime,jdbcType=TIMESTAMP}");
        
        SwToolRunResultExample example = (SwToolRunResultExample) parameter.get("example");
        applyWhere(sql, example, true);
        return sql.toString();
    }

    public String updateByPrimaryKeySelective(SwToolRunResult record) {
        SQL sql = new SQL();
        sql.UPDATE("sw_tool_run_result");
        
        if (record.getOperator() != null) {
            sql.SET("operator = #{operator,jdbcType=VARCHAR}");
        }
        
        if (record.getToolName() != null) {
            sql.SET("tool_name = #{toolName,jdbcType=VARCHAR}");
        }
        
        if (record.getRunResult() != null) {
            sql.SET("run_result = #{runResult,jdbcType=VARCHAR}");
        }
        
        if (record.getIntervals() != null) {
            sql.SET("intervals = #{intervals,jdbcType=BIGINT}");
        }
        
        if (record.getRemark() != null) {
            sql.SET("remark = #{remark,jdbcType=VARCHAR}");
        }
        
        if (record.getCreateTime() != null) {
            sql.SET("create_time = #{createTime,jdbcType=TIMESTAMP}");
        }
        
        sql.WHERE("id = #{id,jdbcType=INTEGER}");
        
        return sql.toString();
    }

    protected void applyWhere(SQL sql, SwToolRunResultExample example, boolean includeExamplePhrase) {
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