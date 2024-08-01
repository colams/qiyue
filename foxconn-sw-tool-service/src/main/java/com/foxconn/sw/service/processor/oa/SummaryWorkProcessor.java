package com.foxconn.sw.service.processor.oa;

import com.foxconn.sw.business.auth.UserBusiness;
import com.foxconn.sw.business.auth.UserLoginBusiness;
import com.foxconn.sw.data.dto.Header;
import com.foxconn.sw.data.dto.entity.oa.MyWorks;
import com.foxconn.sw.data.entity.SwUser;
import com.foxconn.sw.data.entity.SwUserLogin;
import com.foxconn.sw.data.exception.BizException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
public class SummaryWorkProcessor {

    @Autowired
    UserLoginBusiness userLoginBusiness;
    @Autowired
    UserBusiness userBusiness;

    public MyWorks summary(Header head) {
        SwUser swUser = queryUserInfo(head.getToken());


        MyWorks myWorks = new MyWorks();
        myWorks.setTotal(0);
        myWorks.setArgentCount(0);
        myWorks.setScheduleCount(0);
        myWorks.setApprovalCount(0);
        myWorks.setUnReadMailCount(0);
        return myWorks;
    }

    /**
     * 获取登录用户信息
     *
     * @param token
     * @return
     */
    private SwUser queryUserInfo(String token) {
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
}
