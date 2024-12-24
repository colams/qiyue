package com.foxconn.sw.data.mapper.auto;

import com.foxconn.sw.data.entity.SwTask;
import com.foxconn.sw.data.entity.SwTaskExample.Criteria;
import com.foxconn.sw.data.entity.SwTaskExample.Criterion;
import com.foxconn.sw.data.entity.SwTaskExample;
import java.util.List;
import java.util.Map;
import org.apache.ibatis.jdbc.SQL;

public class SwTaskSqlProvider {

    public String countByExample(SwTaskExample example) {
        SQL sql = new SQL();
        sql.SELECT("count(*)").FROM("sw_task");
        applyWhere(sql, example, false);
        return sql.toString();
    }

    public String deleteByExample(SwTaskExample example) {
        SQL sql = new SQL();
        sql.DELETE_FROM("sw_task");
        applyWhere(sql, example, false);
        return sql.toString();
    }

    public String insertSelective(SwTask record) {
        SQL sql = new SQL();
        sql.INSERT_INTO("sw_task");
        
        if (record.getTaskNo() != null) {
            sql.VALUES("task_no", "#{taskNo,jdbcType=BIGINT}");
        }
        
        if (record.getTopCategory() != null) {
            sql.VALUES("top_category", "#{topCategory,jdbcType=VARCHAR}");
        }
        
        if (record.getCategory() != null) {
            sql.VALUES("category", "#{category,jdbcType=VARCHAR}");
        }
        
        if (record.getTitle() != null) {
            sql.VALUES("title", "#{title,jdbcType=VARCHAR}");
        }
        
        if (record.getTopProject() != null) {
            sql.VALUES("top_project", "#{topProject,jdbcType=VARCHAR}");
        }
        
        if (record.getProject() != null) {
            sql.VALUES("project", "#{project,jdbcType=VARCHAR}");
        }
        
        if (record.getLevel() != null) {
            sql.VALUES("level", "#{level,jdbcType=VARCHAR}");
        }
        
        if (record.getProgressPercent() != null) {
            sql.VALUES("progress_percent", "#{progressPercent,jdbcType=INTEGER}");
        }
        
        if (record.getStatus() != null) {
            sql.VALUES("status", "#{status,jdbcType=INTEGER}");
        }
        
        if (record.getRejectStatus() != null) {
            sql.VALUES("reject_status", "#{rejectStatus,jdbcType=INTEGER}");
        }
        
        if (record.getProposerEid() != null) {
            sql.VALUES("proposer_eid", "#{proposerEid,jdbcType=VARCHAR}");
        }
        
        if (record.getManagerEid() != null) {
            sql.VALUES("manager_eid", "#{managerEid,jdbcType=VARCHAR}");
        }
        
        if (record.getHandleEid() != null) {
            sql.VALUES("handle_eid", "#{handleEid,jdbcType=VARCHAR}");
        }
        
        if (record.getDeadLine() != null) {
            sql.VALUES("dead_line", "#{deadLine,jdbcType=VARCHAR}");
        }
        
        if (record.getReflection() != null) {
            sql.VALUES("reflection", "#{reflection,jdbcType=VARCHAR}");
        }
        
        if (record.getParentId() != null) {
            sql.VALUES("parent_id", "#{parentId,jdbcType=INTEGER}");
        }
        
        if (record.getFinishTime() != null) {
            sql.VALUES("finish_time", "#{finishTime,jdbcType=VARCHAR}");
        }
        
        if (record.getCreateTime() != null) {
            sql.VALUES("create_time", "#{createTime,jdbcType=TIMESTAMP}");
        }
        
        if (record.getDatetimeLastchange() != null) {
            sql.VALUES("datetime_lastchange", "#{datetimeLastchange,jdbcType=TIMESTAMP}");
        }
        
        if (record.getDescription() != null) {
            sql.VALUES("description", "#{description,jdbcType=LONGVARCHAR}");
        }
        
        return sql.toString();
    }

