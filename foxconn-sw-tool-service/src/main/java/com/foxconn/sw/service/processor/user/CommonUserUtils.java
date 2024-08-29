package com.foxconn.sw.service.processor.user;

import com.foxconn.sw.business.account.UserBusiness;
import com.foxconn.sw.business.account.UserLoginBusiness;
import com.foxconn.sw.business.context.RequestContext;
import com.foxconn.sw.data.constants.enums.retcode.AccountExceptionCode;
import com.foxconn.sw.data.dto.entity.acount.UserInfo;
import com.foxconn.sw.data.entity.SwUserLogin;
import com.foxconn.sw.data.exception.BizException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
public class CommonUserUtils {

    @Autowired
    UserLoginBusiness userLoginBusiness;
    @Autowired
    UserBusiness userBusiness;

    /**
     * 获取登录用户信息
     *
     * @param token
     * @return
     */
    public UserInfo queryUserInfo(String token) {
        SwUserLogin swUserLogin = userLoginBusiness.queryLoginUser(token);
        if (Objects.isNull(swUserLogin)) {
            throw new BizException(AccountExceptionCode.LOGIN_STATE_EXCEPTION);
        }
        UserInfo userInfo = userBusiness.queryUserInfo(swUserLogin.getEmployeeNo());
        if (Objects.isNull(userInfo)) {
            throw new BizException(AccountExceptionCode.USER_INFO_EXCEPTION);
        }
        return userInfo;
    }

    public String getEmployeeNo(String token) {
        return queryUserInfo(token).getEmployeeNo();
    }

    public String getEmployeeName(String token) {
        UserInfo userInfo = queryUserInfo(token);
        return String.format("%s(%s)", userInfo.getEmployeeName(), userInfo.getEmployeeNo());
    }

    public String getEmployeeName(UserInfo userInfo) {
        return String.format("%s(%s)", userInfo.getEmployeeName(), userInfo.getEmployeeNo());
    }

}
