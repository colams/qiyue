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
    @DeleteProvider(type=SwEmployeeSqlProvider.class, method="deleteByExample")
    int deleteByExample(SwEmployeeExample example);

    @Delete({
        "delete from sw_employee",
        "where id = #{id,jdbcType=INTEGER}"
    })
    int deleteByPrimaryKey(Integer id);

    @Insert({
        "insert into sw_employee (employee_no, assistant, ",
        "name, first_name, ",
        "last_name, gender, ",
        "department_id, post_id, ",
        "inner_email, outer_mail, ",
        "land_line, phone_number, ",
        "hire_date, status, ",
        "outer_work_years, outer_abc_years, ",
        "manager_level, datetime_lastchange, ",
        "is_complete)",
        "values (#{employeeNo,jdbcType=VARCHAR}, #{assistant,jdbcType=VARCHAR}, ",
        "#{name,jdbcType=VARCHAR}, #{firstName,jdbcType=VARCHAR}, ",
        "#{lastName,jdbcType=VARCHAR}, #{gender,jdbcType=VARCHAR}, ",
        "#{departmentId,jdbcType=INTEGER}, #{postId,jdbcType=INTEGER}, ",
        "#{innerEmail,jdbcType=VARCHAR}, #{outerMail,jdbcType=VARCHAR}, ",
        "#{landLine,jdbcType=VARCHAR}, #{phoneNumber,jdbcType=VARCHAR}, ",
        "#{hireDate,jdbcType=VARCHAR}, #{status,jdbcType=INTEGER}, ",
        "#{outerWorkYears,jdbcType=INTEGER}, #{outerAbcYears,jdbcType=INTEGER}, ",
        "#{managerLevel,jdbcType=INTEGER}, #{datetimeLastchange,jdbcType=TIMESTAMP}, ",
        "#{isComplete,jdbcType=INTEGER})"
    })
    @SelectKey(statement="SELECT LAST_INSERT_ID()", keyProperty="id", before=false, resultType=Integer.class)
    int insert(SwEmployee record);

    @InsertProvider(type=SwEmployeeSqlProvider.class, method="insertSelective")
    @SelectKey(statement="SELECT LAST_INSERT_ID()", keyProperty="id", before=false, resultType=Integer.class)
    int insertSelective(SwEmployee record);

    @SelectProvider(type=SwEmployeeSqlProvider.class, method="selectByExample")
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="employee_no", property="employeeNo", jdbcType=JdbcType.VARCHAR),
        @Result(column="assistant", property="assistant", jdbcType=JdbcType.VARCHAR),
        @Result(column="name", property="name", jdbcType=JdbcType.VARCHAR),
        @Result(column="first_name", property="firstName", jdbcType=JdbcType.VARCHAR),
        @Result(column="last_name", property="lastName", jdbcType=JdbcType.VARCHAR),
        @Result(column="gender", property="gender", jdbcType=JdbcType.VARCHAR),
        @Result(column="department_id", property="departmentId", jdbcType=JdbcType.INTEGER),
        @Result(column="post_id", property="postId", jdbcType=JdbcType.INTEGER),
        @Result(column="inner_email", property="innerEmail", jdbcType=JdbcType.VARCHAR),
        @Result(column="outer_mail", property="outerMail", jdbcType=JdbcType.VARCHAR),
        @Result(column="land_line", property="landLine", jdbcType=JdbcType.VARCHAR),
        @Result(column="phone_number", property="phoneNumber", jdbcType=JdbcType.VARCHAR),
        @Result(column="hire_date", property="hireDate", jdbcType=JdbcType.VARCHAR),
        @Result(column="status", property="status", jdbcType=JdbcType.INTEGER),
        @Result(column="outer_work_years", property="outerWorkYears", jdbcType=JdbcType.INTEGER),
        @Result(column="outer_abc_years", property="outerAbcYears", jdbcType=JdbcType.INTEGER),
        @Result(column="manager_level", property="managerLevel", jdbcType=JdbcType.INTEGER),
        @Result(column="datetime_lastchange", property="datetimeLastchange", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="is_complete", property="isComplete", jdbcType=JdbcType.INTEGER)
    })
    List<SwEmployee> selectByExampleWithRowbounds(SwEmployeeExample example, RowBounds rowBounds);

    @SelectProvider(type=SwEmployeeSqlProvider.class, method="selectByExample")
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="employee_no", property="employeeNo", jdbcType=JdbcType.VARCHAR),
        @Result(column="assistant", property="assistant", jdbcType=JdbcType.VARCHAR),
        @Result(column="name", property="name", jdbcType=JdbcType.VARCHAR),
        @Result(column="first_name", property="firstName", jdbcType=JdbcType.VARCHAR),
        @Result(column="last_name", property="lastName", jdbcType=JdbcType.VARCHAR),
        @Result(column="gender", property="gender", jdbcType=JdbcType.VARCHAR),
        @Result(column="department_id", property="departmentId", jdbcType=JdbcType.INTEGER),
        @Result(column="post_id", property="postId", jdbcType=JdbcType.INTEGER),
        @Result(column="inner_email", property="innerEmail", jdbcType=JdbcType.VARCHAR),
        @Result(column="outer_mail", property="outerMail", jdbcType=JdbcType.VARCHAR),
        @Result(column="land_line", property="landLine", jdbcType=JdbcType.VARCHAR),
        @Result(column="phone_number", property="phoneNumber", jdbcType=JdbcType.VARCHAR),
        @Result(column="hire_date", property="hireDate", jdbcType=JdbcType.VARCHAR),
        @Result(column="status", property="status", jdbcType=JdbcType.INTEGER),
        @Result(column="outer_work_years", property="outerWorkYears", jdbcType=JdbcType.INTEGER),
        @Result(column="outer_abc_years", property="outerAbcYears", jdbcType=JdbcType.INTEGER),
        @Result(column="manager_level", property="managerLevel", jdbcType=JdbcType.INTEGER),
        @Result(column="datetime_lastchange", property="datetimeLastchange", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="is_complete", property="isComplete", jdbcType=JdbcType.INTEGER)
    })
    List<SwEmployee> selectByExample(SwEmployeeExample example);

    @Select({
        "select",
        "id, employee_no, assistant, name, first_name, last_name, gender, department_id, ",
        "post_id, inner_email, outer_mail, land_line, phone_number, hire_date, status, ",
        "outer_work_years, outer_abc_years, manager_level, datetime_lastchange, is_complete",
        "from sw_employee",
        "where id = #{id,jdbcType=INTEGER}"
    })
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="employee_no", property="employeeNo", jdbcType=JdbcType.VARCHAR),
        @Result(column="assistant", property="assistant", jdbcType=JdbcType.VARCHAR),
        @Result(column="name", property="name", jdbcType=JdbcType.VARCHAR),
        @Result(column="first_name", property="firstName", jdbcType=JdbcType.VARCHAR),
        @Result(column="last_name", property="lastName", jdbcType=JdbcType.VARCHAR),
        @Result(column="gender", property="gender", jdbcType=JdbcType.VARCHAR),
        @Result(column="department_id", property="departmentId", jdbcType=JdbcType.INTEGER),
        @Result(column="post_id", property="postId", jdbcType=JdbcType.INTEGER),
        @Result(column="inner_email", property="innerEmail", jdbcType=JdbcType.VARCHAR),
        @Result(column="outer_mail", property="outerMail", jdbcType=JdbcType.VARCHAR),
        @Result(column="land_line", property="landLine", jdbcType=JdbcType.VARCHAR),
        @Result(column="phone_number", property="phoneNumber", jdbcType=JdbcType.VARCHAR),
        @Result(column="hire_date", property="hireDate", jdbcType=JdbcType.VARCHAR),
        @Result(column="status", property="status", jdbcType=JdbcType.INTEGER),
        @Result(column="outer_work_years", property="outerWorkYears", jdbcType=JdbcType.INTEGER),
        @Result(column="outer_abc_years", property="outerAbcYears", jdbcType=JdbcType.INTEGER),
        @Result(column="manager_level", property="managerLevel", jdbcType=JdbcType.INTEGER),
        @Result(column="datetime_lastchange", property="datetimeLastchange", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="is_complete", property="isComplete", jdbcType=JdbcType.INTEGER)
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
        "set employee_no = #{employeeNo,jdbcType=VARCHAR},",
          "assistant = #{assistant,jdbcType=VARCHAR},",
          "name = #{name,jdbcType=VARCHAR},",
          "first_name = #{firstName,jdbcType=VARCHAR},",
          "last_name = #{lastName,jdbcType=VARCHAR},",
          "gender = #{gender,jdbcType=VARCHAR},",
          "department_id = #{departmentId,jdbcType=INTEGER},",
          "post_id = #{postId,jdbcType=INTEGER},",
          "inner_email = #{innerEmail,jdbcType=VARCHAR},",
          "outer_mail = #{outerMail,jdbcType=VARCHAR},",
          "land_line = #{landLine,jdbcType=VARCHAR},",
          "phone_number = #{phoneNumber,jdbcType=VARCHAR},",
          "hire_date = #{hireDate,jdbcType=VARCHAR},",
          "status = #{status,jdbcType=INTEGER},",
          "outer_work_years = #{outerWorkYears,jdbcType=INTEGER},",
          "outer_abc_years = #{outerAbcYears,jdbcType=INTEGER},",
          "manager_level = #{managerLevel,jdbcType=INTEGER},",
          "datetime_lastchange = #{datetimeLastchange,jdbcType=TIMESTAMP},",
          "is_complete = #{isComplete,jdbcType=INTEGER}",
        "where id = #{id,jdbcType=INTEGER}"
    })
    int updateByPrimaryKey(SwEmployee record);
}