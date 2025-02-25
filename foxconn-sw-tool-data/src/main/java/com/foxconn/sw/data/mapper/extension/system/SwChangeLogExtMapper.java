package com.foxconn.sw.data.mapper.extension.system;

import com.foxconn.sw.data.entity.SwChangeLog;
import com.foxconn.sw.data.mapper.auto.SwChangeLogMapper;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.type.JdbcType;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SwChangeLogExtMapper extends SwChangeLogMapper {

    @Select({
            "select",
            "id, release_note, release_version, create_time, operator, last_updater, datetime_lastchange",
            "from sw_change_log",
            "order by id desc ",
            "limit 1"
    })
    @Results({
            @Result(column="id", property="id", jdbcType= JdbcType.INTEGER, id=true),
            @Result(column="release_note", property="releaseNote", jdbcType=JdbcType.VARCHAR),
            @Result(column="release_version", property="releaseVersion", jdbcType=JdbcType.VARCHAR),
            @Result(column="create_time", property="createTime", jdbcType=JdbcType.TIMESTAMP),
            @Result(column="operator", property="operator", jdbcType=JdbcType.VARCHAR),
            @Result(column="last_updater", property="lastUpdater", jdbcType=JdbcType.VARCHAR),
            @Result(column="datetime_lastchange", property="datetimeLastchange", jdbcType=JdbcType.TIMESTAMP)
    })
    SwChangeLog getLatestChangeLog();
}
