package com.foxconn.sw.service.processor.oa;

import com.foxconn.sw.data.dto.Header;
import com.foxconn.sw.data.dto.entity.oa.TaskEvaluateParams;
import org.springframework.stereotype.Component;

@Component
public class EvaluateProcessor {


    public boolean evaluate(TaskEvaluateParams data, Header head) {

        return false;

    }
}
