package com.foxconn.sw.service.processor.acount;

import com.foxconn.sw.business.account.UserBusiness;
import com.foxconn.sw.common.utils.DateTimeUtils;
import com.foxconn.sw.common.utils.SecurityUtils;
import com.foxconn.sw.data.dto.entity.acount.UpdatePwdParams;
import com.foxconn.sw.data.entity.SwUser;
import com.foxconn.sw.service.utils.PasswordUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UpdatePwdProcessor {
    @Autowired
    private UserBusiness userBusiness;

    public boolean updatePwd(UpdatePwdParams data) {
        SwUser user = userBusiness.queryUser(data.getEmployeeNo());
        PasswordUtils.assertPassword(data.getOldPassword(), user);
        updatePwd(user, data.getPassword());
        return false;
    }

    private boolean updatePwd(SwUser user, String password) {
        String timeStamp = DateTimeUtils.getTimeStamp();
        user.setPassword(SecurityUtils.encodeMD5(password + timeStamp));
        user.setSolt(timeStamp);
        return userBusiness.update(user);
    }
}
