package com.foxconn.sw.service.processor.oa;

import com.foxconn.sw.data.dto.Header;
import com.foxconn.sw.data.dto.entity.oa.TaskOverviewVo;
import com.google.common.collect.Lists;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class OverviewProcessor {


    /**
     * todo 列表页-任务总览
     *
     * @param head
     * @return
     */
    public List<TaskOverviewVo> overview(Header head) {

        List<TaskOverviewVo> taskOverviews = Lists.newArrayList();
        return taskOverviews;
    }
}
