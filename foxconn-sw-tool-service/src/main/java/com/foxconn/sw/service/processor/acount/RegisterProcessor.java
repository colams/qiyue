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
import com.foxconn.sw.data.exception.BizException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.Objects;

@Component
public class RegisterProcessor {

    @Autowired
    UserBusiness userBusiness;
    @Autowired
    UserLoginBusiness userLoginBusiness;

    public LoginStateVo register(UserBriefParams data) {
        // 创建 用户账户
        SwUser sysUser = createUser(data);
        if (Objects.isNull(sysUser)) {
            throw new BizException(AccountExceptionCode.CREATE_ACCOUNT_EXCEPTION);
        }
        // 生成登录信息
        LoginStateVo result = createLoginState(data.getEmployeeNo());
        return result;
    }

    private LoginStateVo createLoginState(String employeeID) {
        String token = UUIDUtils.getUuid();
        LocalDateTime expireTime = LocalDateTime.now().plusHours(24);
        boolean loginState = userLoginBusiness.saveUserLogin(employeeID, token, expireTime);
        if (loginState) {
            throw new BizException(AccountExceptionCode.CREATE_ACCOUNT_LOGIN_EXCEPTION);
        }
        UserInfo userInfo = userBusiness.queryUserInfo(employeeID);
        return LoginStateVo.init(token, expireTime, userInfo);
    }

    /**
     * 创建账号信息
     *
     * @param data
     * @return
     */
    private SwUser createUser(UserBriefParams data) {
        SwUser user = initUser(data);
        return userBusiness.save(user);
    }

    private SwUser initUser(UserBriefParams data) {
        String timeStamp = DateTimeUtils.getTimeStamp();

        SwUser user = new SwUser();
        user.setNickName(data.getNickName());
        user.setEmployeeNo(data.getEmployeeNo());
        user.setPassword(SecurityUtils.encodeMD5(data.getPassword() + timeStamp));
        user.setSolt(timeStamp);
        return user;
    }
}
