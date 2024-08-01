package com.foxconn.sw.service.processor.oa;

import com.foxconn.sw.data.dto.Header;
import com.foxconn.sw.data.dto.entity.oa.TaskDetailVo;
import org.springframework.stereotype.Component;

@Component
public class CreateTaskProcessor {


    public boolean createTask(TaskDetailVo data, Header head) {
        return true;
    }
}
