package com.foxconn.sw.business;

import com.foxconn.sw.business.mapper.AnnouncementMapper;
import com.foxconn.sw.data.dto.entity.universal.IntegerParams;
import com.foxconn.sw.data.dto.request.announcement.AnnouncementListParams;
import com.foxconn.sw.data.dto.response.announcement.AnnouncementListVo;
import com.foxconn.sw.data.entity.SwAnnouncement;
import com.foxconn.sw.data.entity.SwAnnouncementExample;
import com.foxconn.sw.data.mapper.extension.SwAnnouncementExtensionMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Objects;

@Component
public class AnnouncementBusiness {

    @Autowired
    SwAnnouncementExtensionMapper announcementExtensionMapper;

    public List<AnnouncementListVo> queryAnnouncements(AnnouncementListParams announcementParams) {
        SwAnnouncementExample example = new SwAnnouncementExample();
        SwAnnouncementExample.Criteria criteria = example.createCriteria();
        criteria.andStatusEqualTo(announcementParams.getStatus().getCode());
        criteria.andTitleLike(String.format("%%s%", announcementParams.getTitle()));
        example.setOrderByClause(" create_time desc ");
        List<SwAnnouncement> swAnnouncements = announcementExtensionMapper.selectByExample(example);
        return AnnouncementMapper.INSTANCE.toAnnouncementVos(swAnnouncements);
    }

    public Integer insertOrUpdate(SwAnnouncement announcement) {
        int effectCount;
        if (Objects.nonNull(announcement.getId()) && announcement.getId() > 0) {
            effectCount = announcementExtensionMapper.updateByPrimaryKeySelective(announcement);
        } else {
            announcementExtensionMapper.insertSelective(announcement);
            effectCount = announcement.getId();
        }
        return effectCount;
    }

    public SwAnnouncement getAnnouncementById(Integer params) {
        return announcementExtensionMapper.selectByPrimaryKey(params);
    }
}
