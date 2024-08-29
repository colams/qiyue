package com.foxconn.sw.business.system;

import com.foxconn.sw.data.constants.enums.retcode.AccountExceptionCode;
import com.foxconn.sw.data.dto.entity.acount.AddressBookParams;
import com.foxconn.sw.data.dto.entity.acount.EmployeeVo;
import com.foxconn.sw.data.dto.entity.system.DepartmentVo;
import com.foxconn.sw.data.entity.SwEmployee;
import com.foxconn.sw.data.entity.SwEmployeeExample;
import com.foxconn.sw.data.exception.BizException;
import com.foxconn.sw.data.mapper.extension.system.SwEmployeeExtensionMapper;
import org.apache.commons.lang3.math.NumberUtils;
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

    public SwEmployee save(SwEmployee employee) {
        boolean result = swEmployeeExtensionMapper.insertSelective(employee) > 0;
        if (!result) {
            throw new BizException(AccountExceptionCode.CREATE_EMPLOYEE_EXCEPTION);
        }
        return employee;
    }

    public SwEmployee selectEmployeeByENo(String employeeNo) {
        SwEmployeeExample example = new SwEmployeeExample();
        SwEmployeeExample.Criteria criteria = example.createCriteria();
        criteria.andEmployeeNoEqualTo(employeeNo);
        List<SwEmployee> swEmployees = swEmployeeExtensionMapper.selectByExample(example);
        if (CollectionUtils.isEmpty(swEmployees)) {
            return null;
        }
        return swEmployees.get(0);
    }

    public List<EmployeeVo> getEmployeesByLevel() {
        return swEmployeeExtensionMapper.getEmployeesByLevel();
    }

    public List<SwEmployee> selectEmployeeByENos(List<String> employeeNos) {
        SwEmployeeExample example = new SwEmployeeExample();
        SwEmployeeExample.Criteria criteria = example.createCriteria();
        criteria.andEmployeeNoIn(employeeNos);
        List<SwEmployee> swEmployees = swEmployeeExtensionMapper.selectByExample(example);
        return swEmployees;
    }

    public boolean updateEmployee(SwEmployee updateEmployee) {
        return swEmployeeExtensionMapper.updateByPrimaryKeySelective(updateEmployee) > 0;
    }

    public List<SwEmployee> queryEmployees(AddressBookParams data) {
        SwEmployeeExample example = new SwEmployeeExample();
        SwEmployeeExample.Criteria criteria = example.createCriteria();
        if (data.getSearchType() == 1) {
            criteria.andNameLike(String.format("%%%s%%", data.getSearchKey()));
        } else if (data.getSearchType() == 2) {
            criteria.andEmployeeNoLike(String.format("%%%s%%", data.getSearchKey()));
        } else if (data.getSearchType() == 3 || data.getSearchType() == 4) {
            List<Integer> list = getAllSubDepartID(data.getSearchKey());
            if (!CollectionUtils.isEmpty(list)) {
                criteria.andDepartmentIdIn(list);   // todo
            }
        }
        criteria.andStatusEqualTo(0);
        example.setOrderByClause(" department_id ,post_id,employee_no ");
        List<SwEmployee> employees = swEmployeeExtensionMapper.selectByExample(example);
        return employees;
    }

    private List<Integer> getAllSubDepartID(String searchKey) {
        List<DepartmentVo> departmentVos = departmentBusiness.getDepartList();
        return getDepartmentIds(departmentVos, NumberUtils.toInt(searchKey), 0);
    }

    private List<Integer> getDepartmentIds(List<DepartmentVo> vos, Integer currId, int type) {
        List<Integer> result = new ArrayList<>();

        List<Integer> subVoIds = vos
                .stream()
                .filter(e -> {
                    return type == 0 ? e.getId() == currId : e.getParentId() == currId;
                })
                .map(DepartmentVo::getId)
                .collect(Collectors.toList());
        if (CollectionUtils.isEmpty(subVoIds)) {
            return result;
        }

        subVoIds.forEach(e -> {
            List<Integer> ids = getDepartmentIds(vos, e, 1);
            result.addAll(ids);
        });
        result.addAll(subVoIds);
        return result;
    }

}
