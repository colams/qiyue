package com.foxconn.sw.service.processor.oa;

import com.foxconn.sw.business.oa.SwTaskBusiness;
import com.foxconn.sw.common.utils.DateTimeUtils;
import com.foxconn.sw.data.dto.Header;
import com.foxconn.sw.data.dto.entity.oa.TaskOverviewVo;
import com.foxconn.sw.service.processor.user.CommonUserUtils;
import com.google.common.collect.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class OverviewProcessor {

    @Autowired
    private SwTaskBusiness taskBusiness;
    @Autowired
    CommonUserUtils commonUserUtils;

    /**
     * 列表页-任务总览
     *
     * @param head
     * @return
     */
    public List<TaskOverviewVo> overview(Header head) {

        String employeeNo = commonUserUtils.getEmployeeNo(head.getToken());
        String now = DateTimeUtils.formatNow();

        List<TaskOverviewVo> taskOverviews = Lists.newArrayList();
        taskOverviews.add(init(0, "total", "全部任務", formatData(0, employeeNo, now)));
        taskOverviews.add(init(1, "processing", "待確認", formatData(1, employeeNo, now)));
        taskOverviews.add(init(2, "processing", "處理中", formatData(2, employeeNo, now)));
        taskOverviews.add(init(3, "complete", "待驗收", formatData(3, employeeNo, now)));
        taskOverviews.add(init(4, "over_date", "逾期", formatData(4, employeeNo, now)));
        return taskOverviews;
    }

    private String formatData(int searchType, String employeeNo, String now) {
        int count = taskBusiness.getTotalCountByParams(searchType, employeeNo, now);
        return String.format("%s個", count);
    }

    private TaskOverviewVo init(int code, String category, String title, String countDes) {
        TaskOverviewVo overviewVo = new TaskOverviewVo();
        overviewVo.setCode(code);
        overviewVo.setCategory(category);
        overviewVo.setTitle(title);
        overviewVo.setCountDes(countDes);
        return overviewVo;
    }
}
