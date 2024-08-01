package com.foxconn.sw.service.processor.oa;

import com.foxconn.sw.data.dto.Header;
import com.foxconn.sw.data.dto.entity.oa.TaskStatusChangeParams;
import org.springframework.stereotype.Component;

@Component
public class StatusUpdateProcessor {


    public boolean statusUpdate(TaskStatusChangeParams data, Header head) {
        return false;
    }
}
