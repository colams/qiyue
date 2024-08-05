package com.foxconn.sw.data.mapper.extension.acount;

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
    @Select({"select nick_name,su.employee_no,se.name,se.job_title,se.department_id\n" +
            "from sw_user su\n" +
            "    left join sw_employee se on su.employee_no=se.employee_no\n" +
            "where su.employee_no=#{employeeNo,jdbcType=VARCHAR} " +
            "limit 1 "
    })
    @Results({
            @Result(column = "nick_name", property = "nickName", jdbcType = JdbcType.VARCHAR),
            @Result(column = "employee_no", property = "employeeNo", jdbcType = JdbcType.VARCHAR),
            @Result(column = "name", property = "employeeName", jdbcType = JdbcType.VARCHAR),
            @Result(column = "job_title", property = "jobTitle", jdbcType = JdbcType.VARCHAR),
    })
    UserInfo queryUserInfo(String employeeNo);

    @Select({"<script> " +
            "select nick_name,su.employee_no,se.name,se.job_title,se.department_id\n" +
            "from sw_user su\n" +
            "    left join sw_employee se on su.employee_no=se.employee_no " +
            "where 1=1 " +
            "<if test='keyWord!=null and keyWord!=\"\"' >",
            " and (name like CONCAT('%', #{keyWord,jdbcType=VARCHAR}, '%') or nick_name like CONCAT('%', #{keyWord,jdbcType=VARCHAR}, '%')  ) ",
            "</if> ",
            "</script>"
    })
    @Results({
            @Result(column = "nick_name", property = "nickName", jdbcType = JdbcType.VARCHAR),
            @Result(column = "employee_no", property = "employeeNo", jdbcType = JdbcType.VARCHAR),
            @Result(column = "name", property = "employeeName", jdbcType = JdbcType.VARCHAR),
            @Result(column = "job_title", property = "jobTitle", jdbcType = JdbcType.VARCHAR),
    })
    List<UserInfo> queryUserInfos(UserParams data);
}
