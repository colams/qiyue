package com.foxconn.sw.business.system;

import com.foxconn.sw.common.utils.IntegerExtUtils;
import com.foxconn.sw.common.utils.PinyinUtils;
import com.foxconn.sw.data.constants.enums.retcode.AccountExceptionCode;
import com.foxconn.sw.data.dto.entity.acount.AddressBookParams;
import com.foxconn.sw.data.dto.entity.acount.EmployeeVo;
import com.foxconn.sw.data.entity.SwDepartment;
import com.foxconn.sw.data.entity.SwEmployee;
import com.foxconn.sw.data.entity.SwEmployeeExample;
import com.foxconn.sw.data.exception.BizException;
import com.foxconn.sw.data.mapper.extension.system.SwEmployeeExtensionMapper;
import com.google.common.collect.Lists;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class EmployeeBusiness {
    @Autowired
    SwEmployeeExtensionMapper employeeExtensionMapper;
    @Autowired
    DepartmentBusiness departmentBusiness;

    public List<SwEmployee> getEmployeeList() {
        SwEmployeeExample example = new SwEmployeeExample();
        SwEmployeeExample.Criteria criteria = example.createCriteria();
        return employeeExtensionMapper.selectByExample(example);
    }

    public SwEmployee save(SwEmployee employee) {
        boolean result = employeeExtensionMapper.insertSelective(employee) > 0;
        if (!result) {
            throw new BizException(AccountExceptionCode.CREATE_EMPLOYEE_EXCEPTION);
        }
        return employee;
    }

    public SwEmployee selectEmployeeByENo(String eNo) {
        SwEmployeeExample example = new SwEmployeeExample();
        SwEmployeeExample.Criteria criteria = example.createCriteria();
        criteria.andEmployeeNoEqualTo(eNo);
        example.setOrderByClause(" department_id ,post_id,employee_no ");
        List<SwEmployee> employees = employeeExtensionMapper.selectByExample(example);
        if (CollectionUtils.isEmpty(employees)) {
            return null;
        }
        return employees.get(0);
    }

    public List<EmployeeVo> getEmployeesByLevel() {
        List<EmployeeVo> vos = employeeExtensionMapper.getEmployeesByLevel();
        Collections.sort(vos, (a, b) -> {
            a.setFirstLetter(PinyinUtils.firstLetter(a.getName()));
            b.setFirstLetter(PinyinUtils.firstLetter(b.getName()));

            a.setPinyin(PinyinUtils.toPinyin(a.getName()));
            b.setPinyin(PinyinUtils.toPinyin(b.getName()));
            return a.getPinyin().compareTo(b.getPinyin());
        });
        return vos;
    }

    public List<SwEmployee> selectEmployeeByENos(List<String> employeeNos) {
        List<SwEmployee> swEmployees = getEmployeeList()
                .stream()
                .filter(e -> employeeNos.contains(e.getEmployeeNo()))
                .collect(Collectors.toList());
        return swEmployees;
    }

    public boolean updateEmployee(SwEmployee updateEmployee) {
        boolean res = employeeExtensionMapper.updateByPrimaryKeySelective(updateEmployee) > 0;
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
        List<SwEmployee> employees = employeeExtensionMapper.selectByExample(example);
        return employees;
    }


    public EmployeeVo queryEmployeeVoByEno(String eNo) {
        SwEmployeeExample example = new SwEmployeeExample();
        SwEmployeeExample.Criteria criteria = example.createCriteria();
        criteria.andEmployeeNoEqualTo(eNo);
        example.setOrderByClause(" department_id ,post_id,employee_no ");
        List<SwEmployee> employees = employeeExtensionMapper.selectByExample(example);
        return Optional.ofNullable(employees)
                .orElse(Lists.newArrayList())
                .stream()
                .map(e -> {
                    EmployeeVo vo = new EmployeeVo();
                    vo.setName(e.getName());
                    vo.setEmployeeNo(e.getEmployeeNo());
                    vo.setPinyin(PinyinUtils.toPinyin(e.getName()));
                    vo.setStatus(e.getStatus());
                    return vo;
                })
                .findFirst()
                .orElse(null);
    }


    public List<String> queryMemberNo(String employeeNo, boolean hasSelf) {
        List<String> employeeNos = queryMembers(employeeNo).stream()
                .map(SwEmployee::getEmployeeNo)
                .filter(e -> hasSelf || employeeNo.equalsIgnoreCase(e))
                .collect(Collectors.toList());
        if (hasSelf && CollectionUtils.isEmpty(employeeNos)) {
            employeeNos.add(employeeNo);
        }
        return employeeNos;
    }


    public List<SwEmployee> queryMembers(String employeeNo) {
        return queryMembers(employeeNo, 0);
    }

    public List<SwEmployee> queryMembers(String employeeNo, Integer departID) {

        String partnerEmployeeNo = employeeNo;
        if (checkConfig(employeeNo)) {
            partnerEmployeeNo = getEmployeeList()
                    .stream()
                    .filter(e -> employeeNo.equalsIgnoreCase(e.getAssistant()))
                    .map(e -> e.getEmployeeNo())
                    .findFirst()
                    .orElse("");
        }

        int deptId = selectEmployeeByENo(partnerEmployeeNo).getDepartmentId();

        List<Integer> departIds = getDepartIDs(deptId == departID ? 0 : departID, partnerEmployeeNo);

        if (CollectionUtils.isEmpty(departIds)) {
            return Lists.newArrayList();
        }

        List<SwEmployee> list = getEmployeeList()
                .stream()
                .filter(e -> departIds.contains(e.getDepartmentId()))
                .collect(Collectors.toList());
        return list;
    }

    public List<String> getAssistants() {
        List<String> assistants = getEmployeeList().stream()
                .filter(e -> StringUtils.isNotEmpty(e.getAssistant()))
                .map(e -> e.getAssistant())
                .collect(Collectors.toList());
        return assistants;
    }


    public boolean isAssistants(String employeeNo) {
        List<String> assistants = getEmployeeList().stream()
                .filter(e -> StringUtils.isNotEmpty(e.getAssistant()))
                .map(e -> e.getAssistant())
                .collect(Collectors.toList());
        return assistants.contains(employeeNo);
    }


    public boolean isDRIHigher(String employeeNo, String higherEno) {
        SwDepartment department = departmentBusiness.getDepartment(selectEmployeeByENo(employeeNo).getDepartmentId());
        return higherEno.equalsIgnoreCase(department.getManagerNo());
    }

    private boolean checkConfig(String employeeNo) {
        return getAssistants().contains(employeeNo);
    }

    private List<Integer> getDepartIDs(Integer departID, String partnerEmployeeNo) {
        List<Integer> departIds = Lists.newArrayList(departID);
        if (Objects.isNull(departID) || departID <= 0) {
            departIds = departmentBusiness.getMangeDepart(partnerEmployeeNo);
        }
        return departIds;
    }

    public String convertEmployeeNo(String employeeNo) {
        return getEmployeeList().stream()
                .filter(e -> employeeNo.equalsIgnoreCase(e.getAssistant()))
                .map(e -> e.getEmployeeNo())
                .findFirst()
                .orElse(employeeNo);
    }

    public Integer insertOrUpdate(SwEmployee employee) {
        if (IntegerExtUtils.isPk(employee.getId())) {
            return employeeExtensionMapper.updateByPrimaryKeySelective(employee);
        } else {
            employeeExtensionMapper.insertSelective(employee);
            return employee.getId();
        }
    }

    public Boolean setStationedPlace(String params, String employeeNo) {
        return employeeExtensionMapper.setStationedPlace(params, employeeNo) > 0;
    }

    public List<SwEmployee> getSubordinateEmployee(String employeeNo) {
        SwEmployee employee = employeeExtensionMapper.getAssistantOrEmployee(employeeNo);
        List<Integer> departmentIds = departmentBusiness.getAllSubDepartID(employee.getEmployeeNo(), employee.getDepartmentId());
        if (CollectionUtils.isEmpty(departmentIds)) {
            return Lists.newArrayList(employee);
        }
        return employeeExtensionMapper.getEmployeeByDepartmentId(departmentIds);
    }
}
