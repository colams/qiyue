package com.foxconn.sw.data.mapper.auto;

import com.foxconn.sw.data.entity.SwMeeting;
import com.foxconn.sw.data.entity.SwMeetingExample;
import com.foxconn.sw.data.entity.SwMeetingExample.Criteria;
import com.foxconn.sw.data.entity.SwMeetingExample.Criterion;
import org.apache.ibatis.jdbc.SQL;

import java.util.List;
import java.util.Map;

public class SwMeetingSqlProvider {

    public String countByExample(SwMeetingExample example) {
        SQL sql = new SQL();
        sql.SELECT("count(*)").FROM("sw_meeting");
        applyWhere(sql, example, false);
        return sql.toString();
    }

    public String deleteByExample(SwMeetingExample example) {
        SQL sql = new SQL();
        sql.DELETE_FROM("sw_meeting");
        applyWhere(sql, example, false);
        return sql.toString();
    }

    public String insertSelective(SwMeeting record) {
        SQL sql = new SQL();
        sql.INSERT_INTO("sw_meeting");
        
        if (record.getRoom() != null) {
            sql.VALUES("room", "#{room,jdbcType=VARCHAR}");
        }
        
        if (record.getAbcMeeting() != null) {
            sql.VALUES("abc_meeting", "#{abcMeeting,jdbcType=INTEGER}");
        }
        
        if (record.getTitle() != null) {
            sql.VALUES("title", "#{title,jdbcType=VARCHAR}");
        }
        
        if (record.getDescription() != null) {
            sql.VALUES("description", "#{description,jdbcType=VARCHAR}");
        }
        
        if (record.getMeetingDate() != null) {
            sql.VALUES("meeting_date", "#{meetingDate,jdbcType=VARCHAR}");
        }
        
        if (record.getStartTime() != null) {
            sql.VALUES("start_time", "#{startTime,jdbcType=VARCHAR}");
        }

        if (record.getEndTime() != null) {
            sql.VALUES("end_time", "#{endTime,jdbcType=VARCHAR}");
        }

        if (record.getResourceIds() != null) {
            sql.VALUES("resource_ids", "#{resourceIds,jdbcType=VARCHAR}");
        }

        if (record.getIsRepeat() != null) {
            sql.VALUES("is_repeat", "#{isRepeat,jdbcType=INTEGER}");
        }

        if (record.getCycle() != null) {
            sql.VALUES("cycle", "#{cycle,jdbcType=VARCHAR}");
        }

        if (record.getCycleStart() != null) {
            sql.VALUES("cycle_start", "#{cycleStart,jdbcType=VARCHAR}");
        }

        if (record.getCycleExpire() != null) {
            sql.VALUES("cycle_expire", "#{cycleExpire,jdbcType=VARCHAR}");
        }

        if (record.getCreateTime() != null) {
            sql.VALUES("create_time", "#{createTime,jdbcType=TIMESTAMP}");
        }

        if (record.getDatetimeLastchange() != null) {
            sql.VALUES("datetime_lastchange", "#{datetimeLastchange,jdbcType=TIMESTAMP}");
        }

        if (record.getStatus() != null) {
            sql.VALUES("status", "#{status,jdbcType=INTEGER}");
        }

        if (record.getCreator() != null) {
            sql.VALUES("creator", "#{creator,jdbcType=VARCHAR}");
        }

        return sql.toString();
    }

    public String selectByExample(SwMeetingExample example) {
        SQL sql = new SQL();
        if (example != null && example.isDistinct()) {
            sql.SELECT_DISTINCT("id");
        } else {
            sql.SELECT("id");
        }
        sql.SELECT("room");
        sql.SELECT("abc_meeting");
        sql.SELECT("title");
        sql.SELECT("description");
        sql.SELECT("meeting_date");
        sql.SELECT("start_time");
        sql.SELECT("end_time");
        sql.SELECT("resource_ids");
        sql.SELECT("is_repeat");
        sql.SELECT("cycle");
        sql.SELECT("cycle_start");
        sql.SELECT("cycle_expire");
        sql.SELECT("create_time");
        sql.SELECT("datetime_lastchange");
        sql.SELECT("status");
        sql.SELECT("creator");
        sql.FROM("sw_meeting");
        applyWhere(sql, example, false);

        if (example != null && example.getOrderByClause() != null) {
            sql.ORDER_BY(example.getOrderByClause());
        }

        return sql.toString();
    }

