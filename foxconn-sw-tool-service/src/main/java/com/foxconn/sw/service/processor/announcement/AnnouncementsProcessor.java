package com.foxconn.sw.service.processor.announcement;

import com.foxconn.sw.business.AnnouncementBusiness;
import com.foxconn.sw.business.SwAppendResourceBusiness;
import com.foxconn.sw.common.constanst.NumberConstants;
import com.foxconn.sw.common.utils.DateTimeUtils;
import com.foxconn.sw.data.dto.request.announcement.AnnouncementsParams;
import com.foxconn.sw.data.dto.response.announcement.AnnouncementVo;
import com.foxconn.sw.data.entity.extension.SwAnnouncementExtension;
import com.google.common.collect.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Objects;

@Component
public class AnnouncementsProcessor {
    @Autowired
    AnnouncementBusiness announcementBusiness;
    @Autowired
    SwAppendResourceBusiness appendResourceBusiness;

    public List<AnnouncementVo> announcements(AnnouncementsParams announcementsParams) {
        List<SwAnnouncementExtension> swAnnouncements = announcementBusiness.queryAnnouncements(announcementsParams.getCategory());

        List<AnnouncementVo> vos = Lists.newArrayList();
        for (SwAnnouncementExtension item : swAnnouncements) {
            AnnouncementVo vo = new AnnouncementVo();
            vo.setId(item.getId());
            vo.setCategory(item.getCategory());
            vo.setTitle(item.getTitle());
            vo.setContent(item.getContent());
            vo.setOperator(item.getOperator());
            vo.setResources(appendResourceBusiness.getAppendResourcesVo(item.getResourceids()));
            vo.setReleaseTime(DateTimeUtils.format(item.getDatetimeLastchange()));
            vo.setExpiryDate(item.getExpiryDate());
            vo.setTop(NumberConstants.ONE.equals(item.getTop()));
            vo.setRead(Objects.nonNull(item.getRid()) && item.getRid() > 0);
            vos.add(vo);
        }
        return vos;
    }
}
