package com.foxconn.sw.data.mapper.auto;

import com.foxconn.sw.data.entity.SwEmployee;
import com.foxconn.sw.data.entity.SwEmployeeExample.Criteria;
import com.foxconn.sw.data.entity.SwEmployeeExample.Criterion;
import com.foxconn.sw.data.entity.SwEmployeeExample;
import java.util.List;
import java.util.Map;
import org.apache.ibatis.jdbc.SQL;

public class SwEmployeeSqlProvider {

    public String countByExample(SwEmployeeExample example) {
        SQL sql = new SQL();
        sql.SELECT("count(*)").FROM("sw_employee");
        applyWhere(sql, example, false);
        return sql.toString();
    }

    public String deleteByExample(SwEmployeeExample example) {
        SQL sql = new SQL();
        sql.DELETE_FROM("sw_employee");
        applyWhere(sql, example, false);
        return sql.toString();
    }

    public String insertSelective(SwEmployee record) {
        SQL sql = new SQL();
        sql.INSERT_INTO("sw_employee");
        
        if (record.getName() != null) {
            sql.VALUES("name", "#{name,jdbcType=VARCHAR}");
        }
        
        if (record.getEmployeeNo() != null) {
            sql.VALUES("employee_no", "#{employeeNo,jdbcType=VARCHAR}");
        }
        
        if (record.getDepartmentId() != null) {
            sql.VALUES("department_id", "#{departmentId,jdbcType=INTEGER}");
        }
        
        if (record.getJobTitle() != null) {
            sql.VALUES("job_title", "#{jobTitle,jdbcType=VARCHAR}");
        }
        
        if (record.getEmail() != null) {
            sql.VALUES("email", "#{email,jdbcType=VARCHAR}");
        }
        
        if (record.getPhoneNumber() != null) {
            sql.VALUES("phone_number", "#{phoneNumber,jdbcType=VARCHAR}");
        }
        
        if (record.getHireDate() != null) {
            sql.VALUES("hire_date", "#{hireDate,jdbcType=VARCHAR}");
        }
        
        if (record.getStatus() != null) {
            sql.VALUES("status", "#{status,jdbcType=INTEGER}");
        }
        
        if (record.getPositionStartDate() != null) {
            sql.VALUES("position_start_date", "#{positionStartDate,jdbcType=VARCHAR}");
        }
        
        if (record.getPositionEndDate() != null) {
            sql.VALUES("position_end_date", "#{positionEndDate,jdbcType=VARCHAR}");
        }
        
        if (record.getDatetimeLastchange() != null) {
            sql.VALUES("datetime_lastchange", "#{datetimeLastchange,jdbcType=TIMESTAMP}");
        }
        
        return sql.toString();
    }

    public String selectByExample(SwEmployeeExample example) {
        SQL sql = new SQL();
        if (example != null && example.isDistinct()) {
            sql.SELECT_DISTINCT("id");
        } else {
            sql.SELECT("id");
        }
        sql.SELECT("name");
        sql.SELECT("employee_no");
        sql.SELECT("department_id");
        sql.SELECT("job_title");
        sql.SELECT("email");
        sql.SELECT("phone_number");
        sql.SELECT("hire_date");
        sql.SELECT("status");
        sql.SELECT("position_start_date");
        sql.SELECT("position_end_date");
        sql.SELECT("datetime_lastchange");
        sql.FROM("sw_employee");
        applyWhere(sql, example, false);
        
        if (example != null && example.getOrderByClause() != null) {
            sql.ORDER_BY(example.getOrderByClause());
        }
        
        return sql.toString();
    }

