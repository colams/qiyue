package com.foxconn.sw.service.processor.oa;

import com.foxconn.sw.business.account.UserBusiness;
import com.foxconn.sw.data.dto.Header;
import com.foxconn.sw.data.dto.entity.oa.MyWorks;
import com.foxconn.sw.data.entity.SwUser;
import com.foxconn.sw.service.processor.user.CommonUserUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class SummaryWorkProcessor {
    @Autowired
    CommonUserUtils userUtils;
    @Autowired
    UserBusiness userBusiness;

    public MyWorks summary(Header head) {
        SwUser swUser = userUtils.queryUserInfo(head.getToken());


        MyWorks myWorks = new MyWorks();
        myWorks.setTotal(0);
        myWorks.setArgentCount(0);
        myWorks.setScheduleCount(0);
        myWorks.setApprovalCount(0);
        myWorks.setUnReadMailCount(0);
        return myWorks;
    }

}
