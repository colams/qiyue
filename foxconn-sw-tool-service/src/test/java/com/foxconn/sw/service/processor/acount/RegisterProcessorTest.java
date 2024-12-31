package com.foxconn.sw.service.processor.acount;

import com.foxconn.sw.business.system.DepartmentBusiness;
import com.foxconn.sw.data.dto.entity.acount.LoginStateVo;
import com.foxconn.sw.data.dto.entity.acount.UserBriefParams;
import com.foxconn.sw.data.entity.SwDepartment;
import com.foxconn.sw.service.BaseTest;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Objects;

public class RegisterProcessorTest extends BaseTest {

    @Autowired
    RegisterProcessor registerProcessor;
    @Autowired
    DepartmentBusiness departmentBusiness;

    @Test
    public void registerTest() {

        String name = "";
        String employeeNo = "";
        String departmentName = "";
        Integer departID = 0;
        departID = getDepartID(departmentName);
        if (departID <= 0) {
            System.out.println(String.format("部门找不到*****************%s,%s,failed", employeeNo, departID));
        } else {
            register(name, employeeNo, departID);
        }
    }

    private Integer getDepartID(String departmentName) {
        List<SwDepartment> departmentList = departmentBusiness.getDepartment();
        SwDepartment department = departmentList.stream().filter(e -> e.getName().indexOf(departmentName) >= 0).findFirst().orElse(null);
        if (Objects.isNull(department)) {
            return 0;
        }
        return department.getId();
    }

    public void register(String name, String employeeNo, Integer departID) {

        UserBriefParams params = new UserBriefParams();
        params.setName(name);
        params.setDepartmentId(departID);
        params.setEmployeeNo(employeeNo);
        params.setPassword(employeeNo);
        try {
            LoginStateVo vo = registerProcessor.register(params);
            System.out.println(String.format("-----------------%s,%s", vo.getUser().getEmployeeNo(), vo.getToken()));
        } catch (Exception e) {
            System.out.println(String.format("*****************%s,%s,failed", employeeNo, name));
        }
    }
}