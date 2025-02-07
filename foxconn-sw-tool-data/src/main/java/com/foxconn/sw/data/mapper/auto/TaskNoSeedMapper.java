package com.foxconn.sw.data.mapper.auto;

import com.foxconn.sw.data.entity.TaskNoSeed;
import com.foxconn.sw.data.entity.TaskNoSeedExample;
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
public interface TaskNoSeedMapper {
    @DeleteProvider(type=TaskNoSeedSqlProvider.class, method="deleteByExample")
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

    @InsertProvider(type=TaskNoSeedSqlProvider.class, method="insertSelective")
    @SelectKey(statement="SELECT LAST_INSERT_ID()", keyProperty="id", before=false, resultType=Integer.class)
    int insertSelective(TaskNoSeed record);

    @SelectProvider(type=TaskNoSeedSqlProvider.class, method="selectByExample")
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="seed", property="seed", jdbcType=JdbcType.BIGINT),
        @Result(column="status", property="status", jdbcType=JdbcType.INTEGER),
        @Result(column="datetime_lastchange", property="datetimeLastchange", jdbcType=JdbcType.TIMESTAMP)
    })
    List<TaskNoSeed> selectByExampleWithRowbounds(TaskNoSeedExample example, RowBounds rowBounds);

    @SelectProvider(type=TaskNoSeedSqlProvider.class, method="selectByExample")
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="seed", property="seed", jdbcType=JdbcType.BIGINT),
        @Result(column="status", property="status", jdbcType=JdbcType.INTEGER),
        @Result(column="datetime_lastchange", property="datetimeLastchange", jdbcType=JdbcType.TIMESTAMP)
    })
    List<TaskNoSeed> selectByExample(TaskNoSeedExample example);

    @Select({
        "select",
        "id, seed, status, datetime_lastchange",
        "from task_no_seed",
        "where id = #{id,jdbcType=INTEGER}"
    })
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="seed", property="seed", jdbcType=JdbcType.BIGINT),
        @Result(column="status", property="status", jdbcType=JdbcType.INTEGER),
        @Result(column="datetime_lastchange", property="datetimeLastchange", jdbcType=JdbcType.TIMESTAMP)
    })
    TaskNoSeed selectByPrimaryKey(Integer id);

    @UpdateProvider(type=TaskNoSeedSqlProvider.class, method="updateByExampleSelective")
    int updateByExampleSelective(@Param("record") TaskNoSeed record, @Param("example") TaskNoSeedExample example);

    @UpdateProvider(type=TaskNoSeedSqlProvider.class, method="updateByExample")
    int updateByExample(@Param("record") TaskNoSeed record, @Param("example") TaskNoSeedExample example);

    @UpdateProvider(type=TaskNoSeedSqlProvider.class, method="updateByPrimaryKeySelective")
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