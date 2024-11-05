package com.foxconn.sw.data.mapper.auto;

import com.foxconn.sw.data.entity.SwDocumentPermission;
import com.foxconn.sw.data.entity.SwDocumentPermissionExample.Criteria;
import com.foxconn.sw.data.entity.SwDocumentPermissionExample.Criterion;
import com.foxconn.sw.data.entity.SwDocumentPermissionExample;
import java.util.List;
import java.util.Map;
import org.apache.ibatis.jdbc.SQL;

public class SwDocumentPermissionSqlProvider {

    public String countByExample(SwDocumentPermissionExample example) {
        SQL sql = new SQL();
        sql.SELECT("count(*)").FROM("sw_document_permission");
        applyWhere(sql, example, false);
        return sql.toString();
    }

    public String deleteByExample(SwDocumentPermissionExample example) {
        SQL sql = new SQL();
        sql.DELETE_FROM("sw_document_permission");
        applyWhere(sql, example, false);
        return sql.toString();
    }

    public String insertSelective(SwDocumentPermission record) {
        SQL sql = new SQL();
        sql.INSERT_INTO("sw_document_permission");
        
        if (record.getDocumentId() != null) {
            sql.VALUES("document_id", "#{documentId,jdbcType=INTEGER}");
        }
        
        if (record.getPermissionType() != null) {
            sql.VALUES("permission_type", "#{permissionType,jdbcType=INTEGER}");
        }
        
        if (record.getPermissionValue() != null) {
            sql.VALUES("permission_value", "#{permissionValue,jdbcType=VARCHAR}");
        }
        
        if (record.getExtra() != null) {
            sql.VALUES("extra", "#{extra,jdbcType=VARCHAR}");
        }
        
        return sql.toString();
    }

    public String selectByExample(SwDocumentPermissionExample example) {
        SQL sql = new SQL();
        if (example != null && example.isDistinct()) {
            sql.SELECT_DISTINCT("id");
        } else {
            sql.SELECT("id");
        }
        sql.SELECT("document_id");
        sql.SELECT("permission_type");
        sql.SELECT("permission_value");
        sql.SELECT("extra");
        sql.FROM("sw_document_permission");
        applyWhere(sql, example, false);
        
        if (example != null && example.getOrderByClause() != null) {
            sql.ORDER_BY(example.getOrderByClause());
        }
        
        return sql.toString();
    }

    public String updateByExampleSelective(Map<String, Object> parameter) {
        SwDocumentPermission record = (SwDocumentPermission) parameter.get("record");
        SwDocumentPermissionExample example = (SwDocumentPermissionExample) parameter.get("example");
        
        SQL sql = new SQL();
        sql.UPDATE("sw_document_permission");
        
        if (record.getId() != null) {
            sql.SET("id = #{record.id,jdbcType=INTEGER}");
        }
        
        if (record.getDocumentId() != null) {
            sql.SET("document_id = #{record.documentId,jdbcType=INTEGER}");
        }
        
        if (record.getPermissionType() != null) {
            sql.SET("permission_type = #{record.permissionType,jdbcType=INTEGER}");
        }
        
        if (record.getPermissionValue() != null) {
            sql.SET("permission_value = #{record.permissionValue,jdbcType=VARCHAR}");
        }
        
        if (record.getExtra() != null) {
            sql.SET("extra = #{record.extra,jdbcType=VARCHAR}");
        }
        
        applyWhere(sql, example, true);
        return sql.toString();
    }

    public String updateByExample(Map<String, Object> parameter) {
        SQL sql = new SQL();
        sql.UPDATE("sw_document_permission");
        
        sql.SET("id = #{record.id,jdbcType=INTEGER}");
        sql.SET("document_id = #{record.documentId,jdbcType=INTEGER}");
        sql.SET("permission_type = #{record.permissionType,jdbcType=INTEGER}");
        sql.SET("permission_value = #{record.permissionValue,jdbcType=VARCHAR}");
        sql.SET("extra = #{record.extra,jdbcType=VARCHAR}");
        
        SwDocumentPermissionExample example = (SwDocumentPermissionExample) parameter.get("example");
        applyWhere(sql, example, true);
        return sql.toString();
    }

    public String updateByPrimaryKeySelective(SwDocumentPermission record) {
        SQL sql = new SQL();
        sql.UPDATE("sw_document_permission");
        
        if (record.getDocumentId() != null) {
            sql.SET("document_id = #{documentId,jdbcType=INTEGER}");
        }
        
        if (record.getPermissionType() != null) {
            sql.SET("permission_type = #{permissionType,jdbcType=INTEGER}");
        }
        
        if (record.getPermissionValue() != null) {
            sql.SET("permission_value = #{permissionValue,jdbcType=VARCHAR}");
        }
        
        if (record.getExtra() != null) {
            sql.SET("extra = #{extra,jdbcType=VARCHAR}");
        }
        
        sql.WHERE("id = #{id,jdbcType=INTEGER}");
        
        return sql.toString();
    }

    protected void applyWhere(SQL sql, SwDocumentPermissionExample example, boolean includeExamplePhrase) {
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