package com.foxconn.sw.data.mapper.auto;

import com.foxconn.sw.data.entity.SwChangeLog;
import com.foxconn.sw.data.entity.SwChangeLogExample;
import java.util.List;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.DeleteProvider;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectKey;
import org.apache.ibatis.annotations.SelectProvider;
import org.apache.ibatis.annotations.Update;
import org.apache.ibatis.annotations.UpdateProvider;
import org.apache.ibatis.session.RowBounds;
import org.apache.ibatis.type.JdbcType;

@Mapper
public interface SwChangeLogMapper {
    @SelectProvider(type=SwChangeLogSqlProvider.class, method="countByExample")
    long countByExample(SwChangeLogExample example);

    @DeleteProvider(type=SwChangeLogSqlProvider.class, method="deleteByExample")
    int deleteByExample(SwChangeLogExample example);

    @Delete({
        "delete from sw_change_log",
        "where id = #{id,jdbcType=INTEGER}"
    })
    int deleteByPrimaryKey(Integer id);

    @Insert({
        "insert into sw_change_log (release_note, release_version, ",
        "create_time, operator, ",
        "last_updater, datetime_lastchange)",
        "values (#{releaseNote,jdbcType=VARCHAR}, #{releaseVersion,jdbcType=VARCHAR}, ",
        "#{createTime,jdbcType=TIMESTAMP}, #{operator,jdbcType=VARCHAR}, ",
        "#{lastUpdater,jdbcType=VARCHAR}, #{datetimeLastchange,jdbcType=TIMESTAMP})"
    })
    @SelectKey(statement="SELECT LAST_INSERT_ID()", keyProperty="id", before=false, resultType=Integer.class)
    int insert(SwChangeLog record);

    @InsertProvider(type=SwChangeLogSqlProvider.class, method="insertSelective")
    @SelectKey(statement="SELECT LAST_INSERT_ID()", keyProperty="id", before=false, resultType=Integer.class)
    int insertSelective(SwChangeLog record);

    @SelectProvider(type=SwChangeLogSqlProvider.class, method="selectByExample")
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="release_note", property="releaseNote", jdbcType=JdbcType.VARCHAR),
        @Result(column="release_version", property="releaseVersion", jdbcType=JdbcType.VARCHAR),
        @Result(column="create_time", property="createTime", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="operator", property="operator", jdbcType=JdbcType.VARCHAR),
        @Result(column="last_updater", property="lastUpdater", jdbcType=JdbcType.VARCHAR),
        @Result(column="datetime_lastchange", property="datetimeLastchange", jdbcType=JdbcType.TIMESTAMP)
    })
    List<SwChangeLog> selectByExampleWithRowbounds(SwChangeLogExample example, RowBounds rowBounds);

    @SelectProvider(type=SwChangeLogSqlProvider.class, method="selectByExample")
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="release_note", property="releaseNote", jdbcType=JdbcType.VARCHAR),
        @Result(column="release_version", property="releaseVersion", jdbcType=JdbcType.VARCHAR),
        @Result(column="create_time", property="createTime", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="operator", property="operator", jdbcType=JdbcType.VARCHAR),
        @Result(column="last_updater", property="lastUpdater", jdbcType=JdbcType.VARCHAR),
        @Result(column="datetime_lastchange", property="datetimeLastchange", jdbcType=JdbcType.TIMESTAMP)
    })
    List<SwChangeLog> selectByExample(SwChangeLogExample example);

    @Select({
        "select",
        "id, release_note, release_version, create_time, operator, last_updater, datetime_lastchange",
        "from sw_change_log",
        "where id = #{id,jdbcType=INTEGER}"
    })
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="release_note", property="releaseNote", jdbcType=JdbcType.VARCHAR),
        @Result(column="release_version", property="releaseVersion", jdbcType=JdbcType.VARCHAR),
        @Result(column="create_time", property="createTime", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="operator", property="operator", jdbcType=JdbcType.VARCHAR),
        @Result(column="last_updater", property="lastUpdater", jdbcType=JdbcType.VARCHAR),
        @Result(column="datetime_lastchange", property="datetimeLastchange", jdbcType=JdbcType.TIMESTAMP)
    })
    SwChangeLog selectByPrimaryKey(Integer id);

    @UpdateProvider(type=SwChangeLogSqlProvider.class, method="updateByExampleSelective")
    int updateByExampleSelective(@Param("record") SwChangeLog record, @Param("example") SwChangeLogExample example);

    @UpdateProvider(type=SwChangeLogSqlProvider.class, method="updateByExample")
    int updateByExample(@Param("record") SwChangeLog record, @Param("example") SwChangeLogExample example);

    @UpdateProvider(type=SwChangeLogSqlProvider.class, method="updateByPrimaryKeySelective")
    int updateByPrimaryKeySelective(SwChangeLog record);

    @Update({
        "update sw_change_log",
        "set release_note = #{releaseNote,jdbcType=VARCHAR},",
          "release_version = #{releaseVersion,jdbcType=VARCHAR},",
          "create_time = #{createTime,jdbcType=TIMESTAMP},",
          "operator = #{operator,jdbcType=VARCHAR},",
          "last_updater = #{lastUpdater,jdbcType=VARCHAR},",
          "datetime_lastchange = #{datetimeLastchange,jdbcType=TIMESTAMP}",
        "where id = #{id,jdbcType=INTEGER}"
    })
    int updateByPrimaryKey(SwChangeLog record);
}