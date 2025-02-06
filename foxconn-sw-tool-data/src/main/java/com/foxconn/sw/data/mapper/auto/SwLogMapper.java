package com.foxconn.sw.data.mapper.auto;

import com.foxconn.sw.data.entity.SwLog;
import com.foxconn.sw.data.entity.SwLogExample;
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
public interface SwLogMapper {
    long countByExample(SwLogExample example);

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

    int insertSelective(SwLog record);

    List<SwLog> selectByExampleWithRowbounds(SwLogExample example, RowBounds rowBounds);

    List<SwLog> selectByExample(SwLogExample example);

    @Select({
        "select",
        "id, operator, ip, operateType, remark, intervals, create_time",
        "from sw_log",
        "where id = #{id,jdbcType=INTEGER}"
    })
    @ResultMap("com.foxconn.sw.data.mapper.auto.SwLogMapper.BaseResultMap")
    SwLog selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") SwLog record, @Param("example") SwLogExample example);

    int updateByExample(@Param("record") SwLog record, @Param("example") SwLogExample example);

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