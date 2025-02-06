package com.foxconn.sw.data.mapper.auto;

import com.foxconn.sw.data.entity.SwTaskEvaluation;
import com.foxconn.sw.data.entity.SwTaskEvaluationExample;
import java.util.List;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectKey;
import org.apache.ibatis.annotations.Update;
import org.apache.ibatis.session.RowBounds;

@Mapper
public interface SwTaskEvaluationMapper {
    long countByExample(SwTaskEvaluationExample example);

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

    int insertSelective(SwTaskEvaluation record);

    List<SwTaskEvaluation> selectByExampleWithRowbounds(SwTaskEvaluationExample example, RowBounds rowBounds);

    List<SwTaskEvaluation> selectByExample(SwTaskEvaluationExample example);

    @Select({
        "select",
        "id, task_id, operator, completion, efficiency, quality, content, create_time",
        "from sw_task_evaluation",
        "where id = #{id,jdbcType=INTEGER}"
    })
    @ResultMap("com.foxconn.sw.data.mapper.auto.SwTaskEvaluationMapper.BaseResultMap")
    SwTaskEvaluation selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") SwTaskEvaluation record, @Param("example") SwTaskEvaluationExample example);

    int updateByExample(@Param("record") SwTaskEvaluation record, @Param("example") SwTaskEvaluationExample example);

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