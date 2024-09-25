package com.foxconn.sw.data.mapper.extension.system;

import com.foxconn.sw.data.dto.entity.acount.EmployeeVo;
import com.foxconn.sw.data.entity.SwEmployee;
import com.foxconn.sw.data.entity.SwEmployeeExample;
import com.foxconn.sw.data.mapper.auto.SwEmployeeMapper;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.type.JdbcType;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SwEmployeeExtensionMapper extends SwEmployeeMapper {


    @Select({
            "<script> ",
            "select",
            "se.name, se.employee_no,se.department_id ",
            "from sw_employee se inner join sw_department sd on se.department_id=sd.id",
            "where sd.id>1 ",
            "</script> ",
    })
    @Results({
            @Result(column = "name", property = "name", jdbcType = JdbcType.VARCHAR),
            @Result(column = "employee_no", property = "employeeNo", jdbcType = JdbcType.VARCHAR),
            @Result(column = "department_id", property = "departmentId", jdbcType = JdbcType.INTEGER),
    })
    List<EmployeeVo> getEmployeesByLevel();


    List<SwEmployee> queryEmployees(SwEmployeeExample example);
}
