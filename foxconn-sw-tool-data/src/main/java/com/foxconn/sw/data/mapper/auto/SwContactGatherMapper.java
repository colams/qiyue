package com.foxconn.sw.data.mapper.auto;

import com.foxconn.sw.data.entity.SwContactGather;
import com.foxconn.sw.data.entity.SwContactGatherExample;
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
public interface SwContactGatherMapper {
    @DeleteProvider(type=SwContactGatherSqlProvider.class, method="deleteByExample")
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

    @InsertProvider(type=SwContactGatherSqlProvider.class, method="insertSelective")
    @SelectKey(statement="SELECT LAST_INSERT_ID()", keyProperty="id", before=false, resultType=Integer.class)
    int insertSelective(SwContactGather record);

    @SelectProvider(type=SwContactGatherSqlProvider.class, method="selectByExample")
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="employee_no", property="employeeNo", jdbcType=JdbcType.VARCHAR),
        @Result(column="gather_employee_no", property="gatherEmployeeNo", jdbcType=JdbcType.VARCHAR),
        @Result(column="status", property="status", jdbcType=JdbcType.INTEGER),
        @Result(column="create_time", property="createTime", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="datetime_lastchange", property="datetimeLastchange", jdbcType=JdbcType.TIMESTAMP)
    })
    List<SwContactGather> selectByExampleWithRowbounds(SwContactGatherExample example, RowBounds rowBounds);

    @SelectProvider(type=SwContactGatherSqlProvider.class, method="selectByExample")
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="employee_no", property="employeeNo", jdbcType=JdbcType.VARCHAR),
        @Result(column="gather_employee_no", property="gatherEmployeeNo", jdbcType=JdbcType.VARCHAR),
        @Result(column="status", property="status", jdbcType=JdbcType.INTEGER),
        @Result(column="create_time", property="createTime", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="datetime_lastchange", property="datetimeLastchange", jdbcType=JdbcType.TIMESTAMP)
    })
    List<SwContactGather> selectByExample(SwContactGatherExample example);

    @Select({
        "select",
        "id, employee_no, gather_employee_no, status, create_time, datetime_lastchange",
        "from sw_contact_gather",
        "where id = #{id,jdbcType=INTEGER}"
    })
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="employee_no", property="employeeNo", jdbcType=JdbcType.VARCHAR),
        @Result(column="gather_employee_no", property="gatherEmployeeNo", jdbcType=JdbcType.VARCHAR),
        @Result(column="status", property="status", jdbcType=JdbcType.INTEGER),
        @Result(column="create_time", property="createTime", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="datetime_lastchange", property="datetimeLastchange", jdbcType=JdbcType.TIMESTAMP)
    })
    SwContactGather selectByPrimaryKey(Integer id);

    @UpdateProvider(type=SwContactGatherSqlProvider.class, method="updateByExampleSelective")
    int updateByExampleSelective(@Param("record") SwContactGather record, @Param("example") SwContactGatherExample example);

    @UpdateProvider(type=SwContactGatherSqlProvider.class, method="updateByExample")
    int updateByExample(@Param("record") SwContactGather record, @Param("example") SwContactGatherExample example);

    @UpdateProvider(type=SwContactGatherSqlProvider.class, method="updateByPrimaryKeySelective")
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