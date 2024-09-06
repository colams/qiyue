package com.foxconn.sw.business.oa;

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

    public List<SwWorkReport> queryReport(Integer weekOfYear, String employeeNo) {
        SwWorkReportExample example = new SwWorkReportExample();
        SwWorkReportExample.Criteria criteria = example.createCriteria();
        criteria.andWeekEqualTo(weekOfYear);
        criteria.andEmployeeNoEqualTo(employeeNo);
        criteria.andStatusEqualTo(1);
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

    public boolean updateBatchReports(List<SwWorkReport> reports) {
        reports.forEach(e -> {
            reportExtensionMapper.updateByPrimaryKeySelective(e);
        });
        return true;
    }

    public boolean inertBatchReports(List<SwWorkReport> reports) {
        reports.forEach(e -> {
            reportExtensionMapper.insertSelective(e);
        });
        return true;
    }

    public boolean deleteBatchReports(List<SwWorkReport> reports) {
        reports.forEach(e -> {
            e.setStatus(0);
            reportExtensionMapper.updateByPrimaryKeySelective(e);
        });
        return true;
    }

    public List<SwWorkReport> queryReport(ReportSearchParams searchParams, List<String> employees) {
        SwWorkReportExample example = new SwWorkReportExample();
        SwWorkReportExample.Criteria criteria = example.createCriteria();
        criteria.andStatusEqualTo(1);
        criteria.andEmployeeNoIn(employees);
        if (Objects.nonNull(searchParams.getWeekOfYear()) && searchParams.getWeekOfYear() > 0) {
            criteria.andWeekEqualTo(searchParams.getWeekOfYear());
        }
        List<SwWorkReport> reports = reportExtensionMapper.selectByExample(example);
        return reports;
    }
}
