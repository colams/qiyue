package com.foxconn.sw.business;

import com.foxconn.sw.common.constanst.NumberConstants;
import com.foxconn.sw.common.context.RequestContext;
import com.foxconn.sw.data.dto.PageParams;
import com.foxconn.sw.data.dto.request.feedback.FeedBackConditionParams;
import com.foxconn.sw.data.entity.SwFeedback;
import com.foxconn.sw.data.entity.SwFeedbackExample;
import com.foxconn.sw.data.mapper.extension.SwFeedbackExtMapper;
import com.google.common.collect.Lists;
import org.apache.commons.lang3.StringUtils;
import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Component
public class SwFeedbackBusiness {

    @Autowired
    SwFeedbackExtMapper feedbackExtMapper;

    public List<SwFeedback> queryFeedBack(boolean hasPower, PageParams<FeedBackConditionParams> params) {
        SwFeedbackExample example = new SwFeedbackExample();
        SwFeedbackExample.Criteria criteria = example.createCriteria();
        if (Objects.nonNull(params.getParams().getStatus())) {
            criteria.andStatusEqualTo(params.getParams().getStatus());
        }

        if (StringUtils.isNotEmpty(params.getParams().getTitle())) {
            criteria.andTitleLike("%" + params.getParams().getTitle() + "%");
        }

        if (!hasPower) {
            criteria.andEmployeeNoEqualTo(RequestContext.getEmployeeNo());
        }

        SwFeedbackExample.Criteria or = example.or();
        if (Objects.nonNull(params.getParams().getStatus())) {
            or.andStatusEqualTo(params.getParams().getStatus());
        }

        if (hasPower && StringUtils.isNotEmpty(params.getParams().getEmployeeNo())) {
            or.andEmployeeNoEqualTo(params.getParams().getEmployeeNo());
        }

        example.setOrderByClause(" status,id ");
        RowBounds rowBounds = new RowBounds(params.getPageSize() * (params.getCurrentPage() - 1), params.getPageSize());
        return feedbackExtMapper.selectByExampleWithBLOBsWithRowbounds(example, rowBounds);
    }

    public Long queryFeedBackCount(boolean hasPower, PageParams<FeedBackConditionParams> params) {
        SwFeedbackExample example = new SwFeedbackExample();
        SwFeedbackExample.Criteria criteria = example.createCriteria();
        if (Objects.nonNull(params.getParams().getStatus())) {
            criteria.andStatusEqualTo(params.getParams().getStatus());
        }

        if (StringUtils.isNotEmpty(params.getParams().getTitle())) {
            criteria.andTitleLike("%" + params.getParams().getTitle() + "%");
        }

        if (!hasPower) {
            criteria.andEmployeeNoEqualTo(RequestContext.getEmployeeNo());
        }

        SwFeedbackExample.Criteria or = example.or();
        if (Objects.nonNull(params.getParams().getStatus())) {
            or.andStatusEqualTo(params.getParams().getStatus());
        }

        if (hasPower && StringUtils.isNotEmpty(params.getParams().getEmployeeNo())) {
            or.andEmployeeNoEqualTo(params.getParams().getEmployeeNo());
        }

        return Optional.ofNullable(feedbackExtMapper.selectByExampleWithBLOBs(example))
                .orElse(Lists.newArrayList())
                .stream()
                .count();
    }

    public boolean saveFeedBack(String employeeNo, String contact, String content, String title, String ip) {
        SwFeedback feedback = new SwFeedback();
        feedback.setEmployeeNo(employeeNo);
        feedback.setContact(contact);
        feedback.setContent(content);
        feedback.setTitle(title);
        feedback.setIp(ip);
        return feedbackExtMapper.insertSelective(feedback) > 0;
    }

    public boolean updateFeedBackStatus(Integer feedBackID, Integer status) {
        SwFeedback feedback = new SwFeedback();
        feedback.setId(feedBackID);
        feedback.setStatus(status);
        if (NumberConstants.TWO.equals(status)) {
            feedback.setFinishTime(LocalDateTime.now());
        } else if (NumberConstants.THREE.equals(status)) {
            feedback.setCloseTime(LocalDateTime.now());
        }
        return feedbackExtMapper.updateByPrimaryKeySelective(feedback) > 0;
    }

}
