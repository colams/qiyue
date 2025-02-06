package com.foxconn.sw.data.mapper.auto;

import com.foxconn.sw.data.entity.SwTools;
import com.foxconn.sw.data.entity.SwToolsExample.Criteria;
import com.foxconn.sw.data.entity.SwToolsExample.Criterion;
import com.foxconn.sw.data.entity.SwToolsExample;
import java.util.List;
import java.util.Map;
import org.apache.ibatis.jdbc.SQL;

public class SwToolsSqlProvider {

    public String insertSelective(SwTools record) {
        SQL sql = new SQL();
        sql.INSERT_INTO("sw_tools");
        
        if (record.getToolName() != null) {
            sql.VALUES("tool_name", "#{toolName,jdbcType=VARCHAR}");
        }
        
        if (record.getToolIcon() != null) {
            sql.VALUES("tool_icon", "#{toolIcon,jdbcType=VARCHAR}");
        }
        
        if (record.getPropertyId() != null) {
            sql.VALUES("property_id", "#{propertyId,jdbcType=INTEGER}");
        }
        
        if (record.getVersionNo() != null) {
            sql.VALUES("version_no", "#{versionNo,jdbcType=VARCHAR}");
        }
        
        if (record.getFilePath() != null) {
            sql.VALUES("file_path", "#{filePath,jdbcType=VARCHAR}");
        }
        
        if (record.getResourceId() != null) {
            sql.VALUES("resource_id", "#{resourceId,jdbcType=INTEGER}");
        }
        
        if (record.getToolSize() != null) {
            sql.VALUES("tool_size", "#{toolSize,jdbcType=DOUBLE}");
        }
        
        if (record.getIntroduction() != null) {
            sql.VALUES("introduction", "#{introduction,jdbcType=VARCHAR}");
        }
        
        if (record.getUpdateContent() != null) {
            sql.VALUES("update_content", "#{updateContent,jdbcType=VARCHAR}");
        }
        
        if (record.getUseGuide() != null) {
            sql.VALUES("use_guide", "#{useGuide,jdbcType=VARCHAR}");
        }
        
        if (record.getOperator() != null) {
            sql.VALUES("operator", "#{operator,jdbcType=VARCHAR}");
        }
        
        if (record.getCreateTime() != null) {
            sql.VALUES("create_time", "#{createTime,jdbcType=TIMESTAMP}");
        }
        
        if (record.getDatetimeLastchange() != null) {
            sql.VALUES("datetime_lastchange", "#{datetimeLastchange,jdbcType=TIMESTAMP}");
        }
        
        return sql.toString();
    }

    public String selectByExample(SwToolsExample example) {
        SQL sql = new SQL();
        if (example != null && example.isDistinct()) {
            sql.SELECT_DISTINCT("id");
        } else {
            sql.SELECT("id");
        }
        sql.SELECT("tool_name");
        sql.SELECT("tool_icon");
        sql.SELECT("property_id");
        sql.SELECT("version_no");
        sql.SELECT("file_path");
        sql.SELECT("resource_id");
        sql.SELECT("tool_size");
        sql.SELECT("introduction");
        sql.SELECT("update_content");
        sql.SELECT("use_guide");
        sql.SELECT("operator");
        sql.SELECT("create_time");
        sql.SELECT("datetime_lastchange");
        sql.FROM("sw_tools");
        applyWhere(sql, example, false);
        
        if (example != null && example.getOrderByClause() != null) {
            sql.ORDER_BY(example.getOrderByClause());
        }
        
        return sql.toString();
    }

