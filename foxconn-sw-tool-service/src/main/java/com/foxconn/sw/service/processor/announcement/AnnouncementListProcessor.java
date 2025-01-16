package com.foxconn.sw.service.processor.announcement;

import com.foxconn.sw.business.AnnouncementBusiness;
import com.foxconn.sw.data.context.RequestContext;
import com.foxconn.sw.common.utils.IntegerExtUtils;
import com.foxconn.sw.common.utils.LocalDateExtUtils;
import com.foxconn.sw.data.dto.request.announcement.AnnouncementParams;
import com.foxconn.sw.data.entity.SwAnnouncement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class AnnouncementListProcessor {
    @Autowired
    AnnouncementBusiness announcementBusiness;

    public Boolean list(AnnouncementParams data) {
        SwAnnouncement announcement = new SwAnnouncement();
        announcement.setId(data.getId());
        announcement.setTitle(data.getTitle());

        if (!IntegerExtUtils.isPk(data.getId())) {
            announcement.setOperator(RequestContext.getEmployeeNo());
        }
        announcement.setLastUpdater(RequestContext.getEmployeeNo());
        announcement.setExpiryDate(LocalDateExtUtils.toString(LocalDate.now()));
        announcement.setStatus(data.getStatus().getCode());
        announcement.setContent(data.getContent());

        return announcementBusiness.insertOrUpdate(announcement) > 0;
    }
}
