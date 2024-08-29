package com.foxconn.sw.service.processor.acount;

import com.foxconn.sw.business.account.UserBusiness;
import com.foxconn.sw.data.dto.entity.acount.LoginParams;
import com.foxconn.sw.data.entity.SwUser;
import com.foxconn.sw.service.utils.PasswordUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ValidatePwdProcessor {

    @Autowired
    private UserBusiness userBusiness;

    public boolean validatePwd(LoginParams data) {
        SwUser user = userBusiness.queryUser(data.getEmployeeNo());
        PasswordUtils.assertPassword(data, user);
        return true;
    }
}
