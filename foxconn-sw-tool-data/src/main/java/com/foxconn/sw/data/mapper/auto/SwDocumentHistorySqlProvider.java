package com.foxconn.sw.data.mapper.auto;

import com.foxconn.sw.data.entity.SwDocumentHistory;
import com.foxconn.sw.data.entity.SwDocumentHistoryExample.Criteria;
import com.foxconn.sw.data.entity.SwDocumentHistoryExample.Criterion;
import com.foxconn.sw.data.entity.SwDocumentHistoryExample;
import java.util.List;
import java.util.Map;
import org.apache.ibatis.jdbc.SQL;

public class SwDocumentHistorySqlProvider {

    public String countByExample(SwDocumentHistoryExample example) {
        SQL sql = new SQL();
        sql.SELECT("count(*)").FROM("sw_document_history");
        applyWhere(sql, example, false);
        return sql.toString();
    }

    public String deleteByExample(SwDocumentHistoryExample example) {
        SQL sql = new SQL();
        sql.DELETE_FROM("sw_document_history");
        applyWhere(sql, example, false);
        return sql.toString();
    }

    public String insertSelective(SwDocumentHistory record) {
        SQL sql = new SQL();
        sql.INSERT_INTO("sw_document_history");
        
        if (record.getDocumentId() != null) {
            sql.VALUES("document_id", "#{documentId,jdbcType=INTEGER}");
        }
        
        if (record.getDocumentName() != null) {
            sql.VALUES("document_name", "#{documentName,jdbcType=VARCHAR}");
        }
        
        if (record.getResourceId() != null) {
            sql.VALUES("resource_id", "#{resourceId,jdbcType=INTEGER}");
        }
        
        if (record.getCreateTime() != null) {
            sql.VALUES("create_time", "#{createTime,jdbcType=TIMESTAMP}");
        }
        
        if (record.getDatetimeLastchange() != null) {
            sql.VALUES("datetime_lastchange", "#{datetimeLastchange,jdbcType=TIMESTAMP}");
        }
        
        if (record.getCreator() != null) {
            sql.VALUES("creator", "#{creator,jdbcType=VARCHAR}");
        }
        
        return sql.toString();
    }

    public String selectByExample(SwDocumentHistoryExample example) {
        SQL sql = new SQL();
        if (example != null && example.isDistinct()) {
            sql.SELECT_DISTINCT("id");
        } else {
            sql.SELECT("id");
        }
        sql.SELECT("document_id");
        sql.SELECT("document_name");
        sql.SELECT("resource_id");
        sql.SELECT("create_time");
        sql.SELECT("datetime_lastchange");
        sql.SELECT("creator");
        sql.FROM("sw_document_history");
        applyWhere(sql, example, false);
        
        if (example != null && example.getOrderByClause() != null) {
            sql.ORDER_BY(example.getOrderByClause());
        }
        
        return sql.toString();
    }

    public String updateByExampleSelective(Map<String, Object> parameter) {
        SwDocumentHistory record = (SwDocumentHistory) parameter.get("record");
        SwDocumentHistoryExample example = (SwDocumentHistoryExample) parameter.get("example");
        
        SQL sql = new SQL();
        sql.UPDATE("sw_document_history");
        
        if (record.getId() != null) {
            sql.SET("id = #{record.id,jdbcType=INTEGER}");
        }
        
        if (record.getDocumentId() != null) {
            sql.SET("document_id = #{record.documentId,jdbcType=INTEGER}");
        }
        
        if (record.getDocumentName() != null) {
            sql.SET("document_name = #{record.documentName,jdbcType=VARCHAR}");
        }
        
        if (record.getResourceId() != null) {
            sql.SET("resource_id = #{record.resourceId,jdbcType=INTEGER}");
        }
        
        if (record.getCreateTime() != null) {
            sql.SET("create_time = #{record.createTime,jdbcType=TIMESTAMP}");
        }
        
        if (record.getDatetimeLastchange() != null) {
            sql.SET("datetime_lastchange = #{record.datetimeLastchange,jdbcType=TIMESTAMP}");
        }
        
        if (record.getCreator() != null) {
            sql.SET("creator = #{record.creator,jdbcType=VARCHAR}");
        }
        
        applyWhere(sql, example, true);
        return sql.toString();
    }

    public String updateByExample(Map<String, Object> parameter) {
        SQL sql = new SQL();
        sql.UPDATE("sw_document_history");
        
        sql.SET("id = #{record.id,jdbcType=INTEGER}");
        sql.SET("document_id = #{record.documentId,jdbcType=INTEGER}");
        sql.SET("document_name = #{record.documentName,jdbcType=VARCHAR}");
        sql.SET("resource_id = #{record.resourceId,jdbcType=INTEGER}");
        sql.SET("create_time = #{record.createTime,jdbcType=TIMESTAMP}");
        sql.SET("datetime_lastchange = #{record.datetimeLastchange,jdbcType=TIMESTAMP}");
        sql.SET("creator = #{record.creator,jdbcType=VARCHAR}");
        
        SwDocumentHistoryExample example = (SwDocumentHistoryExample) parameter.get("example");
        applyWhere(sql, example, true);
        return sql.toString();
    }

    public String updateByPrimaryKeySelective(SwDocumentHistory record) {
        SQL sql = new SQL();
        sql.UPDATE("sw_document_history");
        
        if (record.getDocumentId() != null) {
            sql.SET("document_id = #{documentId,jdbcType=INTEGER}");
        }
        
        if (record.getDocumentName() != null) {
            sql.SET("document_name = #{documentName,jdbcType=VARCHAR}");
        }
        
        if (record.getResourceId() != null) {
            sql.SET("resource_id = #{resourceId,jdbcType=INTEGER}");
        }
        
        if (record.getCreateTime() != null) {
            sql.SET("create_time = #{createTime,jdbcType=TIMESTAMP}");
        }
        
        if (record.getDatetimeLastchange() != null) {
            sql.SET("datetime_lastchange = #{datetimeLastchange,jdbcType=TIMESTAMP}");
        }
        
        if (record.getCreator() != null) {
            sql.SET("creator = #{creator,jdbcType=VARCHAR}");
        }
        
        sql.WHERE("id = #{id,jdbcType=INTEGER}");
        
        return sql.toString();
    }

    protected void applyWhere(SQL sql, SwDocumentHistoryExample example, boolean includeExamplePhrase) {
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