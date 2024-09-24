package com.foxconn.sw.data.mapper.auto;

import com.foxconn.sw.data.entity.SwWorkReport;
import com.foxconn.sw.data.entity.SwWorkReportExample.Criteria;
import com.foxconn.sw.data.entity.SwWorkReportExample.Criterion;
import com.foxconn.sw.data.entity.SwWorkReportExample;
import java.util.List;
import java.util.Map;
import org.apache.ibatis.jdbc.SQL;

public class SwWorkReportSqlProvider {

    public String countByExample(SwWorkReportExample example) {
        SQL sql = new SQL();
        sql.SELECT("count(*)").FROM("sw_work_report");
        applyWhere(sql, example, false);
        return sql.toString();
    }

    public String deleteByExample(SwWorkReportExample example) {
        SQL sql = new SQL();
        sql.DELETE_FROM("sw_work_report");
        applyWhere(sql, example, false);
        return sql.toString();
    }

    public String insertSelective(SwWorkReport record) {
        SQL sql = new SQL();
        sql.INSERT_INTO("sw_work_report");
        
        if (record.getEmployeeNo() != null) {
            sql.VALUES("employee_no", "#{employeeNo,jdbcType=VARCHAR}");
        }
        
        if (record.getYearWeek() != null) {
            sql.VALUES("year_week", "#{yearWeek,jdbcType=VARCHAR}");
        }
        
        if (record.getWeek() != null) {
            sql.VALUES("week", "#{week,jdbcType=INTEGER}");
        }
        
        if (record.getProject() != null) {
            sql.VALUES("project", "#{project,jdbcType=VARCHAR}");
        }
        
        if (record.getDays() != null) {
            sql.VALUES("days", "#{days,jdbcType=DOUBLE}");
        }
        
        if (record.getTarget() != null) {
            sql.VALUES("target", "#{target,jdbcType=INTEGER}");
        }
        
        if (record.getCurrent() != null) {
            sql.VALUES("current", "#{current,jdbcType=INTEGER}");
        }
        
        if (record.getStatus() != null) {
            sql.VALUES("status", "#{status,jdbcType=INTEGER}");
        }
        
        if (record.getDescription() != null) {
            sql.VALUES("description", "#{description,jdbcType=VARCHAR}");
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

    public String selectByExample(SwWorkReportExample example) {
        SQL sql = new SQL();
        if (example != null && example.isDistinct()) {
            sql.SELECT_DISTINCT("id");
        } else {
            sql.SELECT("id");
        }
        sql.SELECT("employee_no");
        sql.SELECT("year_week");
        sql.SELECT("week");
        sql.SELECT("project");
        sql.SELECT("days");
        sql.SELECT("target");
        sql.SELECT("current");
        sql.SELECT("status");
        sql.SELECT("description");
        sql.SELECT("remark");
        sql.SELECT("create_time");
        sql.SELECT("datetime_lastchange");
        sql.FROM("sw_work_report");
        applyWhere(sql, example, false);
        
        if (example != null && example.getOrderByClause() != null) {
            sql.ORDER_BY(example.getOrderByClause());
        }
        
        return sql.toString();
    }

    public String updateByExampleSelective(Map<String, Object> parameter) {
        SwWorkReport record = (SwWorkReport) parameter.get("record");
        SwWorkReportExample example = (SwWorkReportExample) parameter.get("example");
        
        SQL sql = new SQL();
        sql.UPDATE("sw_work_report");
        
        if (record.getId() != null) {
            sql.SET("id = #{record.id,jdbcType=INTEGER}");
        }
        
        if (record.getEmployeeNo() != null) {
            sql.SET("employee_no = #{record.employeeNo,jdbcType=VARCHAR}");
        }
        
        if (record.getYearWeek() != null) {
            sql.SET("year_week = #{record.yearWeek,jdbcType=VARCHAR}");
        }
        
        if (record.getWeek() != null) {
            sql.SET("week = #{record.week,jdbcType=INTEGER}");
        }
        
        if (record.getProject() != null) {
            sql.SET("project = #{record.project,jdbcType=VARCHAR}");
        }
        
        if (record.getDays() != null) {
            sql.SET("days = #{record.days,jdbcType=DOUBLE}");
        }
        
        if (record.getTarget() != null) {
            sql.SET("target = #{record.target,jdbcType=INTEGER}");
        }
        
        if (record.getCurrent() != null) {
            sql.SET("current = #{record.current,jdbcType=INTEGER}");
        }
        
        if (record.getStatus() != null) {
            sql.SET("status = #{record.status,jdbcType=INTEGER}");
        }
        
        if (record.getDescription() != null) {
            sql.SET("description = #{record.description,jdbcType=VARCHAR}");
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
        sql.UPDATE("sw_work_report");
        
        sql.SET("id = #{record.id,jdbcType=INTEGER}");
        sql.SET("employee_no = #{record.employeeNo,jdbcType=VARCHAR}");
        sql.SET("year_week = #{record.yearWeek,jdbcType=VARCHAR}");
        sql.SET("week = #{record.week,jdbcType=INTEGER}");
        sql.SET("project = #{record.project,jdbcType=VARCHAR}");
        sql.SET("days = #{record.days,jdbcType=DOUBLE}");
        sql.SET("target = #{record.target,jdbcType=INTEGER}");
        sql.SET("current = #{record.current,jdbcType=INTEGER}");
        sql.SET("status = #{record.status,jdbcType=INTEGER}");
        sql.SET("description = #{record.description,jdbcType=VARCHAR}");
        sql.SET("remark = #{record.remark,jdbcType=VARCHAR}");
        sql.SET("create_time = #{record.createTime,jdbcType=TIMESTAMP}");
        sql.SET("datetime_lastchange = #{record.datetimeLastchange,jdbcType=TIMESTAMP}");
        
        SwWorkReportExample example = (SwWorkReportExample) parameter.get("example");
        applyWhere(sql, example, true);
        return sql.toString();
    }

    public String updateByPrimaryKeySelective(SwWorkReport record) {
        SQL sql = new SQL();
        sql.UPDATE("sw_work_report");
        
        if (record.getEmployeeNo() != null) {
            sql.SET("employee_no = #{employeeNo,jdbcType=VARCHAR}");
        }
        
        if (record.getYearWeek() != null) {
            sql.SET("year_week = #{yearWeek,jdbcType=VARCHAR}");
        }
        
        if (record.getWeek() != null) {
            sql.SET("week = #{week,jdbcType=INTEGER}");
        }
        
        if (record.getProject() != null) {
            sql.SET("project = #{project,jdbcType=VARCHAR}");
        }
        
        if (record.getDays() != null) {
            sql.SET("days = #{days,jdbcType=DOUBLE}");
        }
        
        if (record.getTarget() != null) {
            sql.SET("target = #{target,jdbcType=INTEGER}");
        }
        
        if (record.getCurrent() != null) {
            sql.SET("current = #{current,jdbcType=INTEGER}");
        }
        
        if (record.getStatus() != null) {
            sql.SET("status = #{status,jdbcType=INTEGER}");
        }
        
        if (record.getDescription() != null) {
            sql.SET("description = #{description,jdbcType=VARCHAR}");
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
        
        sql.WHERE("id = #{id,jdbcType=INTEGER}");
        
        return sql.toString();
    }

    protected void applyWhere(SQL sql, SwWorkReportExample example, boolean includeExamplePhrase) {
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