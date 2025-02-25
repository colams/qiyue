package com.foxconn.sw.service.processor.feedback;

import com.foxconn.sw.business.SwFeedbackBusiness;
import com.foxconn.sw.common.utils.ServletUtils;
import com.foxconn.sw.data.dto.request.feedback.CreateFeedBackParams;
import com.foxconn.sw.data.dto.request.feedback.FeedBackStatusParams;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class FeedBackSaveProcessor {

    @Autowired
    SwFeedbackBusiness feedbackBusiness;
    @Autowired
    private ServletUtils servletUtils;

    public Boolean save(CreateFeedBackParams data) {
        String ip = servletUtils.getRemoteIp();
        return feedbackBusiness.saveFeedBack(data.getEmployeeNo(), data.getContact(), data.getContent(), data.getTitle(), ip);
    }

    public Boolean updateFeedBackStatus(FeedBackStatusParams data) {
        return feedbackBusiness.updateFeedBackStatus(data.getId(), data.getStatus(), data.getRemark());
    }
}
