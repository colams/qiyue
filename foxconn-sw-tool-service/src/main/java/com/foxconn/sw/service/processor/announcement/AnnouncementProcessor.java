package com.foxconn.sw.service.processor.announcement;

import com.foxconn.sw.business.AnnouncementBusiness;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class AnnouncementProcessor {
    @Autowired
    AnnouncementBusiness announcementBusiness;



}
