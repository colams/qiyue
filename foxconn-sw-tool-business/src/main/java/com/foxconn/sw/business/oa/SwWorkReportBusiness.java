package com.foxconn.sw.business.oa;

import com.foxconn.sw.data.entity.SwWorkReport;
import com.foxconn.sw.data.entity.SwWorkReportExample;
import com.foxconn.sw.data.mapper.extension.oa.SwWorkReportExtensionMapper;
import com.google.common.collect.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.Optional;

@Component
public class SwWorkReportBusiness {

    @Autowired
    SwWorkReportExtensionMapper reportExtensionMapper;

    public List<SwWorkReport> queryReport(String yearWeek, String employeeNo) {
        SwWorkReportExample example = new SwWorkReportExample();
        SwWorkReportExample.Criteria criteria = example.createCriteria();
        criteria.andYearWeekEqualTo(yearWeek);
        criteria.andEmployeeNoEqualTo(employeeNo);
        criteria.andStatusEqualTo(1);
        List<SwWorkReport> reports = reportExtensionMapper.selectByExample(example);
        return Optional.ofNullable(reports).orElse(Lists.newArrayList());
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

    public List<SwWorkReport> queryReport(List<String> searchWeeks, List<String> employees) {
        SwWorkReportExample example = new SwWorkReportExample();
        SwWorkReportExample.Criteria criteria = example.createCriteria()
                .andStatusEqualTo(1)
                .andEmployeeNoIn(employees);

        if (!CollectionUtils.isEmpty(searchWeeks)) {
            criteria.andYearWeekIn(searchWeeks);
        }
        List<SwWorkReport> reports = reportExtensionMapper.selectByExample(example);
        return reports;
    }
}
