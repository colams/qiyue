package com.foxconn.sw.service.processor.feedback;

import com.foxconn.sw.business.SwFeedbackBusiness;
import com.foxconn.sw.business.context.RequestContext;
import com.foxconn.sw.data.entity.SwFeedback;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class FeedBackListProcessor {

    @Autowired
    SwFeedbackBusiness feedbackBusiness;

    public List<SwFeedback> list() {
        String employeeNo = RequestContext.getEmployeeNo();
        return feedbackBusiness.queryFeedBack("", 0);
    }

}
