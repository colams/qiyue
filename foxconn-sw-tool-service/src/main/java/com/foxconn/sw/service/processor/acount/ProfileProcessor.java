package com.foxconn.sw.service.processor.acount;

import com.foxconn.sw.business.account.UserBusiness;
import com.foxconn.sw.business.system.EmployeeBusiness;
import com.foxconn.sw.data.dto.Header;
import com.foxconn.sw.data.dto.entity.acount.UserProfileVo;
import com.foxconn.sw.data.entity.SwEmployee;
import com.foxconn.sw.data.entity.SwUser;
import com.foxconn.sw.service.processor.user.CommonUserUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ProfileProcessor {

    @Autowired
    CommonUserUtils commonUserUtils;
    @Autowired
    EmployeeBusiness employeeBusiness;
    @Autowired
    UserBusiness userBusiness;

    public UserProfileVo profile(Header head) {
        String employeeNo = commonUserUtils.getEmployeeNo(head.getToken());
        SwUser swUser = userBusiness.queryUser(employeeNo);
        SwEmployee employee = employeeBusiness.selectEmployeeByENo(employeeNo);
        return initProfile(swUser, employee);
    }

    private UserProfileVo initProfile(SwUser swUser, SwEmployee employee) {
        UserProfileVo profileVo = new UserProfileVo();
        profileVo.setEmployeeNo(employee.getEmployeeNo());
        profileVo.setDepartName(employee.getDepartmentId().toString());
        profileVo.setName(employee.getName());
        profileVo.setFirstName(employee.getFirstName());
        profileVo.setLastName(employee.getLastName());
        profileVo.setGender(profileVo.getGender());
        profileVo.setDepartmentId(employee.getDepartmentId());
        profileVo.setPostId(employee.getPostId());
        profileVo.setInnerEmail(employee.getInnerEmail());
        profileVo.setOuterMail(employee.getOuterMail());
        profileVo.setLandLine(employee.getLandLine());
        profileVo.setPhoneNumber(employee.getPhoneNumber());
        profileVo.setHireDate(employee.getHireDate());
        profileVo.setOuterWorkYears(employee.getOuterWorkYears());
        profileVo.setOuterAbcYears(employee.getOuterAbcYears());
        profileVo.setSignature(swUser.getSignature());
        profileVo.setDepartment("產品研發處 - 軟件開發部 - 軟體二課");
        return profileVo;
    }
}
