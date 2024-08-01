package com.foxconn.sw.data.mapper.auto;

import com.foxconn.sw.data.entity.SwMenu;
import com.foxconn.sw.data.entity.SwMenuExample.Criteria;
import com.foxconn.sw.data.entity.SwMenuExample.Criterion;
import com.foxconn.sw.data.entity.SwMenuExample;
import java.util.List;
import java.util.Map;
import org.apache.ibatis.jdbc.SQL;

public class SwMenuSqlProvider {

    public String countByExample(SwMenuExample example) {
        SQL sql = new SQL();
        sql.SELECT("count(*)").FROM("sw_menu");
        applyWhere(sql, example, false);
        return sql.toString();
    }

    public String deleteByExample(SwMenuExample example) {
        SQL sql = new SQL();
        sql.DELETE_FROM("sw_menu");
        applyWhere(sql, example, false);
        return sql.toString();
    }

    public String insertSelective(SwMenu record) {
        SQL sql = new SQL();
        sql.INSERT_INTO("sw_menu");
        
        if (record.getModuleNo() != null) {
            sql.VALUES("module_no", "#{moduleNo,jdbcType=INTEGER}");
        }
        
        if (record.getMenuName() != null) {
            sql.VALUES("menu_name", "#{menuName,jdbcType=VARCHAR}");
        }
        
        if (record.getMenuUrl() != null) {
            sql.VALUES("menu_url", "#{menuUrl,jdbcType=VARCHAR}");
        }
        
        if (record.getRoute() != null) {
            sql.VALUES("route", "#{route,jdbcType=VARCHAR}");
        }
        
        if (record.getIsMenu() != null) {
            sql.VALUES("is_menu", "#{isMenu,jdbcType=INTEGER}");
        }
        
        if (record.getParentId() != null) {
            sql.VALUES("parent_id", "#{parentId,jdbcType=INTEGER}");
        }
        
        if (record.getOrderBy() != null) {
            sql.VALUES("order_by", "#{orderBy,jdbcType=INTEGER}");
        }
        
        if (record.getStatus() != null) {
            sql.VALUES("status", "#{status,jdbcType=INTEGER}");
        }
        
        if (record.getCreateTime() != null) {
            sql.VALUES("create_time", "#{createTime,jdbcType=TIMESTAMP}");
        }
        
        if (record.getDatetimeLastchange() != null) {
            sql.VALUES("datetime_lastchange", "#{datetimeLastchange,jdbcType=TIMESTAMP}");
        }
        
        return sql.toString();
    }

    public String selectByExample(SwMenuExample example) {
        SQL sql = new SQL();
        if (example != null && example.isDistinct()) {
            sql.SELECT_DISTINCT("id");
        } else {
            sql.SELECT("id");
        }
        sql.SELECT("module_no");
        sql.SELECT("menu_name");
        sql.SELECT("menu_url");
        sql.SELECT("route");
        sql.SELECT("is_menu");
        sql.SELECT("parent_id");
        sql.SELECT("order_by");
        sql.SELECT("status");
        sql.SELECT("create_time");
        sql.SELECT("datetime_lastchange");
        sql.FROM("sw_menu");
        applyWhere(sql, example, false);
        
        if (example != null && example.getOrderByClause() != null) {
            sql.ORDER_BY(example.getOrderByClause());
        }
        
        return sql.toString();
    }

    public String updateByExampleSelective(Map<String, Object> parameter) {
        SwMenu record = (SwMenu) parameter.get("record");
        SwMenuExample example = (SwMenuExample) parameter.get("example");
        
        SQL sql = new SQL();
        sql.UPDATE("sw_menu");
        
        if (record.getId() != null) {
            sql.SET("id = #{record.id,jdbcType=INTEGER}");
        }
        
        if (record.getModuleNo() != null) {
            sql.SET("module_no = #{record.moduleNo,jdbcType=INTEGER}");
        }
        
        if (record.getMenuName() != null) {
            sql.SET("menu_name = #{record.menuName,jdbcType=VARCHAR}");
        }
        
        if (record.getMenuUrl() != null) {
            sql.SET("menu_url = #{record.menuUrl,jdbcType=VARCHAR}");
        }
        
        if (record.getRoute() != null) {
            sql.SET("route = #{record.route,jdbcType=VARCHAR}");
        }
        
        if (record.getIsMenu() != null) {
            sql.SET("is_menu = #{record.isMenu,jdbcType=INTEGER}");
        }
        
        if (record.getParentId() != null) {
            sql.SET("parent_id = #{record.parentId,jdbcType=INTEGER}");
        }
        
        if (record.getOrderBy() != null) {
            sql.SET("order_by = #{record.orderBy,jdbcType=INTEGER}");
        }
        
        if (record.getStatus() != null) {
            sql.SET("status = #{record.status,jdbcType=INTEGER}");
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
        sql.UPDATE("sw_menu");
        
        sql.SET("id = #{record.id,jdbcType=INTEGER}");
        sql.SET("module_no = #{record.moduleNo,jdbcType=INTEGER}");
        sql.SET("menu_name = #{record.menuName,jdbcType=VARCHAR}");
        sql.SET("menu_url = #{record.menuUrl,jdbcType=VARCHAR}");
        sql.SET("route = #{record.route,jdbcType=VARCHAR}");
        sql.SET("is_menu = #{record.isMenu,jdbcType=INTEGER}");
        sql.SET("parent_id = #{record.parentId,jdbcType=INTEGER}");
        sql.SET("order_by = #{record.orderBy,jdbcType=INTEGER}");
        sql.SET("status = #{record.status,jdbcType=INTEGER}");
        sql.SET("create_time = #{record.createTime,jdbcType=TIMESTAMP}");
        sql.SET("datetime_lastchange = #{record.datetimeLastchange,jdbcType=TIMESTAMP}");
        
        SwMenuExample example = (SwMenuExample) parameter.get("example");
        applyWhere(sql, example, true);
        return sql.toString();
    }

    public String updateByPrimaryKeySelective(SwMenu record) {
        SQL sql = new SQL();
        sql.UPDATE("sw_menu");
        
        if (record.getModuleNo() != null) {
            sql.SET("module_no = #{moduleNo,jdbcType=INTEGER}");
        }
        
        if (record.getMenuName() != null) {
            sql.SET("menu_name = #{menuName,jdbcType=VARCHAR}");
        }
        
        if (record.getMenuUrl() != null) {
            sql.SET("menu_url = #{menuUrl,jdbcType=VARCHAR}");
        }
        
        if (record.getRoute() != null) {
            sql.SET("route = #{route,jdbcType=VARCHAR}");
        }
        
        if (record.getIsMenu() != null) {
            sql.SET("is_menu = #{isMenu,jdbcType=INTEGER}");
        }
        
        if (record.getParentId() != null) {
            sql.SET("parent_id = #{parentId,jdbcType=INTEGER}");
        }
        
        if (record.getOrderBy() != null) {
            sql.SET("order_by = #{orderBy,jdbcType=INTEGER}");
        }
        
        if (record.getStatus() != null) {
            sql.SET("status = #{status,jdbcType=INTEGER}");
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

    protected void applyWhere(SQL sql, SwMenuExample example, boolean includeExamplePhrase) {
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