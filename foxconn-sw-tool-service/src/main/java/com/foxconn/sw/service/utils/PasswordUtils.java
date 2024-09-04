package com.foxconn.sw.service.utils;

import com.foxconn.sw.common.utils.SecurityUtils;
import com.foxconn.sw.data.constants.enums.retcode.AccountExceptionCode;
import com.foxconn.sw.data.dto.entity.acount.LoginParams;
import com.foxconn.sw.data.entity.SwUser;
import com.foxconn.sw.data.exception.BizException;
import org.apache.commons.lang3.StringUtils;

import java.util.Objects;

public class PasswordUtils {

    /**
     * 账户有效性和密码一致性验证
     *
     * @param data
     * @param user
     * @return
     */
    public static boolean assertPassword(LoginParams data, SwUser user) {
        return assertPassword(data.getPassword(), user);
    }

    /**
     * 账户有效性和密码一致性验证
     *
     * @param password
     * @param user
     * @return
     */
    public static boolean assertPassword(String password, SwUser user) {

        if (Objects.isNull(user)) {
            throw new BizException(AccountExceptionCode.LOGIN_ACCOUNT_UN_REGIST_EXCEPTION);
        }

        boolean validity = Objects.nonNull(user)
                && (StringUtils.isBlank(user.getPassword())
                || user.getPassword().equalsIgnoreCase(password)
                || user.getPassword().equalsIgnoreCase(SecurityUtils.encodeMD5(password + user.getSolt())));

        if (!validity) {
            throw new BizException(AccountExceptionCode.LOGIN_ACCOUNT_EXCEPTION);
        }
        return validity;
    }

}
