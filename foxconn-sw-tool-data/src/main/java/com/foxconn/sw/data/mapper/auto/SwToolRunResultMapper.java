package com.foxconn.sw.data.mapper.auto;

import com.foxconn.sw.data.entity.SwToolRunResult;
import com.foxconn.sw.data.entity.SwToolRunResultExample;
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
public interface SwToolRunResultMapper {
    long countByExample(SwToolRunResultExample example);

    int deleteByExample(SwToolRunResultExample example);

    @Delete({
        "delete from sw_tool_run_result",
        "where id = #{id,jdbcType=INTEGER}"
    })
    int deleteByPrimaryKey(Integer id);

    @Insert({
        "insert into sw_tool_run_result (operator, tool_name, ",
        "run_result, intervals, ",
        "remark, create_time)",
        "values (#{operator,jdbcType=VARCHAR}, #{toolName,jdbcType=VARCHAR}, ",
        "#{runResult,jdbcType=VARCHAR}, #{intervals,jdbcType=BIGINT}, ",
        "#{remark,jdbcType=VARCHAR}, #{createTime,jdbcType=TIMESTAMP})"
    })
    @SelectKey(statement="SELECT LAST_INSERT_ID()", keyProperty="id", before=false, resultType=Integer.class)
    int insert(SwToolRunResult record);

    int insertSelective(SwToolRunResult record);

    List<SwToolRunResult> selectByExampleWithRowbounds(SwToolRunResultExample example, RowBounds rowBounds);

    List<SwToolRunResult> selectByExample(SwToolRunResultExample example);

    @Select({
        "select",
        "id, operator, tool_name, run_result, intervals, remark, create_time",
        "from sw_tool_run_result",
        "where id = #{id,jdbcType=INTEGER}"
    })
    @ResultMap("com.foxconn.sw.data.mapper.auto.SwToolRunResultMapper.BaseResultMap")
    SwToolRunResult selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") SwToolRunResult record, @Param("example") SwToolRunResultExample example);

    int updateByExample(@Param("record") SwToolRunResult record, @Param("example") SwToolRunResultExample example);

    int updateByPrimaryKeySelective(SwToolRunResult record);

    @Update({
        "update sw_tool_run_result",
        "set operator = #{operator,jdbcType=VARCHAR},",
          "tool_name = #{toolName,jdbcType=VARCHAR},",
          "run_result = #{runResult,jdbcType=VARCHAR},",
          "intervals = #{intervals,jdbcType=BIGINT},",
          "remark = #{remark,jdbcType=VARCHAR},",
          "create_time = #{createTime,jdbcType=TIMESTAMP}",
        "where id = #{id,jdbcType=INTEGER}"
    })
    int updateByPrimaryKey(SwToolRunResult record);
}