package com.foxconn.sw.data.mapper.auto;

import com.foxconn.sw.data.entity.SwTaskEvaluation;
import com.foxconn.sw.data.entity.SwTaskEvaluationExample;
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
public interface SwTaskEvaluationMapper {
    @SelectProvider(type=SwTaskEvaluationSqlProvider.class, method="countByExample")
    long countByExample(SwTaskEvaluationExample example);

    @DeleteProvider(type=SwTaskEvaluationSqlProvider.class, method="deleteByExample")
    int deleteByExample(SwTaskEvaluationExample example);

    @Delete({
        "delete from sw_task_evaluation",
        "where id = #{id,jdbcType=INTEGER}"
    })
    int deleteByPrimaryKey(Integer id);

    @Insert({
        "insert into sw_task_evaluation (task_id, operator, ",
        "completion, efficiency, ",
        "quality, content, ",
        "create_time)",
        "values (#{taskId,jdbcType=INTEGER}, #{operator,jdbcType=VARCHAR}, ",
        "#{completion,jdbcType=INTEGER}, #{efficiency,jdbcType=INTEGER}, ",
        "#{quality,jdbcType=INTEGER}, #{content,jdbcType=VARCHAR}, ",
        "#{createTime,jdbcType=TIMESTAMP})"
    })
    @SelectKey(statement="SELECT LAST_INSERT_ID()", keyProperty="id", before=false, resultType=Integer.class)
    int insert(SwTaskEvaluation record);

    @InsertProvider(type=SwTaskEvaluationSqlProvider.class, method="insertSelective")
    @SelectKey(statement="SELECT LAST_INSERT_ID()", keyProperty="id", before=false, resultType=Integer.class)
    int insertSelective(SwTaskEvaluation record);

    @SelectProvider(type=SwTaskEvaluationSqlProvider.class, method="selectByExample")
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="task_id", property="taskId", jdbcType=JdbcType.INTEGER),
        @Result(column="operator", property="operator", jdbcType=JdbcType.VARCHAR),
        @Result(column="completion", property="completion", jdbcType=JdbcType.INTEGER),
        @Result(column="efficiency", property="efficiency", jdbcType=JdbcType.INTEGER),
        @Result(column="quality", property="quality", jdbcType=JdbcType.INTEGER),
        @Result(column="content", property="content", jdbcType=JdbcType.VARCHAR),
        @Result(column="create_time", property="createTime", jdbcType=JdbcType.TIMESTAMP)
    })
    List<SwTaskEvaluation> selectByExampleWithRowbounds(SwTaskEvaluationExample example, RowBounds rowBounds);

    @SelectProvider(type=SwTaskEvaluationSqlProvider.class, method="selectByExample")
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="task_id", property="taskId", jdbcType=JdbcType.INTEGER),
        @Result(column="operator", property="operator", jdbcType=JdbcType.VARCHAR),
        @Result(column="completion", property="completion", jdbcType=JdbcType.INTEGER),
        @Result(column="efficiency", property="efficiency", jdbcType=JdbcType.INTEGER),
        @Result(column="quality", property="quality", jdbcType=JdbcType.INTEGER),
        @Result(column="content", property="content", jdbcType=JdbcType.VARCHAR),
        @Result(column="create_time", property="createTime", jdbcType=JdbcType.TIMESTAMP)
    })
    List<SwTaskEvaluation> selectByExample(SwTaskEvaluationExample example);

    @Select({
        "select",
        "id, task_id, operator, completion, efficiency, quality, content, create_time",
        "from sw_task_evaluation",
        "where id = #{id,jdbcType=INTEGER}"
    })
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="task_id", property="taskId", jdbcType=JdbcType.INTEGER),
        @Result(column="operator", property="operator", jdbcType=JdbcType.VARCHAR),
        @Result(column="completion", property="completion", jdbcType=JdbcType.INTEGER),
        @Result(column="efficiency", property="efficiency", jdbcType=JdbcType.INTEGER),
        @Result(column="quality", property="quality", jdbcType=JdbcType.INTEGER),
        @Result(column="content", property="content", jdbcType=JdbcType.VARCHAR),
        @Result(column="create_time", property="createTime", jdbcType=JdbcType.TIMESTAMP)
    })
    SwTaskEvaluation selectByPrimaryKey(Integer id);

    @UpdateProvider(type=SwTaskEvaluationSqlProvider.class, method="updateByExampleSelective")
    int updateByExampleSelective(@Param("record") SwTaskEvaluation record, @Param("example") SwTaskEvaluationExample example);

    @UpdateProvider(type=SwTaskEvaluationSqlProvider.class, method="updateByExample")
    int updateByExample(@Param("record") SwTaskEvaluation record, @Param("example") SwTaskEvaluationExample example);

    @UpdateProvider(type=SwTaskEvaluationSqlProvider.class, method="updateByPrimaryKeySelective")
    int updateByPrimaryKeySelective(SwTaskEvaluation record);

    @Update({
        "update sw_task_evaluation",
        "set task_id = #{taskId,jdbcType=INTEGER},",
          "operator = #{operator,jdbcType=VARCHAR},",
          "completion = #{completion,jdbcType=INTEGER},",
          "efficiency = #{efficiency,jdbcType=INTEGER},",
          "quality = #{quality,jdbcType=INTEGER},",
          "content = #{content,jdbcType=VARCHAR},",
          "create_time = #{createTime,jdbcType=TIMESTAMP}",
        "where id = #{id,jdbcType=INTEGER}"
    })
    int updateByPrimaryKey(SwTaskEvaluation record);
}