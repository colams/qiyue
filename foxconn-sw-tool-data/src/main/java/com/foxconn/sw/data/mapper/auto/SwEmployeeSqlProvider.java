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
        
        if (record.getEmployeeNo() != null) {
            sql.VALUES("employee_no", "#{employeeNo,jdbcType=VARCHAR}");
        }
        
        if (record.getAssistant() != null) {
            sql.VALUES("assistant", "#{assistant,jdbcType=VARCHAR}");
        }
        
        if (record.getName() != null) {
            sql.VALUES("name", "#{name,jdbcType=VARCHAR}");
        }
        
        if (record.getFirstName() != null) {
            sql.VALUES("first_name", "#{firstName,jdbcType=VARCHAR}");
        }
        
        if (record.getLastName() != null) {
            sql.VALUES("last_name", "#{lastName,jdbcType=VARCHAR}");
        }
        
        if (record.getGender() != null) {
            sql.VALUES("gender", "#{gender,jdbcType=INTEGER}");
        }
        
        if (record.getDepartmentId() != null) {
            sql.VALUES("department_id", "#{departmentId,jdbcType=INTEGER}");
        }
        
        if (record.getPostId() != null) {
            sql.VALUES("post_id", "#{postId,jdbcType=INTEGER}");
        }
        
        if (record.getInnerEmail() != null) {
            sql.VALUES("inner_email", "#{innerEmail,jdbcType=VARCHAR}");
        }
        
        if (record.getOuterMail() != null) {
            sql.VALUES("outer_mail", "#{outerMail,jdbcType=VARCHAR}");
        }
        
        if (record.getLandLine() != null) {
            sql.VALUES("land_line", "#{landLine,jdbcType=VARCHAR}");
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
        
        if (record.getOuterWorkYears() != null) {
            sql.VALUES("outer_work_years", "#{outerWorkYears,jdbcType=INTEGER}");
        }
        
        if (record.getOuterAbcYears() != null) {
            sql.VALUES("outer_abc_years", "#{outerAbcYears,jdbcType=INTEGER}");
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
        sql.SELECT("employee_no");
        sql.SELECT("assistant");
        sql.SELECT("name");
        sql.SELECT("first_name");
        sql.SELECT("last_name");
        sql.SELECT("gender");
        sql.SELECT("department_id");
        sql.SELECT("post_id");
        sql.SELECT("inner_email");
        sql.SELECT("outer_mail");
        sql.SELECT("land_line");
        sql.SELECT("phone_number");
        sql.SELECT("hire_date");
        sql.SELECT("status");
        sql.SELECT("outer_work_years");
        sql.SELECT("outer_abc_years");
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
        
        if (record.getEmployeeNo() != null) {
            sql.SET("employee_no = #{record.employeeNo,jdbcType=VARCHAR}");
        }
        
        if (record.getAssistant() != null) {
            sql.SET("assistant = #{record.assistant,jdbcType=VARCHAR}");
        }
        
        if (record.getName() != null) {
            sql.SET("name = #{record.name,jdbcType=VARCHAR}");
        }
        
        if (record.getFirstName() != null) {
            sql.SET("first_name = #{record.firstName,jdbcType=VARCHAR}");
        }
        
        if (record.getLastName() != null) {
            sql.SET("last_name = #{record.lastName,jdbcType=VARCHAR}");
        }
        
        if (record.getGender() != null) {
            sql.SET("gender = #{record.gender,jdbcType=INTEGER}");
        }
        
        if (record.getDepartmentId() != null) {
            sql.SET("department_id = #{record.departmentId,jdbcType=INTEGER}");
        }
        
        if (record.getPostId() != null) {
            sql.SET("post_id = #{record.postId,jdbcType=INTEGER}");
        }
        
        if (record.getInnerEmail() != null) {
            sql.SET("inner_email = #{record.innerEmail,jdbcType=VARCHAR}");
        }
        
        if (record.getOuterMail() != null) {
            sql.SET("outer_mail = #{record.outerMail,jdbcType=VARCHAR}");
        }
        
        if (record.getLandLine() != null) {
            sql.SET("land_line = #{record.landLine,jdbcType=VARCHAR}");
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
        
        if (record.getOuterWorkYears() != null) {
            sql.SET("outer_work_years = #{record.outerWorkYears,jdbcType=INTEGER}");
        }
        
        if (record.getOuterAbcYears() != null) {
            sql.SET("outer_abc_years = #{record.outerAbcYears,jdbcType=INTEGER}");
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
        sql.SET("employee_no = #{record.employeeNo,jdbcType=VARCHAR}");
        sql.SET("assistant = #{record.assistant,jdbcType=VARCHAR}");
        sql.SET("name = #{record.name,jdbcType=VARCHAR}");
        sql.SET("first_name = #{record.firstName,jdbcType=VARCHAR}");
        sql.SET("last_name = #{record.lastName,jdbcType=VARCHAR}");
        sql.SET("gender = #{record.gender,jdbcType=INTEGER}");
        sql.SET("department_id = #{record.departmentId,jdbcType=INTEGER}");
        sql.SET("post_id = #{record.postId,jdbcType=INTEGER}");
        sql.SET("inner_email = #{record.innerEmail,jdbcType=VARCHAR}");
        sql.SET("outer_mail = #{record.outerMail,jdbcType=VARCHAR}");
        sql.SET("land_line = #{record.landLine,jdbcType=VARCHAR}");
        sql.SET("phone_number = #{record.phoneNumber,jdbcType=VARCHAR}");
        sql.SET("hire_date = #{record.hireDate,jdbcType=VARCHAR}");
        sql.SET("status = #{record.status,jdbcType=INTEGER}");
        sql.SET("outer_work_years = #{record.outerWorkYears,jdbcType=INTEGER}");
        sql.SET("outer_abc_years = #{record.outerAbcYears,jdbcType=INTEGER}");
        sql.SET("datetime_lastchange = #{record.datetimeLastchange,jdbcType=TIMESTAMP}");
        
        SwEmployeeExample example = (SwEmployeeExample) parameter.get("example");
        applyWhere(sql, example, true);
        return sql.toString();
    }

    public String updateByPrimaryKeySelective(SwEmployee record) {
        SQL sql = new SQL();
        sql.UPDATE("sw_employee");
        
        if (record.getEmployeeNo() != null) {
            sql.SET("employee_no = #{employeeNo,jdbcType=VARCHAR}");
        }
        
        if (record.getAssistant() != null) {
            sql.SET("assistant = #{assistant,jdbcType=VARCHAR}");
        }
        
        if (record.getName() != null) {
            sql.SET("name = #{name,jdbcType=VARCHAR}");
        }
        
        if (record.getFirstName() != null) {
            sql.SET("first_name = #{firstName,jdbcType=VARCHAR}");
        }
        
        if (record.getLastName() != null) {
            sql.SET("last_name = #{lastName,jdbcType=VARCHAR}");
        }
        
        if (record.getGender() != null) {
            sql.SET("gender = #{gender,jdbcType=INTEGER}");
        }
        
        if (record.getDepartmentId() != null) {
            sql.SET("department_id = #{departmentId,jdbcType=INTEGER}");
        }
        
        if (record.getPostId() != null) {
            sql.SET("post_id = #{postId,jdbcType=INTEGER}");
        }
        
        if (record.getInnerEmail() != null) {
            sql.SET("inner_email = #{innerEmail,jdbcType=VARCHAR}");
        }
        
        if (record.getOuterMail() != null) {
            sql.SET("outer_mail = #{outerMail,jdbcType=VARCHAR}");
        }
        
        if (record.getLandLine() != null) {
            sql.SET("land_line = #{landLine,jdbcType=VARCHAR}");
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
        
        if (record.getOuterWorkYears() != null) {
            sql.SET("outer_work_years = #{outerWorkYears,jdbcType=INTEGER}");
        }
        
        if (record.getOuterAbcYears() != null) {
            sql.SET("outer_abc_years = #{outerAbcYears,jdbcType=INTEGER}");
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