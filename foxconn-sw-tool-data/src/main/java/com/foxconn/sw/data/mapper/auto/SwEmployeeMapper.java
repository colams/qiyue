package com.foxconn.sw.data.mapper.auto;

import com.foxconn.sw.data.entity.SwEmployee;
import com.foxconn.sw.data.entity.SwEmployeeExample;
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
public interface SwEmployeeMapper {
    long countByExample(SwEmployeeExample example);

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

    int insertSelective(SwEmployee record);

    List<SwEmployee> selectByExampleWithRowbounds(SwEmployeeExample example, RowBounds rowBounds);

    List<SwEmployee> selectByExample(SwEmployeeExample example);

    @Select({
        "select",
        "id, employee_no, assistant, name, first_name, last_name, gender, department_id, ",
        "post_id, inner_email, outer_mail, land_line, phone_number, hire_date, status, ",
        "outer_work_years, outer_abc_years, manager_level, datetime_lastchange, is_complete",
        "from sw_employee",
        "where id = #{id,jdbcType=INTEGER}"
    })
    @ResultMap("com.foxconn.sw.data.mapper.auto.SwEmployeeMapper.BaseResultMap")
    SwEmployee selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") SwEmployee record, @Param("example") SwEmployeeExample example);

    int updateByExample(@Param("record") SwEmployee record, @Param("example") SwEmployeeExample example);

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