    public String updateByExampleSelective(Map<String, Object> parameter) {
        SwEmployee record = (SwEmployee) parameter.get("record");
        SwEmployeeExample example = (SwEmployeeExample) parameter.get("example");
        
        SQL sql = new SQL();
        sql.UPDATE("sw_employee");
        
        if (record.getId() != null) {
            sql.SET("id = #{record.id,jdbcType=INTEGER}");
        }
        
        if (record.getName() != null) {
            sql.SET("name = #{record.name,jdbcType=VARCHAR}");
        }
        
        if (record.getEmployeeNo() != null) {
            sql.SET("employee_no = #{record.employeeNo,jdbcType=VARCHAR}");
        }
        
        if (record.getDepartmentId() != null) {
            sql.SET("department_id = #{record.departmentId,jdbcType=INTEGER}");
        }
        
        if (record.getJobTitle() != null) {
            sql.SET("job_title = #{record.jobTitle,jdbcType=VARCHAR}");
        }
        
        if (record.getEmail() != null) {
            sql.SET("email = #{record.email,jdbcType=VARCHAR}");
        }
        
        if (record.getPhoneNumber() != null) {
            sql.SET("phone_number = #{record.phoneNumber,jdbcType=VARCHAR}");
        }
        
        if (record.getHireDate() != null) {
            sql.SET("hire_date = #{record.hireDate,jdbcType=VARCHAR}");
        }
        
        if (record.getStatus() != null) {
            sql.SET("status = #{record.status,jdbcType=INTEGER}");
        }
        
        if (record.getPositionStartDate() != null) {
            sql.SET("position_start_date = #{record.positionStartDate,jdbcType=VARCHAR}");
        }
        
        if (record.getPositionEndDate() != null) {
            sql.SET("position_end_date = #{record.positionEndDate,jdbcType=VARCHAR}");
        }
        
        if (record.getDatetimeLastchange() != null) {
            sql.SET("datetime_lastchange = #{record.datetimeLastchange,jdbcType=TIMESTAMP}");
        }
        
        applyWhere(sql, example, true);
        return sql.toString();
    }

    public String updateByExample(Map<String, Object> parameter) {
        SQL sql = new SQL();
        sql.UPDATE("sw_employee");
        
        sql.SET("id = #{record.id,jdbcType=INTEGER}");
        sql.SET("name = #{record.name,jdbcType=VARCHAR}");
        sql.SET("employee_no = #{record.employeeNo,jdbcType=VARCHAR}");
        sql.SET("department_id = #{record.departmentId,jdbcType=INTEGER}");
        sql.SET("job_title = #{record.jobTitle,jdbcType=VARCHAR}");
        sql.SET("email = #{record.email,jdbcType=VARCHAR}");
        sql.SET("phone_number = #{record.phoneNumber,jdbcType=VARCHAR}");
        sql.SET("hire_date = #{record.hireDate,jdbcType=VARCHAR}");
        sql.SET("status = #{record.status,jdbcType=INTEGER}");
        sql.SET("position_start_date = #{record.positionStartDate,jdbcType=VARCHAR}");
        sql.SET("position_end_date = #{record.positionEndDate,jdbcType=VARCHAR}");
        sql.SET("datetime_lastchange = #{record.datetimeLastchange,jdbcType=TIMESTAMP}");
        
        SwEmployeeExample example = (SwEmployeeExample) parameter.get("example");
        applyWhere(sql, example, true);
        return sql.toString();
    }

    public String updateByPrimaryKeySelective(SwEmployee record) {
        SQL sql = new SQL();
        sql.UPDATE("sw_employee");
        
        if (record.getName() != null) {
            sql.SET("name = #{name,jdbcType=VARCHAR}");
        }
        
        if (record.getEmployeeNo() != null) {
            sql.SET("employee_no = #{employeeNo,jdbcType=VARCHAR}");
        }
        
        if (record.getDepartmentId() != null) {
            sql.SET("department_id = #{departmentId,jdbcType=INTEGER}");
        }
        
        if (record.getJobTitle() != null) {
            sql.SET("job_title = #{jobTitle,jdbcType=VARCHAR}");
        }
        
        if (record.getEmail() != null) {
            sql.SET("email = #{email,jdbcType=VARCHAR}");
        }
        
        if (record.getPhoneNumber() != null) {
            sql.SET("phone_number = #{phoneNumber,jdbcType=VARCHAR}");
        }
        
        if (record.getHireDate() != null) {
            sql.SET("hire_date = #{hireDate,jdbcType=VARCHAR}");
        }
        
        if (record.getStatus() != null) {
            sql.SET("status = #{status,jdbcType=INTEGER}");
        }
        
        if (record.getPositionStartDate() != null) {
            sql.SET("position_start_date = #{positionStartDate,jdbcType=VARCHAR}");
        }
        
        if (record.getPositionEndDate() != null) {
            sql.SET("position_end_date = #{positionEndDate,jdbcType=VARCHAR}");
        }
        
        if (record.getDatetimeLastchange() != null) {
            sql.SET("datetime_lastchange = #{datetimeLastchange,jdbcType=TIMESTAMP}");
        }
        
        sql.WHERE("id = #{id,jdbcType=INTEGER}");
        
        return sql.toString();
    }

    protected void applyWhere(SQL sql, SwEmployeeExample example, boolean includeExamplePhrase) {
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