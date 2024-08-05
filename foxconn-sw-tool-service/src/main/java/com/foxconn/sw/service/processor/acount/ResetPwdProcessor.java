package com.foxconn.sw.service.processor.acount;

import com.foxconn.sw.business.account.UserBusiness;
import com.foxconn.sw.business.account.UserLoginBusiness;
import com.foxconn.sw.common.utils.DateTimeUtils;
import com.foxconn.sw.common.utils.SecurityUtils;
import com.foxconn.sw.common.utils.UUIDUtils;
import com.foxconn.sw.data.constants.enums.retcode.AccountExceptionCode;
import com.foxconn.sw.data.dto.entity.acount.LoginStateVo;
import com.foxconn.sw.data.dto.entity.acount.UserBriefParams;
import com.foxconn.sw.data.dto.entity.acount.UserInfo;
import com.foxconn.sw.data.entity.SwUser;
import com.foxconn.sw.data.entity.SwUserLogin;
import com.foxconn.sw.data.exception.BizException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.Objects;

@Component
public class ResetPwdProcessor {

    @Autowired
    UserBusiness userBusiness;
    @Autowired
    UserLoginBusiness userLoginBusiness;

    public LoginStateVo resetPwd(UserBriefParams data) {
        // 创建 用户账户
        SwUser sysUser = userBusiness.queryUser(data.getEmployeeNo());
        if (Objects.isNull(sysUser)) {
            throw new BizException(AccountExceptionCode.CREATE_ACCOUNT_EXCEPTION);
        }
        boolean isSuc = updateNewPwd(sysUser);
        if (isSuc) {
            return getLoginStateVo(data.getEmployeeNo());
        }
        throw new BizException(AccountExceptionCode.RESET_PASSWORD_EXCEPRTION);
    }

    private LoginStateVo getLoginStateVo(String employeeNo) {
        LocalDateTime now = LocalDateTime.now();
        UserInfo userInfo = userBusiness.queryUserInfo(employeeNo);
        SwUserLogin userLogin = userLoginBusiness.queryLoginStateByName(employeeNo, now);

        String token = UUIDUtils.getUuid();
        LocalDateTime expireTime = now.plusHours(24);


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

    private boolean updateNewPwd(SwUser user) {
        String timeStamp = DateTimeUtils.getTimeStamp();
        user.setPassword(SecurityUtils.encodeMD5("123456" + timeStamp));
        user.setSolt(timeStamp);
        return userBusiness.update(user);
    }

}