    public String selectByExampleWithBLOBs(SwTaskExample example) {
        SQL sql = new SQL();
        if (example != null && example.isDistinct()) {
            sql.SELECT_DISTINCT("id");
        } else {
            sql.SELECT("id");
        }
        sql.SELECT("task_no");
        sql.SELECT("top_category");
        sql.SELECT("category");
        sql.SELECT("title");
        sql.SELECT("top_project");
        sql.SELECT("project");
        sql.SELECT("level");
        sql.SELECT("progress_percent");
        sql.SELECT("status");
        sql.SELECT("reject_status");
        sql.SELECT("proposer_eid");
        sql.SELECT("manager_eid");
        sql.SELECT("handle_eid");
        sql.SELECT("dead_line");
        sql.SELECT("reflection");
        sql.SELECT("parent_id");
        sql.SELECT("finish_time");
        sql.SELECT("create_time");
        sql.SELECT("datetime_lastchange");
        sql.SELECT("description");
        sql.FROM("sw_task");
        applyWhere(sql, example, false);
        
        if (example != null && example.getOrderByClause() != null) {
            sql.ORDER_BY(example.getOrderByClause());
        }
        
        return sql.toString();
    }

    public String selectByExample(SwTaskExample example) {
        SQL sql = new SQL();
        if (example != null && example.isDistinct()) {
            sql.SELECT_DISTINCT("id");
        } else {
            sql.SELECT("id");
        }
        sql.SELECT("task_no");
        sql.SELECT("top_category");
        sql.SELECT("category");
        sql.SELECT("title");
        sql.SELECT("top_project");
        sql.SELECT("project");
        sql.SELECT("level");
        sql.SELECT("progress_percent");
        sql.SELECT("status");
        sql.SELECT("reject_status");
        sql.SELECT("proposer_eid");
        sql.SELECT("manager_eid");
        sql.SELECT("handle_eid");
        sql.SELECT("dead_line");
        sql.SELECT("reflection");
        sql.SELECT("parent_id");
        sql.SELECT("finish_time");
        sql.SELECT("create_time");
        sql.SELECT("datetime_lastchange");
        sql.FROM("sw_task");
        applyWhere(sql, example, false);
        
        if (example != null && example.getOrderByClause() != null) {
            sql.ORDER_BY(example.getOrderByClause());
        }
        
        return sql.toString();
    }

    public String updateByExampleSelective(Map<String, Object> parameter) {
        SwTask record = (SwTask) parameter.get("record");
        SwTaskExample example = (SwTaskExample) parameter.get("example");
        
        SQL sql = new SQL();
        sql.UPDATE("sw_task");
        
        if (record.getId() != null) {
            sql.SET("id = #{record.id,jdbcType=INTEGER}");
        }
        
        if (record.getTaskNo() != null) {
            sql.SET("task_no = #{record.taskNo,jdbcType=BIGINT}");
        }
        
        if (record.getTopCategory() != null) {
            sql.SET("top_category = #{record.topCategory,jdbcType=VARCHAR}");
        }
        
        if (record.getCategory() != null) {
            sql.SET("category = #{record.category,jdbcType=VARCHAR}");
        }
        
        if (record.getTitle() != null) {
            sql.SET("title = #{record.title,jdbcType=VARCHAR}");
        }
        
        if (record.getTopProject() != null) {
            sql.SET("top_project = #{record.topProject,jdbcType=VARCHAR}");
        }
        
        if (record.getProject() != null) {
            sql.SET("project = #{record.project,jdbcType=VARCHAR}");
        }
        
        if (record.getLevel() != null) {
            sql.SET("level = #{record.level,jdbcType=VARCHAR}");
        }
        
        if (record.getProgressPercent() != null) {
            sql.SET("progress_percent = #{record.progressPercent,jdbcType=INTEGER}");
        }
        
        if (record.getStatus() != null) {
            sql.SET("status = #{record.status,jdbcType=INTEGER}");
        }
        
        if (record.getRejectStatus() != null) {
            sql.SET("reject_status = #{record.rejectStatus,jdbcType=INTEGER}");
        }
        
        if (record.getProposerEid() != null) {
            sql.SET("proposer_eid = #{record.proposerEid,jdbcType=VARCHAR}");
        }
        
        if (record.getManagerEid() != null) {
            sql.SET("manager_eid = #{record.managerEid,jdbcType=VARCHAR}");
        }
        
        if (record.getHandleEid() != null) {
            sql.SET("handle_eid = #{record.handleEid,jdbcType=VARCHAR}");
        }
        
        if (record.getDeadLine() != null) {
            sql.SET("dead_line = #{record.deadLine,jdbcType=VARCHAR}");
        }
        
        if (record.getReflection() != null) {
            sql.SET("reflection = #{record.reflection,jdbcType=VARCHAR}");
        }
        
        if (record.getParentId() != null) {
            sql.SET("parent_id = #{record.parentId,jdbcType=INTEGER}");
        }
        
        if (record.getFinishTime() != null) {
            sql.SET("finish_time = #{record.finishTime,jdbcType=VARCHAR}");
        }
        
        if (record.getCreateTime() != null) {
            sql.SET("create_time = #{record.createTime,jdbcType=TIMESTAMP}");
        }
        
        if (record.getDatetimeLastchange() != null) {
            sql.SET("datetime_lastchange = #{record.datetimeLastchange,jdbcType=TIMESTAMP}");
        }
        
        if (record.getDescription() != null) {
            sql.SET("description = #{record.description,jdbcType=LONGVARCHAR}");
        }
        
        applyWhere(sql, example, true);
        return sql.toString();
    }

