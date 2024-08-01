package com.foxconn.sw.service.processor.oa;

import com.foxconn.sw.data.dto.Header;
import com.foxconn.sw.data.dto.entity.common.IDParams;
import com.foxconn.sw.data.dto.entity.oa.TaskDetailVo;
import org.springframework.stereotype.Component;

@Component
public class TaskDetailProcessor {

    public TaskDetailVo detail(IDParams data, Header head) {
        TaskDetailVo detailVo = new TaskDetailVo();
        return detailVo;
    }
}
