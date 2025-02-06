package com.foxconn.sw.data.mapper.auto;

import com.foxconn.sw.data.entity.SwProject;
import com.foxconn.sw.data.entity.SwProjectExample.Criteria;
import com.foxconn.sw.data.entity.SwProjectExample.Criterion;
import com.foxconn.sw.data.entity.SwProjectExample;
import java.util.List;
import java.util.Map;
import org.apache.ibatis.jdbc.SQL;

public class SwProjectSqlProvider {

    public String insertSelective(SwProject record) {
        SQL sql = new SQL();
        sql.INSERT_INTO("sw_project");
        
        if (record.getYears() != null) {
            sql.VALUES("years", "#{years,jdbcType=INTEGER}");
        }
        
        if (record.getProjectCode() != null) {
            sql.VALUES("project_code", "#{projectCode,jdbcType=VARCHAR}");
        }
        
        if (record.getCustomerName() != null) {
            sql.VALUES("customer_name", "#{customerName,jdbcType=VARCHAR}");
        }
        
        if (record.getFullName() != null) {
            sql.VALUES("full_name", "#{fullName,jdbcType=VARCHAR}");
        }
        
        if (record.getManufacturingModel() != null) {
            sql.VALUES("manufacturing_model", "#{manufacturingModel,jdbcType=VARCHAR}");
        }
        
        if (record.getStatus() != null) {
            sql.VALUES("status", "#{status,jdbcType=VARCHAR}");
        }
        
        if (record.getRfqTime() != null) {
            sql.VALUES("rfq_time", "#{rfqTime,jdbcType=VARCHAR}");
        }
        
        if (record.getCustomer() != null) {
            sql.VALUES("customer", "#{customer,jdbcType=VARCHAR}");
        }
        
        if (record.getCustomerPartNo() != null) {
            sql.VALUES("customer_part_no", "#{customerPartNo,jdbcType=VARCHAR}");
        }
        
        if (record.getApplication() != null) {
            sql.VALUES("application", "#{application,jdbcType=VARCHAR}");
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

    public String selectByExample(SwProjectExample example) {
        SQL sql = new SQL();
        if (example != null && example.isDistinct()) {
            sql.SELECT_DISTINCT("id");
        } else {
            sql.SELECT("id");
        }
        sql.SELECT("years");
        sql.SELECT("project_code");
        sql.SELECT("customer_name");
        sql.SELECT("full_name");
        sql.SELECT("manufacturing_model");
        sql.SELECT("status");
        sql.SELECT("rfq_time");
        sql.SELECT("customer");
        sql.SELECT("customer_part_no");
        sql.SELECT("application");
        sql.SELECT("operator");
        sql.SELECT("is_delete");
        sql.SELECT("create_time");
        sql.SELECT("datetime_lastchange");
        sql.FROM("sw_project");
        applyWhere(sql, example, false);
        
        if (example != null && example.getOrderByClause() != null) {
            sql.ORDER_BY(example.getOrderByClause());
        }
        
        return sql.toString();
    }

    public String updateByExampleSelective(Map<String, Object> parameter) {
        SwProject record = (SwProject) parameter.get("record");
        SwProjectExample example = (SwProjectExample) parameter.get("example");
        
        SQL sql = new SQL();
        sql.UPDATE("sw_project");
        
        if (record.getId() != null) {
            sql.SET("id = #{record.id,jdbcType=INTEGER}");
        }
        
        if (record.getYears() != null) {
            sql.SET("years = #{record.years,jdbcType=INTEGER}");
        }
        
        if (record.getProjectCode() != null) {
            sql.SET("project_code = #{record.projectCode,jdbcType=VARCHAR}");
        }
        
        if (record.getCustomerName() != null) {
            sql.SET("customer_name = #{record.customerName,jdbcType=VARCHAR}");
        }
        
        if (record.getFullName() != null) {
            sql.SET("full_name = #{record.fullName,jdbcType=VARCHAR}");
        }
        
        if (record.getManufacturingModel() != null) {
            sql.SET("manufacturing_model = #{record.manufacturingModel,jdbcType=VARCHAR}");
        }
        
        if (record.getStatus() != null) {
            sql.SET("status = #{record.status,jdbcType=VARCHAR}");
        }
        
        if (record.getRfqTime() != null) {
            sql.SET("rfq_time = #{record.rfqTime,jdbcType=VARCHAR}");
        }
        
        if (record.getCustomer() != null) {
            sql.SET("customer = #{record.customer,jdbcType=VARCHAR}");
        }
        
        if (record.getCustomerPartNo() != null) {
            sql.SET("customer_part_no = #{record.customerPartNo,jdbcType=VARCHAR}");
        }
        
        if (record.getApplication() != null) {
            sql.SET("application = #{record.application,jdbcType=VARCHAR}");
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
        sql.UPDATE("sw_project");
        
        sql.SET("id = #{record.id,jdbcType=INTEGER}");
        sql.SET("years = #{record.years,jdbcType=INTEGER}");
        sql.SET("project_code = #{record.projectCode,jdbcType=VARCHAR}");
        sql.SET("customer_name = #{record.customerName,jdbcType=VARCHAR}");
        sql.SET("full_name = #{record.fullName,jdbcType=VARCHAR}");
        sql.SET("manufacturing_model = #{record.manufacturingModel,jdbcType=VARCHAR}");
        sql.SET("status = #{record.status,jdbcType=VARCHAR}");
        sql.SET("rfq_time = #{record.rfqTime,jdbcType=VARCHAR}");
        sql.SET("customer = #{record.customer,jdbcType=VARCHAR}");
        sql.SET("customer_part_no = #{record.customerPartNo,jdbcType=VARCHAR}");
        sql.SET("application = #{record.application,jdbcType=VARCHAR}");
        sql.SET("operator = #{record.operator,jdbcType=VARCHAR}");
        sql.SET("is_delete = #{record.isDelete,jdbcType=INTEGER}");
        sql.SET("create_time = #{record.createTime,jdbcType=TIMESTAMP}");
        sql.SET("datetime_lastchange = #{record.datetimeLastchange,jdbcType=TIMESTAMP}");
        
        SwProjectExample example = (SwProjectExample) parameter.get("example");
        applyWhere(sql, example, true);
        return sql.toString();
    }

    public String updateByPrimaryKeySelective(SwProject record) {
        SQL sql = new SQL();
        sql.UPDATE("sw_project");
        
        if (record.getYears() != null) {
            sql.SET("years = #{years,jdbcType=INTEGER}");
        }
        
        if (record.getProjectCode() != null) {
            sql.SET("project_code = #{projectCode,jdbcType=VARCHAR}");
        }
        
        if (record.getCustomerName() != null) {
            sql.SET("customer_name = #{customerName,jdbcType=VARCHAR}");
        }
        
        if (record.getFullName() != null) {
            sql.SET("full_name = #{fullName,jdbcType=VARCHAR}");
        }
        
        if (record.getManufacturingModel() != null) {
            sql.SET("manufacturing_model = #{manufacturingModel,jdbcType=VARCHAR}");
        }
        
        if (record.getStatus() != null) {
            sql.SET("status = #{status,jdbcType=VARCHAR}");
        }
        
        if (record.getRfqTime() != null) {
            sql.SET("rfq_time = #{rfqTime,jdbcType=VARCHAR}");
        }
        
        if (record.getCustomer() != null) {
            sql.SET("customer = #{customer,jdbcType=VARCHAR}");
        }
        
        if (record.getCustomerPartNo() != null) {
            sql.SET("customer_part_no = #{customerPartNo,jdbcType=VARCHAR}");
        }
        
        if (record.getApplication() != null) {
            sql.SET("application = #{application,jdbcType=VARCHAR}");
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

    protected void applyWhere(SQL sql, SwProjectExample example, boolean includeExamplePhrase) {
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