    public String updateByExampleWithBLOBs(Map<String, Object> parameter) {
        SQL sql = new SQL();
        sql.UPDATE("sw_task");
        
        sql.SET("id = #{record.id,jdbcType=INTEGER}");
        sql.SET("task_no = #{record.taskNo,jdbcType=BIGINT}");
        sql.SET("top_category = #{record.topCategory,jdbcType=VARCHAR}");
        sql.SET("category = #{record.category,jdbcType=VARCHAR}");
        sql.SET("title = #{record.title,jdbcType=VARCHAR}");
        sql.SET("top_project = #{record.topProject,jdbcType=VARCHAR}");
        sql.SET("project = #{record.project,jdbcType=VARCHAR}");
        sql.SET("level = #{record.level,jdbcType=VARCHAR}");
        sql.SET("progress_percent = #{record.progressPercent,jdbcType=INTEGER}");
        sql.SET("status = #{record.status,jdbcType=INTEGER}");
        sql.SET("reject_status = #{record.rejectStatus,jdbcType=INTEGER}");
        sql.SET("proposer_eid = #{record.proposerEid,jdbcType=VARCHAR}");
        sql.SET("manager_eid = #{record.managerEid,jdbcType=VARCHAR}");
        sql.SET("handle_eid = #{record.handleEid,jdbcType=VARCHAR}");
        sql.SET("dead_line = #{record.deadLine,jdbcType=VARCHAR}");
        sql.SET("reflection = #{record.reflection,jdbcType=VARCHAR}");
        sql.SET("parent_id = #{record.parentId,jdbcType=INTEGER}");
        sql.SET("finish_time = #{record.finishTime,jdbcType=VARCHAR}");
        sql.SET("create_time = #{record.createTime,jdbcType=TIMESTAMP}");
        sql.SET("datetime_lastchange = #{record.datetimeLastchange,jdbcType=TIMESTAMP}");
        sql.SET("description = #{record.description,jdbcType=LONGVARCHAR}");
        
        SwTaskExample example = (SwTaskExample) parameter.get("example");
        applyWhere(sql, example, true);
        return sql.toString();
    }

