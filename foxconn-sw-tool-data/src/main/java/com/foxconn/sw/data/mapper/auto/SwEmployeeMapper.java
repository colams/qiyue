package com.foxconn.sw.data.mapper.auto;

import com.foxconn.sw.data.entity.SwEmployee;
import com.foxconn.sw.data.entity.SwEmployeeExample;
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
public interface SwEmployeeMapper {
    @SelectProvider(type=SwEmployeeSqlProvider.class, method="countByExample")
    long countByExample(SwEmployeeExample example);

    @DeleteProvider(type=SwEmployeeSqlProvider.class, method="deleteByExample")
    int deleteByExample(SwEmployeeExample example);

    @Delete({
        "delete from sw_employee",
        "where id = #{id,jdbcType=INTEGER}"
    })
    int deleteByPrimaryKey(Integer id);

    @Insert({
        "insert into sw_employee (name, employee_no, ",
        "department_id, job_title, ",
        "email, phone_number, ",
        "hire_date, status, ",
        "position_start_date, position_end_date, ",
        "datetime_lastchange)",
        "values (#{name,jdbcType=VARCHAR}, #{employeeNo,jdbcType=VARCHAR}, ",
        "#{departmentId,jdbcType=INTEGER}, #{jobTitle,jdbcType=VARCHAR}, ",
        "#{email,jdbcType=VARCHAR}, #{phoneNumber,jdbcType=VARCHAR}, ",
        "#{hireDate,jdbcType=VARCHAR}, #{status,jdbcType=INTEGER}, ",
        "#{positionStartDate,jdbcType=VARCHAR}, #{positionEndDate,jdbcType=VARCHAR}, ",
        "#{datetimeLastchange,jdbcType=TIMESTAMP})"
    })
    @SelectKey(statement="SELECT LAST_INSERT_ID()", keyProperty="id", before=false, resultType=Integer.class)
    int insert(SwEmployee record);

    @InsertProvider(type=SwEmployeeSqlProvider.class, method="insertSelective")
    @SelectKey(statement="SELECT LAST_INSERT_ID()", keyProperty="id", before=false, resultType=Integer.class)
    int insertSelective(SwEmployee record);

    @SelectProvider(type=SwEmployeeSqlProvider.class, method="selectByExample")
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="name", property="name", jdbcType=JdbcType.VARCHAR),
        @Result(column="employee_no", property="employeeNo", jdbcType=JdbcType.VARCHAR),
        @Result(column="department_id", property="departmentId", jdbcType=JdbcType.INTEGER),
        @Result(column="job_title", property="jobTitle", jdbcType=JdbcType.VARCHAR),
        @Result(column="email", property="email", jdbcType=JdbcType.VARCHAR),
        @Result(column="phone_number", property="phoneNumber", jdbcType=JdbcType.VARCHAR),
        @Result(column="hire_date", property="hireDate", jdbcType=JdbcType.VARCHAR),
        @Result(column="status", property="status", jdbcType=JdbcType.INTEGER),
        @Result(column="position_start_date", property="positionStartDate", jdbcType=JdbcType.VARCHAR),
        @Result(column="position_end_date", property="positionEndDate", jdbcType=JdbcType.VARCHAR),
        @Result(column="datetime_lastchange", property="datetimeLastchange", jdbcType=JdbcType.TIMESTAMP)
    })
    List<SwEmployee> selectByExampleWithRowbounds(SwEmployeeExample example, RowBounds rowBounds);

    @SelectProvider(type=SwEmployeeSqlProvider.class, method="selectByExample")
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="name", property="name", jdbcType=JdbcType.VARCHAR),
        @Result(column="employee_no", property="employeeNo", jdbcType=JdbcType.VARCHAR),
        @Result(column="department_id", property="departmentId", jdbcType=JdbcType.INTEGER),
        @Result(column="job_title", property="jobTitle", jdbcType=JdbcType.VARCHAR),
        @Result(column="email", property="email", jdbcType=JdbcType.VARCHAR),
        @Result(column="phone_number", property="phoneNumber", jdbcType=JdbcType.VARCHAR),
        @Result(column="hire_date", property="hireDate", jdbcType=JdbcType.VARCHAR),
        @Result(column="status", property="status", jdbcType=JdbcType.INTEGER),
        @Result(column="position_start_date", property="positionStartDate", jdbcType=JdbcType.VARCHAR),
        @Result(column="position_end_date", property="positionEndDate", jdbcType=JdbcType.VARCHAR),
        @Result(column="datetime_lastchange", property="datetimeLastchange", jdbcType=JdbcType.TIMESTAMP)
    })
    List<SwEmployee> selectByExample(SwEmployeeExample example);

    @Select({
        "select",
        "id, name, employee_no, department_id, job_title, email, phone_number, hire_date, ",
        "status, position_start_date, position_end_date, datetime_lastchange",
        "from sw_employee",
        "where id = #{id,jdbcType=INTEGER}"
    })
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="name", property="name", jdbcType=JdbcType.VARCHAR),
        @Result(column="employee_no", property="employeeNo", jdbcType=JdbcType.VARCHAR),
        @Result(column="department_id", property="departmentId", jdbcType=JdbcType.INTEGER),
        @Result(column="job_title", property="jobTitle", jdbcType=JdbcType.VARCHAR),
        @Result(column="email", property="email", jdbcType=JdbcType.VARCHAR),
        @Result(column="phone_number", property="phoneNumber", jdbcType=JdbcType.VARCHAR),
        @Result(column="hire_date", property="hireDate", jdbcType=JdbcType.VARCHAR),
        @Result(column="status", property="status", jdbcType=JdbcType.INTEGER),
        @Result(column="position_start_date", property="positionStartDate", jdbcType=JdbcType.VARCHAR),
        @Result(column="position_end_date", property="positionEndDate", jdbcType=JdbcType.VARCHAR),
        @Result(column="datetime_lastchange", property="datetimeLastchange", jdbcType=JdbcType.TIMESTAMP)
    })
    SwEmployee selectByPrimaryKey(Integer id);

    @UpdateProvider(type=SwEmployeeSqlProvider.class, method="updateByExampleSelective")
    int updateByExampleSelective(@Param("record") SwEmployee record, @Param("example") SwEmployeeExample example);

    @UpdateProvider(type=SwEmployeeSqlProvider.class, method="updateByExample")
    int updateByExample(@Param("record") SwEmployee record, @Param("example") SwEmployeeExample example);

    @UpdateProvider(type=SwEmployeeSqlProvider.class, method="updateByPrimaryKeySelective")
    int updateByPrimaryKeySelective(SwEmployee record);

    @Update({
        "update sw_employee",
        "set name = #{name,jdbcType=VARCHAR},",
          "employee_no = #{employeeNo,jdbcType=VARCHAR},",
          "department_id = #{departmentId,jdbcType=INTEGER},",
          "job_title = #{jobTitle,jdbcType=VARCHAR},",
          "email = #{email,jdbcType=VARCHAR},",
          "phone_number = #{phoneNumber,jdbcType=VARCHAR},",
          "hire_date = #{hireDate,jdbcType=VARCHAR},",
          "status = #{status,jdbcType=INTEGER},",
          "position_start_date = #{positionStartDate,jdbcType=VARCHAR},",
          "position_end_date = #{positionEndDate,jdbcType=VARCHAR},",
          "datetime_lastchange = #{datetimeLastchange,jdbcType=TIMESTAMP}",
        "where id = #{id,jdbcType=INTEGER}"
    })
    int updateByPrimaryKey(SwEmployee record);
}