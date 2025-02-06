package com.foxconn.sw.data.mapper.auto;

import com.foxconn.sw.data.entity.TaskNoSeed;
import com.foxconn.sw.data.entity.TaskNoSeedExample;
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
public interface TaskNoSeedMapper {
    long countByExample(TaskNoSeedExample example);

    int deleteByExample(TaskNoSeedExample example);

    @Delete({
        "delete from task_no_seed",
        "where id = #{id,jdbcType=INTEGER}"
    })
    int deleteByPrimaryKey(Integer id);

    @Insert({
        "insert into task_no_seed (seed, status, ",
        "datetime_lastchange)",
        "values (#{seed,jdbcType=BIGINT}, #{status,jdbcType=INTEGER}, ",
        "#{datetimeLastchange,jdbcType=TIMESTAMP})"
    })
    @SelectKey(statement="SELECT LAST_INSERT_ID()", keyProperty="id", before=false, resultType=Integer.class)
    int insert(TaskNoSeed record);

    int insertSelective(TaskNoSeed record);

    List<TaskNoSeed> selectByExampleWithRowbounds(TaskNoSeedExample example, RowBounds rowBounds);

    List<TaskNoSeed> selectByExample(TaskNoSeedExample example);

    @Select({
        "select",
        "id, seed, status, datetime_lastchange",
        "from task_no_seed",
        "where id = #{id,jdbcType=INTEGER}"
    })
    @ResultMap("com.foxconn.sw.data.mapper.auto.TaskNoSeedMapper.BaseResultMap")
    TaskNoSeed selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") TaskNoSeed record, @Param("example") TaskNoSeedExample example);

    int updateByExample(@Param("record") TaskNoSeed record, @Param("example") TaskNoSeedExample example);

    int updateByPrimaryKeySelective(TaskNoSeed record);

    @Update({
        "update task_no_seed",
        "set seed = #{seed,jdbcType=BIGINT},",
          "status = #{status,jdbcType=INTEGER},",
          "datetime_lastchange = #{datetimeLastchange,jdbcType=TIMESTAMP}",
        "where id = #{id,jdbcType=INTEGER}"
    })
    int updateByPrimaryKey(TaskNoSeed record);
}