    public String updateByExampleSelective(Map<String, Object> parameter) {
        SwTools record = (SwTools) parameter.get("record");
        SwToolsExample example = (SwToolsExample) parameter.get("example");
        
        SQL sql = new SQL();
        sql.UPDATE("sw_tools");
        
        if (record.getId() != null) {
            sql.SET("id = #{record.id,jdbcType=INTEGER}");
        }
        
        if (record.getToolName() != null) {
            sql.SET("tool_name = #{record.toolName,jdbcType=VARCHAR}");
        }
        
        if (record.getToolIcon() != null) {
            sql.SET("tool_icon = #{record.toolIcon,jdbcType=VARCHAR}");
        }
        
        if (record.getPropertyId() != null) {
            sql.SET("property_id = #{record.propertyId,jdbcType=INTEGER}");
        }
        
        if (record.getVersionNo() != null) {
            sql.SET("version_no = #{record.versionNo,jdbcType=VARCHAR}");
        }
        
        if (record.getFilePath() != null) {
            sql.SET("file_path = #{record.filePath,jdbcType=VARCHAR}");
        }
        
        if (record.getResourceId() != null) {
            sql.SET("resource_id = #{record.resourceId,jdbcType=INTEGER}");
        }
        
        if (record.getToolSize() != null) {
            sql.SET("tool_size = #{record.toolSize,jdbcType=DOUBLE}");
        }
        
        if (record.getIntroduction() != null) {
            sql.SET("introduction = #{record.introduction,jdbcType=VARCHAR}");
        }
        
        if (record.getUpdateContent() != null) {
            sql.SET("update_content = #{record.updateContent,jdbcType=VARCHAR}");
        }
        
        if (record.getUseGuide() != null) {
            sql.SET("use_guide = #{record.useGuide,jdbcType=VARCHAR}");
        }
        
        if (record.getOperator() != null) {
            sql.SET("operator = #{record.operator,jdbcType=VARCHAR}");
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
        sql.UPDATE("sw_tools");
        
        sql.SET("id = #{record.id,jdbcType=INTEGER}");
        sql.SET("tool_name = #{record.toolName,jdbcType=VARCHAR}");
        sql.SET("tool_icon = #{record.toolIcon,jdbcType=VARCHAR}");
        sql.SET("property_id = #{record.propertyId,jdbcType=INTEGER}");
        sql.SET("version_no = #{record.versionNo,jdbcType=VARCHAR}");
        sql.SET("file_path = #{record.filePath,jdbcType=VARCHAR}");
        sql.SET("resource_id = #{record.resourceId,jdbcType=INTEGER}");
        sql.SET("tool_size = #{record.toolSize,jdbcType=DOUBLE}");
        sql.SET("introduction = #{record.introduction,jdbcType=VARCHAR}");
        sql.SET("update_content = #{record.updateContent,jdbcType=VARCHAR}");
        sql.SET("use_guide = #{record.useGuide,jdbcType=VARCHAR}");
        sql.SET("operator = #{record.operator,jdbcType=VARCHAR}");
        sql.SET("create_time = #{record.createTime,jdbcType=TIMESTAMP}");
        sql.SET("datetime_lastchange = #{record.datetimeLastchange,jdbcType=TIMESTAMP}");
        
        SwToolsExample example = (SwToolsExample) parameter.get("example");
        applyWhere(sql, example, true);
        return sql.toString();
    }

    public String updateByPrimaryKeySelective(SwTools record) {
        SQL sql = new SQL();
        sql.UPDATE("sw_tools");
        
        if (record.getToolName() != null) {
            sql.SET("tool_name = #{toolName,jdbcType=VARCHAR}");
        }
        
        if (record.getToolIcon() != null) {
            sql.SET("tool_icon = #{toolIcon,jdbcType=VARCHAR}");
        }
        
        if (record.getPropertyId() != null) {
            sql.SET("property_id = #{propertyId,jdbcType=INTEGER}");
        }
        
        if (record.getVersionNo() != null) {
            sql.SET("version_no = #{versionNo,jdbcType=VARCHAR}");
        }
        
        if (record.getFilePath() != null) {
            sql.SET("file_path = #{filePath,jdbcType=VARCHAR}");
        }
        
        if (record.getResourceId() != null) {
            sql.SET("resource_id = #{resourceId,jdbcType=INTEGER}");
        }
        
        if (record.getToolSize() != null) {
            sql.SET("tool_size = #{toolSize,jdbcType=DOUBLE}");
        }
        
        if (record.getIntroduction() != null) {
            sql.SET("introduction = #{introduction,jdbcType=VARCHAR}");
        }
        
        if (record.getUpdateContent() != null) {
            sql.SET("update_content = #{updateContent,jdbcType=VARCHAR}");
        }
        
        if (record.getUseGuide() != null) {
            sql.SET("use_guide = #{useGuide,jdbcType=VARCHAR}");
        }
        
        if (record.getOperator() != null) {
            sql.SET("operator = #{operator,jdbcType=VARCHAR}");
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

    protected void applyWhere(SQL sql, SwToolsExample example, boolean includeExamplePhrase) {
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