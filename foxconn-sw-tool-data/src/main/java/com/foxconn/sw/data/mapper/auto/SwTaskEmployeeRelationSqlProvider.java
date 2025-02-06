package com.foxconn.sw.data.mapper.auto;

import com.foxconn.sw.data.entity.SwTaskEmployeeRelation;
import com.foxconn.sw.data.entity.SwTaskEmployeeRelationExample.Criteria;
import com.foxconn.sw.data.entity.SwTaskEmployeeRelationExample.Criterion;
import com.foxconn.sw.data.entity.SwTaskEmployeeRelationExample;
import java.util.List;
import java.util.Map;
import org.apache.ibatis.jdbc.SQL;

public class SwTaskEmployeeRelationSqlProvider {

    public String insertSelective(SwTaskEmployeeRelation record) {
        SQL sql = new SQL();
        sql.INSERT_INTO("sw_task_employee_relation");
        
        if (record.getTaskId() != null) {
            sql.VALUES("task_id", "#{taskId,jdbcType=INTEGER}");
        }
        
        if (record.getEmployeeNo() != null) {
            sql.VALUES("employee_no", "#{employeeNo,jdbcType=VARCHAR}");
        }
        
        if (record.getPrevId() != null) {
            sql.VALUES("prev_id", "#{prevId,jdbcType=INTEGER}");
        }
        
        if (record.getRoleFlag() != null) {
            sql.VALUES("role_flag", "#{roleFlag,jdbcType=INTEGER}");
        }
        
        if (record.getIsActive() != null) {
            sql.VALUES("is_active", "#{isActive,jdbcType=INTEGER}");
        }
        
        if (record.getIsInspector() != null) {
            sql.VALUES("is_inspector", "#{isInspector,jdbcType=INTEGER}");
        }
        
        if (record.getIsRead() != null) {
            sql.VALUES("is_read", "#{isRead,jdbcType=INTEGER}");
        }
        
        if (record.getProcessStatus() != null) {
            sql.VALUES("process_status", "#{processStatus,jdbcType=INTEGER}");
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

    public String selectByExample(SwTaskEmployeeRelationExample example) {
        SQL sql = new SQL();
        if (example != null && example.isDistinct()) {
            sql.SELECT_DISTINCT("id");
        } else {
            sql.SELECT("id");
        }
        sql.SELECT("task_id");
        sql.SELECT("employee_no");
        sql.SELECT("prev_id");
        sql.SELECT("role_flag");
        sql.SELECT("is_active");
        sql.SELECT("is_inspector");
        sql.SELECT("is_read");
        sql.SELECT("process_status");
        sql.SELECT("is_delete");
        sql.SELECT("create_time");
        sql.SELECT("datetime_lastchange");
        sql.FROM("sw_task_employee_relation");
        applyWhere(sql, example, false);
        
        if (example != null && example.getOrderByClause() != null) {
            sql.ORDER_BY(example.getOrderByClause());
        }
        
        return sql.toString();
    }

    public String updateByExampleSelective(Map<String, Object> parameter) {
        SwTaskEmployeeRelation record = (SwTaskEmployeeRelation) parameter.get("record");
        SwTaskEmployeeRelationExample example = (SwTaskEmployeeRelationExample) parameter.get("example");
        
        SQL sql = new SQL();
        sql.UPDATE("sw_task_employee_relation");
        
        if (record.getId() != null) {
            sql.SET("id = #{record.id,jdbcType=INTEGER}");
        }
        
        if (record.getTaskId() != null) {
            sql.SET("task_id = #{record.taskId,jdbcType=INTEGER}");
        }
        
        if (record.getEmployeeNo() != null) {
            sql.SET("employee_no = #{record.employeeNo,jdbcType=VARCHAR}");
        }
        
        if (record.getPrevId() != null) {
            sql.SET("prev_id = #{record.prevId,jdbcType=INTEGER}");
        }
        
        if (record.getRoleFlag() != null) {
            sql.SET("role_flag = #{record.roleFlag,jdbcType=INTEGER}");
        }
        
        if (record.getIsActive() != null) {
            sql.SET("is_active = #{record.isActive,jdbcType=INTEGER}");
        }
        
        if (record.getIsInspector() != null) {
            sql.SET("is_inspector = #{record.isInspector,jdbcType=INTEGER}");
        }
        
        if (record.getIsRead() != null) {
            sql.SET("is_read = #{record.isRead,jdbcType=INTEGER}");
        }
        
        if (record.getProcessStatus() != null) {
            sql.SET("process_status = #{record.processStatus,jdbcType=INTEGER}");
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
        sql.UPDATE("sw_task_employee_relation");
        
        sql.SET("id = #{record.id,jdbcType=INTEGER}");
        sql.SET("task_id = #{record.taskId,jdbcType=INTEGER}");
        sql.SET("employee_no = #{record.employeeNo,jdbcType=VARCHAR}");
        sql.SET("prev_id = #{record.prevId,jdbcType=INTEGER}");
        sql.SET("role_flag = #{record.roleFlag,jdbcType=INTEGER}");
        sql.SET("is_active = #{record.isActive,jdbcType=INTEGER}");
        sql.SET("is_inspector = #{record.isInspector,jdbcType=INTEGER}");
        sql.SET("is_read = #{record.isRead,jdbcType=INTEGER}");
        sql.SET("process_status = #{record.processStatus,jdbcType=INTEGER}");
        sql.SET("is_delete = #{record.isDelete,jdbcType=INTEGER}");
        sql.SET("create_time = #{record.createTime,jdbcType=TIMESTAMP}");
        sql.SET("datetime_lastchange = #{record.datetimeLastchange,jdbcType=TIMESTAMP}");
        
        SwTaskEmployeeRelationExample example = (SwTaskEmployeeRelationExample) parameter.get("example");
        applyWhere(sql, example, true);
        return sql.toString();
    }

    public String updateByPrimaryKeySelective(SwTaskEmployeeRelation record) {
        SQL sql = new SQL();
        sql.UPDATE("sw_task_employee_relation");
        
        if (record.getTaskId() != null) {
            sql.SET("task_id = #{taskId,jdbcType=INTEGER}");
        }
        
        if (record.getEmployeeNo() != null) {
            sql.SET("employee_no = #{employeeNo,jdbcType=VARCHAR}");
        }
        
        if (record.getPrevId() != null) {
            sql.SET("prev_id = #{prevId,jdbcType=INTEGER}");
        }
        
        if (record.getRoleFlag() != null) {
            sql.SET("role_flag = #{roleFlag,jdbcType=INTEGER}");
        }
        
        if (record.getIsActive() != null) {
            sql.SET("is_active = #{isActive,jdbcType=INTEGER}");
        }
        
        if (record.getIsInspector() != null) {
            sql.SET("is_inspector = #{isInspector,jdbcType=INTEGER}");
        }
        
        if (record.getIsRead() != null) {
            sql.SET("is_read = #{isRead,jdbcType=INTEGER}");
        }
        
        if (record.getProcessStatus() != null) {
            sql.SET("process_status = #{processStatus,jdbcType=INTEGER}");
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

    protected void applyWhere(SQL sql, SwTaskEmployeeRelationExample example, boolean includeExamplePhrase) {
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