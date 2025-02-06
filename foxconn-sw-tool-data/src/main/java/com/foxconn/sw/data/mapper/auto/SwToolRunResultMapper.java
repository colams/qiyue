package com.foxconn.sw.data.mapper.auto;

import com.foxconn.sw.data.entity.SwToolRunResult;
import com.foxconn.sw.data.entity.SwToolRunResultExample;
import java.util.List;
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
public interface SwToolRunResultMapper {
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

    @InsertProvider(type=SwToolRunResultSqlProvider.class, method="insertSelective")
    @SelectKey(statement="SELECT LAST_INSERT_ID()", keyProperty="id", before=false, resultType=Integer.class)
    int insertSelective(SwToolRunResult record);

    @SelectProvider(type=SwToolRunResultSqlProvider.class, method="selectByExample")
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="operator", property="operator", jdbcType=JdbcType.VARCHAR),
        @Result(column="tool_name", property="toolName", jdbcType=JdbcType.VARCHAR),
        @Result(column="run_result", property="runResult", jdbcType=JdbcType.VARCHAR),
        @Result(column="intervals", property="intervals", jdbcType=JdbcType.BIGINT),
        @Result(column="remark", property="remark", jdbcType=JdbcType.VARCHAR),
        @Result(column="create_time", property="createTime", jdbcType=JdbcType.TIMESTAMP)
    })
    List<SwToolRunResult> selectByExampleWithRowbounds(SwToolRunResultExample example, RowBounds rowBounds);

    @SelectProvider(type=SwToolRunResultSqlProvider.class, method="selectByExample")
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="operator", property="operator", jdbcType=JdbcType.VARCHAR),
        @Result(column="tool_name", property="toolName", jdbcType=JdbcType.VARCHAR),
        @Result(column="run_result", property="runResult", jdbcType=JdbcType.VARCHAR),
        @Result(column="intervals", property="intervals", jdbcType=JdbcType.BIGINT),
        @Result(column="remark", property="remark", jdbcType=JdbcType.VARCHAR),
        @Result(column="create_time", property="createTime", jdbcType=JdbcType.TIMESTAMP)
    })
    List<SwToolRunResult> selectByExample(SwToolRunResultExample example);

    @Select({
        "select",
        "id, operator, tool_name, run_result, intervals, remark, create_time",
        "from sw_tool_run_result",
        "where id = #{id,jdbcType=INTEGER}"
    })
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="operator", property="operator", jdbcType=JdbcType.VARCHAR),
        @Result(column="tool_name", property="toolName", jdbcType=JdbcType.VARCHAR),
        @Result(column="run_result", property="runResult", jdbcType=JdbcType.VARCHAR),
        @Result(column="intervals", property="intervals", jdbcType=JdbcType.BIGINT),
        @Result(column="remark", property="remark", jdbcType=JdbcType.VARCHAR),
        @Result(column="create_time", property="createTime", jdbcType=JdbcType.TIMESTAMP)
    })
    SwToolRunResult selectByPrimaryKey(Integer id);

    @UpdateProvider(type=SwToolRunResultSqlProvider.class, method="updateByExampleSelective")
    int updateByExampleSelective(@Param("record") SwToolRunResult record, @Param("example") SwToolRunResultExample example);

    @UpdateProvider(type=SwToolRunResultSqlProvider.class, method="updateByExample")
    int updateByExample(@Param("record") SwToolRunResult record, @Param("example") SwToolRunResultExample example);

    @UpdateProvider(type=SwToolRunResultSqlProvider.class, method="updateByPrimaryKeySelective")
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