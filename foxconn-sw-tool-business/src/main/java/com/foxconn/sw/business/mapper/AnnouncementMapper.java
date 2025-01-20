package com.foxconn.sw.business.mapper;

import com.foxconn.sw.data.dto.response.announcement.AnnouncementListVo;
import com.foxconn.sw.data.entity.SwAnnouncement;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface AnnouncementMapper {

    AnnouncementMapper INSTANCE = Mappers.getMapper(AnnouncementMapper.class);

    List<AnnouncementListVo> toAnnouncementVos(List<SwAnnouncement> swAnnouncements);

    AnnouncementListVo toAnnouncement(SwAnnouncement swAnnouncements);

}
