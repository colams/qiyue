package com.foxconn.sw.business.mapper;

import com.foxconn.sw.data.dto.entity.common.SwAnnouncementDto;
import com.foxconn.sw.data.entity.SwAnnouncement;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface AnnouncementMapper {

    AnnouncementMapper INSTANCE = Mappers.getMapper(AnnouncementMapper.class);

    List<SwAnnouncementDto> toAnnouncementDtos(List<SwAnnouncement> swAnnouncements);

    SwAnnouncementDto toAnnouncement(SwAnnouncement swAnnouncements);

}
