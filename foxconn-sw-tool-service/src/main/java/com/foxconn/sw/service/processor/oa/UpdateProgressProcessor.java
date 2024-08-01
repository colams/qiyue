package com.foxconn.sw.service.processor.oa;

import com.foxconn.sw.data.dto.Header;
import com.foxconn.sw.data.dto.entity.oa.TaskProgressBriefParams;
import org.springframework.stereotype.Component;

@Component
public class UpdateProgressProcessor {


    public boolean updateProgress(TaskProgressBriefParams data, Header head) {
        return false;
    }
}
