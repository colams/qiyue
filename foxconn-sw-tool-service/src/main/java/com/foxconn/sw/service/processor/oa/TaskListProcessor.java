package com.foxconn.sw.service.processor.oa;

import com.foxconn.sw.data.dto.Header;
import com.foxconn.sw.data.dto.PageEntity;
import com.foxconn.sw.data.dto.PageParams;
import com.foxconn.sw.data.dto.entity.oa.TaskBriefVo;
import com.foxconn.sw.data.dto.entity.oa.TaskParams;
import com.google.common.collect.Lists;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class TaskListProcessor {


    public PageEntity<TaskBriefVo> list(PageParams<TaskParams> taskParams, Header head) {
        List<TaskBriefVo> brefVos = Lists.newArrayList();
        int totalCount = 0;//  toolsBusiness.getTotalCountByParams(pageParams);
        PageEntity<TaskBriefVo> voPageEntity = new PageEntity<>(totalCount, brefVos);
        return voPageEntity;
    }
}
