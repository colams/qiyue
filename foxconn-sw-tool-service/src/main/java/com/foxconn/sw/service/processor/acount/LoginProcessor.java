package com.foxconn.sw.service.processor.acount;

import com.foxconn.sw.business.account.UserBusiness;
import com.foxconn.sw.business.account.UserLoginBusiness;
import com.foxconn.sw.business.converter.SwSysUserConverter;
import com.foxconn.sw.common.utils.UUIDUtils;
import com.foxconn.sw.common.utils.cipher.CipherUtils;
import com.foxconn.sw.data.constants.enums.retcode.AccountExceptionCode;
import com.foxconn.sw.data.dto.entity.acount.LoginParams;
import com.foxconn.sw.data.dto.entity.acount.LoginResponseType;
import com.foxconn.sw.data.dto.entity.acount.UserDTO;
import com.foxconn.sw.data.entity.SwUser;
import com.foxconn.sw.data.entity.SwUserLogin;
import com.foxconn.sw.data.exception.BizException;
import org.apache.commons.lang3.StringUtils;
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
    public LoginResponseType login(LoginParams data) {
        LocalDateTime now = LocalDateTime.now();
        SwUser user = userBusiness.queryUser(data.getUserName());
        boolean validity = assertPassword(data, user);
        if (!validity) {
            throw new BizException(AccountExceptionCode.LOGIN_ACCOUNT_EXCEPTION);
        }

        UserDTO userDTO = SwSysUserConverter.toUserDto(user);
        SwUserLogin userLogin = userLoginBusiness.queryLoginStateByName(data.getUserName(), now);

        String token = UUIDUtils.getUuid();
        LocalDateTime expireTime = now.plusHours(24);
        if (Objects.nonNull(userLogin)) {
            token = userLogin.getToken();
            expireTime = userLogin.getExpireTime();
            return LoginResponseType.init(token, expireTime, userDTO);
        } else {
            boolean loginState = userLoginBusiness.saveUserLogin(data.getUserName(), token, expireTime);
            if (loginState) {
                return LoginResponseType.init(token, expireTime, userDTO);
            }
            throw new BizException(AccountExceptionCode.LOGIN_STATE_KEEP_EXCEPTION);
        }
    }

    /**
     * 账户有效性和密码一致性验证
     *
     * @param data
     * @param user
     * @return
     */
    private boolean assertPassword(LoginParams data, SwUser user) {

        return Objects.nonNull(user)
                && (StringUtils.isBlank(user.getPassword())
                || user.getPassword().equalsIgnoreCase(data.getPassword())
                || user.getPassword().equalsIgnoreCase(CipherUtils.encodeMD5(data.getPassword() + user.getSolt())));
    }
}
