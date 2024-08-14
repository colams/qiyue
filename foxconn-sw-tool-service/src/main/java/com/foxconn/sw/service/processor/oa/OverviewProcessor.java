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
        taskOverviews.add(init("mil", "MIL", "15个"));
        taskOverviews.add(init("processing", "进行中", "36个"));
        taskOverviews.add(init("new", "新需求", "15个"));
        taskOverviews.add(init("complete", "待完成", "9个"));
        taskOverviews.add(init("over_date", "逾期", "15个"));
        return taskOverviews;
    }

    public TaskOverviewVo init(String category, String title, String countDes) {
        TaskOverviewVo overviewVo = new TaskOverviewVo();
        overviewVo.setCategory(category);
        overviewVo.setTitle(title);
        overviewVo.setCountDes(countDes);
        return overviewVo;
    }
}
