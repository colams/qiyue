package com.foxconn.sw.service.processor.acount;

import com.foxconn.sw.business.account.SwContactGatherBusiness;
import com.foxconn.sw.business.system.DepartmentBusiness;
import com.foxconn.sw.business.system.EmployeeBusiness;
import com.foxconn.sw.data.constants.enums.GenderEnums;
import com.foxconn.sw.data.dto.Header;
import com.foxconn.sw.data.dto.entity.acount.AddressBookParams;
import com.foxconn.sw.data.dto.entity.acount.AddressBookVo;
import com.foxconn.sw.data.dto.entity.system.DepartmentVo;
import com.foxconn.sw.data.entity.SwContactGather;
import com.foxconn.sw.data.entity.SwEmployee;
import com.foxconn.sw.service.processor.user.CommonUserUtils;
import com.google.common.collect.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.stream.Collectors;

@Component
public class ListAddressBookProcessor {

    @Autowired
    EmployeeBusiness employeeBusiness;
    @Autowired
    CommonUserUtils commonUserUtils;
    @Autowired
    SwContactGatherBusiness gatherBusiness;
    @Autowired
    DepartmentBusiness departmentBusiness;

    public List<AddressBookVo> list(Header head, AddressBookParams data) {
        String userNo = commonUserUtils.getEmployeeNo(head.getToken());

        List<SwEmployee> employees = employeeBusiness.queryEmployees(data);
        Map<String, SwContactGather> gatherMap = gatherBusiness.queryGatherInfo(userNo);
        Map<Integer, DepartmentVo> voMap = departmentBusiness.getDepartMap();

        List<AddressBookVo> bookVoList = new ArrayList<>();
        employees.forEach(e -> {
            AddressBookVo vo = toAddressBookVo(e, voMap, gatherMap);
            if (Objects.nonNull(vo)) {
                bookVoList.add(vo);
            }
        });

        return Optional.ofNullable(bookVoList)
                .orElse(Lists.newArrayList())
                .stream()
                .filter(e -> Objects.isNull(data.getStatus()) || data.getStatus() == 0 || data.getStatus() == e.getStatus())
                .collect(Collectors.toList());
    }

    private AddressBookVo toAddressBookVo(SwEmployee e, Map<Integer, DepartmentVo> voMap, Map<String, SwContactGather> gatherMap) {

        AddressBookVo vo = new AddressBookVo();

        List<DepartmentVo> voList = departmentBusiness.getDepartList(voMap, e.getDepartmentId());

        vo.setSeniorDepart(voList.stream().filter(departmentVo -> departmentVo.getName().endsWith("處")).map(v -> v.getName()).findFirst().orElse("-"));
        vo.setDepartment(voList.stream().filter(departmentVo -> departmentVo.getName().contains("部")).map(v -> v.getName()).findFirst().orElse("-"));
        vo.setEmployeeNo(e.getEmployeeNo());
        vo.setName(e.getName());
        vo.setEnName(String.format("%s %s", e.getFirstName(), e.getLastName()));
        vo.setGender(GenderEnums.getGenderDes(e.getGender()));
        vo.setPhoneMobile(e.getPhoneNumber());
        vo.setLandLine(e.getLandLine());
        vo.setInnerMail(e.getInnerEmail());
        vo.setOuterMail(e.getOuterMail());
        vo.setStatus(getGatherStatus(gatherMap, e.getEmployeeNo()));
        return vo;
    }

    private Integer getGatherStatus(Map<String, SwContactGather> gatherMap, String employeeNo) {
        return Objects.nonNull(gatherMap.get(employeeNo)) ? 1 : 0;
    }


}
