package com.foxconn.sw.service.processor.acount;

import com.foxconn.sw.business.account.UserBusiness;
import com.foxconn.sw.business.account.UserLoginBusiness;
import com.foxconn.sw.common.utils.UUIDUtils;
import com.foxconn.sw.data.constants.enums.retcode.AccountExceptionCode;
import com.foxconn.sw.data.dto.entity.acount.LoginParams;
import com.foxconn.sw.data.dto.entity.acount.LoginStateVo;
import com.foxconn.sw.data.dto.entity.acount.UserInfo;
import com.foxconn.sw.data.entity.SwUser;
import com.foxconn.sw.data.entity.SwUserLogin;
import com.foxconn.sw.data.exception.BizException;
import com.foxconn.sw.service.utils.PasswordUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.Objects;

@Component
public class LoginProcessor {

    @Autowired
    UserBusiness userBusiness;
    @Autowired
    UserLoginBusiness userLoginBusiness;

    /**
     * 返回登录token
     *
     * @param data
     * @return
     */
    public LoginStateVo login(LoginParams data) {
        SwUser user = userBusiness.queryUser(data.getEmployeeNo());
        PasswordUtils.assertPassword(data, user);
        return getLoginStateVo(data.getEmployeeNo());
    }

    private LoginStateVo getLoginStateVo(String employeeNo) {
        LocalDateTime now = LocalDateTime.now();
        UserInfo userInfo = userBusiness.queryUserInfo(employeeNo);
        SwUserLogin userLogin = userLoginBusiness.queryLoginStateByName(employeeNo, now);

        String token = UUIDUtils.getUuid() + "#" + employeeNo;
        LocalDateTime expireTime = now.plusHours(24 * 7);

        LoginStateVo vo;
        if (Objects.nonNull(userLogin)) {
            token = userLogin.getToken();
            expireTime = userLogin.getExpireTime();
        } else {
            boolean loginState = userLoginBusiness.saveUserLogin(employeeNo, token, expireTime);
            if (!loginState) {
                throw new BizException(AccountExceptionCode.LOGIN_STATE_KEEP_EXCEPTION);
            }
        }
        vo = LoginStateVo.init(token, expireTime, userInfo);
        return vo;
    }


}
