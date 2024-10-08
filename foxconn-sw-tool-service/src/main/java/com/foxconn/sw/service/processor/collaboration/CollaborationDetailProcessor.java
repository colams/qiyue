package com.foxconn.sw.service.processor.collaboration;

import com.foxconn.sw.data.dto.entity.collaboration.CollaborationVo;
import com.foxconn.sw.data.dto.entity.universal.IntegerParams;
import com.google.common.collect.Lists;
import org.springframework.stereotype.Component;

@Component
public class CollaborationDetailProcessor {

    public CollaborationVo detail(IntegerParams data) {
        CollaborationVo vo = new CollaborationVo();
        vo.setHeaders(Lists.newArrayList());
        vo.setEmployeeVos(Lists.newArrayList());
        return vo;
    }
}
