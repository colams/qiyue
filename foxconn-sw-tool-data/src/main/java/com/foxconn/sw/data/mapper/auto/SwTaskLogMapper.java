package com.foxconn.sw.data.mapper.auto;

import com.foxconn.sw.data.entity.SwTaskLog;
import com.foxconn.sw.data.entity.SwTaskLogExample;
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
public interface SwTaskLogMapper {
    @SelectProvider(type=SwTaskLogSqlProvider.class, method="countByExample")
    long countByExample(SwTaskLogExample example);

    @DeleteProvider(type=SwTaskLogSqlProvider.class, method="deleteByExample")
    int deleteByExample(SwTaskLogExample example);

    @Delete({
        "delete from sw_task_log",
        "where id = #{id,jdbcType=INTEGER}"
    })
    int deleteByPrimaryKey(Integer id);

    @Insert({
        "insert into sw_task_log (task_id, operator, ",
        "content, datetime_lastchange)",
        "values (#{taskId,jdbcType=INTEGER}, #{operator,jdbcType=VARCHAR}, ",
        "#{content,jdbcType=VARCHAR}, #{datetimeLastchange,jdbcType=TIMESTAMP})"
    })
    @SelectKey(statement="SELECT LAST_INSERT_ID()", keyProperty="id", before=false, resultType=Integer.class)
    int insert(SwTaskLog record);

    @InsertProvider(type=SwTaskLogSqlProvider.class, method="insertSelective")
    @SelectKey(statement="SELECT LAST_INSERT_ID()", keyProperty="id", before=false, resultType=Integer.class)
    int insertSelective(SwTaskLog record);

    @SelectProvider(type=SwTaskLogSqlProvider.class, method="selectByExample")
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="task_id", property="taskId", jdbcType=JdbcType.INTEGER),
        @Result(column="operator", property="operator", jdbcType=JdbcType.VARCHAR),
        @Result(column="content", property="content", jdbcType=JdbcType.VARCHAR),
        @Result(column="datetime_lastchange", property="datetimeLastchange", jdbcType=JdbcType.TIMESTAMP)
    })
    List<SwTaskLog> selectByExampleWithRowbounds(SwTaskLogExample example, RowBounds rowBounds);

    @SelectProvider(type=SwTaskLogSqlProvider.class, method="selectByExample")
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="task_id", property="taskId", jdbcType=JdbcType.INTEGER),
        @Result(column="operator", property="operator", jdbcType=JdbcType.VARCHAR),
        @Result(column="content", property="content", jdbcType=JdbcType.VARCHAR),
        @Result(column="datetime_lastchange", property="datetimeLastchange", jdbcType=JdbcType.TIMESTAMP)
    })
    List<SwTaskLog> selectByExample(SwTaskLogExample example);

    @Select({
        "select",
        "id, task_id, operator, content, datetime_lastchange",
        "from sw_task_log",
        "where id = #{id,jdbcType=INTEGER}"
    })
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="task_id", property="taskId", jdbcType=JdbcType.INTEGER),
        @Result(column="operator", property="operator", jdbcType=JdbcType.VARCHAR),
        @Result(column="content", property="content", jdbcType=JdbcType.VARCHAR),
        @Result(column="datetime_lastchange", property="datetimeLastchange", jdbcType=JdbcType.TIMESTAMP)
    })
    SwTaskLog selectByPrimaryKey(Integer id);

    @UpdateProvider(type=SwTaskLogSqlProvider.class, method="updateByExampleSelective")
    int updateByExampleSelective(@Param("record") SwTaskLog record, @Param("example") SwTaskLogExample example);

    @UpdateProvider(type=SwTaskLogSqlProvider.class, method="updateByExample")
    int updateByExample(@Param("record") SwTaskLog record, @Param("example") SwTaskLogExample example);

    @UpdateProvider(type=SwTaskLogSqlProvider.class, method="updateByPrimaryKeySelective")
    int updateByPrimaryKeySelective(SwTaskLog record);

    @Update({
        "update sw_task_log",
        "set task_id = #{taskId,jdbcType=INTEGER},",
          "operator = #{operator,jdbcType=VARCHAR},",
          "content = #{content,jdbcType=VARCHAR},",
          "datetime_lastchange = #{datetimeLastchange,jdbcType=TIMESTAMP}",
        "where id = #{id,jdbcType=INTEGER}"
    })
    int updateByPrimaryKey(SwTaskLog record);
}