package com.foxconn.sw.service.processor.collaboration;

import com.foxconn.sw.data.dto.request.collaboration.CollaborationDetailParams;
import com.foxconn.sw.data.dto.request.collaboration.CollaborationEvaluationParams;
import com.foxconn.sw.data.dto.request.collaboration.CollaborationUpdateParams;
import org.springframework.stereotype.Component;

@Component
public class CollaborationUpdateProcessor {

    public Boolean update(CollaborationUpdateParams data) {
        return false;
    }

    public Boolean evaluation(CollaborationEvaluationParams data) {
        return false;
    }

    public Boolean submit(CollaborationDetailParams data) {
        return true;
    }
}
