package com.foxconn.sw.service.processor.acount;

import com.foxconn.sw.business.account.UserBusiness;
import com.foxconn.sw.data.dto.entity.acount.UserInfo;
import com.foxconn.sw.data.dto.entity.acount.UserParams;
import com.foxconn.sw.data.dto.entity.universal.StringParams;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class QueryUsersProcessor {

    @Autowired
    UserBusiness userBusiness;


    public List<UserInfo> queryUsers(UserParams data) {
        List<UserInfo> userBriefs = userBusiness.list(data);
        return userBriefs;
    }


    public UserInfo queryUsers(StringParams data) {
        UserInfo userBriefs = userBusiness.queryUserInfo(data.getParams());
        return userBriefs;
    }
}
