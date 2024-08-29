package com.foxconn.sw.business;

import com.foxconn.sw.business.mapper.AnnouncementMapper;
import com.foxconn.sw.data.dto.entity.universal.AnnouncementParams;
import com.foxconn.sw.data.dto.entity.universal.IntegerParams;
import com.foxconn.sw.data.dto.entity.universal.SwAnnouncementDto;
import com.foxconn.sw.data.entity.SwAnnouncement;
import com.foxconn.sw.data.entity.SwAnnouncementExample;
import com.foxconn.sw.data.mapper.extension.SwAnnouncementExtensionMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class AnnouncementBusiness {

    @Autowired
    SwAnnouncementExtensionMapper announcementExtensionMapper;


    public List<SwAnnouncementDto> queryAnnouncements(AnnouncementParams announcementParams) {
        SwAnnouncementExample example = new SwAnnouncementExample();
        SwAnnouncementExample.Criteria criteria = example.createCriteria();
        criteria.andStatusEqualTo(announcementParams.getStatus());
        example.setOrderByClause(" order by create_time desc ");
        List<SwAnnouncement> swAnnouncements = announcementExtensionMapper.selectByExample(example);
        return AnnouncementMapper.INSTANCE.toAnnouncementDtos(swAnnouncements);
    }


    public SwAnnouncementDto detailAnnouncement(IntegerParams params) {
        SwAnnouncement swAnnouncement =
                announcementExtensionMapper.selectByPrimaryKey(params.getParams());
        return AnnouncementMapper.INSTANCE.toAnnouncement(swAnnouncement);
    }

}
