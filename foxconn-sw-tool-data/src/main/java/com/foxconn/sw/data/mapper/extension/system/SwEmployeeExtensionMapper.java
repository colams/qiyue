package com.foxconn.sw.data.mapper.extension.system;

import com.foxconn.sw.data.dto.entity.acount.EmployeeVo;
import com.foxconn.sw.data.mapper.auto.SwEmployeeMapper;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.apache.ibatis.type.JdbcType;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SwEmployeeExtensionMapper extends SwEmployeeMapper {


    @Select({
            "<script> ",
            "select",
            "se.name, se.employee_no,se.department_id,sd.name as departmentName ",
            "from sw_employee se inner join sw_department sd on se.department_id=sd.id",
            "where sd.id>=1 and se.status=0",
            "</script> ",
    })
    @Results({
            @Result(column = "name", property = "name", jdbcType = JdbcType.VARCHAR),
            @Result(column = "employee_no", property = "employeeNo", jdbcType = JdbcType.VARCHAR),
            @Result(column = "department_id", property = "departmentId", jdbcType = JdbcType.INTEGER),
            @Result(column = "departmentName", property = "departmentName", jdbcType = JdbcType.VARCHAR),
    })
    List<EmployeeVo> getEmployeesByLevel();

    @Update({
            "update sw_employee",
            "set identity_of_cadre = #{params,jdbcType=VARCHAR}",
            "where employee_no = #{employeeNo,jdbcType=INTEGER}"
    })
    Integer setStationedPlace(String params, String employeeNo);
}
