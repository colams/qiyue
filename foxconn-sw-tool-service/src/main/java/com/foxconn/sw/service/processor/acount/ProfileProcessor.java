package com.foxconn.sw.service.processor.acount;

import com.foxconn.sw.business.SwAppendResourceBusiness;
import com.foxconn.sw.business.account.UserBusiness;
import com.foxconn.sw.business.system.DepartmentBusiness;
import com.foxconn.sw.business.system.EmployeeBusiness;
import com.foxconn.sw.data.dto.Header;
import com.foxconn.sw.data.dto.entity.acount.UserProfileVo;
import com.foxconn.sw.data.dto.entity.system.DepartmentVo;
import com.foxconn.sw.data.entity.SwEmployee;
import com.foxconn.sw.data.entity.SwUser;
import com.foxconn.sw.service.processor.user.CommonUserUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class ProfileProcessor {

    @Autowired
    CommonUserUtils commonUserUtils;
    @Autowired
    EmployeeBusiness employeeBusiness;
    @Autowired
    UserBusiness userBusiness;
    @Autowired
    DepartmentBusiness departmentBusiness;
    @Autowired
    SwAppendResourceBusiness appendResourceBusiness;

    public UserProfileVo profile(Header head) {
        String employeeNo = commonUserUtils.getEmployeeNo(head.getToken());
        SwUser swUser = userBusiness.queryUser(employeeNo);
        SwEmployee employee = employeeBusiness.selectEmployeeByENo(employeeNo);
        return initProfile(swUser, employee);
    }

    private UserProfileVo initProfile(SwUser swUser, SwEmployee employee) {
        UserProfileVo profileVo = new UserProfileVo();
        profileVo.setEmployeeNo(employee.getEmployeeNo());
        profileVo.setDepartName(getDepartName(employee.getDepartmentId()));
        profileVo.setName(employee.getName());
        profileVo.setFirstName(employee.getFirstName());
        profileVo.setLastName(employee.getLastName());
        profileVo.setGender(employee.getGender());
        profileVo.setDepartmentId(employee.getDepartmentId());
        profileVo.setPostId(employee.getPostId());
        profileVo.setPosition(getPosition(employee.getEmployeeNo()));
        profileVo.setInnerEmail(employee.getInnerEmail());
        profileVo.setOuterMail(employee.getOuterMail());
        profileVo.setLandLine(employee.getLandLine());
        profileVo.setPhoneNumber(employee.getPhoneNumber());
        profileVo.setHireDate(employee.getHireDate());
        profileVo.setOuterWorkYears(employee.getOuterWorkYears());
        profileVo.setOuterAbcYears(employee.getOuterAbcYears());
        profileVo.setSignature(swUser.getSignature());
        profileVo.setDepartment(departmentBusiness.getFullDepartName(employee.getDepartmentId()));
        profileVo.setAvatar(appendResourceBusiness.getResourceUrl(swUser.getAvatarId()));
        return profileVo;
    }

    private String getPosition(String employeeNo) {
        Map<String, String> map = new HashMap<>();
        map.put("PL1910055", "資深經理");
        map.put("PL1910121", "資深副理");
        map.put("G1654668", "副課長");
        map.put("G1651074", "副課長");
        map.put("F2827381", "經理");
        map.put("F1655140", "專理");
        map.put("G1650067", "課長");
        map.put("G1654631", "工程師");
        map.put("F1678218", "課長");
        map.put("F2829504", "專理");
        map.put("G1654707", "副課長");
        map.put("F1679627", "經理");
        map.put("F1668046", "資深專理");
        map.put("F1669075", "專理");
        map.put("G1651084", "副課長");
        map.put("G1653388", "工程師");
        map.put("G1653619", "工程師");
        map.put("F1648344", "資深副理");
        map.put("F1676766", "副課長");
        map.put("F9233118", "專理");
        map.put("F1631845", "專理");
        map.put("FSUS140", "工程師");
        map.put("G1655080", "資深專理");
        map.put("F1652662", "課長");
        map.put("G1655716", "工程師");
        map.put("G1655614", "副課長");
        map.put("PL2210065", "工程師");
        map.put("F1669092", "副課長");
        map.put("PL1910091", "經理");
        map.put("F5400084", "資深專理");
        map.put("F1666045", "專理");
        map.put("G1650865", "副課長");
        map.put("F1669081", "課長");
        return map.getOrDefault(employeeNo, "工程師");
    }

    private String getDepartName(int departID) {
        List<DepartmentVo> vos = departmentBusiness.getDepartList();
        return vos.stream().filter(e -> e.getId() == departID).findFirst().map(DepartmentVo::getName).orElse("");
    }
}
