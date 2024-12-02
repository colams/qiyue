package com.foxconn.sw.service.processor.task;

import com.foxconn.sw.common.context.RequestContext;
import com.foxconn.sw.business.oa.SwTaskBusiness;
import com.foxconn.sw.business.system.EmployeeBusiness;
import com.foxconn.sw.common.utils.DateTimeUtils;
import com.foxconn.sw.data.dto.entity.task.TaskOverviewVo;
import com.foxconn.sw.data.dto.request.task.OverviewParams;
import com.foxconn.sw.service.processor.user.CommonUserUtils;
import com.google.common.collect.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;

@Component
public class OverviewProcessor {

    @Autowired
    private SwTaskBusiness taskBusiness;
    @Autowired
    CommonUserUtils commonUserUtils;
    @Autowired
    EmployeeBusiness employeeBusiness;

    /**
     * 列表页-任务总览
     *
     * @param params
     * @return
     */
    public List<TaskOverviewVo> overview(OverviewParams params) {
        List<String> employees = Lists.newArrayList(RequestContext.getEmployeeNo());
        Integer viewType = params.getViewType();
        LocalDateTime localDateTime = LocalDateTime.now();
        String now = DateTimeUtils.formatYMD(localDateTime);
        List<TaskOverviewVo> taskOverviews = Lists.newArrayList();

        String lable = "全部任務";
        if (viewType == 1) {
            lable = "我的任務";
        } else if (viewType == 1) {
            lable = "我的關注";
        }

        taskOverviews.add(init(0, "total", lable, formatData(0, employees, now, viewType)));
        taskOverviews.add(init(1, "processing", "待確認", formatData(1, employees, now, viewType)));
        taskOverviews.add(init(2, "processing", "處理中", formatData(2, employees, now, viewType)));
        taskOverviews.add(init(3, "complete", "待驗收", formatData(3, employees, now, viewType)));
        taskOverviews.add(init(4, "over_date", "逾期", formatData(4, employees, now, viewType)));
        return taskOverviews;
    }

    private String formatData(int searchType, List<String> employees, String now, Integer viewType) {
        String proposer = RequestContext.getEmployeeNo();
        int count = taskBusiness.getTotalCountByParams(searchType, employees, now, proposer, viewType);
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
