package com.foxconn.sw.data.mapper.auto;

import com.foxconn.sw.data.entity.SwCollaborationDetailLog;
import com.foxconn.sw.data.entity.SwCollaborationDetailLogExample.Criteria;
import com.foxconn.sw.data.entity.SwCollaborationDetailLogExample.Criterion;
import com.foxconn.sw.data.entity.SwCollaborationDetailLogExample;
import java.util.List;
import java.util.Map;
import org.apache.ibatis.jdbc.SQL;

public class SwCollaborationDetailLogSqlProvider {

    public String countByExample(SwCollaborationDetailLogExample example) {
        SQL sql = new SQL();
        sql.SELECT("count(*)").FROM("sw_collaboration_detail_log");
        applyWhere(sql, example, false);
        return sql.toString();
    }

    public String deleteByExample(SwCollaborationDetailLogExample example) {
        SQL sql = new SQL();
        sql.DELETE_FROM("sw_collaboration_detail_log");
        applyWhere(sql, example, false);
        return sql.toString();
    }

    public String insertSelective(SwCollaborationDetailLog record) {
        SQL sql = new SQL();
        sql.INSERT_INTO("sw_collaboration_detail_log");
        
        if (record.getDetailId() != null) {
            sql.VALUES("detail_id", "#{detailId,jdbcType=BIGINT}");
        }
        
        if (record.getRowIndex() != null) {
            sql.VALUES("row_index", "#{rowIndex,jdbcType=INTEGER}");
        }
        
        if (record.getColIndex() != null) {
            sql.VALUES("col_index", "#{colIndex,jdbcType=INTEGER}");
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

    public String selectByExample(SwCollaborationDetailLogExample example) {
        SQL sql = new SQL();
        if (example != null && example.isDistinct()) {
            sql.SELECT_DISTINCT("id");
        } else {
            sql.SELECT("id");
        }
        sql.SELECT("detail_id");
        sql.SELECT("row_index");
        sql.SELECT("col_index");
        sql.SELECT("remark");
        sql.SELECT("create_time");
        sql.SELECT("datetime_lastchange");
        sql.FROM("sw_collaboration_detail_log");
        applyWhere(sql, example, false);
        
        if (example != null && example.getOrderByClause() != null) {
            sql.ORDER_BY(example.getOrderByClause());
        }
        
        return sql.toString();
    }

    public String updateByExampleSelective(Map<String, Object> parameter) {
        SwCollaborationDetailLog record = (SwCollaborationDetailLog) parameter.get("record");
        SwCollaborationDetailLogExample example = (SwCollaborationDetailLogExample) parameter.get("example");
        
        SQL sql = new SQL();
        sql.UPDATE("sw_collaboration_detail_log");
        
        if (record.getId() != null) {
            sql.SET("id = #{record.id,jdbcType=BIGINT}");
        }
        
        if (record.getDetailId() != null) {
            sql.SET("detail_id = #{record.detailId,jdbcType=BIGINT}");
        }
        
        if (record.getRowIndex() != null) {
            sql.SET("row_index = #{record.rowIndex,jdbcType=INTEGER}");
        }
        
        if (record.getColIndex() != null) {
            sql.SET("col_index = #{record.colIndex,jdbcType=INTEGER}");
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
        sql.UPDATE("sw_collaboration_detail_log");
        
        sql.SET("id = #{record.id,jdbcType=BIGINT}");
        sql.SET("detail_id = #{record.detailId,jdbcType=BIGINT}");
        sql.SET("row_index = #{record.rowIndex,jdbcType=INTEGER}");
        sql.SET("col_index = #{record.colIndex,jdbcType=INTEGER}");
        sql.SET("remark = #{record.remark,jdbcType=VARCHAR}");
        sql.SET("create_time = #{record.createTime,jdbcType=TIMESTAMP}");
        sql.SET("datetime_lastchange = #{record.datetimeLastchange,jdbcType=TIMESTAMP}");
        
        SwCollaborationDetailLogExample example = (SwCollaborationDetailLogExample) parameter.get("example");
        applyWhere(sql, example, true);
        return sql.toString();
    }

    public String updateByPrimaryKeySelective(SwCollaborationDetailLog record) {
        SQL sql = new SQL();
        sql.UPDATE("sw_collaboration_detail_log");
        
        if (record.getDetailId() != null) {
            sql.SET("detail_id = #{detailId,jdbcType=BIGINT}");
        }
        
        if (record.getRowIndex() != null) {
            sql.SET("row_index = #{rowIndex,jdbcType=INTEGER}");
        }
        
        if (record.getColIndex() != null) {
            sql.SET("col_index = #{colIndex,jdbcType=INTEGER}");
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

    protected void applyWhere(SQL sql, SwCollaborationDetailLogExample example, boolean includeExamplePhrase) {
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