package com.foxconn.sw.business.oa;

import com.foxconn.sw.business.context.RequestContext;
import com.foxconn.sw.data.dto.entity.oa.ReportSearchParams;
import com.foxconn.sw.data.entity.SwWorkReport;
import com.foxconn.sw.data.entity.SwWorkReportExample;
import com.foxconn.sw.data.mapper.extension.oa.SwWorkReportExtensionMapper;
import com.google.common.collect.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Component
public class SwWorkReportBusiness {

    @Autowired
    SwWorkReportExtensionMapper reportExtensionMapper;

    public List<SwWorkReport> queryReport(List<Integer> weekOfYears) {
        SwWorkReportExample example = new SwWorkReportExample();
        SwWorkReportExample.Criteria criteria = example.createCriteria();
        criteria.andWeekIn(weekOfYears);
        List<SwWorkReport> reports = reportExtensionMapper.selectByExample(example);
        return Optional.ofNullable(reports).orElse(Lists.newArrayList());
    }

    public int invalidReport(ArrayList<Integer> weekOfYears) {
        SwWorkReportExample example = new SwWorkReportExample();
        SwWorkReportExample.Criteria criteria = example.createCriteria();
        criteria.andWeekIn(weekOfYears);
        criteria.andStatusEqualTo(1);

        SwWorkReport report = new SwWorkReport();
        report.setStatus(0);
        return reportExtensionMapper.updateByExampleSelective(report, example);
    }

    public boolean inertBatchReports(List<SwWorkReport> reports) {
        reports.forEach(e -> {
            reportExtensionMapper.insertSelective(e);
        });
        return true;
    }

    public List<SwWorkReport> queryReport(ReportSearchParams searchParams) {
        SwWorkReportExample example = new SwWorkReportExample();
        SwWorkReportExample.Criteria criteria = example.createCriteria();
        criteria.andEmployeeNoEqualTo(RequestContext.getEmployeeNo());
        if (Objects.nonNull(searchParams.getWeekOfYear())) {
            criteria.andWeekEqualTo(searchParams.getWeekOfYear());
        }
        List<SwWorkReport> reports = reportExtensionMapper.selectByExample(example);
        return reports;
    }
}
