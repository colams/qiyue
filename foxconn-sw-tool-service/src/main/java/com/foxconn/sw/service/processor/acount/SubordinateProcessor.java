package com.foxconn.sw.service.processor.acount;

import com.foxconn.sw.business.system.DepartmentBusiness;
import com.foxconn.sw.business.system.EmployeeBusiness;
import com.foxconn.sw.data.context.RequestContext;
import com.foxconn.sw.data.dto.response.user.SubordinateVo;
import com.foxconn.sw.data.entity.SwDepartment;
import com.foxconn.sw.data.entity.SwEmployee;
import com.google.common.collect.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class SubordinateProcessor {
    @Autowired
    EmployeeBusiness employeeBusiness;
    @Autowired
    DepartmentBusiness departmentBusiness;

    public List<SubordinateVo> subordinateList() {
        List<SwEmployee> ee = getSubordinateList();
        return ee.stream().map(e -> {
            SubordinateVo vo = new SubordinateVo();
            vo.setId(e.getId());
            vo.setName(e.getName());
            vo.setEmployeeNo(e.getEmployeeNo());
            vo.setInnerEmail(e.getInnerEmail());
            vo.setDepartmentId(e.getDepartmentId());
            vo.setDepartments(getDepartmentList(e.getDepartmentId()));
            vo.setStatus(e.getStatus());
            vo.setIdentityOfCadre(e.getIdentityOfCadre());
            return vo;
        }).collect(Collectors.toList());
    }

    private List<String> getDepartmentList(Integer departmentID) {
        List<SwDepartment> departments = departmentBusiness.getDepartment();
        List<Integer> result = findAncestorIds(departments, departmentID);
        List<String> r = result.stream().map(e -> e.toString()).collect(Collectors.toList());
        Collections.reverse(r);
        return r;
    }

    private List<Integer> findAncestorIds(List<SwDepartment> departments, Integer departmentID) {
        List<Integer> result = Lists.newArrayList();
        Optional<SwDepartment> department = departments.stream().filter(e -> e.getId().equals(departmentID)).findFirst();
        if (department.isEmpty()) {
            return result;
        }
        result.add(department.get().getId());

        List<Integer> list = findAncestorIds(departments, department.get().getParentId());

        if (!CollectionUtils.isEmpty(list)) {
            result.addAll(list);
        }
        return result;
    }

    private List<SwEmployee> getSubordinateList() {
        boolean isHead = departmentBusiness.isHead();
        if (isHead) {
            SwEmployee employee = employeeBusiness.selectEmployeeByENo(RequestContext.getEmployeeNo());
            return Lists.newArrayList(employee);
        }
        return employeeBusiness.queryMembers(RequestContext.getEmployeeNo());
    }
}
