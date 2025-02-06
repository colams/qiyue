package com.foxconn.sw.data.mapper.auto;

import com.foxconn.sw.data.entity.SwProject;
import com.foxconn.sw.data.entity.SwProjectExample;
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
public interface SwProjectMapper {
    int deleteByExample(SwProjectExample example);

    @Delete({
        "delete from sw_project",
        "where id = #{id,jdbcType=INTEGER}"
    })
    int deleteByPrimaryKey(Integer id);

    @Insert({
        "insert into sw_project (years, project_code, ",
        "customer_name, full_name, ",
        "manufacturing_model, status, ",
        "rfq_time, customer, ",
        "customer_part_no, application, ",
        "operator, is_delete, ",
        "create_time, datetime_lastchange)",
        "values (#{years,jdbcType=INTEGER}, #{projectCode,jdbcType=VARCHAR}, ",
        "#{customerName,jdbcType=VARCHAR}, #{fullName,jdbcType=VARCHAR}, ",
        "#{manufacturingModel,jdbcType=VARCHAR}, #{status,jdbcType=VARCHAR}, ",
        "#{rfqTime,jdbcType=VARCHAR}, #{customer,jdbcType=VARCHAR}, ",
        "#{customerPartNo,jdbcType=VARCHAR}, #{application,jdbcType=VARCHAR}, ",
        "#{operator,jdbcType=VARCHAR}, #{isDelete,jdbcType=INTEGER}, ",
        "#{createTime,jdbcType=TIMESTAMP}, #{datetimeLastchange,jdbcType=TIMESTAMP})"
    })
    @SelectKey(statement="SELECT LAST_INSERT_ID()", keyProperty="id", before=false, resultType=Integer.class)
    int insert(SwProject record);

    int insertSelective(SwProject record);

    List<SwProject> selectByExampleWithRowbounds(SwProjectExample example, RowBounds rowBounds);

    List<SwProject> selectByExample(SwProjectExample example);

    @Select({
        "select",
        "id, years, project_code, customer_name, full_name, manufacturing_model, status, ",
        "rfq_time, customer, customer_part_no, application, operator, is_delete, create_time, ",
        "datetime_lastchange",
        "from sw_project",
        "where id = #{id,jdbcType=INTEGER}"
    })
    @ResultMap("com.foxconn.sw.data.mapper.auto.SwProjectMapper.BaseResultMap")
    SwProject selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") SwProject record, @Param("example") SwProjectExample example);

    int updateByExample(@Param("record") SwProject record, @Param("example") SwProjectExample example);

    int updateByPrimaryKeySelective(SwProject record);

    @Update({
        "update sw_project",
        "set years = #{years,jdbcType=INTEGER},",
          "project_code = #{projectCode,jdbcType=VARCHAR},",
          "customer_name = #{customerName,jdbcType=VARCHAR},",
          "full_name = #{fullName,jdbcType=VARCHAR},",
          "manufacturing_model = #{manufacturingModel,jdbcType=VARCHAR},",
          "status = #{status,jdbcType=VARCHAR},",
          "rfq_time = #{rfqTime,jdbcType=VARCHAR},",
          "customer = #{customer,jdbcType=VARCHAR},",
          "customer_part_no = #{customerPartNo,jdbcType=VARCHAR},",
          "application = #{application,jdbcType=VARCHAR},",
          "operator = #{operator,jdbcType=VARCHAR},",
          "is_delete = #{isDelete,jdbcType=INTEGER},",
          "create_time = #{createTime,jdbcType=TIMESTAMP},",
          "datetime_lastchange = #{datetimeLastchange,jdbcType=TIMESTAMP}",
        "where id = #{id,jdbcType=INTEGER}"
    })
    int updateByPrimaryKey(SwProject record);
}