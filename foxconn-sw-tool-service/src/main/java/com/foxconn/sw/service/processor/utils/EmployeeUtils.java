package com.foxconn.sw.service.processor.utils;

import com.foxconn.sw.business.account.UserBusiness;
import com.foxconn.sw.business.system.EmployeeBusiness;
import com.foxconn.sw.data.dto.entity.acount.EmployeeVo;
import com.foxconn.sw.data.dto.entity.acount.UserInfo;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
public class EmployeeUtils {

    @Autowired
    EmployeeBusiness employeeBusiness;
    @Autowired
    UserBusiness userBusiness;

    public EmployeeVo mapEmployee(String employeeNo) {
        if (StringUtils.isEmpty(employeeNo)) {
            return null;
        }

        UserInfo userInfo = userBusiness.queryUserInfo(employeeNo);

        EmployeeVo employeeVo = new EmployeeVo();
        employeeVo.setEmployeeNo(employeeNo);

        if (Objects.isNull(userInfo)) {
            employeeVo.setName(employeeNo);
        } else {
            employeeVo.setName(userInfo.getEmployeeName());
            employeeVo.setDepartmentName(userInfo.getDepartName());
            employeeVo.setAvatar(userInfo.getAvatar());
        }

        return employeeVo;
    }

}
