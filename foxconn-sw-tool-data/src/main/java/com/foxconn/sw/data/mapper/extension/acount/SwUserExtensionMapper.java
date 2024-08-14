package com.foxconn.sw.data.mapper.extension.acount;

import com.foxconn.sw.data.dto.entity.acount.EmployeeParams;
import com.foxconn.sw.data.dto.entity.acount.EmployeeVo;
import com.foxconn.sw.data.dto.entity.acount.UserInfo;
import com.foxconn.sw.data.dto.entity.acount.UserParams;
import com.foxconn.sw.data.mapper.auto.SwUserMapper;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.type.JdbcType;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SwUserExtensionMapper extends SwUserMapper {
    @Select({"select su.employee_no,se.name,se.job_title,sd.name as departName \n" +
            "from sw_user su\n" +
            "    left join sw_employee se on su.employee_no=se.employee_no\n" +
            "    left join sw_department sd on se.department_id=sd.id\n" +
            "where su.employee_no=#{employeeNo,jdbcType=VARCHAR} " +
            "limit 1 "
    })
    @Results({
            @Result(column = "employee_no", property = "employeeNo", jdbcType = JdbcType.VARCHAR),
            @Result(column = "name", property = "employeeName", jdbcType = JdbcType.VARCHAR),
            @Result(column = "job_title", property = "jobTitle", jdbcType = JdbcType.VARCHAR),
            @Result(column = "departName", property = "departName", jdbcType = JdbcType.VARCHAR),
    })
    UserInfo queryUserInfo(String employeeNo);

    @Select({"<script> " +
            "select employee_no,name " +
            "from sw_employee " +
            "where 1=1 " +
            "<if test='employeeNo!=null and employeeNo!=\"\"' >" +
            " and employee_no=#{employeeNo,jdbcType=VARCHAR} " +
            "</if> " +
            "<if test='name!=null and name!=\"\"' >" +
            " and name like CONCAT('%', #{name,jdbcType=VARCHAR}, '%') " +
            "</if> " +
            "limit 15 " +
            " </script> "
    })
    @Results({
            @Result(column = "employee_no", property = "employeeNo", jdbcType = JdbcType.VARCHAR),
            @Result(column = "name", property = "name", jdbcType = JdbcType.VARCHAR),
    })
    List<EmployeeVo> queryEmployees(EmployeeParams params);

    @Select({"<script> " +
            "select su.employee_no,se.name,se.job_title,se.department_id\n" +
            "from sw_user su\n" +
            "    left join sw_employee se on su.employee_no=se.employee_no " +
            "where 1=1 " +
            "<if test='employeeNo!=null and employeeNo!=\"\"' >",
            " and employee_no=#{employeeNo,jdbcType=VARCHAR} ",
            "</if> ",
            "<if test='name!=null and name!=\"\"' >",
            " and name like CONCAT('%', #{name,jdbcType=VARCHAR}, '%') ",
            "</if> ",
            "</script>"
    })
    @Results({
            @Result(column = "employee_no", property = "employeeNo", jdbcType = JdbcType.VARCHAR),
            @Result(column = "name", property = "employeeName", jdbcType = JdbcType.VARCHAR),
            @Result(column = "job_title", property = "jobTitle", jdbcType = JdbcType.VARCHAR),
    })
    List<UserInfo> queryUserInfos(UserParams data);
}
