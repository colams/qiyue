package com.foxconn.sw.data.mapper.auto;

import com.foxconn.sw.data.entity.SwProject;
import com.foxconn.sw.data.entity.SwProjectExample;
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
public interface SwProjectMapper {
    @DeleteProvider(type=SwProjectSqlProvider.class, method="deleteByExample")
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

    @InsertProvider(type=SwProjectSqlProvider.class, method="insertSelective")
    @SelectKey(statement="SELECT LAST_INSERT_ID()", keyProperty="id", before=false, resultType=Integer.class)
    int insertSelective(SwProject record);

    @SelectProvider(type=SwProjectSqlProvider.class, method="selectByExample")
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="years", property="years", jdbcType=JdbcType.INTEGER),
        @Result(column="project_code", property="projectCode", jdbcType=JdbcType.VARCHAR),
        @Result(column="customer_name", property="customerName", jdbcType=JdbcType.VARCHAR),
        @Result(column="full_name", property="fullName", jdbcType=JdbcType.VARCHAR),
        @Result(column="manufacturing_model", property="manufacturingModel", jdbcType=JdbcType.VARCHAR),
        @Result(column="status", property="status", jdbcType=JdbcType.VARCHAR),
        @Result(column="rfq_time", property="rfqTime", jdbcType=JdbcType.VARCHAR),
        @Result(column="customer", property="customer", jdbcType=JdbcType.VARCHAR),
        @Result(column="customer_part_no", property="customerPartNo", jdbcType=JdbcType.VARCHAR),
        @Result(column="application", property="application", jdbcType=JdbcType.VARCHAR),
        @Result(column="operator", property="operator", jdbcType=JdbcType.VARCHAR),
        @Result(column="is_delete", property="isDelete", jdbcType=JdbcType.INTEGER),
        @Result(column="create_time", property="createTime", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="datetime_lastchange", property="datetimeLastchange", jdbcType=JdbcType.TIMESTAMP)
    })
    List<SwProject> selectByExampleWithRowbounds(SwProjectExample example, RowBounds rowBounds);

    @SelectProvider(type=SwProjectSqlProvider.class, method="selectByExample")
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="years", property="years", jdbcType=JdbcType.INTEGER),
        @Result(column="project_code", property="projectCode", jdbcType=JdbcType.VARCHAR),
        @Result(column="customer_name", property="customerName", jdbcType=JdbcType.VARCHAR),
        @Result(column="full_name", property="fullName", jdbcType=JdbcType.VARCHAR),
        @Result(column="manufacturing_model", property="manufacturingModel", jdbcType=JdbcType.VARCHAR),
        @Result(column="status", property="status", jdbcType=JdbcType.VARCHAR),
        @Result(column="rfq_time", property="rfqTime", jdbcType=JdbcType.VARCHAR),
        @Result(column="customer", property="customer", jdbcType=JdbcType.VARCHAR),
        @Result(column="customer_part_no", property="customerPartNo", jdbcType=JdbcType.VARCHAR),
        @Result(column="application", property="application", jdbcType=JdbcType.VARCHAR),
        @Result(column="operator", property="operator", jdbcType=JdbcType.VARCHAR),
        @Result(column="is_delete", property="isDelete", jdbcType=JdbcType.INTEGER),
        @Result(column="create_time", property="createTime", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="datetime_lastchange", property="datetimeLastchange", jdbcType=JdbcType.TIMESTAMP)
    })
    List<SwProject> selectByExample(SwProjectExample example);

    @Select({
        "select",
        "id, years, project_code, customer_name, full_name, manufacturing_model, status, ",
        "rfq_time, customer, customer_part_no, application, operator, is_delete, create_time, ",
        "datetime_lastchange",
        "from sw_project",
        "where id = #{id,jdbcType=INTEGER}"
    })
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="years", property="years", jdbcType=JdbcType.INTEGER),
        @Result(column="project_code", property="projectCode", jdbcType=JdbcType.VARCHAR),
        @Result(column="customer_name", property="customerName", jdbcType=JdbcType.VARCHAR),
        @Result(column="full_name", property="fullName", jdbcType=JdbcType.VARCHAR),
        @Result(column="manufacturing_model", property="manufacturingModel", jdbcType=JdbcType.VARCHAR),
        @Result(column="status", property="status", jdbcType=JdbcType.VARCHAR),
        @Result(column="rfq_time", property="rfqTime", jdbcType=JdbcType.VARCHAR),
        @Result(column="customer", property="customer", jdbcType=JdbcType.VARCHAR),
        @Result(column="customer_part_no", property="customerPartNo", jdbcType=JdbcType.VARCHAR),
        @Result(column="application", property="application", jdbcType=JdbcType.VARCHAR),
        @Result(column="operator", property="operator", jdbcType=JdbcType.VARCHAR),
        @Result(column="is_delete", property="isDelete", jdbcType=JdbcType.INTEGER),
        @Result(column="create_time", property="createTime", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="datetime_lastchange", property="datetimeLastchange", jdbcType=JdbcType.TIMESTAMP)
    })
    SwProject selectByPrimaryKey(Integer id);

    @UpdateProvider(type=SwProjectSqlProvider.class, method="updateByExampleSelective")
    int updateByExampleSelective(@Param("record") SwProject record, @Param("example") SwProjectExample example);

    @UpdateProvider(type=SwProjectSqlProvider.class, method="updateByExample")
    int updateByExample(@Param("record") SwProject record, @Param("example") SwProjectExample example);

    @UpdateProvider(type=SwProjectSqlProvider.class, method="updateByPrimaryKeySelective")
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