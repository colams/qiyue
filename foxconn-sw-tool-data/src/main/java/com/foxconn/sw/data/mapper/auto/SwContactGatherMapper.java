package com.foxconn.sw.data.mapper.auto;

import com.foxconn.sw.data.entity.SwContactGather;
import com.foxconn.sw.data.entity.SwContactGatherExample;
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
public interface SwContactGatherMapper {
    long countByExample(SwContactGatherExample example);

    int deleteByExample(SwContactGatherExample example);

    @Delete({
        "delete from sw_contact_gather",
        "where id = #{id,jdbcType=INTEGER}"
    })
    int deleteByPrimaryKey(Integer id);

    @Insert({
        "insert into sw_contact_gather (employee_no, gather_employee_no, ",
        "status, create_time, ",
        "datetime_lastchange)",
        "values (#{employeeNo,jdbcType=VARCHAR}, #{gatherEmployeeNo,jdbcType=VARCHAR}, ",
        "#{status,jdbcType=INTEGER}, #{createTime,jdbcType=TIMESTAMP}, ",
        "#{datetimeLastchange,jdbcType=TIMESTAMP})"
    })
    @SelectKey(statement="SELECT LAST_INSERT_ID()", keyProperty="id", before=false, resultType=Integer.class)
    int insert(SwContactGather record);

    int insertSelective(SwContactGather record);

    List<SwContactGather> selectByExampleWithRowbounds(SwContactGatherExample example, RowBounds rowBounds);

    List<SwContactGather> selectByExample(SwContactGatherExample example);

    @Select({
        "select",
        "id, employee_no, gather_employee_no, status, create_time, datetime_lastchange",
        "from sw_contact_gather",
        "where id = #{id,jdbcType=INTEGER}"
    })
    @ResultMap("com.foxconn.sw.data.mapper.auto.SwContactGatherMapper.BaseResultMap")
    SwContactGather selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") SwContactGather record, @Param("example") SwContactGatherExample example);

    int updateByExample(@Param("record") SwContactGather record, @Param("example") SwContactGatherExample example);

    int updateByPrimaryKeySelective(SwContactGather record);

    @Update({
        "update sw_contact_gather",
        "set employee_no = #{employeeNo,jdbcType=VARCHAR},",
          "gather_employee_no = #{gatherEmployeeNo,jdbcType=VARCHAR},",
          "status = #{status,jdbcType=INTEGER},",
          "create_time = #{createTime,jdbcType=TIMESTAMP},",
          "datetime_lastchange = #{datetimeLastchange,jdbcType=TIMESTAMP}",
        "where id = #{id,jdbcType=INTEGER}"
    })
    int updateByPrimaryKey(SwContactGather record);
}