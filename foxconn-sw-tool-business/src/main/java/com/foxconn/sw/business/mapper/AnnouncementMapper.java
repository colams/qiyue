package com.foxconn.sw.business.mapper;

import com.foxconn.sw.common.utils.DateTimeUtils;
import com.foxconn.sw.data.context.RequestContext;
import com.foxconn.sw.data.dto.response.announcement.AnnouncementListVo;
import com.foxconn.sw.data.entity.SwAnnouncement;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface AnnouncementMapper {

    AnnouncementMapper INSTANCE = Mappers.getMapper(AnnouncementMapper.class);

    List<AnnouncementListVo> toAnnouncementVos(List<SwAnnouncement> swAnnouncements);

    default AnnouncementListVo toAnnouncement(SwAnnouncement swAnnouncements) {
        if (swAnnouncements == null) {
            return null;
        }

        AnnouncementListVo announcementListVo = new AnnouncementListVo();

        announcementListVo.setId(swAnnouncements.getId());
        announcementListVo.setTitle(swAnnouncements.getTitle());
        announcementListVo.setContent(swAnnouncements.getContent());
        announcementListVo.setOperator(swAnnouncements.getOperator());
        announcementListVo.setCanDelete(RequestContext.getEmployeeNo().equalsIgnoreCase(swAnnouncements.getOperator()));
        announcementListVo.setCanUpdate(RequestContext.getEmployeeNo().equalsIgnoreCase(swAnnouncements.getOperator()));
        announcementListVo.setExpiryDate(swAnnouncements.getExpiryDate());
        announcementListVo.setCreateTime(DateTimeUtils.format(swAnnouncements.getCreateTime()));
        announcementListVo.setUpdateTime(DateTimeUtils.format(swAnnouncements.getDatetimeLastchange()));
        announcementListVo.setTop(swAnnouncements.getTop());
        return announcementListVo;
    }
}
