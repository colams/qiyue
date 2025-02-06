package com.foxconn.sw.data.mapper.auto;

import com.foxconn.sw.data.entity.SwTaskFollow;
import com.foxconn.sw.data.entity.SwTaskFollowExample;
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
public interface SwTaskFollowMapper {
    @SelectProvider(type=SwTaskFollowSqlProvider.class, method="countByExample")
    long countByExample(SwTaskFollowExample example);

    @DeleteProvider(type=SwTaskFollowSqlProvider.class, method="deleteByExample")
    int deleteByExample(SwTaskFollowExample example);

    @Delete({
        "delete from sw_task_follow",
        "where id = #{id,jdbcType=INTEGER}"
    })
    int deleteByPrimaryKey(Integer id);

    @Insert({
        "insert into sw_task_follow (task_id, content, ",
        "status, operator, ",
        "create_time, datetime_lastchange)",
        "values (#{taskId,jdbcType=INTEGER}, #{content,jdbcType=VARCHAR}, ",
        "#{status,jdbcType=INTEGER}, #{operator,jdbcType=VARCHAR}, ",
        "#{createTime,jdbcType=TIMESTAMP}, #{datetimeLastchange,jdbcType=TIMESTAMP})"
    })
    @SelectKey(statement="SELECT LAST_INSERT_ID()", keyProperty="id", before=false, resultType=Integer.class)
    int insert(SwTaskFollow record);

    @InsertProvider(type=SwTaskFollowSqlProvider.class, method="insertSelective")
    @SelectKey(statement="SELECT LAST_INSERT_ID()", keyProperty="id", before=false, resultType=Integer.class)
    int insertSelective(SwTaskFollow record);

    @SelectProvider(type=SwTaskFollowSqlProvider.class, method="selectByExample")
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="task_id", property="taskId", jdbcType=JdbcType.INTEGER),
        @Result(column="content", property="content", jdbcType=JdbcType.VARCHAR),
        @Result(column="status", property="status", jdbcType=JdbcType.INTEGER),
        @Result(column="operator", property="operator", jdbcType=JdbcType.VARCHAR),
        @Result(column="create_time", property="createTime", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="datetime_lastchange", property="datetimeLastchange", jdbcType=JdbcType.TIMESTAMP)
    })
    List<SwTaskFollow> selectByExampleWithRowbounds(SwTaskFollowExample example, RowBounds rowBounds);

    @SelectProvider(type=SwTaskFollowSqlProvider.class, method="selectByExample")
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="task_id", property="taskId", jdbcType=JdbcType.INTEGER),
        @Result(column="content", property="content", jdbcType=JdbcType.VARCHAR),
        @Result(column="status", property="status", jdbcType=JdbcType.INTEGER),
        @Result(column="operator", property="operator", jdbcType=JdbcType.VARCHAR),
        @Result(column="create_time", property="createTime", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="datetime_lastchange", property="datetimeLastchange", jdbcType=JdbcType.TIMESTAMP)
    })
    List<SwTaskFollow> selectByExample(SwTaskFollowExample example);

    @Select({
        "select",
        "id, task_id, content, status, operator, create_time, datetime_lastchange",
        "from sw_task_follow",
        "where id = #{id,jdbcType=INTEGER}"
    })
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="task_id", property="taskId", jdbcType=JdbcType.INTEGER),
        @Result(column="content", property="content", jdbcType=JdbcType.VARCHAR),
        @Result(column="status", property="status", jdbcType=JdbcType.INTEGER),
        @Result(column="operator", property="operator", jdbcType=JdbcType.VARCHAR),
        @Result(column="create_time", property="createTime", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="datetime_lastchange", property="datetimeLastchange", jdbcType=JdbcType.TIMESTAMP)
    })
    SwTaskFollow selectByPrimaryKey(Integer id);

    @UpdateProvider(type=SwTaskFollowSqlProvider.class, method="updateByExampleSelective")
    int updateByExampleSelective(@Param("record") SwTaskFollow record, @Param("example") SwTaskFollowExample example);

    @UpdateProvider(type=SwTaskFollowSqlProvider.class, method="updateByExample")
    int updateByExample(@Param("record") SwTaskFollow record, @Param("example") SwTaskFollowExample example);

    @UpdateProvider(type=SwTaskFollowSqlProvider.class, method="updateByPrimaryKeySelective")
    int updateByPrimaryKeySelective(SwTaskFollow record);

    @Update({
        "update sw_task_follow",
        "set task_id = #{taskId,jdbcType=INTEGER},",
          "content = #{content,jdbcType=VARCHAR},",
          "status = #{status,jdbcType=INTEGER},",
          "operator = #{operator,jdbcType=VARCHAR},",
          "create_time = #{createTime,jdbcType=TIMESTAMP},",
          "datetime_lastchange = #{datetimeLastchange,jdbcType=TIMESTAMP}",
        "where id = #{id,jdbcType=INTEGER}"
    })
    int updateByPrimaryKey(SwTaskFollow record);
}