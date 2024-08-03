package com.foxconn.sw.service.processor.oa;

import com.foxconn.sw.data.dto.Header;
import com.foxconn.sw.data.dto.entity.oa.TaskEvaluateParams;
import com.foxconn.sw.data.dto.entity.oa.TaskRejectParams;
import org.springframework.stereotype.Component;

@Component
public class RejectTaskProcessor {


    /**
     * todo 驳回任务
     * @param data
     * @param head
     * @return
     */
    public boolean reject(TaskRejectParams data, Header head) {

        return false;

    }
}
