package com.foxconn.sw.business.system;

import com.foxconn.sw.data.constants.enums.retcode.AccountExceptionCode;
import com.foxconn.sw.data.dto.entity.acount.AddressBookParams;
import com.foxconn.sw.data.dto.entity.acount.EmployeeVo;
import com.foxconn.sw.data.entity.SwEmployee;
import com.foxconn.sw.data.entity.SwEmployeeExample;
import com.foxconn.sw.data.exception.BizException;
import com.foxconn.sw.data.mapper.extension.system.SwEmployeeExtensionMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class EmployeeBusiness {
    @Autowired
    SwEmployeeExtensionMapper swEmployeeExtensionMapper;
    @Autowired
    DepartmentBusiness departmentBusiness;


    private static List<SwEmployee> employeeList = new ArrayList<>();

    public List<SwEmployee> getEmployeeList() {
        if (!CollectionUtils.isEmpty(employeeList)) {
            return employeeList;
        }
        return init();
    }

    private List<SwEmployee> init() {
        SwEmployeeExample example = new SwEmployeeExample();
        SwEmployeeExample.Criteria criteria = example.createCriteria();
        criteria.andStatusEqualTo(0);
        employeeList = swEmployeeExtensionMapper.selectByExample(example);
        return employeeList;
    }

    public SwEmployee save(SwEmployee employee) {
        boolean result = swEmployeeExtensionMapper.insertSelective(employee) > 0;
        if (!result) {
            throw new BizException(AccountExceptionCode.CREATE_EMPLOYEE_EXCEPTION);
        }
        init();
        return employee;
    }

    public SwEmployee selectEmployeeByENo(String employeeNo) {
        List<SwEmployee> swEmployees = getEmployeeList()
                .stream()
                .filter(e -> e.getEmployeeNo().equalsIgnoreCase(employeeNo))
                .collect(Collectors.toList());
        if (CollectionUtils.isEmpty(swEmployees)) {
            return null;
        }
        return swEmployees.get(0);
    }

    public List<EmployeeVo> getEmployeesByLevel() {
        return swEmployeeExtensionMapper.getEmployeesByLevel();
    }

    public List<SwEmployee> selectEmployeeByENos(List<String> employeeNos) {
        List<SwEmployee> swEmployees = getEmployeeList()
                .stream()
                .filter(e -> employeeNos.contains(e.getEmployeeNo()))
                .collect(Collectors.toList());
        return swEmployees;
    }

    public boolean updateEmployee(SwEmployee updateEmployee) {
        boolean res = swEmployeeExtensionMapper.updateByPrimaryKeySelective(updateEmployee) > 0;
        init();
        return res;
    }

    public List<SwEmployee> queryEmployees(AddressBookParams data) {
        SwEmployeeExample example = new SwEmployeeExample();
        SwEmployeeExample.Criteria criteria = example.createCriteria();
        if (data.getSearchType() == 1) {
            criteria.andNameLike(String.format("%%%s%%", data.getSearchKey()));
        } else if (data.getSearchType() == 2) {
            criteria.andEmployeeNoLike(String.format("%%%s%%", data.getSearchKey()));
        } else if (data.getSearchType() == 3 || data.getSearchType() == 4) {
            List<Integer> list = departmentBusiness.getAllSubDepartID(data.getSearchKey());
            if (!CollectionUtils.isEmpty(list)) {
                criteria.andDepartmentIdIn(list);   // todo
            }
        }
        criteria.andStatusEqualTo(0);
        example.setOrderByClause(" department_id ,post_id,employee_no ");
        List<SwEmployee> employees = swEmployeeExtensionMapper.selectByExample(example);
        return employees;
    }


}
