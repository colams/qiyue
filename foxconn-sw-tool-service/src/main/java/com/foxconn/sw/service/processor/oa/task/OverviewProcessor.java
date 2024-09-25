package com.foxconn.sw.service.processor.oa.task;

import com.foxconn.sw.business.context.RequestContext;
import com.foxconn.sw.business.oa.SwTaskBusiness;
import com.foxconn.sw.business.system.EmployeeBusiness;
import com.foxconn.sw.common.utils.DateTimeUtils;
import com.foxconn.sw.data.dto.entity.oa.TaskOverviewVo;
import com.foxconn.sw.data.dto.entity.universal.IntegerParams;
import com.foxconn.sw.service.processor.user.CommonUserUtils;
import com.google.common.collect.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

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
    public List<TaskOverviewVo> overview(IntegerParams params) {
        List<String> employees = Lists.newArrayList(RequestContext.getEmployeeNo());
        if (Objects.nonNull(params) && Objects.nonNull(params.getParams()) && params.getParams() == 1) {
            employees = employeeBusiness.queryMemberNo(RequestContext.getEmployeeNo(), true);
        }

        LocalDateTime localDateTime = LocalDateTime.now().plusDays(1);
        String now = DateTimeUtils.formatYMD(localDateTime);
        List<TaskOverviewVo> taskOverviews = Lists.newArrayList();

        taskOverviews.add(init(0, "total", "全部任務", formatData(0, employees, now)));
        taskOverviews.add(init(1, "processing", "待確認", formatData(1, employees, now)));
        taskOverviews.add(init(2, "processing", "處理中", formatData(2, employees, now)));
        taskOverviews.add(init(3, "complete", "待驗收", formatData(3, employees, now)));
        taskOverviews.add(init(4, "over_date", "逾期", formatData(4, employees, now)));
        return taskOverviews;
    }

    private String formatData(int searchType, List<String> employees, String now) {
        int count = taskBusiness.getTotalCountByParams(searchType, employees, now);
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
