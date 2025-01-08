package com.foxconn.sw.service.processor.acount;

import com.foxconn.sw.business.account.UserBusiness;
import com.foxconn.sw.business.system.EmployeeBusiness;
import com.foxconn.sw.common.constanst.NumberConstants;
import com.foxconn.sw.data.dto.entity.acount.UserProfileBrief;
import com.foxconn.sw.data.entity.SwEmployee;
import com.foxconn.sw.data.entity.SwUser;
import com.foxconn.sw.service.processor.user.CommonUserUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UpdateProfileProcessor {

    @Autowired
    CommonUserUtils commonUserUtils;
    @Autowired
    EmployeeBusiness employeeBusiness;
    @Autowired
    UserBusiness userBusiness;


    public boolean updateProfile(String token, UserProfileBrief data) {
        String employeeNo = commonUserUtils.getEmployeeNo(token);
        updateEmployeeInfo(data, employeeNo);
        updateUser(data, employeeNo);
        return true;
    }

    private boolean updateEmployeeInfo(UserProfileBrief data, String employeeNo) {
        SwEmployee employee = employeeBusiness.selectEmployeeByENo(employeeNo);
        SwEmployee updateEmployee = new SwEmployee();
        updateEmployee.setId(employee.getId());
        updateEmployee.setFirstName(data.getFirstName());
        updateEmployee.setLastName(data.getLastName());
        updateEmployee.setPostId(data.getPostId());
        updateEmployee.setGender(data.getGender());
        updateEmployee.setLandLine(data.getLandLine());
        updateEmployee.setOuterWorkYears(data.getOuterWorkYears());
        updateEmployee.setOuterAbcYears(data.getOuterAbcYears());
        updateEmployee.setInnerEmail(data.getInnerEmail());
        updateEmployee.setOuterMail(data.getOuterMail());
        updateEmployee.setPhoneNumber(data.getPhoneNumber());
        updateEmployee.setHireDate(data.getHireDate());
        updateEmployee.setDepartmentId(data.getDepartmentID());
        updateEmployee.setIsComplete(NumberConstants.ONE);
        return employeeBusiness.updateEmployee(updateEmployee);
    }

    private boolean updateUser(UserProfileBrief data, String employeeNo) {
        SwUser user = userBusiness.queryUser(employeeNo);
        SwUser updateUser = new SwUser();
        updateUser.setId(user.getId());
        updateUser.setSignature(data.getSignature());
        return userBusiness.update(updateUser);
    }
}
