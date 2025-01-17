package com.foxconn.sw.business.system;

import com.foxconn.sw.data.mapper.extension.system.SwNotificationExtMapper;
import com.foxconn.sw.data.mapper.extension.system.SwNotificationStatusExtMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class SwNotificationStatusBusiness {

    @Autowired
    SwNotificationStatusExtMapper notificationStatusExtMapper;



}