    public String updateByExample(Map<String, Object> parameter) {
        SQL sql = new SQL();
        sql.UPDATE("sw_task");
        
        sql.SET("id = #{record.id,jdbcType=INTEGER}");
        sql.SET("task_no = #{record.taskNo,jdbcType=BIGINT}");
        sql.SET("top_category = #{record.topCategory,jdbcType=VARCHAR}");
        sql.SET("category = #{record.category,jdbcType=VARCHAR}");
        sql.SET("title = #{record.title,jdbcType=VARCHAR}");
        sql.SET("top_project = #{record.topProject,jdbcType=VARCHAR}");
        sql.SET("project = #{record.project,jdbcType=VARCHAR}");
        sql.SET("level = #{record.level,jdbcType=VARCHAR}");
        sql.SET("progress_percent = #{record.progressPercent,jdbcType=INTEGER}");
        sql.SET("status = #{record.status,jdbcType=INTEGER}");
        sql.SET("reject_status = #{record.rejectStatus,jdbcType=INTEGER}");
        sql.SET("proposer_eid = #{record.proposerEid,jdbcType=VARCHAR}");
        sql.SET("manager_eid = #{record.managerEid,jdbcType=VARCHAR}");
        sql.SET("handle_eid = #{record.handleEid,jdbcType=VARCHAR}");
        sql.SET("dead_line = #{record.deadLine,jdbcType=VARCHAR}");
        sql.SET("reflection = #{record.reflection,jdbcType=VARCHAR}");
        sql.SET("parent_id = #{record.parentId,jdbcType=INTEGER}");
        sql.SET("finish_time = #{record.finishTime,jdbcType=VARCHAR}");
        sql.SET("create_time = #{record.createTime,jdbcType=TIMESTAMP}");
        sql.SET("datetime_lastchange = #{record.datetimeLastchange,jdbcType=TIMESTAMP}");
        
        SwTaskExample example = (SwTaskExample) parameter.get("example");
        applyWhere(sql, example, true);
        return sql.toString();
    }

    public String updateByPrimaryKeySelective(SwTask record) {
        SQL sql = new SQL();
        sql.UPDATE("sw_task");
        
        if (record.getTaskNo() != null) {
            sql.SET("task_no = #{taskNo,jdbcType=BIGINT}");
        }
        
        if (record.getTopCategory() != null) {
            sql.SET("top_category = #{topCategory,jdbcType=VARCHAR}");
        }
        
        if (record.getCategory() != null) {
            sql.SET("category = #{category,jdbcType=VARCHAR}");
        }
        
        if (record.getTitle() != null) {
            sql.SET("title = #{title,jdbcType=VARCHAR}");
        }
        
        if (record.getTopProject() != null) {
            sql.SET("top_project = #{topProject,jdbcType=VARCHAR}");
        }
        
        if (record.getProject() != null) {
            sql.SET("project = #{project,jdbcType=VARCHAR}");
        }
        
        if (record.getLevel() != null) {
            sql.SET("level = #{level,jdbcType=VARCHAR}");
        }
        
        if (record.getProgressPercent() != null) {
            sql.SET("progress_percent = #{progressPercent,jdbcType=INTEGER}");
        }
        
        if (record.getStatus() != null) {
            sql.SET("status = #{status,jdbcType=INTEGER}");
        }
        
        if (record.getRejectStatus() != null) {
            sql.SET("reject_status = #{rejectStatus,jdbcType=INTEGER}");
        }
        
        if (record.getProposerEid() != null) {
            sql.SET("proposer_eid = #{proposerEid,jdbcType=VARCHAR}");
        }
        
        if (record.getManagerEid() != null) {
            sql.SET("manager_eid = #{managerEid,jdbcType=VARCHAR}");
        }
        
        if (record.getHandleEid() != null) {
            sql.SET("handle_eid = #{handleEid,jdbcType=VARCHAR}");
        }
        
        if (record.getDeadLine() != null) {
            sql.SET("dead_line = #{deadLine,jdbcType=VARCHAR}");
        }
        
        if (record.getReflection() != null) {
            sql.SET("reflection = #{reflection,jdbcType=VARCHAR}");
        }
        
        if (record.getParentId() != null) {
            sql.SET("parent_id = #{parentId,jdbcType=INTEGER}");
        }
        
        if (record.getFinishTime() != null) {
            sql.SET("finish_time = #{finishTime,jdbcType=VARCHAR}");
        }
        
        if (record.getCreateTime() != null) {
            sql.SET("create_time = #{createTime,jdbcType=TIMESTAMP}");
        }
        
        if (record.getDatetimeLastchange() != null) {
            sql.SET("datetime_lastchange = #{datetimeLastchange,jdbcType=TIMESTAMP}");
        }
        
        if (record.getDescription() != null) {
            sql.SET("description = #{description,jdbcType=LONGVARCHAR}");
        }
        
        sql.WHERE("id = #{id,jdbcType=INTEGER}");
        
        return sql.toString();
    }

    protected void applyWhere(SQL sql, SwTaskExample example, boolean includeExamplePhrase) {
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