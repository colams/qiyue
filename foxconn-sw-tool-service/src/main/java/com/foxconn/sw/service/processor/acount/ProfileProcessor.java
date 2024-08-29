package com.foxconn.sw.service.processor.acount;

import com.foxconn.sw.business.SwAppendResourceBusiness;
import com.foxconn.sw.business.account.UserBusiness;
import com.foxconn.sw.business.system.DepartmentBusiness;
import com.foxconn.sw.business.system.EmployeeBusiness;
import com.foxconn.sw.common.utils.DomainRetrieval;
import com.foxconn.sw.data.dto.Header;
import com.foxconn.sw.data.dto.entity.acount.UserProfileVo;
import com.foxconn.sw.data.dto.entity.system.DepartmentVo;
import com.foxconn.sw.data.entity.SwAppendResource;
import com.foxconn.sw.data.entity.SwEmployee;
import com.foxconn.sw.data.entity.SwUser;
import com.foxconn.sw.service.processor.user.CommonUserUtils;
import com.google.common.collect.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

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
        profileVo.setInnerEmail(employee.getInnerEmail());
        profileVo.setOuterMail(employee.getOuterMail());
        profileVo.setLandLine(employee.getLandLine());
        profileVo.setPhoneNumber(employee.getPhoneNumber());
        profileVo.setHireDate(employee.getHireDate());
        profileVo.setOuterWorkYears(employee.getOuterWorkYears());
        profileVo.setOuterAbcYears(employee.getOuterAbcYears());
        profileVo.setSignature(swUser.getSignature());
        profileVo.setDepartment(getFullDepartName(employee.getDepartmentId()));
        profileVo.setAvatar(appendResourceBusiness.getResourceUrl(swUser.getAvatarId()));
        return profileVo;
    }

    private String getDepartName(int departID) {
        List<DepartmentVo> vos = departmentBusiness.getDepartList();
        return vos.stream().filter(e -> e.getId() == departID).findFirst().map(DepartmentVo::getName).orElse("");
    }

    private String getFullDepartName(int departID) {
        Map<Integer, DepartmentVo> voMap = departmentBusiness.getDepartMap();
        List<DepartmentVo> departmentVos = getDepartList(voMap, departID);
        return departmentVos.stream().map(e -> e.getName()).collect(Collectors.joining(" - "));
    }

    private List<DepartmentVo> getDepartList(Map<Integer, DepartmentVo> voMap, int departID) {
        List<DepartmentVo> vos = new ArrayList<>();

        DepartmentVo departmentVo = voMap.get(departID);
        if (Objects.isNull(departmentVo.getParentId()) || departmentVo.getParentId() == 0) {
            return vos;
        }
        List<DepartmentVo> temps = getDepartList(voMap, departmentVo.getParentId());
        temps.addAll(Lists.newArrayList(departmentVo));
        vos.addAll(temps);
        return vos;
    }

}
