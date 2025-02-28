package com.foxconn.sw.business;

import com.foxconn.sw.business.mapper.AnnouncementMapper;
import com.foxconn.sw.data.context.RequestContext;
import com.foxconn.sw.data.dto.request.announcement.AnnouncementListParams;
import com.foxconn.sw.data.dto.response.announcement.AnnouncementListVo;
import com.foxconn.sw.data.entity.SwAnnouncement;
import com.foxconn.sw.data.entity.SwAnnouncementExample;
import com.foxconn.sw.data.entity.extension.SwAnnouncementExtension;
import com.foxconn.sw.data.mapper.extension.SwAnnouncementExtensionMapper;
import com.google.common.collect.Lists;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Component
public class AnnouncementBusiness {

    @Autowired
    SwAnnouncementExtensionMapper announcementExtensionMapper;

    public List<AnnouncementListVo> queryAnnouncements(AnnouncementListParams announcementParams) {
        SwAnnouncementExample example = new SwAnnouncementExample();
        SwAnnouncementExample.Criteria criteria = example.createCriteria();
        criteria.andStatusEqualTo(announcementParams.getStatus().getCode());
        if (StringUtils.isNotEmpty(announcementParams.getTitle())) {
            criteria.andTitleLike("%" + announcementParams.getTitle() + "%");
        }
        example.setOrderByClause(" create_time desc ");
        List<SwAnnouncement> swAnnouncements = announcementExtensionMapper.selectByExample(example);
        return AnnouncementMapper.INSTANCE.toAnnouncementVos(swAnnouncements);
    }

    public List<SwAnnouncementExtension> queryAnnouncements(String category) {
        List<SwAnnouncementExtension> list = announcementExtensionMapper.selectAnnounces(RequestContext.getEmployeeNo(), category);
        return Optional.ofNullable(list).orElse(Lists.newArrayList());
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
