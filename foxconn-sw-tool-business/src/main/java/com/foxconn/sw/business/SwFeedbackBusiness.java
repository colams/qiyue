package com.foxconn.sw.business;

import com.foxconn.sw.data.entity.SwFeedback;
import com.foxconn.sw.data.entity.SwFeedbackExample;
import com.foxconn.sw.data.mapper.extension.SwFeedbackExtMapper;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Objects;

@Component
public class SwFeedbackBusiness {

    @Autowired
    SwFeedbackExtMapper feedbackExtMapper;

    public List<SwFeedback> queryFeedBack(String employeeNo, String title, Integer status) {
        SwFeedbackExample example = new SwFeedbackExample();
        SwFeedbackExample.Criteria criteria = example.createCriteria();
        if (StringUtils.isNotEmpty(employeeNo)) {
            criteria.andEmployeeNoEqualTo(employeeNo);
        }

        if (StringUtils.isNotEmpty(title)) {
            criteria.andTitleLike("%" + title + "%");
        }

        if (Objects.nonNull(status)) {
            criteria.andStatusEqualTo(status);
        }

        example.setOrderByClause(" status,id ");
        return feedbackExtMapper.selectByExampleWithBLOBs(example);
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
        return feedbackExtMapper.updateByPrimaryKeySelective(feedback) > 0;
    }

}
