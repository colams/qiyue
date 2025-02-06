package com.foxconn.sw.data.mapper.auto;

import com.foxconn.sw.data.entity.SwDocument;
import com.foxconn.sw.data.entity.SwDocumentExample.Criteria;
import com.foxconn.sw.data.entity.SwDocumentExample.Criterion;
import com.foxconn.sw.data.entity.SwDocumentExample;
import java.util.List;
import java.util.Map;
import org.apache.ibatis.jdbc.SQL;

public class SwDocumentSqlProvider {

    public String deleteByExample(SwDocumentExample example) {
        SQL sql = new SQL();
        sql.DELETE_FROM("sw_document");
        applyWhere(sql, example, false);
        return sql.toString();
    }

    public String insertSelective(SwDocument record) {
        SQL sql = new SQL();
        sql.INSERT_INTO("sw_document");
        
        if (record.getDocumentName() != null) {
            sql.VALUES("document_name", "#{documentName,jdbcType=VARCHAR}");
        }
        
        if (record.getFileVersion() != null) {
            sql.VALUES("file_version", "#{fileVersion,jdbcType=VARCHAR}");
        }
        
        if (record.getCategory() != null) {
            sql.VALUES("category", "#{category,jdbcType=VARCHAR}");
        }
        
        if (record.getDepartment() != null) {
            sql.VALUES("department", "#{department,jdbcType=INTEGER}");
        }
        
        if (record.getProject() != null) {
            sql.VALUES("project", "#{project,jdbcType=VARCHAR}");
        }
        
        if (record.getSecretLevel() != null) {
            sql.VALUES("secret_level", "#{secretLevel,jdbcType=INTEGER}");
        }
        
        if (record.getExpireDate() != null) {
            sql.VALUES("expire_date", "#{expireDate,jdbcType=VARCHAR}");
        }
        
        if (record.getDisableDown() != null) {
            sql.VALUES("disable_down", "#{disableDown,jdbcType=INTEGER}");
        }
        
        if (record.getResourceId() != null) {
            sql.VALUES("resource_id", "#{resourceId,jdbcType=INTEGER}");
        }
        
        if (record.getAuthor() != null) {
            sql.VALUES("author", "#{author,jdbcType=VARCHAR}");
        }
        
        if (record.getCreator() != null) {
            sql.VALUES("creator", "#{creator,jdbcType=VARCHAR}");
        }
        
        if (record.getFileType() != null) {
            sql.VALUES("file_type", "#{fileType,jdbcType=INTEGER}");
        }
        
        if (record.getContent() != null) {
            sql.VALUES("content", "#{content,jdbcType=VARCHAR}");
        }
        
        if (record.getWorkType() != null) {
            sql.VALUES("work_type", "#{workType,jdbcType=VARCHAR}");
        }
        
        if (record.getMainType() != null) {
            sql.VALUES("main_type", "#{mainType,jdbcType=VARCHAR}");
        }
        
        if (record.getSubType() != null) {
            sql.VALUES("sub_type", "#{subType,jdbcType=VARCHAR}");
        }
        
        if (record.getMainPart() != null) {
            sql.VALUES("main_part", "#{mainPart,jdbcType=VARCHAR}");
        }
        
        if (record.getSupplier() != null) {
            sql.VALUES("supplier", "#{supplier,jdbcType=VARCHAR}");
        }
        
        if (record.getSource() != null) {
            sql.VALUES("source", "#{source,jdbcType=VARCHAR}");
        }
        
        if (record.getDeadLine() != null) {
            sql.VALUES("dead_line", "#{deadLine,jdbcType=VARCHAR}");
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

    public String selectByExample(SwDocumentExample example) {
        SQL sql = new SQL();
        if (example != null && example.isDistinct()) {
            sql.SELECT_DISTINCT("id");
        } else {
            sql.SELECT("id");
        }
        sql.SELECT("document_name");
        sql.SELECT("file_version");
        sql.SELECT("category");
        sql.SELECT("department");
        sql.SELECT("project");
        sql.SELECT("secret_level");
        sql.SELECT("expire_date");
        sql.SELECT("disable_down");
        sql.SELECT("resource_id");
        sql.SELECT("author");
        sql.SELECT("creator");
        sql.SELECT("file_type");
        sql.SELECT("content");
        sql.SELECT("work_type");
        sql.SELECT("main_type");
        sql.SELECT("sub_type");
        sql.SELECT("main_part");
        sql.SELECT("supplier");
        sql.SELECT("source");
        sql.SELECT("dead_line");
        sql.SELECT("is_delete");
        sql.SELECT("create_time");
        sql.SELECT("datetime_lastchange");
        sql.FROM("sw_document");
        applyWhere(sql, example, false);
        
        if (example != null && example.getOrderByClause() != null) {
            sql.ORDER_BY(example.getOrderByClause());
        }
        
        return sql.toString();
    }

    public String updateByExampleSelective(Map<String, Object> parameter) {
        SwDocument record = (SwDocument) parameter.get("record");
        SwDocumentExample example = (SwDocumentExample) parameter.get("example");
        
        SQL sql = new SQL();
        sql.UPDATE("sw_document");
        
        if (record.getId() != null) {
            sql.SET("id = #{record.id,jdbcType=INTEGER}");
        }
        
        if (record.getDocumentName() != null) {
            sql.SET("document_name = #{record.documentName,jdbcType=VARCHAR}");
        }
        
        if (record.getFileVersion() != null) {
            sql.SET("file_version = #{record.fileVersion,jdbcType=VARCHAR}");
        }
        
        if (record.getCategory() != null) {
            sql.SET("category = #{record.category,jdbcType=VARCHAR}");
        }
        
        if (record.getDepartment() != null) {
            sql.SET("department = #{record.department,jdbcType=INTEGER}");
        }
        
        if (record.getProject() != null) {
            sql.SET("project = #{record.project,jdbcType=VARCHAR}");
        }
        
        if (record.getSecretLevel() != null) {
            sql.SET("secret_level = #{record.secretLevel,jdbcType=INTEGER}");
        }
        
        if (record.getExpireDate() != null) {
            sql.SET("expire_date = #{record.expireDate,jdbcType=VARCHAR}");
        }
        
        if (record.getDisableDown() != null) {
            sql.SET("disable_down = #{record.disableDown,jdbcType=INTEGER}");
        }
        
        if (record.getResourceId() != null) {
            sql.SET("resource_id = #{record.resourceId,jdbcType=INTEGER}");
        }
        
        if (record.getAuthor() != null) {
            sql.SET("author = #{record.author,jdbcType=VARCHAR}");
        }
        
        if (record.getCreator() != null) {
            sql.SET("creator = #{record.creator,jdbcType=VARCHAR}");
        }
        
        if (record.getFileType() != null) {
            sql.SET("file_type = #{record.fileType,jdbcType=INTEGER}");
        }
        
        if (record.getContent() != null) {
            sql.SET("content = #{record.content,jdbcType=VARCHAR}");
        }
        
        if (record.getWorkType() != null) {
            sql.SET("work_type = #{record.workType,jdbcType=VARCHAR}");
        }
        
        if (record.getMainType() != null) {
            sql.SET("main_type = #{record.mainType,jdbcType=VARCHAR}");
        }
        
        if (record.getSubType() != null) {
            sql.SET("sub_type = #{record.subType,jdbcType=VARCHAR}");
        }
        
        if (record.getMainPart() != null) {
            sql.SET("main_part = #{record.mainPart,jdbcType=VARCHAR}");
        }
        
        if (record.getSupplier() != null) {
            sql.SET("supplier = #{record.supplier,jdbcType=VARCHAR}");
        }
        
        if (record.getSource() != null) {
            sql.SET("source = #{record.source,jdbcType=VARCHAR}");
        }
        
        if (record.getDeadLine() != null) {
            sql.SET("dead_line = #{record.deadLine,jdbcType=VARCHAR}");
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
        sql.UPDATE("sw_document");
        
        sql.SET("id = #{record.id,jdbcType=INTEGER}");
        sql.SET("document_name = #{record.documentName,jdbcType=VARCHAR}");
        sql.SET("file_version = #{record.fileVersion,jdbcType=VARCHAR}");
        sql.SET("category = #{record.category,jdbcType=VARCHAR}");
        sql.SET("department = #{record.department,jdbcType=INTEGER}");
        sql.SET("project = #{record.project,jdbcType=VARCHAR}");
        sql.SET("secret_level = #{record.secretLevel,jdbcType=INTEGER}");
        sql.SET("expire_date = #{record.expireDate,jdbcType=VARCHAR}");
        sql.SET("disable_down = #{record.disableDown,jdbcType=INTEGER}");
        sql.SET("resource_id = #{record.resourceId,jdbcType=INTEGER}");
        sql.SET("author = #{record.author,jdbcType=VARCHAR}");
        sql.SET("creator = #{record.creator,jdbcType=VARCHAR}");
        sql.SET("file_type = #{record.fileType,jdbcType=INTEGER}");
        sql.SET("content = #{record.content,jdbcType=VARCHAR}");
        sql.SET("work_type = #{record.workType,jdbcType=VARCHAR}");
        sql.SET("main_type = #{record.mainType,jdbcType=VARCHAR}");
        sql.SET("sub_type = #{record.subType,jdbcType=VARCHAR}");
        sql.SET("main_part = #{record.mainPart,jdbcType=VARCHAR}");
        sql.SET("supplier = #{record.supplier,jdbcType=VARCHAR}");
        sql.SET("source = #{record.source,jdbcType=VARCHAR}");
        sql.SET("dead_line = #{record.deadLine,jdbcType=VARCHAR}");
        sql.SET("is_delete = #{record.isDelete,jdbcType=INTEGER}");
        sql.SET("create_time = #{record.createTime,jdbcType=TIMESTAMP}");
        sql.SET("datetime_lastchange = #{record.datetimeLastchange,jdbcType=TIMESTAMP}");
        
        SwDocumentExample example = (SwDocumentExample) parameter.get("example");
        applyWhere(sql, example, true);
        return sql.toString();
    }

    public String updateByPrimaryKeySelective(SwDocument record) {
        SQL sql = new SQL();
        sql.UPDATE("sw_document");
        
        if (record.getDocumentName() != null) {
            sql.SET("document_name = #{documentName,jdbcType=VARCHAR}");
        }
        
        if (record.getFileVersion() != null) {
            sql.SET("file_version = #{fileVersion,jdbcType=VARCHAR}");
        }
        
        if (record.getCategory() != null) {
            sql.SET("category = #{category,jdbcType=VARCHAR}");
        }
        
        if (record.getDepartment() != null) {
            sql.SET("department = #{department,jdbcType=INTEGER}");
        }
        
        if (record.getProject() != null) {
            sql.SET("project = #{project,jdbcType=VARCHAR}");
        }
        
        if (record.getSecretLevel() != null) {
            sql.SET("secret_level = #{secretLevel,jdbcType=INTEGER}");
        }
        
        if (record.getExpireDate() != null) {
            sql.SET("expire_date = #{expireDate,jdbcType=VARCHAR}");
        }
        
        if (record.getDisableDown() != null) {
            sql.SET("disable_down = #{disableDown,jdbcType=INTEGER}");
        }
        
        if (record.getResourceId() != null) {
            sql.SET("resource_id = #{resourceId,jdbcType=INTEGER}");
        }
        
        if (record.getAuthor() != null) {
            sql.SET("author = #{author,jdbcType=VARCHAR}");
        }
        
        if (record.getCreator() != null) {
            sql.SET("creator = #{creator,jdbcType=VARCHAR}");
        }
        
        if (record.getFileType() != null) {
            sql.SET("file_type = #{fileType,jdbcType=INTEGER}");
        }
        
        if (record.getContent() != null) {
            sql.SET("content = #{content,jdbcType=VARCHAR}");
        }
        
        if (record.getWorkType() != null) {
            sql.SET("work_type = #{workType,jdbcType=VARCHAR}");
        }
        
        if (record.getMainType() != null) {
            sql.SET("main_type = #{mainType,jdbcType=VARCHAR}");
        }
        
        if (record.getSubType() != null) {
            sql.SET("sub_type = #{subType,jdbcType=VARCHAR}");
        }
        
        if (record.getMainPart() != null) {
            sql.SET("main_part = #{mainPart,jdbcType=VARCHAR}");
        }
        
        if (record.getSupplier() != null) {
            sql.SET("supplier = #{supplier,jdbcType=VARCHAR}");
        }
        
        if (record.getSource() != null) {
            sql.SET("source = #{source,jdbcType=VARCHAR}");
        }
        
        if (record.getDeadLine() != null) {
            sql.SET("dead_line = #{deadLine,jdbcType=VARCHAR}");
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

    protected void applyWhere(SQL sql, SwDocumentExample example, boolean includeExamplePhrase) {
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