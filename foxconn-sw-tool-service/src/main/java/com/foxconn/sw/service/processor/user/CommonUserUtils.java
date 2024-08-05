package com.foxconn.sw.service.processor.user;

import com.foxconn.sw.business.account.UserBusiness;
import com.foxconn.sw.business.account.UserLoginBusiness;
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
            throw new BizException("登录状态异常，请登录后再操作");
        }
        UserInfo userInfo = userBusiness.queryUserInfo(swUserLogin.getEmployeeNo());
        if (Objects.isNull(userInfo)) {
            throw new BizException("用户信息错误，请联系管理员处理");
        }
        return userInfo;
    }

    public String getEmployeeNo(String token) {
        return queryUserInfo(token).getEmployeeNo();
    }

}
