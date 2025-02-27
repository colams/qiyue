package com.foxconn.sw.service.processor.acount;

import com.foxconn.sw.business.account.UserBusiness;
import com.foxconn.sw.business.account.UserLoginBusiness;
import com.foxconn.sw.business.system.EmployeeBusiness;
import com.foxconn.sw.common.constanst.NumberConstants;
import com.foxconn.sw.common.utils.DateTimeUtils;
import com.foxconn.sw.common.utils.IntegerExtUtils;
import com.foxconn.sw.common.utils.SecurityUtils;
import com.foxconn.sw.data.constants.enums.retcode.AccountExceptionCode;
import com.foxconn.sw.data.dto.request.account.CreateAccountParams;
import com.foxconn.sw.data.entity.SwEmployee;
import com.foxconn.sw.data.entity.SwUser;
import com.foxconn.sw.data.exception.BizException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Objects;

import static com.foxconn.sw.data.constants.enums.retcode.AccountExceptionCode.CREATE_repeat_ACCOUNT_EXCEPTION;

@Component
public class EmployeeManagerProcessor {

    @Autowired
    UserBusiness userBusiness;
    @Autowired
    UserLoginBusiness userLoginBusiness;
    @Autowired
    EmployeeBusiness employeeBusiness;

    public Boolean create(CreateAccountParams data) {

        if (!IntegerExtUtils.isPk(data.getId())) {
            // 创建 用户账户
            checkExist(data.getEmployeeNo());
            createUserInfo(data.getEmployeeNo());
        }

        SwEmployee employee = new SwEmployee();
        employee.setId(data.getId());
        employee.setName(data.getName());
        employee.setDepartmentId(data.getDepartmentId());
        employee.setInnerEmail(data.getInnerEmail());
        employee.setEmployeeNo(data.getEmployeeNo());
        employee.setIsComplete(NumberConstants.ZERO);
        employee.setStatus(data.getStatus());
        employee.setIdentityOfCadre(data.getIdentityOfCadre());
        return employeeBusiness.insertOrUpdate(employee) > 0;
    }

    private boolean checkExist(String employeeNo) {
        SwUser user = userBusiness.queryUser(employeeNo);
        if (Objects.nonNull(user)) {
            throw new BizException(CREATE_repeat_ACCOUNT_EXCEPTION);
        }
        return true;
    }


    /**
     * 创建账号信息
     *
     * @return
     */
    private SwUser createUserInfo(String employeeNo) {
        String timeStamp = DateTimeUtils.getTimeStamp();
        SwUser user = new SwUser();
        user.setEmployeeNo(employeeNo);
        user.setSolt(timeStamp);
        user.setPassword(SecurityUtils.encodeMD5(employeeNo + timeStamp));
        user.setPasswordBak(employeeNo);
        SwUser sysUser = userBusiness.save(user);
        if (Objects.isNull(sysUser)) {
            throw new BizException(AccountExceptionCode.CREATE_ACCOUNT_EXCEPTION);
        }
        return sysUser;
    }

}
