package com.foxconn.sw.service.processor.announcement;

import com.foxconn.sw.business.AnnouncementBusiness;
import com.foxconn.sw.business.SwAppendResourceBusiness;
import com.foxconn.sw.business.system.EmployeeBusiness;
import com.foxconn.sw.common.utils.DateTimeUtils;
import com.foxconn.sw.data.dto.entity.universal.IntegerParams;
import com.foxconn.sw.data.dto.response.announcement.AnnouncementDetailVo;
import com.foxconn.sw.data.entity.SwAnnouncement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class AnnouncementDetailProcessor {
    @Autowired
    AnnouncementBusiness announcementBusiness;
    @Autowired
    EmployeeBusiness employeeBusiness;
    @Autowired
    SwAppendResourceBusiness appendResourceBusiness;

    public AnnouncementDetailVo detail(IntegerParams data) {
        SwAnnouncement announcement = announcementBusiness.getAnnouncementById(data.getParams());
        return toAnnouncementDetail(announcement);
    }

    private AnnouncementDetailVo toAnnouncementDetail(SwAnnouncement announcement) {
        AnnouncementDetailVo detailVo = new AnnouncementDetailVo();
        detailVo.setId(announcement.getId());
        detailVo.setTitle(announcement.getTitle());
        detailVo.setContent(announcement.getContent());
        detailVo.setOperator(employeeBusiness.queryEmployeeByEno(announcement.getLastUpdater()).getName());
        detailVo.setReleaseDate(DateTimeUtils.format(announcement.getDatetimeLastchange()));
        detailVo.setResourceVos(appendResourceBusiness.getAppendResourcesVo(announcement.getResourceids()));
        detailVo.setCategory(announcement.getCategory());
        detailVo.setTop(announcement.getTop());
        detailVo.setExpiryDate(announcement.getExpiryDate());
        return detailVo;
    }
}
