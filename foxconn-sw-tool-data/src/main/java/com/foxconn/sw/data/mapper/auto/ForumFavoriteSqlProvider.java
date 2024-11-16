package com.foxconn.sw.data.mapper.auto;

import com.foxconn.sw.data.entity.ForumFavorite;
import com.foxconn.sw.data.entity.ForumFavoriteExample.Criteria;
import com.foxconn.sw.data.entity.ForumFavoriteExample.Criterion;
import com.foxconn.sw.data.entity.ForumFavoriteExample;
import java.util.List;
import java.util.Map;
import org.apache.ibatis.jdbc.SQL;

public class ForumFavoriteSqlProvider {

    public String countByExample(ForumFavoriteExample example) {
        SQL sql = new SQL();
        sql.SELECT("count(*)").FROM("forum_favorite");
        applyWhere(sql, example, false);
        return sql.toString();
    }

    public String deleteByExample(ForumFavoriteExample example) {
        SQL sql = new SQL();
        sql.DELETE_FROM("forum_favorite");
        applyWhere(sql, example, false);
        return sql.toString();
    }

    public String insertSelective(ForumFavorite record) {
        SQL sql = new SQL();
        sql.INSERT_INTO("forum_favorite");
        
        if (record.getAuthorNo() != null) {
            sql.VALUES("author_no", "#{authorNo,jdbcType=VARCHAR}");
        }
        
        if (record.getPostsId() != null) {
            sql.VALUES("posts_id", "#{postsId,jdbcType=INTEGER}");
        }
        
        if (record.getIsDelete() != null) {
            sql.VALUES("is_delete", "#{isDelete,jdbcType=INTEGER}");
        }
        
        if (record.getCreateTime() != null) {
            sql.VALUES("create_time", "#{createTime,jdbcType=TIMESTAMP}");
        }
        
        if (record.getDatatimeLastchange() != null) {
            sql.VALUES("datatime_lastchange", "#{datatimeLastchange,jdbcType=TIMESTAMP}");
        }
        
        return sql.toString();
    }

    public String selectByExample(ForumFavoriteExample example) {
        SQL sql = new SQL();
        if (example != null && example.isDistinct()) {
            sql.SELECT_DISTINCT("id");
        } else {
            sql.SELECT("id");
        }
        sql.SELECT("author_no");
        sql.SELECT("posts_id");
        sql.SELECT("is_delete");
        sql.SELECT("create_time");
        sql.SELECT("datatime_lastchange");
        sql.FROM("forum_favorite");
        applyWhere(sql, example, false);
        
        if (example != null && example.getOrderByClause() != null) {
            sql.ORDER_BY(example.getOrderByClause());
        }
        
        return sql.toString();
    }

    public String updateByExampleSelective(Map<String, Object> parameter) {
        ForumFavorite record = (ForumFavorite) parameter.get("record");
        ForumFavoriteExample example = (ForumFavoriteExample) parameter.get("example");
        
        SQL sql = new SQL();
        sql.UPDATE("forum_favorite");
        
        if (record.getId() != null) {
            sql.SET("id = #{record.id,jdbcType=INTEGER}");
        }
        
        if (record.getAuthorNo() != null) {
            sql.SET("author_no = #{record.authorNo,jdbcType=VARCHAR}");
        }
        
        if (record.getPostsId() != null) {
            sql.SET("posts_id = #{record.postsId,jdbcType=INTEGER}");
        }
        
        if (record.getIsDelete() != null) {
            sql.SET("is_delete = #{record.isDelete,jdbcType=INTEGER}");
        }
        
        if (record.getCreateTime() != null) {
            sql.SET("create_time = #{record.createTime,jdbcType=TIMESTAMP}");
        }
        
        if (record.getDatatimeLastchange() != null) {
            sql.SET("datatime_lastchange = #{record.datatimeLastchange,jdbcType=TIMESTAMP}");
        }
        
        applyWhere(sql, example, true);
        return sql.toString();
    }

    public String updateByExample(Map<String, Object> parameter) {
        SQL sql = new SQL();
        sql.UPDATE("forum_favorite");
        
        sql.SET("id = #{record.id,jdbcType=INTEGER}");
        sql.SET("author_no = #{record.authorNo,jdbcType=VARCHAR}");
        sql.SET("posts_id = #{record.postsId,jdbcType=INTEGER}");
        sql.SET("is_delete = #{record.isDelete,jdbcType=INTEGER}");
        sql.SET("create_time = #{record.createTime,jdbcType=TIMESTAMP}");
        sql.SET("datatime_lastchange = #{record.datatimeLastchange,jdbcType=TIMESTAMP}");
        
        ForumFavoriteExample example = (ForumFavoriteExample) parameter.get("example");
        applyWhere(sql, example, true);
        return sql.toString();
    }

    public String updateByPrimaryKeySelective(ForumFavorite record) {
        SQL sql = new SQL();
        sql.UPDATE("forum_favorite");
        
        if (record.getAuthorNo() != null) {
            sql.SET("author_no = #{authorNo,jdbcType=VARCHAR}");
        }
        
        if (record.getPostsId() != null) {
            sql.SET("posts_id = #{postsId,jdbcType=INTEGER}");
        }
        
        if (record.getIsDelete() != null) {
            sql.SET("is_delete = #{isDelete,jdbcType=INTEGER}");
        }
        
        if (record.getCreateTime() != null) {
            sql.SET("create_time = #{createTime,jdbcType=TIMESTAMP}");
        }
        
        if (record.getDatatimeLastchange() != null) {
            sql.SET("datatime_lastchange = #{datatimeLastchange,jdbcType=TIMESTAMP}");
        }
        
        sql.WHERE("id = #{id,jdbcType=INTEGER}");
        
        return sql.toString();
    }

    protected void applyWhere(SQL sql, ForumFavoriteExample example, boolean includeExamplePhrase) {
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