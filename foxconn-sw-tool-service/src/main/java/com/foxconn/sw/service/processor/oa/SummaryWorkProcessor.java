package com.foxconn.sw.service.processor.oa;

import com.foxconn.sw.business.account.UserBusiness;
import com.foxconn.sw.data.dto.Header;
import com.foxconn.sw.data.dto.entity.acount.UserInfo;
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
        MyWorks myWorks = new MyWorks();
        myWorks.setUrgencyCount(10);
        myWorks.setScheduleCount(10);
        myWorks.setMeetingCount(10);
        myWorks.setLogCount(10);
        return myWorks;
    }

}