    public String updateByExampleSelective(Map<String, Object> parameter) {
        SwMeeting record = (SwMeeting) parameter.get("record");
        SwMeetingExample example = (SwMeetingExample) parameter.get("example");
        
        SQL sql = new SQL();
        sql.UPDATE("sw_meeting");
        
        if (record.getId() != null) {
            sql.SET("id = #{record.id,jdbcType=INTEGER}");
        }
        
        if (record.getRoom() != null) {
            sql.SET("room = #{record.room,jdbcType=VARCHAR}");
        }
        
        if (record.getAbcMeeting() != null) {
            sql.SET("abc_meeting = #{record.abcMeeting,jdbcType=INTEGER}");
        }
        
        if (record.getTitle() != null) {
            sql.SET("title = #{record.title,jdbcType=VARCHAR}");
        }
        
        if (record.getDescription() != null) {
            sql.SET("description = #{record.description,jdbcType=VARCHAR}");
        }
        
        if (record.getMeetingDate() != null) {
            sql.SET("meeting_date = #{record.meetingDate,jdbcType=VARCHAR}");
        }
        
        if (record.getStartTime() != null) {
            sql.SET("start_time = #{record.startTime,jdbcType=VARCHAR}");
        }

        if (record.getEndTime() != null) {
            sql.SET("end_time = #{record.endTime,jdbcType=VARCHAR}");
        }

        if (record.getResourceIds() != null) {
            sql.SET("resource_ids = #{record.resourceIds,jdbcType=VARCHAR}");
        }

        if (record.getIsRepeat() != null) {
            sql.SET("is_repeat = #{record.isRepeat,jdbcType=INTEGER}");
        }

        if (record.getCycle() != null) {
            sql.SET("cycle = #{record.cycle,jdbcType=VARCHAR}");
        }

        if (record.getCycleStart() != null) {
            sql.SET("cycle_start = #{record.cycleStart,jdbcType=VARCHAR}");
        }

        if (record.getCycleExpire() != null) {
            sql.SET("cycle_expire = #{record.cycleExpire,jdbcType=VARCHAR}");
        }

        if (record.getCreateTime() != null) {
            sql.SET("create_time = #{record.createTime,jdbcType=TIMESTAMP}");
        }

        if (record.getDatetimeLastchange() != null) {
            sql.SET("datetime_lastchange = #{record.datetimeLastchange,jdbcType=TIMESTAMP}");
        }

        if (record.getStatus() != null) {
            sql.SET("status = #{record.status,jdbcType=INTEGER}");
        }

        if (record.getCreator() != null) {
            sql.SET("creator = #{record.creator,jdbcType=VARCHAR}");
        }

        applyWhere(sql, example, true);
        return sql.toString();
    }

    public String updateByExample(Map<String, Object> parameter) {
        SQL sql = new SQL();
        sql.UPDATE("sw_meeting");
        
        sql.SET("id = #{record.id,jdbcType=INTEGER}");
        sql.SET("room = #{record.room,jdbcType=VARCHAR}");
        sql.SET("abc_meeting = #{record.abcMeeting,jdbcType=INTEGER}");
        sql.SET("title = #{record.title,jdbcType=VARCHAR}");
        sql.SET("description = #{record.description,jdbcType=VARCHAR}");
        sql.SET("meeting_date = #{record.meetingDate,jdbcType=VARCHAR}");
        sql.SET("start_time = #{record.startTime,jdbcType=VARCHAR}");
        sql.SET("end_time = #{record.endTime,jdbcType=VARCHAR}");
        sql.SET("resource_ids = #{record.resourceIds,jdbcType=VARCHAR}");
        sql.SET("is_repeat = #{record.isRepeat,jdbcType=INTEGER}");
        sql.SET("cycle = #{record.cycle,jdbcType=VARCHAR}");
        sql.SET("cycle_start = #{record.cycleStart,jdbcType=VARCHAR}");
        sql.SET("cycle_expire = #{record.cycleExpire,jdbcType=VARCHAR}");
        sql.SET("create_time = #{record.createTime,jdbcType=TIMESTAMP}");
        sql.SET("datetime_lastchange = #{record.datetimeLastchange,jdbcType=TIMESTAMP}");
        sql.SET("status = #{record.status,jdbcType=INTEGER}");
        sql.SET("creator = #{record.creator,jdbcType=VARCHAR}");

        SwMeetingExample example = (SwMeetingExample) parameter.get("example");
        applyWhere(sql, example, true);
        return sql.toString();
    }

    public String updateByPrimaryKeySelective(SwMeeting record) {
        SQL sql = new SQL();
        sql.UPDATE("sw_meeting");
        
        if (record.getRoom() != null) {
            sql.SET("room = #{room,jdbcType=VARCHAR}");
        }
        
        if (record.getAbcMeeting() != null) {
            sql.SET("abc_meeting = #{abcMeeting,jdbcType=INTEGER}");
        }
        
        if (record.getTitle() != null) {
            sql.SET("title = #{title,jdbcType=VARCHAR}");
        }
        
        if (record.getDescription() != null) {
            sql.SET("description = #{description,jdbcType=VARCHAR}");
        }
        
        if (record.getMeetingDate() != null) {
            sql.SET("meeting_date = #{meetingDate,jdbcType=VARCHAR}");
        }
        
        if (record.getStartTime() != null) {
            sql.SET("start_time = #{startTime,jdbcType=VARCHAR}");
        }

        if (record.getEndTime() != null) {
            sql.SET("end_time = #{endTime,jdbcType=VARCHAR}");
        }

        if (record.getResourceIds() != null) {
            sql.SET("resource_ids = #{resourceIds,jdbcType=VARCHAR}");
        }

        if (record.getIsRepeat() != null) {
            sql.SET("is_repeat = #{isRepeat,jdbcType=INTEGER}");
        }

        if (record.getCycle() != null) {
            sql.SET("cycle = #{cycle,jdbcType=VARCHAR}");
        }

        if (record.getCycleStart() != null) {
            sql.SET("cycle_start = #{cycleStart,jdbcType=VARCHAR}");
        }

        if (record.getCycleExpire() != null) {
            sql.SET("cycle_expire = #{cycleExpire,jdbcType=VARCHAR}");
        }

        if (record.getCreateTime() != null) {
            sql.SET("create_time = #{createTime,jdbcType=TIMESTAMP}");
        }

        if (record.getDatetimeLastchange() != null) {
            sql.SET("datetime_lastchange = #{datetimeLastchange,jdbcType=TIMESTAMP}");
        }

        if (record.getStatus() != null) {
            sql.SET("status = #{status,jdbcType=INTEGER}");
        }

        if (record.getCreator() != null) {
            sql.SET("creator = #{creator,jdbcType=VARCHAR}");
        }

        sql.WHERE("id = #{id,jdbcType=INTEGER}");
        
        return sql.toString();
    }

    protected void applyWhere(SQL sql, SwMeetingExample example, boolean includeExamplePhrase) {
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