package com.foxconn.sw.business.system;

import com.foxconn.sw.data.constants.enums.retcode.AccountExceptionCode;
import com.foxconn.sw.data.dto.entity.acount.EmployeeVo;
import com.foxconn.sw.data.entity.SwEmployee;
import com.foxconn.sw.data.entity.SwEmployeeExample;
import com.foxconn.sw.data.exception.BizException;
import com.foxconn.sw.data.mapper.extension.system.SwEmployeeExtensionMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.util.List;

@Component
public class EmployeeBusiness {
    @Autowired
    SwEmployeeExtensionMapper swEmployeeExtensionMapper;

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
}
