package com.foxconn.sw.business.system;

import com.foxconn.sw.data.mapper.extension.system.SwNotificationExtMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class SwNotificationBusiness {

    @Autowired
    SwNotificationExtMapper notificationExtMapper;



}
