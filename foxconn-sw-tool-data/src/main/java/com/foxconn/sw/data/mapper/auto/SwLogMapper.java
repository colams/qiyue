package com.foxconn.sw.data.mapper.auto;

import com.foxconn.sw.data.entity.SwLog;
import com.foxconn.sw.data.entity.SwLogExample;
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
public interface SwLogMapper {
    @DeleteProvider(type=SwLogSqlProvider.class, method="deleteByExample")
    int deleteByExample(SwLogExample example);

    @Delete({
        "delete from sw_log",
        "where id = #{id,jdbcType=INTEGER}"
    })
    int deleteByPrimaryKey(Integer id);

    @Insert({
        "insert into sw_log (operator, ip, ",
        "operateType, remark, ",
        "intervals, create_time)",
        "values (#{operator,jdbcType=VARCHAR}, #{ip,jdbcType=VARCHAR}, ",
        "#{operatetype,jdbcType=VARCHAR}, #{remark,jdbcType=VARCHAR}, ",
        "#{intervals,jdbcType=BIGINT}, #{createTime,jdbcType=TIMESTAMP})"
    })
    @SelectKey(statement="SELECT LAST_INSERT_ID()", keyProperty="id", before=false, resultType=Integer.class)
    int insert(SwLog record);

    @InsertProvider(type=SwLogSqlProvider.class, method="insertSelective")
    @SelectKey(statement="SELECT LAST_INSERT_ID()", keyProperty="id", before=false, resultType=Integer.class)
    int insertSelective(SwLog record);

    @SelectProvider(type=SwLogSqlProvider.class, method="selectByExample")
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="operator", property="operator", jdbcType=JdbcType.VARCHAR),
        @Result(column="ip", property="ip", jdbcType=JdbcType.VARCHAR),
        @Result(column="operateType", property="operatetype", jdbcType=JdbcType.VARCHAR),
        @Result(column="remark", property="remark", jdbcType=JdbcType.VARCHAR),
        @Result(column="intervals", property="intervals", jdbcType=JdbcType.BIGINT),
        @Result(column="create_time", property="createTime", jdbcType=JdbcType.TIMESTAMP)
    })
    List<SwLog> selectByExampleWithRowbounds(SwLogExample example, RowBounds rowBounds);

    @SelectProvider(type=SwLogSqlProvider.class, method="selectByExample")
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="operator", property="operator", jdbcType=JdbcType.VARCHAR),
        @Result(column="ip", property="ip", jdbcType=JdbcType.VARCHAR),
        @Result(column="operateType", property="operatetype", jdbcType=JdbcType.VARCHAR),
        @Result(column="remark", property="remark", jdbcType=JdbcType.VARCHAR),
        @Result(column="intervals", property="intervals", jdbcType=JdbcType.BIGINT),
        @Result(column="create_time", property="createTime", jdbcType=JdbcType.TIMESTAMP)
    })
    List<SwLog> selectByExample(SwLogExample example);

    @Select({
        "select",
        "id, operator, ip, operateType, remark, intervals, create_time",
        "from sw_log",
        "where id = #{id,jdbcType=INTEGER}"
    })
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="operator", property="operator", jdbcType=JdbcType.VARCHAR),
        @Result(column="ip", property="ip", jdbcType=JdbcType.VARCHAR),
        @Result(column="operateType", property="operatetype", jdbcType=JdbcType.VARCHAR),
        @Result(column="remark", property="remark", jdbcType=JdbcType.VARCHAR),
        @Result(column="intervals", property="intervals", jdbcType=JdbcType.BIGINT),
        @Result(column="create_time", property="createTime", jdbcType=JdbcType.TIMESTAMP)
    })
    SwLog selectByPrimaryKey(Integer id);

    @UpdateProvider(type=SwLogSqlProvider.class, method="updateByExampleSelective")
    int updateByExampleSelective(@Param("record") SwLog record, @Param("example") SwLogExample example);

    @UpdateProvider(type=SwLogSqlProvider.class, method="updateByExample")
    int updateByExample(@Param("record") SwLog record, @Param("example") SwLogExample example);

    @UpdateProvider(type=SwLogSqlProvider.class, method="updateByPrimaryKeySelective")
    int updateByPrimaryKeySelective(SwLog record);

    @Update({
        "update sw_log",
        "set operator = #{operator,jdbcType=VARCHAR},",
          "ip = #{ip,jdbcType=VARCHAR},",
          "operateType = #{operatetype,jdbcType=VARCHAR},",
          "remark = #{remark,jdbcType=VARCHAR},",
          "intervals = #{intervals,jdbcType=BIGINT},",
          "create_time = #{createTime,jdbcType=TIMESTAMP}",
        "where id = #{id,jdbcType=INTEGER}"
    })
    int updateByPrimaryKey(SwLog record);
}