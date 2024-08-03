package com.foxconn.sw.service.processor.user;

import com.foxconn.sw.business.account.UserBusiness;
import com.foxconn.sw.business.account.UserLoginBusiness;
import com.foxconn.sw.data.entity.SwUser;
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
    public SwUser queryUserInfo(String token) {
        SwUserLogin swUserLogin = userLoginBusiness.queryLoginUser(token);
        if (Objects.isNull(swUserLogin)) {
            throw new BizException("登录状态异常，请登录后再操作");
        }
        SwUser swUser = userBusiness.queryUser(swUserLogin.getUserName());
        if (Objects.isNull(swUser)) {
            throw new BizException("用户信息错误，请联系管理员处理");
        }
        return swUser;
    }

    public String getEmployeeId(String token) {
        SwUser user = queryUserInfo(token);
        return user.getUserName();
    }

}
