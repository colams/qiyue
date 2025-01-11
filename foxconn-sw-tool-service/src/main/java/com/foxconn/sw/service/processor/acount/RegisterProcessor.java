package com.foxconn.sw.service.processor.acount;

import com.foxconn.sw.business.account.UserBusiness;
import com.foxconn.sw.business.account.UserLoginBusiness;
import com.foxconn.sw.business.system.EmployeeBusiness;
import com.foxconn.sw.common.utils.DateTimeUtils;
import com.foxconn.sw.common.utils.SecurityUtils;
import com.foxconn.sw.common.utils.UUIDUtils;
import com.foxconn.sw.data.constants.enums.retcode.AccountExceptionCode;
import com.foxconn.sw.data.dto.entity.acount.LoginStateVo;
import com.foxconn.sw.data.dto.entity.acount.UserBriefParams;
import com.foxconn.sw.data.dto.entity.acount.UserInfo;
import com.foxconn.sw.data.dto.request.account.CreateAccountParams;
import com.foxconn.sw.data.entity.SwEmployee;
import com.foxconn.sw.data.entity.SwUser;
import com.foxconn.sw.data.exception.BizException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.Objects;

import static com.foxconn.sw.data.constants.enums.retcode.AccountExceptionCode.CREATE_repeat_ACCOUNT_EXCEPTION;

@Component
public class RegisterProcessor {

    @Autowired
    UserBusiness userBusiness;
    @Autowired
    UserLoginBusiness userLoginBusiness;
    @Autowired
    EmployeeBusiness employeeBusiness;

    public LoginStateVo register(UserBriefParams data) {
        // 创建 用户账户
        checkExist(data.getEmployeeNo());
        SwUser sysUser = createUser(data.getEmployeeNo(), data.getPassword());
        if (Objects.isNull(sysUser)) {
            throw new BizException(AccountExceptionCode.CREATE_ACCOUNT_EXCEPTION);
        }
        saveEmployee(data);
        // 生成登录信息
        LoginStateVo result = createLoginState(data.getEmployeeNo());
        return result;
    }

    private boolean checkExist(String employeeNo) {
        SwUser user = userBusiness.queryUser(employeeNo);
        if (Objects.nonNull(user)) {
            throw new BizException(CREATE_repeat_ACCOUNT_EXCEPTION);
        }
        return true;
    }

    private SwEmployee saveEmployee(UserBriefParams data) {
        SwEmployee swEmployee = employeeBusiness.selectEmployeeByENo(data.getEmployeeNo());
        if (Objects.nonNull(swEmployee)) {
            return swEmployee;
        } else {
            SwEmployee employee = initEmployee(data);
            return employeeBusiness.save(employee);
        }
    }

    private SwEmployee initEmployee(UserBriefParams data) {
        SwEmployee employee = new SwEmployee();
        employee.setName(data.getName());
        employee.setEmployeeNo(data.getEmployeeNo());
        employee.setDepartmentId(data.getDepartmentId());
        employee.setManagerLevel(data.getManagerLevel());
        employee.setStatus(0);
        return employee;
    }

    private LoginStateVo createLoginState(String employeeID) {
        String token = UUIDUtils.getUuid();
        LocalDateTime expireTime = LocalDateTime.now().plusHours(24);
        boolean loginState = userLoginBusiness.saveUserLogin(employeeID, token, expireTime);
        if (!loginState) {
            throw new BizException(AccountExceptionCode.CREATE_ACCOUNT_LOGIN_EXCEPTION);
        }
        UserInfo userInfo = userBusiness.queryUserInfo(employeeID);
        return LoginStateVo.init(token, expireTime, userInfo);
    }

    /**
     * 创建账号信息
     *
     * @return
     */
    private SwUser createUser(String employeeNo, String password) {
        SwUser user = initUser(employeeNo, password);
        return userBusiness.save(user);
    }

    private SwUser initUser(String employeeNo, String password) {
        String timeStamp = DateTimeUtils.getTimeStamp();
        SwUser user = new SwUser();
        user.setEmployeeNo(employeeNo);
        user.setSolt(timeStamp);
        user.setPassword(SecurityUtils.encodeMD5(password + timeStamp));
        user.setPasswordBak(password);
        return user;
    }

}
