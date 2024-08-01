package com.foxconn.sw.service.processor.oa;

import com.foxconn.sw.data.dto.Header;
import com.foxconn.sw.data.dto.entity.oa.TaskAssignParams;
import org.springframework.stereotype.Component;

@Component
public class AssignTaskProcessor {
    public boolean assignTask(TaskAssignParams data, Header head) {
        return false;
    }
}
