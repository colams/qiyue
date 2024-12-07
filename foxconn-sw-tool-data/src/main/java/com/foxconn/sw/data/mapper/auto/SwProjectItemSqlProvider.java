package com.foxconn.sw.data.mapper.auto;

import com.foxconn.sw.data.entity.SwProjectItem;
import com.foxconn.sw.data.entity.SwProjectItemExample.Criteria;
import com.foxconn.sw.data.entity.SwProjectItemExample.Criterion;
import com.foxconn.sw.data.entity.SwProjectItemExample;
import java.util.List;
import java.util.Map;
import org.apache.ibatis.jdbc.SQL;

public class SwProjectItemSqlProvider {

    public String countByExample(SwProjectItemExample example) {
        SQL sql = new SQL();
        sql.SELECT("count(*)").FROM("sw_project_item");
        applyWhere(sql, example, false);
        return sql.toString();
    }

    public String deleteByExample(SwProjectItemExample example) {
        SQL sql = new SQL();
        sql.DELETE_FROM("sw_project_item");
        applyWhere(sql, example, false);
        return sql.toString();
    }

    public String insertSelective(SwProjectItem record) {
        SQL sql = new SQL();
        sql.INSERT_INTO("sw_project_item");
        
        if (record.getProjectId() != null) {
            sql.VALUES("project_id", "#{projectId,jdbcType=INTEGER}");
        }
        
        if (record.getModuleType() != null) {
            sql.VALUES("module_type", "#{moduleType,jdbcType=VARCHAR}");
        }
        
        if (record.getUpdateBy() != null) {
            sql.VALUES("update_by", "#{updateBy,jdbcType=VARCHAR}");
        }
        
        if (record.getDetailType() != null) {
            sql.VALUES("detail_type", "#{detailType,jdbcType=VARCHAR}");
        }
        
        if (record.getProjectItem() != null) {
            sql.VALUES("project_item", "#{projectItem,jdbcType=VARCHAR}");
        }
        
        if (record.getProjectValue() != null) {
            sql.VALUES("project_value", "#{projectValue,jdbcType=VARCHAR}");
        }
        
        if (record.getSpareValue() != null) {
            sql.VALUES("spare_value", "#{spareValue,jdbcType=VARCHAR}");
        }
        
        if (record.getOperator() != null) {
            sql.VALUES("operator", "#{operator,jdbcType=VARCHAR}");
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

    public String selectByExample(SwProjectItemExample example) {
        SQL sql = new SQL();
        if (example != null && example.isDistinct()) {
            sql.SELECT_DISTINCT("id");
        } else {
            sql.SELECT("id");
        }
        sql.SELECT("project_id");
        sql.SELECT("module_type");
        sql.SELECT("update_by");
        sql.SELECT("detail_type");
        sql.SELECT("project_item");
        sql.SELECT("project_value");
        sql.SELECT("spare_value");
        sql.SELECT("operator");
        sql.SELECT("is_delete");
        sql.SELECT("create_time");
        sql.SELECT("datetime_lastchange");
        sql.FROM("sw_project_item");
        applyWhere(sql, example, false);
        
        if (example != null && example.getOrderByClause() != null) {
            sql.ORDER_BY(example.getOrderByClause());
        }
        
        return sql.toString();
    }

    public String updateByExampleSelective(Map<String, Object> parameter) {
        SwProjectItem record = (SwProjectItem) parameter.get("record");
        SwProjectItemExample example = (SwProjectItemExample) parameter.get("example");
        
        SQL sql = new SQL();
        sql.UPDATE("sw_project_item");
        
        if (record.getId() != null) {
            sql.SET("id = #{record.id,jdbcType=INTEGER}");
        }
        
        if (record.getProjectId() != null) {
            sql.SET("project_id = #{record.projectId,jdbcType=INTEGER}");
        }
        
        if (record.getModuleType() != null) {
            sql.SET("module_type = #{record.moduleType,jdbcType=VARCHAR}");
        }
        
        if (record.getUpdateBy() != null) {
            sql.SET("update_by = #{record.updateBy,jdbcType=VARCHAR}");
        }
        
        if (record.getDetailType() != null) {
            sql.SET("detail_type = #{record.detailType,jdbcType=VARCHAR}");
        }
        
        if (record.getProjectItem() != null) {
            sql.SET("project_item = #{record.projectItem,jdbcType=VARCHAR}");
        }
        
        if (record.getProjectValue() != null) {
            sql.SET("project_value = #{record.projectValue,jdbcType=VARCHAR}");
        }
        
        if (record.getSpareValue() != null) {
            sql.SET("spare_value = #{record.spareValue,jdbcType=VARCHAR}");
        }
        
        if (record.getOperator() != null) {
            sql.SET("operator = #{record.operator,jdbcType=VARCHAR}");
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
        sql.UPDATE("sw_project_item");
        
        sql.SET("id = #{record.id,jdbcType=INTEGER}");
        sql.SET("project_id = #{record.projectId,jdbcType=INTEGER}");
        sql.SET("module_type = #{record.moduleType,jdbcType=VARCHAR}");
        sql.SET("update_by = #{record.updateBy,jdbcType=VARCHAR}");
        sql.SET("detail_type = #{record.detailType,jdbcType=VARCHAR}");
        sql.SET("project_item = #{record.projectItem,jdbcType=VARCHAR}");
        sql.SET("project_value = #{record.projectValue,jdbcType=VARCHAR}");
        sql.SET("spare_value = #{record.spareValue,jdbcType=VARCHAR}");
        sql.SET("operator = #{record.operator,jdbcType=VARCHAR}");
        sql.SET("is_delete = #{record.isDelete,jdbcType=INTEGER}");
        sql.SET("create_time = #{record.createTime,jdbcType=TIMESTAMP}");
        sql.SET("datetime_lastchange = #{record.datetimeLastchange,jdbcType=TIMESTAMP}");
        
        SwProjectItemExample example = (SwProjectItemExample) parameter.get("example");
        applyWhere(sql, example, true);
        return sql.toString();
    }

    public String updateByPrimaryKeySelective(SwProjectItem record) {
        SQL sql = new SQL();
        sql.UPDATE("sw_project_item");
        
        if (record.getProjectId() != null) {
            sql.SET("project_id = #{projectId,jdbcType=INTEGER}");
        }
        
        if (record.getModuleType() != null) {
            sql.SET("module_type = #{moduleType,jdbcType=VARCHAR}");
        }
        
        if (record.getUpdateBy() != null) {
            sql.SET("update_by = #{updateBy,jdbcType=VARCHAR}");
        }
        
        if (record.getDetailType() != null) {
            sql.SET("detail_type = #{detailType,jdbcType=VARCHAR}");
        }
        
        if (record.getProjectItem() != null) {
            sql.SET("project_item = #{projectItem,jdbcType=VARCHAR}");
        }
        
        if (record.getProjectValue() != null) {
            sql.SET("project_value = #{projectValue,jdbcType=VARCHAR}");
        }
        
        if (record.getSpareValue() != null) {
            sql.SET("spare_value = #{spareValue,jdbcType=VARCHAR}");
        }
        
        if (record.getOperator() != null) {
            sql.SET("operator = #{operator,jdbcType=VARCHAR}");
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

    protected void applyWhere(SQL sql, SwProjectItemExample example, boolean includeExamplePhrase) {
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